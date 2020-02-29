package command.make;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DocumentDAO;
import VO.DetailDocVO;
import VO.UserVO;
import command.ComHandler;

public class make_PersonalDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		
		
		DetailDocVO vo = new DetailDocVO();
		
		vo.setSender(((UserVO)request.getSession().getAttribute("UserInfo")).getUserid());
		vo.setTitle(request.getParameter("title"));
		vo.setContent(	request.getParameter("content"));
		vo.setDes1(request.getParameter("des1"));
		vo.setDoctype("Personal_Type");
		
		DocumentDAO dao = new DocumentDAO();
		
		
		dao.makeDocument(vo);
		
		request.setAttribute("page", "/MyDoc_List/My_Document.jsp");
		
	}

}
