<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 我的订单" id="mySpace" classes="my-orders">
    <jsp:attribute name="main">
        <jsp:include page="../common/searchbar.jsp"/>
        <div class="container my-container">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <div class="profile">
                        <img src="${ctx}/resources/img/icon/user.jpg" alt="" class="avatar">
                        <p class="name">测试用户</p>
                    </div>
                    <div class="upload-button">
                        <a href="${ctx}/user/upload" target="_blank" class="btn btn-info btn-block"> <i class="glyphicon glyphicon-upload"></i> 上传文档</a>
                    </div>

                    <br>
                    <jsp:include page="side-menu.jsp"/>
                </div>
                <div class="col-md-10 main">
                    <br>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="#">  全部订单</a></li>
                        <li role="presentation" ><a href="#">  已支付</a></li>
                        <li role="presentation" ><a href="#">  未支付</a></li>
                    </ul>
                    <br>

                    <div class="well text-center">
                        暂时没有订单哦！
                    </div>

                    <%--<table class="table">--%>
                        <%--<thead>--%>
                            <%--<tr>--%>
                                <%--<th>商品名称</th>--%>
                                <%--<th>订单金额</th>--%>
                                <%--<th>下单时间</th>--%>
                                <%--<th>订单状态</th>--%>
                                <%--<th>操作</th>--%>
                            <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<i class="ext ext-doc"></i>--%>
                                    <%--<a href="/doc/view/1"> 高中语文知识点笔记-part4-山...</a>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--3.00 ¥--%>
                                <%--</td>--%>
                                <%--<td>2016-6-1</td>--%>
                                <%--<td>已支付</td>--%>
                                <%--<td>&nbsp;</td>--%>
                            <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
</layout:default>