package command.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DocumentDAO;
import VO.SimpleApprovalListVO;
import VO.SimpleBinListVO;
import VO.UserVO;
import command.ComHandler;

public class viewBinComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("UserInfo");
		String userid = uservo.getUserid();

		DocumentDAO documentDAO = new DocumentDAO();
		List<SimpleBinListVO> binList = documentDAO.binList(userid);
		
		for(SimpleBinListVO a : binList)
		{
			System.out.println(a.getTitle());
		}
		
		request.setAttribute("docList", binList);
		request.setAttribute("page", "/BinDoc_List/Bin_List.jsp");
	}

}
