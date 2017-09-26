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
                        <a href="${ctx}/user/doc/upload" target="_blank" class="btn btn-info btn-block"> <i class="glyphicon glyphicon-upload"></i> 上传文档</a>
                    </div>

                    <br>
                   <jsp:include page="side-menu.jsp"/>
                </div>
                <div class="col-md-10 main">

                    <br>
                    <ul class="nav nav-tabs">
                        <li role="presentation" ><a href="${ctx}/user/my"> <i class="glyphicon glyphicon-shopping-cart"></i> 我购买的</a></li>
                        <li role="presentation" class="active"><a href="${ctx}/user/doc/myUpload/wait"> <i class="glyphicon glyphicon-upload"></i> 我上传的</a></li>
                    </ul>
                    <br>

                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation" <c:if test="${status eq 'wait'}"> class="active"</c:if> > <a href="${ctx}/user/doc/myUpload/wait"> <i class="glyphicon glyphicon-time"></i>  正在审核中</a></li>
                        <li role="presentation" <c:if test="${status eq 'ok'}"> class="active"</c:if>><a href="${ctx}/user/doc/myUpload/ok"> <i class="glyphicon glyphicon-ok"></i>  审核通过 </a></li>
                        <li role="presentation" <c:if test="${status eq 'failed'}"> class="active"</c:if>><a href="${ctx}/user/doc/myUpload/failed"> <i class="glyphicon glyphicon-remove"></i>  审核不通过 </a></li>
                    </ul>

                    <br>

                    <c:choose>
                        <c:when test="${fn:length(docs)>0}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>价格 （¥）</th>
                                    <th>上传日期</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${docs}" var="doc">
                                        <tr>
                                            <td>
                                                <i class="ext ext-doc"></i>
                                                <a href="#">${doc.title1}</a>
                                            </td>
                                            <td> <fmt:formatNumber value="${doc.price}" maxFractionDigits="2"/> </td>
                                            <td> <fmt:formatDate value="${doc.createTime}" pattern="yyyy-MM-dd"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div class="well text-center">
                                暂时没有符合条件的文档！
                            </div>
                        </c:otherwise>
                    </c:choose>



                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
</layout:default>