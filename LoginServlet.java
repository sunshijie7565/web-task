package WebProject.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebProject.user.domain.User;
import WebProject.user.service.UserException;
import WebProject.user.service.UserService;
import cn.itcast.commons.CommonUtils;

public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		
		try {
		    User user = userService.login(form);
			request.getSession().setAttribute("sessionuser", user);
			response.sendRedirect(request.getContextPath() + "/user/welcome.jsp");
		}catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;	
		}
		
	
	}
}
