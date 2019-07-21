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
    <title>报销单财务审核</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
        <li>系统管理</li>
        <li>审核管理</li>
        <li>财务审核</li>
    </ul>
</div>
<form action="" method="post" id="f1" class="form-inline">
    <div class="row alert alert-info" style="margin: 0px; padding: 5px;">
        <div class="form-group">
            <label>报销人 </label> <input type="text" class="form-control"
                                       name="roleName" placeholder="请输入报销人"/>
            <label>报销时间 </label> <input type="text" class="form-control"
                                        name="roleName" placeholder="请输入报销时间"/>
        </div>
        <input type="submit" value="查询报销单" class="btn btn-success"/>
    </div>
</form>
<div class="row" style="padding: 15px">
    <table class="table">
        <tr>
            <th>报销编号</th>
            <th>报销原因</th>
            <th>报销人</th>
            <th>报销时间</th>
            <th>报销总金额</th>
            <th>审核</th>
        </tr>
        <tr>
            <td>1001</td>
            <td>出差开会</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥5000.0</td>
            <th><a href="expense/loadFinance.do">审核</a></th>
        </tr>
        <tr>
            <td>1001</td>
            <td>出差开会</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥5000.0</td>
            <th><a href="expense/loadFinance.do">审核</a></th>
        </tr>
        <tr>
            <td>1001</td>
            <td>出差开会</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥5000.0</td>
            <th><a href="expense/loadFinance.do">审核</a></th>
        </tr>
        <tr>
            <td>1001</td>
            <td>出差开会</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥5000.0</td>
            <th><a href="expense/loadFinance.do">审核</a></th>
        </tr>
        <tr>
            <td>1001</td>
            <td>出差开会</td>
            <td>章山</td>
            <td>2018-04-04</td>
            <td>￥5000.0</td>
            <th><a href="expense/loadFinance.do">审核</a></th>
        </tr>
    </table>
</div>
</body>
</html>
