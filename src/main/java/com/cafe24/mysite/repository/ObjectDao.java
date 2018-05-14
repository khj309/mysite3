package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected SqlSession sqlSession;
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
