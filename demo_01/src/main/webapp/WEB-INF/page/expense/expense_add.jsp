<%--
  Created by IntelliJ IDEA.
  User: BoVane
  Date: 2019/6/23
  Time: 18:01
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
    <link rel="stylesheet" href="res/css/bootstrap.css">
    <script type="text/javascript" src="res/js/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function(){
//给选择按钮添加事件
            $("#selected").click(function(){ //#selected是"选择"按钮的id值
//获取table
                var tab1=$("#tab1"); //#tab1是表格的id值
                var ids=$("input[id=ids]:checked");
                ids.each(function () {
                    var td=$(this).parent().parent().find("td");//checkbox的父节点是td,再上一级是tr
//alert($(this).parent().parent().find("td").length);
                    tab1.append("<tr><td>"+td[2].innerHTML+"</td><td>"+td[3].innerHTML+"</td><td><input type='text' name='expenseDetailAmount'></td></tr>");
//往表格tab1添加数据,td[2]是费用编号，td[3]是费用名称，td[4]是费用描述，td[1]是勾选框
                });
// 隐藏模态框
                $("#myModal").modal('hide');
            });
        });
    </script>
    <title>报销单</title>
</head>
<body>
<div style="padding: 0px; margin: 0px;">
    <ul class="breadcrumb" style="padding: 0px; padding-left:
20px;">
        <li>报销管理</li>
        <li>报销单</li>
    </ul>
</div>
<form action="" method="post" class="form-horizontal">
    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px">报销
        单基本信息</h5>
    <div class="row">
        <div class="col-sm-7">
            <div class="form-group">
                <label class="col-sm-3 control-label">报销原因</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name=""
                           placeholder=" 请输入报销原因 " value="">
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="form-group">
                <label class="col-sm-3 control-label">报销时间</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name=""
                           placeholder=" 请输入报销时间 " />
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="form-group">
                <label class="col-sm-3 control-label">报销总金额</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name=""
                           placeholder=" 请输入报销总金额 " />
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="form-group">
                <label class="col-sm-3 control-label">报销详情</label>
                <div class="col-sm-9">
<textarea rows="" cols="" class="form-control"
          placeholder=" 请输入报销详情 "></textarea>
                </div>
            </div>
        </div>
    </div>
    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px">报销
        单明细
        <input type="button" class="btn btn-primary btn-xs" value=" 选择费
用明细 "
               data-toggle="modal" data-target="#myModal" />
    </h5>
    <table class="table" id="tab1">
        <tr>
            <th>费用名称</th>
            <th>费用说明</th>
            <th>具体金额</th>
        </tr>
    </table>
    <div class="row">
        <div class="col-sm-7" align="center">
            <input type="submit" value=" 提交报销单 " class="btn btn-
success" />
        </div>
    </div>
</form>
<!-- Modal BootStrap模态框的写法，具体写法看BootStrap文档-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-
                        dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">费用明细
                </h4>
            </div>
            <div class="modal-body">
                <table class="table" id="tab2">
                    <tr>
                        <th></th>
                        <th>费用编号</th>
                        <th>费用名称</th>
                        <th>费用描述</th>
                    </tr>
                    <tr>
                        <td><input type="checkbox" checked="checked"
                                   id="ids" /></td>
                        <td>1001</td>
                        <td>住宿费</td>
                        <td>住宿费每天最多报销600元</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" id="ids" /></td>
                        <td>1002</td>
                        <td>加油费</td>
                        <td>加油费每天最多能报销1000元</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" id="ids" /></td>
                        <td>1003</td>
                        <td>差旅费</td>
                        <td>包括高铁费、飞机费</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" id="ids"/></td>
                        <td>1004</td>
                        <td>会议费</td>
                        <td>包括会议注册费、资料费</td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" id="selected" class="btn btn-
primary">选择</button>
                <button type="button" class="btn btn-default" data-
                        dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
