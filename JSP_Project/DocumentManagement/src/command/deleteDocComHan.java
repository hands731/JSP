package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DocumentDAO;

public class deleteDocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String docId = request.getParameter("docid");
		String listid = request.getParameter("listid");
		String userid = request.getParameter("userid");
		String num = request.getParameter("num");
		request.setAttribute("delcase", num);
		DocumentDAO documentDAO = new DocumentDAO();
		documentDAO.deleteDoc(Integer.parseInt(docId), listid, userid);

		request.removeAttribute("page");
	}

}
