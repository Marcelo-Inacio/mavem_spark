package br.com.fatec.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import java.sql.Connection;

import br.com.fatec.commons.ConnectionMySql;
import br.com.fatec.model.entity.Competence;

public class DaoCompetencies {
	private static ConnectionMySql connMySql = new ConnectionMySql();
	
	// Insert Competence - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static boolean insertCompetence(Competence competence) throws SQLException {
		Connection conn = null;
		String sql = "insert into competence (com_type, com_registration_date) values (?, date_format(now(), '%Y-%m-%d'))";

		boolean insert = false;
		try {
			conn = connMySql.getConection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,competence.getType());			
			if (connMySql.executeSql(ps)) {
				ps.close();
				insert = true;
			}
		} finally {
			conn.close();
			return insert;
		}
	}
	
	// Delete Competence - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static boolean deleteCompetence(Long code) throws SQLException {
		Connection conn = null;
		String sql = "delete from competence where com_code = ?;";		
		boolean delete = false;
		try {
			conn = connMySql.getConection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, code);
			if (connMySql.executeSql(ps)) {
				ps.close();
				delete = true;
			}
			conn.close();
		} finally {
			return delete;
		}
	}

	// Search competence by code - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static Competence searchCompetenceByCode(Long code) throws SQLException {
		Competence competence = null;
		Connection conn;
		String query = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from competence where com_code = ?";
		try {
			competence = new Competence();
			conn = connMySql.getConection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
					competence.setCode(rs.getLong("COM_CODE"));
					competence.setType(rs.getString("COM_TYPE"));
					competence.setRegistration_date(rs.getString("COM_REGISTRATION_DATE"));
			}
			rs.close();
			ps.close();
			conn.close();
		}finally{
			return competence;
		}
	}

	// Update Competence - FUNCIONANDO!!!
	@SuppressWarnings("finally")
	public static boolean updateCompetence(Competence competence) throws SQLException {
		Connection conn = null;
		String sql = "update competence set COM_TYPE= ?, COM_REGISTRATION_DATE = date_format(now(), '%Y-%m-%d') where COM_CODE=?;";
		boolean update = false;
		try {
			conn = connMySql.getConection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, competence.getType());
			ps.setLong(2, competence.getCode());
			if (connMySql.executeSql(ps)) {
				update = true;
			}
			ps.close();
			conn.close();
		} finally {
			return update;
		}
	}
		
	// Search all competences - FUNCIONANDO !!!!
	@SuppressWarnings({ "finally" })
	public static List<Competence> searchAll() throws SQLException {
		List<Competence> listCompetence = null;
		String sql = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from COMPETENCE ;";
		Connection conn = null;
		try {
			listCompetence = new ArrayList<>();
			conn = connMySql.getConection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Competence competence = new Competence();
				competence.setCode(rs.getLong("COM_CODE"));
				competence.setType(rs.getString("COM_TYPE"));
				competence.setRegistration_date(rs.getString("COM_REGISTRATION_DATE"));
				listCompetence.add(competence);
			} 
			rs.close();
			ps.close();
			conn.close();
		} finally {
			return listCompetence;
		}
	}
}