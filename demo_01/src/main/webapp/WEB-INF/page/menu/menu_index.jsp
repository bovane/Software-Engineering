<%--
  Created by IntelliJ IDEA.
  User: BoVane
  Date: 2019/6/24
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    </head>

    <frameset rows="40,*" border="0">
    <frame src="menu/menutop.do">

    <!-- 分为两边，左边占22% -->
    <frameset cols="22%, *" border="0">
    <frame src="menu/menuleft.do">
    <frame name="rightframe" src="menu/menumain.do?menuId=0">
    <!-- 给menumain.do传参数值menuId=0 -->
    </frameset>
    </frameset>
    <body>

    </body>
    </html>
