package command.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import VO.DetailDocVO;
import VO.SimpleApprovalListVO;
import VO.SimpleGroupListVO;
import VO.SimplePersonalListVO;
import VO.UserVO;
import command.ComHandler;

public class viewMyDocListComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("UserInfo");
		String userid = uservo.getUserid();
		List<String> groups = uservo.getGroupList();
		String listid = "receiveList";
		int readcheck = 0; // ���߿� request�� session���� �޾ƿ��ּ���
		
		DocumentDAO documentDAO = new DocumentDAO();
		
		int pagecount = documentDAO.getDocPage(userid,"Personal_Type", listid); // count of pages
		int selectpage = 1;
		String temp=request.getParameter("selectpage"); //default == 1?
		if(temp!=null)
			selectpage = Integer.parseInt(temp);
		
		
		
		
		List<SimplePersonalListVO> personalList = documentDAO.personalList(userid, listid, readcheck, selectpage);
		List<SimpleApprovalListVO> approvalList = documentDAO.approvalList(userid, listid, readcheck, selectpage);
		List<SimpleGroupListVO> myGroupList = documentDAO.myGroupList(groups);
		
		//request.setAttribute("docList", approvalList);
		request.setAttribute("mydocList_1",  personalList);
		request.setAttribute("mydocList_2", approvalList);
		request.setAttribute("mydocList_3", myGroupList);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("page", "/MyDoc_List/My_Document.jsp");
	}

}
