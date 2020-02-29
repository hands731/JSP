package command.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import VO.SimpleApprovalListVO;
import VO.SimplePersonalListVO;
import VO.UserVO;
import command.ComHandler;

public class viewApprovalReceiveListComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * String userid = (String)request.getAttribute("userId"); int readCheck =
		 * (int)request.getAttribute("readCheck"); DocumentDAO dao = new DocumentDAO();
		 * List<SimplePersonalListVO> list = dao.personalList(userid, "receiveList",
		 * readCheck); if(list !=null) { request.setAttribute("personalReceiveList",
		 * list); request.setAttribute("page",
		 * "/PersonalDoc_List/Personal_ReceiveList.jsp");
		 */
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("UserInfo");
		String userid = uservo.getUserid();
		String listid = "receiveList";
		int readcheck = 2; // ���߿� request�� session���� �޾ƿ��ּ���
		String notRead = request.getParameter("readCheck");
		if(notRead != null) readcheck = 0;
		

		DocumentDAO documentDAO = new DocumentDAO();
		int pagecount = documentDAO.getDocPage(userid,"Approval_Type", listid); // count of pages
		int selectpage = 1;
		String temp=request.getParameter("selectpage"); //default == 1?
		if(temp!=null)
			selectpage = Integer.parseInt(temp);
		List<SimpleApprovalListVO> approvalList = documentDAO.approvalList(userid, listid, readcheck, pagecount);

		request.setAttribute("docList", approvalList);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("page", "/ApprovalDoc_List/Approval_ReceiveList.jsp");
	}

}
