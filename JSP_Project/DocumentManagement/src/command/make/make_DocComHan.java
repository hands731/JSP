package command.make;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ComHandler;

public class make_DocComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.removeAttribute("page");
		request.setAttribute("page", "/MakeDoc/Make_Doc.jsp");
		
	}

}
