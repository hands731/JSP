package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Common.JdbcTemplate;
import VO.SimpleApprovalListVO;
import VO.SimpleGroupListVO;
import VO.SimplePersonalListVO;

public class SearchDAO {

	// searchId(documenttbl.title, documenttbl.content, documenttbl.sender,
	// documenttbl.des1)
	// �� ���񽺿��� switch������ �־��ּ�
	public List<SimplePersonalListVO> searchPersonalList(String userId, String listId, int readCheck, String searchId,
			String searchText) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimplePersonalListVO> personalList = new ArrayList<>();

		searchText = "%" + searchText + "%";
		System.out.println("readCheck : "+readCheck+"  userId : "+userId+"  listId : "+listId+"  searchText " + searchText);
		try {
			StringBuilder sql = new StringBuilder(
					"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, documenttbl.des1 from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where documenttbl.doctype = 'PERSONAL_TYPE' and personaltbl.listid = ? and isdelete = 0 and ");

			if(listId.equalsIgnoreCase("receiveList")) {
				System.out.println("일루와야돼");
				sql.append(" documenttbl.des1 = ? and ");  
			}else {
				sql.append(" documenttbl.sender = ? and ");
			}
			
			
			
			
			
			sql.append(searchId + " like " + "'" + searchText + "'");

			if (readCheck == 0) {
				System.out.println("readCheck = 0");
				sql.append(" and isRead = 0 ");
			}

			sql.append(" order by ctime desc");

			System.out.println(sql.toString());

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, listId);
			pstmt.setString(2, userId);
    int i = 1;
    System.out.println("확인중 : 1");
			rs = pstmt.executeQuery();
			System.out.println("확인중 : 1");
			while (rs.next()) {
				System.out.println("확인중 : 2");
				{
					SimplePersonalListVO personalVO = new SimplePersonalListVO();
					personalVO.setDocId(rs.getInt(1));
					personalVO.setTitle(rs.getString(2));
					personalVO.setCtime(rs.getString(3));
					personalVO.setSender(rs.getString(4));
					personalVO.setIsRead(rs.getInt(5));
					personalVO.setDes1(rs.getString(6));
					personalList.add(personalVO);

				}

			}
			System.out.println("확인중 : 1");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return personalList;
	}

	// ���� ���ϵ���
	public List<SimpleApprovalListVO> searchApprovalList(String userId, String listId, int readCheck, String searchId,
			String searchText) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleApprovalListVO> approvalList = new ArrayList<>();

		try {
			StringBuilder sql = new StringBuilder(
					"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where documenttbl.doctype = 'APPROVAL_TYPE'  and personaltbl.listid = ?  and isdelete = 0 and ");
			
			if(listId.equalsIgnoreCase("receiveList")) {
				sql.append("documenttbl.des1 = ? and ");
			}else {
				sql.append("documenttbl.sender = ? and ");
			}
			
			sql.append(searchId + " like " + "'" + searchText + "'");

		
			
			
			if (readCheck == 0) {
				sql.append(" and isRead = 1 ");
			}

			sql.append(" order by ctime desc");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, listId);
			pstmt.setString(2, userId);
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

	// myDoc���� �׷�˻�
	public List<SimpleGroupListVO> searchMyGroupList(List<String> groupNames, String searchId, String searchText) {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleGroupListVO> groupList = new ArrayList<>();

		try {
			StringBuilder sql = new StringBuilder(
					"select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where groupname = ? ");
			if (groupNames.size() > 1) {
				for (int i = 1; i < groupNames.size(); i++) {
					sql.append(" or groupName = ?");
				}
			}

			sql.append(searchId + " like " + " '" + searchText + " ' ");
			sql.append(" order by boardtbl.boardseq desc");
			pstmt = conn.prepareStatement(sql.toString());

			for (int i = 0; i < groupNames.size(); i++) {
				pstmt.setString(i + 1, groupNames.get(i));
			}
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

	// ������Ͽ��� �׷� �˻�
	//각각목록에서 그룹 검색
	public List<SimpleGroupListVO> searchGroupList(String groupName, String searchId, String searchText){
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SimpleGroupListVO> groupList = new ArrayList<>();
		searchText = "%"+searchText+"%";
		try {
			StringBuilder sql =new StringBuilder("select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where documenttbl.groupname = ? and ");
			sql.append(searchId +" like "+" '"+searchText+"'");
			
			sql.append(" order by boardtbl.boardseq desc");
			

			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, groupName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {SimpleGroupListVO groupVO = new SimpleGroupListVO();
				groupVO.setDocId(rs.getInt(1));
				groupVO.setTitle(rs.getString(2));
				groupVO.setCtime(rs.getString(3));
				groupVO.setSender(rs.getString(4));
				groupVO.setGroupName(rs.getString(5));
			
				groupList.add(groupVO);
				}while(rs.next());
			}
	}catch(Exception e){e.printStackTrace();}
	   finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return groupList;
		
}

}
