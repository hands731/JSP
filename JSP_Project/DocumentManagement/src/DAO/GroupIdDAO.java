package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Common.JdbcTemplate;

public class GroupIdDAO {

	// List<String> 교수의 그룹목록(userId)
	public List<String> professorGroupList(String userId, Connection conn) {
		// Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> professorList = new ArrayList<>();
		try {
			String sql = "select groupName from grouptbl where instructor = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				professorList.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		return professorList;
	}

	// List<String> 학생의 그룹목록(userId)
	public List<String> studentGroupList(String userId, Connection conn) {

		// Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> studentList = new ArrayList<>();
		try {
			String sql = "select groupName from groupmembertbl where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentList.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		return studentList;
	}

}