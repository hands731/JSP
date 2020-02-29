package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logoutComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		/*
		 * try {
		 * request.getRequestDispatcher("/Login/login.jsp").forward(request,response); }
		 * catch (ServletException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
		 */
		/*
		 * try { response.sendRedirect("/DocumentManagement/Login/login.jsp"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * catch( Exception e) { e.printStackTrace(); }
		 */
	}

}
