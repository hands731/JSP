package command.approval;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.JdbcTemplate;
import DAO.DocumentDAO;
import VO.SimpleApprovalListVO;
import command.ComHandler;

public class holdApprovalDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int docid = Integer.parseInt(request.getParameter("docid"));
		String userid = request.getParameter("userid");
		
		DocumentDAO documentDAO = new DocumentDAO();
		Connection conn = JdbcTemplate.getConnection();
		documentDAO.changeState(1, docid, conn);
		
		List<SimpleApprovalListVO> approvalList = documentDAO.approvalList(userid, "receiveList", 0,1);

		request.setAttribute("docList", approvalList);
		request.setAttribute("page", "/ApprovalDoc_List/Approval_ReceiveList.jsp");
	}

}
