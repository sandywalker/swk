<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<div class="list-group my-menu">
    <a href="${ctx}/user/my" class="list-group-item menu-doc"> <i class="glyphicon glyphicon-book"></i> 我的文档</a>
    <a href="${ctx}/user/orders" class="list-group-item menu-orders"> <i class="glyphicon glyphicon-shopping-cart"></i> 我的订单</a>
    <a href="${ctx}/user/setting" class="list-group-item menu-setting"> <i class="glyphicon glyphicon-user"></i> 账号设置</a>
</div>