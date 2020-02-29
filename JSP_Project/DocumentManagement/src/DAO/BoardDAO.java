package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Common.JdbcTemplate;

public class BoardDAO {

	public boolean insertBoardtbl(String groupName, int docId, int sequence) {
		int insertCount = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into boardtbl values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.setInt(2, sequence);
			pstmt.setInt(3, docId);
			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		if (insertCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int searchSequence(int docId) {
		int sequence = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select boardseq from boardtbl where docId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sequence = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		return sequence;

	}
}
