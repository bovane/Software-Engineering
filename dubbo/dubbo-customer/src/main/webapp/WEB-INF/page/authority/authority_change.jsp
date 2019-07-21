<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath( );
    String basePath = request.getScheme( ) + "://" + request.getServerName( ) + ":" + request.getServerPort( ) + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权限变更</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
        <li>系统管理</li>
        <li>权限管理</li>
        <li>权限变更</li>
    </ul>
</div>
<form action="" method="post" id="f1" class="form-inline">
    <div class="row alert alert-info" style="margin: 0px; padding: 5px;">
        <h4 align="center">超级管理员</h4>
    </div>
</form>
<div class="row" style="padding: 15px">
    <table class="table">
        <tr>
            <th>第一级菜单</th>
            <th>第二级菜单</th>
        </tr>
        <tr>
            <td><input type="checkbox"/>系统管理</td>
            <td><label><input type="checkbox"/>用户管理</label>
                <label><input type="checkbox"/>角色管理</label>
                <label><input type="checkbox"/>菜单管理</label>
                <label><input type="checkbox"/>权限管理</label></td>
        </tr>
        <tr>
            <td><input type="checkbox"/>报销管理</td>
            <td><label><input type="checkbox"/>报销单</label>
                <label><input type="checkbox"/>我的报销</label>
                <label><input type="checkbox"/>部门审核</label>
                <label><input type="checkbox"/>财务审核</label>
                <label><input type="checkbox"/>我的审核</label>
            </td>
        </tr>
    </table>
</div>
</body>
</html>