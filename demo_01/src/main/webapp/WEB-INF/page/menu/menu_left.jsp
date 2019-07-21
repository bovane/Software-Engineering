<%--
  Created by IntelliJ IDEA.
  User: BoVane
  Date: 2019/6/24
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" import="java.util.*, cn.edu.zjgsu.bean.MenuInfo"%>
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
    <link href="res/dtree/dtree.css" rel="stylesheet">
    <script type="text/javascript" src="res/dtree/dtree.js"></script>
    </head>
    <body>
    <div>
    <script type="text/javascript">
    var d = new dTree('d');
    /* d.add(0,-1,"根节点","menu.menumain.do","","rightframe"); */
        <% List<MenuInfo> list = (List<MenuInfo>)request.getAttribute("list");
		if(list!=null && list.size()>0){
		for(MenuInfo menu:list){
		%>
    d.add(<%=menu.getMenuId()%>,<%=menu.getParentMenuId()%>,"<%=menu.getMenuName()%>","menu/menumain.do?menuId=<%=menu.getMenuId()%>","","rightframe");
        <%}}%>
    document.write(d);
    </script>
    </div>
    </body>
    </html>
