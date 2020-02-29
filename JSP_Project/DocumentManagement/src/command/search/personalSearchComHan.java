package command.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SearchDAO;
import VO.SimplePersonalListVO;
import VO.UserVO;
import command.ComHandler;

public class personalSearchComHan implements ComHandler{

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserVO user;		
		user = (UserVO)request.getSession().getAttribute("UserInfo");
		
		String readCheck,searchId;
		List<SimplePersonalListVO> personalList;
		SearchDAO searchDAO = new SearchDAO();
		readCheck = (String)request.getParameter("readCheck");
		if(readCheck==null) readCheck = "2";
		searchId = (String)request.getParameter("searchId");
		
			
		
		
		personalList = searchDAO.searchPersonalList(
				user.getUserid(), 
				(String)request.getParameter("listId"), 
				Integer.parseInt(readCheck),
				searchId,
				(String)request.getParameter("searchText")
				);
		
		System.out.println(user.getUserid());
		System.out.println(request.getParameter("listId"));
		System.out.println(readCheck);
		System.out.println((String)request.getParameter("searchId"));
		System.out.println(request.getParameter("searchText"));
		System.out.println("List "+personalList.size());
		
		request.setAttribute("docList", personalList);
		request.setAttribute("page", "/PersonalDoc_List/Personal_ReceiveList.jsp");
	}

}
