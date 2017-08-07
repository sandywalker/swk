<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 账号设置" id="mySpace" classes="my-setting">
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

                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
</layout:default>