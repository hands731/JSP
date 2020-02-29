package command.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import VO.SimplePersonalListVO;
import VO.UserVO;
import command.ComHandler;

public class viewPersonalReceiveListComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//String userid = (String)request.getSession().getAttribute("UserInfo").getUserid();
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO)session.getAttribute("UserInfo");
		String userid = uservo.getUserid();
		String listid = "receiveList";
		int readcheck = 2;	//���߿� request�� session���� �޾ƿ��ּ���
		String notRead = request.getParameter("readCheck");
		if(notRead != null) readcheck = 0;
		
		DocumentDAO documentDAO = new DocumentDAO();
		int pagecount = documentDAO.getDocPage(userid,"Personal_Type", listid); // count of pages
		int selectpage = 1;
		String temp=request.getParameter("selectpage"); //default == 1?
		if(temp!=null)
			selectpage = Integer.parseInt(temp);
		
		
		
		List<SimplePersonalListVO> personalList = documentDAO.personalList(userid, listid, readcheck, selectpage);
		//select된 페이지의 리스트를 리턴....
		
		request.setAttribute("docList", personalList);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("page", "/PersonalDoc_List/Personal_ReceiveList.jsp");
	}

}
