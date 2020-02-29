package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import VO.UserVO;


public class loginComHan implements ComHandler {

	@Override
	public void Process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = (String)request.getParameter("userid");
		String pwd = (String)request.getParameter("userpassword");
		
		UserDAO userDao =  new UserDAO();
		UserVO userVO = null;
		
		HttpSession session = request.getSession();
		userVO = userDao.getUserInfo(id,pwd);
		
		if(userVO!=null) {
			session.setAttribute("UserInfo", userVO);
			/*
			 * request.removeAttribute("page");
			 * 
			 * try { request.getRequestDispatcher("/viewMyDocList.do").forward(request,
			 * response); } catch (ServletException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch(Exception e) { e.printStackTrace(); }
			 */
		}
		
		
		
		/*
		 * if(userVO==null) { //request.setAttribute("login", "fail");
		 * session.setAttribute("islogin", "fail"); }else {
		 * //request.setAttribute("login", "suceed"); session.setAttribute("islogin",
		 * "suceed"); session.setAttribute("UserInfo", userVO);//로그인 필터
		 * request.removeAttribute("page"); //request.setAttribute("page",
		 * "/MyDoc_List/My_Document.jsp"); try {
		 * request.getRequestDispatcher("/viewMyDocList.do").forward(request, response);
		 * } catch (ServletException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } }
		 */
	}

}
