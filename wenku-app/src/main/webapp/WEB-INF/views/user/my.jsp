<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 我的空间" id="mySpace" classes="my-space">
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
                        <li role="presentation" class="active"><a href="#"> <i class="glyphicon glyphicon-shopping-cart"></i> 我购买的</a></li>
                        <li role="presentation" ><a href="#"> <i class="glyphicon glyphicon-upload"></i> 我上传的</a></li>
                    </ul>
                    <br>

                    <table class="table">
                        <thead>
                            <tr>
                                <th>名称</th>
                                <th>来源</th>
                                <th>创建日期</th>
                                <th>购买日期</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <i class="ext ext-doc"></i>
                                    <a href="/doc/view/1"> 高中语文知识点笔记-part4-山...</a>
                                </td>
                                <td>
                                    购买
                                </td>
                                <td>2016-10-15</td>
                                <td>2017-6-1</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
</layout:default>