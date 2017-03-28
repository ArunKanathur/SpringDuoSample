package com.arun.duo.dao;

import java.sql.SQLException;

public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;
}
