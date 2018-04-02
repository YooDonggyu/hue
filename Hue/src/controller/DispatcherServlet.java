package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		handleRequest(request, response);
	}

	private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String command=request.getParameter("command");
			String url=
			HandlerMapping.getInstance().create(command).execute(request, response);
			if(url.trim().startsWith("redirect:"))
				response.sendRedirect(url.trim().substring(9));
			else
				request.getRequestDispatcher(url).forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();			
			response.sendRedirect("error.jsp");		
		}
	}
}
