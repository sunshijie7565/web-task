package WebProject.user.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		VerifyCode vc = new VerifyCode();//创建验证码类
		BufferedImage image  = vc.getImage();//创建验证码图片
		request.getSession().setAttribute("session_vcode", vc.getText());//获取验证码文本

		VerifyCode.output(image, response.getOutputStream());//输出图片到页面
	}
}
