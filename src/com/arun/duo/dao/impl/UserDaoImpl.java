package com.arun.duo.dao.impl;


import java.sql.SQLException;

import com.arun.duo.dao.UserDao;

public class UserDaoImpl implements UserDao
{

		@Override
		public boolean isValidUser(String username, String password) throws SQLException
 {

		return ("john".equalsIgnoreCase(username) && "pass123".equals(password))
				|| ("pktr".equalsIgnoreCase(username) && "pass123"
						.equals(password));
	}

}