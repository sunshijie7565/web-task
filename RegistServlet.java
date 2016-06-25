package WebProject.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebProject.user.domain.User;
import WebProject.user.service.UserException;
import WebProject.user.service.UserService;
import cn.itcast.commons.CommonUtils;

public class RegistServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		
		
		
		Map<String,String> errors = new HashMap<String,String>();
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty())
		{
			errors.put("username", "用户名不能为空!");
		}
		else if(username.length()<3||username.length()>15)
		{
			errors.put("username", "用户名必须在3-15之间!");
		}
		String password = form.getPassword();
		if(password == null ||password.trim().isEmpty())
		{
			errors.put("password", "密码不能为空!");
		}
		else if(password.length()<3||password.length()>15)
		{
			errors.put("password", "密码必须在3-15之间!");
		}
		
		String sessionVerifyCode = (String)request.getSession().getAttribute("session_vcode");
		String verifyCode = form.getVerifyCode();
		if(verifyCode == null || verifyCode.trim().isEmpty())
		{
			errors.put("verifyCode", "验证码不能为空!");
		}
		else if(verifyCode.length()!=4)
		{
			errors.put("verifyCode", "验证码长度必须为4!");
		}
		else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode))
		{
			errors.put("verifyCode", "验证码错误");
		}


		if(errors != null && errors.size()>0)
		{
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response) ;
			return;
		}
		
		try {
			userService.regist(form);
			response.getWriter().print("<h1>注册成功!</h1><a href='"+request.getContextPath() +
					 "/user/login.jsp"+"'>点击这里去登陆</a>");
					
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("user",form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response) ;
			return;
		}
		}
		
}
