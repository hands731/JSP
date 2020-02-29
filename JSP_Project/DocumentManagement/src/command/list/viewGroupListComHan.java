package command.list;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import DAO.SearchDAO;
import VO.SimpleGroupListVO;
import command.ComHandler;

public class viewGroupListComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		String groupName = (String) request.getParameter("groupName");
		System.out.println(":�׷��̸�:"+groupName);
		DocumentDAO dao = new DocumentDAO();
		
		List<SimpleGroupListVO> groupList = dao.groupList(groupName);
		
		
	
		
		for(int i =0;i<groupList.size();i++) {
			System.out.println(groupList.get(i).getTitle());
		}
		
		
		//System.out.println("1");
		request.setAttribute("docList", groupList);
		request.setAttribute("page", "/GroupDoc_List/Group_List.jsp");
		//System.out.println("1");
	}

}
