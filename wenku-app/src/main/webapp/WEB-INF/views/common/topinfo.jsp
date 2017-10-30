<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='security' tagdir="/WEB-INF/tags/security"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    欢迎访问搜文库
    <c:choose>
        <c:when test="${logined}">
            &nbsp;&nbsp; 用户： <a href="${ctx}/user/my"><security:user/></a>  &nbsp; | <a href="${ctx}/user/doc/upload">上传文档</a> &nbsp; | &nbsp; <a href="${ctx}/logout">退出</a>&nbsp;&nbsp;&nbsp;
        </c:when>
        <c:otherwise>
            <a href="${ctx}/register">注册</a>  &nbsp; | &nbsp; <a href="${ctx}/login">登录</a> &nbsp;&nbsp;&nbsp;
        </c:otherwise>
    </c:choose>
</header>