package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import com.database.DbConnection;
import com.model.KycModel;
import com.model.RegisterModel;

public class DAO {

	public int registerUser(RegisterModel rm) throws ClassNotFoundException, SQLException {

		Connection con = DbConnection.getCon();
		String sql = "insert into register values(0,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, rm.getName());
		ps.setString(2, rm.getEmailid());
		ps.setString(3, rm.getPassword());
		ps.setString(4, rm.getDepartment());
		int statusReg = ps.executeUpdate();
		return statusReg;
	}

	public ResultSet getEmails() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getCon();
		String sql = "select * from register";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}

	public int insertKyc(KycModel km) throws ClassNotFoundException, SQLException {

		int statusReg = 0;
		Connection con = DbConnection.getCon();
		String sql = "insert into userfiles values(0,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBinaryStream(1, km.getInputStream());
		ps.setString(2, new Date().toString());
		ps.setInt(3, km.getUserid());
		ps.setString(4, km.getHashValue());
		ps.setString(5, km.getName());
		ps.setString(6, km.getFatherName());
		ps.setString(7, km.getMotherName());
		ps.setString(8, km.getMobile());
		ps.setString(9, km.getAddress());
		ps.setString(10, "");
		statusReg = ps.executeUpdate();
		return statusReg;

	}

	public ResultSet getKyc(int userid) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getCon();
		String sql = "select * from userfiles where userid="+userid ;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}

}
