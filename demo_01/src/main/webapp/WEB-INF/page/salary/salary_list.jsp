<%--
  Created by IntelliJ IDEA.
  User: BoVane
  Date: 2019/6/23
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>薪资发放查询<</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left:
20px;">
        <li>薪资管理</li>
        <li>薪资发放查询</li>
    </ul>
</div>
<form action="" method="post" id="f1" class="form-inline">
    <div class="row alert alert-info" style="margin: 0px; padding:
5px;">
        <div class="form-group">
            <label>领取人 </label>
            <input type="text" class="form-control" name="" placeholder=" 请
输入领取人 "/>
            <label>发放时间</label>
            <input type="text" class="form-control" name="" placeholder="
请输入发放时间 "/>
        </div>
        <input type="submit" value=" 查询 " class="btn btn-success"/>
        <input type="button" value=" 导入 Excel" class="btn btn-
success"/>
    </div>
</form>
<div class="row" style="padding: 15px">
    <table class="table">
        <tr>
            <th>编号</th>
            <th>领取人</th>
            <th>发放时间</th>
            <th>发放金额</th>
        </tr>
        <tr>
            <td>1001</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥10000</td>
        </tr>
        <tr>
            <td>1002</td>
            <td>王建新</td>
            <td>2018-04-04</td>
            <td>￥18000</td>
        </tr>
        <tr>
            <td>1003</td>
            <td>谭红</td>
            <td>2018-04-04</td>
            <td>￥15000</td>
        </tr>
        <tr>
            <td>1003</td>
            <td>刘明</td>
            <td>2018-04-04</td>
            <td>￥20000</td>
        </tr>
    </table>
</div>
</body>
</html>