<%--
  Created by IntelliJ IDEA.
  User: BoVane
  Date: 2019/6/23
  Time: 17:22
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
    <title>Insert title here</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
</head>
<body>
<div style="padding:0px; margin:0px;">
    <ul class="breadcrumb" style=" padding:0px; padding-left:20px;" >
        <li ><a href="#">首页</a></li>
        <li>工作台</li>
    </ul>
</div>

<div class="row " style="padding:1px; margin:0px;"  >
    <div class="col-sm-8">
        <div class="panel panel-default" >
            <div class="panel-heading" style=" padding:3px;height:30px;"  >
                <span class="glyphicon glyphicon-refresh"></span>报销报表
            </div>
            <div class="panel-body">
                <img src="res/img/03.png" width="100%" />
            </div>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading"  style=" padding:3px;height:30px;"  >
                <span class="glyphicon glyphicon-refresh"></span>关键试图
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>视图</th>
                    <th>模块</th>
                    <th>数量</th>
                </tr>

                <tr>
                    <td>2017-10-10</td>
                    <td>李明</td>
                    <td>5</th>
                </tr><tr>
                <td>2017-10-11</td>
                <td>王敏</td>
                <td>3</th>
            </tr><tr>
                <td>2016-10-11</td>
                <td>胡军</td>
                <td>13</th>
            </tr>
            </table>
        </div>
    </div>

    <div class="col-sm-8">
        <div class="panel panel-default" >
            <div class="panel-heading" style=" padding:3px;height:30px;"  >
                <span class="glyphicon glyphicon-refresh"></span>报销报表
            </div>
            <div class="panel-body">
                <img src="res/img/03.png" width="100%" />
            </div>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading"  style=" padding:3px;height:30px;"  >
                <span class="glyphicon glyphicon-refresh"></span>关键试图
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>视图</th>
                    <th>模块</th>
                    <th>数量</th>
                </tr>

                <tr>
                    <td>2017-11-01</td>
                    <td>刘宏</td>
                    <td>5</th>
                </tr><tr>
                <td>2017-11-11</td>
                <td>田辉</td>
                <td>3</th>
            </tr><tr>
                <td>2017-11-18</td>
                <td>封毅</td>
                <td>13</th>
            </tr>
            </table>
        </div>
    </div>

</div>


</body>
</html>
