package Servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.ComHandler;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, ComHandler> commandHandlerMap = new HashMap<>();

	public void init(ServletConfig config) throws ServletException {
		String configFile = config.getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = config.getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				ComHandler handlerInstance = (ComHandler) handlerClass.newInstance();
				// System.out.println(command);
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getRequestURI();
		// System.out.println("ㅇㅇ");
		// System.out.println(command);
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		// System.out.println("ㅇㅇ");
		// System.out.println(command);
		ComHandler handler = commandHandlerMap.get(command);

		// 원래는 추가로 널페이지 넣고 하는 과정이 있음.
		handler.Process(request, response);

		// String temp = (String)request.getAttribute("login");
		// System.out.println("fsf");
		// System.out.println(temp);

		// if(viewPage!=null) {
		
		try {
			switch (command) {
			case "/login.do":
				request.getRequestDispatcher("/viewMyDocList.do").forward(request, response);
				break;
			case "/logout.do":
				response.sendRedirect("/DocumentManagement/Login/login.jsp");
				break;
			case "/deleteDoc.do":
				int temp = Integer.parseInt((String)request.getAttribute("delcase"));
				switch(temp) {
				case 1:
					request.getRequestDispatcher("/viewApprovalReceiveList.do").forward(request, response);
					break;
				case 2:
					request.getRequestDispatcher("/viewApprovalSendList.do").forward(request, response);
					break;
				case 3:
					request.getRequestDispatcher("/viewPersonalReceiveList.do").forward(request, response);
					break;
				case 4:
					request.getRequestDispatcher("/viewPersonalSendList.do").forward(request, response);
					break;
				}
				break;
			case "/dropDoc.do":
			case "/recoveryDoc.do":
				request.getRequestDispatcher("/viewBin.do").forward(request, response);
				break;
			case "/make_GroupDoc.do":
			case "/make_PersonalDoc.do":
			case "/make_ApprovalDoc.do":
				request.getRequestDispatcher("/viewMyDocList.do").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		// String viewPage = (String)request.getAttribute("page");

		/*
		 * String islogin = (String) request.getAttribute("login"); try {
		 * RequestDispatcher dispatcher; if (islogin.equalsIgnoreCase("suceed")) {
		 * dispatcher = request.getRequestDispatcher("/index.jsp");
		 * dispatcher.forward(request, response); } else { dispatcher =
		 * request.getRequestDispatcher("/Login/login.jsp"); dispatcher.forward(request,
		 * response); request.getSession().invalidate(); }
		 * 
		 * 
		 * } catch (ServletException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}
}