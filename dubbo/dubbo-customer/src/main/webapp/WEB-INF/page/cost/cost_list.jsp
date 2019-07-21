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
    <title>费用查询</title>
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
    <script type="text/javascript">
        <!--给修改按钮添加事件-->
        $(function () {
            $("#update").click(function () {
                var costids = $("input[id=costids]:checked");
                if (costids.length == 1) { // 若只勾选了一个
//提交表单
                    var f1 = $("#f1"); //f1是表单form的id值
//$()可以是$(expresion)，即css选择器、Xpath或html元素，也就是通过上述表达式来匹配目标元素
//是Juery的自定义函数的写法
                    f1.attr("action", "cost/loadupdate.do");//跳转至loadupdate.do
                    f1.submit();
                } else {
                    alert("必须选中一个且只能选中一个费用进行修改");
                }
            });
//给删除按钮绑定事件
            $("#delete").click(function () {
                if (confirm("你确定要删除该费用吗？")) {
//提交表单
                    var f1 = $("#f1"); //f1是表单form的id值
                    f1.attr("action", "cost/delete.do");
                    f1.submit();
                }
            });
        });

        //checkboxfun实现全选和全不选
        function checkboxfun(v) {
            var costids = $("input[id=costids]");
//遍历所有的checkbox
            costids.each(function (i) {
//设置checked的值
                this.checked = v.checked;//v.checked表示第一个checkbox的选择状态
//this.checked表示后面每一个checkbox的选择状态与第一个checkbox的选择状态一样
            });
        }
    </script>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
        <li>系统管理</li>
        <li>费用管理</li>
        <li>查询费用</li>
    </ul>
</div>
<form action="" method="post" id="f1" class="form-inline">
    <div class="row alert alert-info" style="margin: 0px; padding: 5px;">
        <div class="form-group">
            <label>费用名称 </label> <input type="text" class="form-control"
                                        name="costName" placeholder="请输入费用名称"/>
        </div>
        <input type="submit" value="查询费用" class="btn btn-success"/>
        <a href="cost/loadadd.do" class="btn btn-info">添加费用</a>
        <input type="button" id="update" value="修改费用" class="btn btn-primary"/>
        <input type="button" id="delete" value="删除费用" class="btn btn-danger"/>
    </div>
</form>
<div class="row" style="padding: 15px">
    <table class="table">
        <tr>
            <th><input type="checkbox" onclick="checkboxfun(this)"/></th>
            <th>费用编号</th>
            <th>费用名称</th>
            <th>费用描述</th>
        </tr>
        <tr>
            <td><input type="checkbox" checked="checked" id="costids"/></td>
            <td>1001</td>
            <td>住宿费</td>
            <td>住宿费每晚最多报销500元</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="costids"/></td>
            <td>1002</td>
            <td>电话费</td>
            <td>电话费每天最多能报销200元</td>
        </tr>
        <tr>
        <tr>
            <td><input type="checkbox" checked="checked" id="costids"/></td>
            <td>1003</td>
            <td>差旅费</td>
            <td>包括高铁费、飞机费</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="costids"/></td>
            <td>1004</td>
            <td>会议费</td>
            <td>包括会议注册费、资料费</td>
        </tr>
    </table>
</div>
</body>
</html>