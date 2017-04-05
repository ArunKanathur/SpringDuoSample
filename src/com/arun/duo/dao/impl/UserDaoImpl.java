package com.arun.duo.dao.impl;

import java.sql.SQLException;

import com.arun.duo.dao.UserDao;
import com.arun.duo.util.Constants;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean isValidUser(String username, String password)
			throws SQLException {

		return Constants.USERNAMES.contains(username.toLowerCase())
				&& "pass123".equals(password);
	}

}