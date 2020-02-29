package command.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import VO.SimpleApprovalListVO;
import VO.UserVO;
import command.ComHandler;

public class viewApprovalSendListComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("UserInfo");
		String userid = uservo.getUserid();
		String listid = "sendList";
		int readcheck = 0; // ���߿� request�� session���� �޾ƿ��ּ���

		DocumentDAO documentDAO = new DocumentDAO();
		int pagecount = documentDAO.getDocPage(userid,"Approval_Type", listid); // count of pages
		int selectpage = 1;
		String temp=request.getParameter("selectpage"); //default == 1?
		if(temp!=null)
			selectpage = Integer.parseInt(temp);
		List<SimpleApprovalListVO> approvalList = documentDAO.approvalList(userid, listid, readcheck, pagecount);

		request.setAttribute("docList", approvalList);
		request.setAttribute("page", "/ApprovalDoc_List/Approval_SendList.jsp");
	}

}
