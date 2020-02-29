package command.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DocumentDAO;
import VO.DetailDocVO;

public class viewGroupDocComHan implements command.ComHandler{

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String docid = (String)request.getParameter("docid");
		String userid = (String)request.getParameter("userid");
		DocumentDAO documentDAO = new DocumentDAO();
		DetailDocVO detailDocVO = documentDAO.viewDoc(Integer.parseInt(docid));
		documentDAO.changeIsRead(Integer.parseInt(docid), userid, null);
		request.setAttribute("doc", detailDocVO);
		request.removeAttribute("page");
		request.setAttribute("page", "/DocContent/Content_GroupDoc.jsp");
		
	}

}
