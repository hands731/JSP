package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Common.JdbcTemplate;
import VO.DetailDocVO;
import VO.SimpleApprovalListVO;
import VO.SimpleBinListVO;
import VO.SimpleGroupListVO;
import VO.SimplePersonalListVO;

public class DocumentDAO {

	// 占쏙옙占쏙옙占쏙옙占쏙옙(占쏙옙占쏙옙)
	public int makeDocument(DetailDocVO detailDocVO) {
		int docId = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into documenttbl values(null, ?, ?, ?,sysdate(), ?,?,?,?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, detailDocVO.getDoctype());
			pstmt.setString(2, detailDocVO.getTitle());
			pstmt.setString(3, detailDocVO.getContent());
			// pstmt.setString(4, detailDocVO.getCtime());
			pstmt.setString(4, detailDocVO.getDes1());
			pstmt.setString(5, detailDocVO.getDes2());
			pstmt.setString(6, detailDocVO.getDes3());
			pstmt.setString(7, detailDocVO.getSender());
			pstmt.setString(8, detailDocVO.getGroupName());
			int insertCount = pstmt.executeUpdate();
			if (insertCount <= 0) {
				throw new SQLException();
			} else {
				if (detailDocVO.getDoctype().equalsIgnoreCase("Group_Type"))
					docId = searchDocId(detailDocVO.getSender());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return docId;

	}

	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占�
	public boolean deleteDoc(int docId, String listid, String userid) {
		int changeIsDelete = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update personaltbl set isdelete = 1 where docid = ? and listid = ? and userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			pstmt.setString(2, listid);
			pstmt.setString(3, userid);
			changeIsDelete = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		if (changeIsDelete > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean recoveryDoc(int docId, String userid) {
		int isrecovery = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "update personaltbl set isdelete = 0 where docid = ? and userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			pstmt.setString(2, userid);
			isrecovery = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		if (isrecovery > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 占싣울옙 占쏙옙占쏙옙
	public boolean dropDocument(int docId, String userid) {
		int isDrop = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "delete from personaltbl where docid = ? and userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			pstmt.setString(2, userid);
			isDrop = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		if (isDrop > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 占쏙옙占쏙옙占쏙옙占쏙옙
	public boolean changeIsRead(int docId, String userid, String listid) {
		int changeIsRead = 0;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "update personaltbl set isread = 1 where docid = ? and listid = ? and userid = ?";
			System.out.println(sql.toString() + userid + docId);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			pstmt.setString(2, listid);
			pstmt.setString(3, userid);

			changeIsRead = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		if (changeIsRead > 0) {
			return true;
		} else {
			return false;
		}

	}

	// 占쏙옙占싸뱄옙占쏙옙占쏙옙 占쏙옙占�(userid, 占쏙옙占신발쏙옙, readCheck)
	// readCheck = 0 : 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	// = 1 : 占쏙옙체 占쏙옙j
	public List<SimplePersonalListVO> personalList(String userId, String listId, int readCheck, int pagecount) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimplePersonalListVO> personalList = new ArrayList<>();
		int tempnum = (pagecount-1)*10;
		
		try {
			StringBuilder sql = null;
			if (listId.equalsIgnoreCase("sendList"))
				sql = new StringBuilder(
						"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, "
								+ "documenttbl.des1 from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where "
								+ "doctype = 'PERSONAL_TYPE' and documenttbl.sender = ? and personaltbl.listid = ? and isdelete = 0 ");
			else if (listId.equalsIgnoreCase("receiveList"))
				sql = new StringBuilder(
						"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, "
								+ "documenttbl.des1 from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where "
								+ "doctype = 'PERSONAL_TYPE' and documenttbl.des1 = ? and personaltbl.listid = ? and isdelete = 0 ");

			if (readCheck == 0) {
				sql.append(" and isRead = 0 ");
				System.out.println("isRead :" + readCheck);
			}

			System.out.println(sql.toString());
			sql.append(" order by ctime desc");
			sql.append(" limit "+tempnum+", 10");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, listId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				SimplePersonalListVO personalVO = new SimplePersonalListVO();
				personalVO.setDocId(rs.getInt(1));
				personalVO.setTitle(rs.getString(2));
				personalVO.setCtime(rs.getString(3));
				personalVO.setSender(rs.getString(4));
				personalVO.setIsRead(rs.getInt(5));
				personalVO.setDes1(rs.getString(6));
				personalList.add(personalVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return personalList;

	}

	// 占쏙옙占썹문占쏙옙占쏙옙 占쏙옙占�(userid, 占쏙옙占신발쏙옙, readCheck)
	public List<SimpleApprovalListVO> approvalList(String userId, String listId, int readCheck, int pagecount) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tempnum = (pagecount-1)*10;
		
		List<SimpleApprovalListVO> approvalList = new ArrayList<>();
		StringBuilder sql = null;
		String approvalLevel = null;
		try {

			if (listId.equalsIgnoreCase("sendList")) {
				sql = new StringBuilder(
						"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where doctype = 'APPROVAL_TYPE' and documenttbl.sender = ? and personaltbl.listid = ?  and isdelete = 0 ");
			} else {
				sql = new StringBuilder(
						"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where doctype = 'APPROVAL_TYPE' and personaltbl.userid=? and personaltbl.listid = ?  and isdelete = 0 ");
				// 이부분 나중에 DAO수정필요할듯.
				// 일단은 그냥 하겠는데 결제 모듈 바뀌면서 자연스럽게 업데이트 될거같긴함.
				// 쿼리문에서 des1이 userid일경우에 불러오는데...
				// 나중에 가면 아니니까
			}

			if (readCheck == 0) {
				sql.append(" and isRead = 0 ");
			}

			sql.append(" order by ctime desc");
			sql.append(" limit "+tempnum+", 10");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, listId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					SimpleApprovalListVO approvalVO = new SimpleApprovalListVO();
					approvalVO.setDocId(rs.getInt(1));
					approvalVO.setTitle(rs.getString(2));
					approvalVO.setCtime(rs.getString(3));
					approvalVO.setSender(rs.getString(4));
					approvalVO.setIsRead(rs.getInt(5));
					approvalVO.setDes1(rs.getString(6));
					approvalVO.setDes2(rs.getString(7));
					approvalVO.setDes3(rs.getString(8));
					approvalVO.setState(rs.getString(9));

					approvalList.add(approvalVO);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return approvalList;

	}

	// myDoc 占쌓룹리占쏙옙트
	public List<SimpleGroupListVO> myGroupList(List<String> groupNames) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleGroupListVO> groupList = new ArrayList<>();
		int i = 1;
		try {
			System.out.println("인자로받은 그룹리스트의 사이즈 : "+groupNames.size());
			if (groupNames.size() == 0)
				return null;
			StringBuilder sql = new StringBuilder(
					"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where groupname = ? ");
			if (groupNames.size() > 1) {
				for (i = 1; i < groupNames.size();) {
					i++;
					sql.append(" or groupName = ?");
				}
			}
			sql.append("order by boardtbl.boardseq desc");
			pstmt = conn.prepareStatement(sql.toString());

			/*
			 * for (int i = 0; i < groupNames.size(); i++) { pstmt.setString(i + 1,
			 * groupNames.get(i)); }
			 */

			i = 0;
			while (i < groupNames.size()) {
				pstmt.setString(i + 1, groupNames.get(i));
				i++;
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SimpleGroupListVO groupVO = new SimpleGroupListVO();
				groupVO.setDocId(rs.getInt(1));
				groupVO.setTitle(rs.getString(2));
				groupVO.setCtime(rs.getString(3));
				groupVO.setSender(rs.getString(4));
				groupVO.setGroupName(rs.getString(5));

				groupList.add(groupVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return groupList;

	}

	// 占쏙옙占쏙옙占쏙옙 占쌓뤄옙 占쏙옙占쏙옙占쏙옙
	public List<SimpleGroupListVO> groupList(String groupName) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleGroupListVO> groupList = new ArrayList<>();

		try {
			String sql = "select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where groupname = ? order by boardtbl.boardseq desc";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					SimpleGroupListVO groupVO = new SimpleGroupListVO();
					groupVO.setDocId(rs.getInt(1));
					groupVO.setTitle(rs.getString(2));
					groupVO.setCtime(rs.getString(3));
					groupVO.setSender(rs.getString(4));
					groupVO.setGroupName(rs.getString(5));

					groupList.add(groupVO);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return groupList;

	}

	// 占쏙옙占쏙옙 占쏢세븝옙占쏙옙
	public DetailDocVO viewDoc(int docId) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DetailDocVO detailVO = new DetailDocVO();
		try {
			String sql = "select  * from documenttbl left outer join personaltbl on documenttbl.docid = personaltbl.docid where documenttbl.docid = ? limit 1; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				detailVO.setDocId(rs.getInt(1));
				detailVO.setDoctype(rs.getString(2));
				detailVO.setTitle(rs.getString(3));
				detailVO.setContent(rs.getString(4));
				detailVO.setCtime(rs.getString(5));
				detailVO.setDes1(rs.getString(6));
				detailVO.setDes2(rs.getString(7));
				detailVO.setDes3(rs.getString(8));
				detailVO.setSender(rs.getString(9));
				detailVO.setGroupName(rs.getString(10));

				detailVO.setListid(rs.getString(12));
				detailVO.setState(rs.getString(15));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return detailVO;

	}

	// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占�
	public List<SimpleBinListVO> binList(String userId) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleBinListVO> binList = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder(

					"select documenttbl.docid, documenttbl.doctype, documenttbl.sender, documenttbl.title, documenttbl.ctime from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where personaltbl.isdelete = 1 and personaltbl.userid =? order by ctime");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			String temp = null;
			while (rs.next()) {
				SimpleBinListVO binVO = new SimpleBinListVO();
				binVO.setDocId(rs.getInt(1));
				// binVO.setType(rs.getString(2));
				temp = rs.getString(2);
				if (temp.equalsIgnoreCase("PERSONAL_TYPE"))
					binVO.setType("개인문서");
				else if (temp.equalsIgnoreCase("APPROVAL_TYPE"))
					binVO.setType("결재문서");
				else
					binVO.setType("그룹문서");
				binVO.setSender(rs.getString(3));
				binVO.setTitle(rs.getString(4));
				binVO.setCtime(rs.getString(5));
				binList.add(binVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return binList;

	}

	public int searchDocId(String sender) {
		int docId = -1;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select docid from documenttbl where sender = ? order by docId desc limit 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sender);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				docId = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return docId;
	}

	public void changeState(int cases, int docid, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = null;
			if (cases == 1)
				sql = "update personaltbl set state = '결재중' where docid = ?";
			else if (cases == 2)
				sql = "update personaltbl set state = '결재취소' where docid = ?";
			else if (cases == 3)
				sql = "update personaltbl set state = '결재승인' where docid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	public String checkApproval(int docid, String userid) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select des1, des2, des3 from documenttbl where docid=? limit 1";

			String des1 = null;
			String des2 = null;
			String des3 = null;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				des1 = rs.getString(1);
				des2 = rs.getString(2);
				des3 = rs.getString(3);
			}
			if (des1.equalsIgnoreCase(userid)) {
				return "des1";
			} else if (des2.equalsIgnoreCase(userid)) {
				return "des2";
			} else if (des3.equalsIgnoreCase(userid)) {
				return "des3";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}

		return null;
	}

	public void acceptApproval(String userid, int docid, String sender) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select des1, des2, des3 from documenttbl where docid=? limit 1";
			String next;
			String des1 = null;
			String des2 = null;
			String des3 = null;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				des1 = rs.getString(1);
				des2 = rs.getString(2);
				des3 = rs.getString(3);
			}
			if (des1.equalsIgnoreCase(userid)) {
				next = des2;
				if (next == null) {
					changeState(3, docid, conn);
					return;
				}
				addToNextApproval(next, docid, conn);
				changeState(1, docid, conn);
			} else if (des2.equalsIgnoreCase(userid)) {
				next = des3;
				if (next == null) {
					changeState(3, docid, conn);
					return;
				}
				addToNextApproval(next, docid, conn);
				changeState(1, docid, conn);
			} else if (des3.equalsIgnoreCase(userid)) {
				changeState(3, docid, conn);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	public void addToNextApproval(String nextuser, int docid, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into personaltbl values (?, 'receiveList', 0, ?, '결재중', 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nextuser);
			pstmt.setInt(2, docid);
			pstmt.executeUpdate();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);

		}
	}
	public int getDocPage(String userid, String listid, String doctype) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int doccount=0;
		int pagecount = 1;
		try {
			//sql  = "select * from documenttbl inner join personaltbl on documenttbl.docid = personaltbl.docid where personaltbl.userid=? and documenttbl.doctype=? and personaltbl.listid = ? order by ctime desc limit 0,10";
			sql = "select count(documenttbl.docid) from documenttbl inner join personaltbl on documenttbl.docid = personaltbl.docid where personaltbl.userid=? and documenttbl.doctype=? and personaltbl.listid = ? order by ctime desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			pstmt.setString(2,listid);
			pstmt.setString(3,doctype);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				doccount = rs.getInt(1);
			}
			pagecount = doccount/10+1;
			return pagecount;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return pagecount;
	}

}
