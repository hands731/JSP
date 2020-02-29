 package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Common.JdbcTemplate;


import VO.UserVO;
public class UserDAO {
	
	
	public VO.UserVO getUserInfo(String userId, String userPassword) {

		List<String> groupList = new ArrayList<>();
		
		Connection conn = JdbcTemplate.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVO userVO = new UserVO();
		
		GroupIdDAO dao = new GroupIdDAO();

		String sql = "select * from usertbl where userid = ? and password = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);

		
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);

			rs = pstmt.executeQuery();

			if (!rs.next())
				return null;

			userVO.setUserid(rs.getString(1));
			userVO.setName(rs.getString(3));
			userVO.setGrade(rs.getString(4));
			userVO.set_class(rs.getString(5));
			userVO.setPosition(rs.getString(6));

			if (userVO.getPosition().equals("professor")) {
				groupList = dao.professorGroupList(userId, conn);

			} else {
				groupList = dao.studentGroupList(userId, conn);

			}
			userVO.setGroupList(groupList);

		} catch (SQLException e) {
			e.printStackTrace();
			// return null;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		
		return userVO;

	}
}
