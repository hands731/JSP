package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ComHandler {
	public void Process(HttpServletRequest request, HttpServletResponse response);
	
}
