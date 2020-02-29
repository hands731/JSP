package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpSession session = httpRequest.getSession();
			
			if(session != null)
			{
				if(session.getAttribute("UserInfo")!=null) {
					session.removeAttribute("islogin");
					session.setAttribute("islogin", true);
				}
			}
			
			if((boolean)session.getAttribute("islogin")) {
				//System.out.println("���� : islogin�� true. �α�������. doFilter.");
				chain.doFilter(request, response);		
			}
			else {
				HttpServletRequest req = (HttpServletRequest)request;
				String temp = req.getRequestURI().toString().trim();
				
				if(temp.startsWith("/DocumentManagement/login")) {
					//System.out.println("���� : islogin�� false���� ��ΰ� login���� ����. doFilter.");
					chain.doFilter(request, response);
				}else {
				//System.out.println("���� : islogin�� false. : loginâ����.��� : "+temp);
				//System.out.println("�α������� : "+islogin);
				//HttpServletResponse rs = (HttpServletResponse)response;
				
				req.getSession().invalidate();
				request.getRequestDispatcher("/Login/login.jsp").forward(httpRequest, response);
				//rs.sendRedirect("/DocumentManagement/Login/login.jsp");
				}
			}
			
		// pass the request along the filter chain
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
