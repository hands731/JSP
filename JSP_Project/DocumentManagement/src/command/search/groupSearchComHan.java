package command.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SearchDAO;
import VO.SimpleGroupListVO;
import command.ComHandler;

public class groupSearchComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		SearchDAO searchDAO = new SearchDAO();
		List<SimpleGroupListVO> groupList = new ArrayList<>();
		
		
		String readCheck =(String)request.getParameter("readCheck");
		
		
		String searchId = "documenttbl."+(String)request.getParameter("searchId");
		
			
		groupList = searchDAO.searchGroupList(
				(String)request.getParameter("groupName"),
				searchId, 
				(String)request.getParameter("searchText"));
		
		
		request.setAttribute("docList", groupList);
		request.setAttribute("page", "/GroupDoc_List/Group_List.jsp");
	}

}
