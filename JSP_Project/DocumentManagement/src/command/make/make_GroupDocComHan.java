package command.make;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.DocumentDAO;
import VO.DetailDocVO;
import VO.UserVO;
import command.ComHandler;

public class make_GroupDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		int docId = -1;
		DetailDocVO vo = new DetailDocVO();
		int sequence ;
		vo.setSender(((UserVO) request.getSession().getAttribute("UserInfo")).getUserid());
		System.out.println("Ȯ��: "+request.getParameter("parentId"));
		if (!request.getParameter("parentId").equalsIgnoreCase("null"))
		{
		vo.setTitle("	"+" re("+request.getParameter("parentId")+") : "+request.getParameter("title"));
		}
		else {
			vo.setTitle(request.getParameter("title"));
		}
		vo.setContent(request.getParameter("content"));
		vo.setGroupName(request.getParameter("groupName"));
		vo.setDoctype("Group_Type");

		BoardDAO dao1 = new BoardDAO();
		
		DocumentDAO dao = new DocumentDAO();
		docId = dao.makeDocument(vo);
		if (!request.getParameter("parentId").equalsIgnoreCase("null")) {
			// ����̸�
			int parentId = Integer.parseInt(request.getParameter("parentId"));	
			sequence = dao1.searchSequence(parentId)-1;
			
			System.out.println("parentId : "+parentId+"sequence-parentId*100000 : "+(sequence-parentId*100000)+"  sequence : "+sequence);
			
			
			/*
			 * if((sequence-parentId*100000) == 99999) { sequence = parentId * 100000 +
			 * 99998; }else if((sequence-parentId*100000) ==99998) { sequence = parentId *
			 * 100000 + 99997; }
			 */

		} else {
			sequence = docId * 100000 + 99999;

		}
		dao1.insertBoardtbl(vo.getGroupName(), docId, sequence);

		request.setAttribute("page", "/MyDoc_List/My_Document.jsp");

	}
}
