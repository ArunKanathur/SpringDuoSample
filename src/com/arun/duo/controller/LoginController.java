package com.arun.duo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arun.duo.delegate.LoginDelegate;
import com.arun.duo.util.Constants;
import com.arun.duo.viewBean.LoginBean;
import com.duosecurity.duoweb.DuoWeb;

@Controller
public class LoginController {

	@Autowired
	private LoginDelegate loginDelegate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root(HttpServletRequest request,
			HttpServletResponse response, LoginBean loginBean) {
		return displayLogin(request, response, loginBean);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request,
			HttpServletResponse response, LoginBean loginBean) {
		ModelAndView model = new ModelAndView("login");
		// LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("loginBean") LoginBean loginBean) {
		ModelAndView model = null;
		try {
			boolean isValidUser = loginDelegate.isValidUser(
					loginBean.getUsername(), loginBean.getPassword());
			if (isValidUser) {

				System.out.println("User authenticated");

				String genaratedSignedRequest = DuoWeb.signRequest(
						Constants.IKEY, Constants.SKEY, Constants.AKEY,
						loginBean.getUsername());

				request.setAttribute("genaratedSignedRequest",
						genaratedSignedRequest);
				request.setAttribute("loggedInUser", loginBean.getUsername());
				model = new ModelAndView("duo");
				/*
				 * System.out.println("User Login Successful");
				 * request.setAttribute("loggedInUser",
				 * loginBean.getUsername()); model = new
				 * ModelAndView("welcome");
				 */
			} else {
				model = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/duo-verify", method = RequestMethod.POST)
	public ModelAndView verifyDuoResponse(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("sig_response") String loginBean) {
		ModelAndView model = null;
		try {
			String verifiedResponse = DuoWeb.verifyResponse(Constants.IKEY,
					Constants.SKEY, Constants.AKEY, loginBean);

			if (!StringUtils.isEmpty(verifiedResponse)
					&& Constants.USERNAMES.contains(verifiedResponse
							.toLowerCase())) {
				System.out.println("User Login Successful");
				//request.setAttribute("loggedInUser", "john");
				model = new ModelAndView("welcome");
			} else {
				model = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}
