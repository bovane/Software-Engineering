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
    <title>角色查询</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
        <li>系统管理</li>
        <li>权限管理</li>
        <li>查询角色</li>
    </ul>
</div>
<form action="" method="post" id="f1" class="form-inline">
    <div class="row alert alert-info" style="margin: 0px; padding: 5px;">
        <div class="form-group">
            <label>角色名称 </label> <input type="text" class="form-control"
                                        name="roleName" placeholder="请输入角色名称"/>
        </div>
        <input type="submit" value="查询角色" class="btn btn-success"/>
    </div>
</form>
<div class="row" style="padding: 15px">
    <table class="table">
        <tr>
            <th><input type="checkbox" onclick="checkboxfun(this)"/></th>
            <th>角色编号</th>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>权限变更</th>
        </tr>
        <tr>
            <td><input type="checkbox" checked="checked" id="roleids"/></td>
            <td>1001</td>
            <td>普通管理员</td>
            <td>可以添加用户，删除用户、修改用户</td>
            <td><a href="authority/change.do">权限变更</a></td>
        </tr>
        <tr>
            <td><input type="checkbox" id="roleids"/></td>
            <td>1001</td>
            <td>普通管理员</td>
            <td>可以添加用户，删除用户、修改用户</td>
            <td><a href="authority/change.do">权限变更</a></td>
        </tr>
        <tr>
            <td><input type="checkbox" id="roleids"/></td>
            <td>1001</td>
            <td>普通管理员</td>
            <td>可以添加用户，删除用户、修改用户</td>
            <td><a href="authority/change.do">权限变更</a></td>
        </tr>
        <tr>
            <td><input type="checkbox" id="roleids"/></td>
            <td>1001</td>
            <td>普通管理员</td>
            <td>可以添加用户，删除用户、修改用户</td>
            <td><a href="authority/change.do">权限变更</a></td>
        </tr>
        <tr>
            <td><input type="checkbox" id="roleids"/></td>
            <td>1001</td>
            <td>普通管理员</td>
            <td>可以添加用户，删除用户、修改用户</td>
            <td><a href="authority/change.do">权限变更</a></td>
        </tr>
    </table>
</div>
</body>
</html>