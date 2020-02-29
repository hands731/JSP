package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DocumentDAO;

public class recoveryDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String docId = request.getParameter("docid");
		String userid = request.getParameter("userid");
		
		DocumentDAO documentDAO = new DocumentDAO();
		documentDAO.recoveryDoc(Integer.parseInt(docId), userid);

		request.removeAttribute("page");
		
	}

}
