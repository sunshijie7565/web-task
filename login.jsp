<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
    <h1>登录</h1>
  
    <p style="font-weight: 900; color: red;">${msg }</p>
    <form action="<c:url value='/LoginServlet'/>" method="post">
    	用户名：<input type="text" name="username" value="${user.username }" /><br/>
    	密　码：<input type="password" name="password"/><br/>
    	验证码：<input type="text" name="loginCode" size="2"/>
    	<img id="vCode" src="<c:url value='/VerifyCodeServlet?name=loginCode'/>" border="2"/>
    	<a href="javascript:_change()" style="font-size: 12;">看不清，换一张</a><br/>
        <input type="submit" value="登录"/>
    </form>
  </body>
    <script type="text/javascript">
    	function _change() {
    		var img = document.getElementById("vCode");
    		img.src = "<c:url value='/VerifyCodeServlet?name=loginCode&'/>" + new Date().getTime();
    	}
    </script>
</html>
