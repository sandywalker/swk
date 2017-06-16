<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="title" type="java.lang.String" description="页面的标题" required="true" %>
<%@ attribute name="classes" type="java.lang.String" description="css class" %>
<%@ attribute name="id" type="java.lang.String" description="id" %>


<%@ attribute name="main" fragment="true" description="主体信息，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>
<%@ attribute name="css" fragment="true" description="需要引入的额外的css信息或自定义的css，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>
<%@ attribute name="js" fragment="true" description="需要引入的额外的js信息或自定义的js，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 无响应式布局的CSS -->
    <!--[if lt IE 9]>
    <script src="${ctx}/resources/js/html5.js"></script>
    <![endif]-->
    <c:choose>
        <c:when test="${mode eq 'dev'}">
            <link href="${ctx}/resources/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
            <link href="${ctx}/resources/lib/sweetalert/css/sweetalert.css" rel="stylesheet" type="text/css">
            <link href="${ctx}/resources/css/admin.css" rel="stylesheet" type="text/css">
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
            <link rel="stylesheet" href="http://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" type="text/css">
            <link href="${ctx}/resources/css/admin.css" rel="stylesheet" type="text/css">
        </c:otherwise>
    </c:choose>

    <!--[if lt IE 9]>
    <script src="${ctx}/resources/js/respond.min.js"></script>
    <![endif]-->

    <jsp:invoke fragment="css"/>
    <script>ctx = '${ctx}';</script>
    <title> ${title}- <spring:message code="app_title"/></title>
</head>
<body id="${id}" class="${classes}">

    <jsp:invoke fragment="main"/>



    <c:choose>
        <c:when test="${mode eq 'dev'}">
            <script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.1.11.2.min.js"></script>
            <script type="text/javascript" src="${ctx}/resources/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="${ctx}/resources/lib/underscore/underscore.js"></script>
            <script type="text/javascript" src="${ctx}/resources/lib/handlebars/handlebars.min.js"></script>
            <script type="text/javascript" src="${ctx}/resources/lib/sweetalert/js/sweetalert.min.js"></script>
            <script type="text/javascript" src="${ctx}/resources/js/wenku.js"></script>
            <script type="text/javascript" src="${ctx}/resources/js/app.js"></script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
            <script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="http://cdn.bootcss.com/underscore.js/1.8.3/underscore-min.js"></script>
            <script type="text/javascript" src="http://cdn.bootcss.com/handlebars.js/4.0.10/handlebars.min.js"></script>
            <script type="text/javascript" src="${ctx}/resources/js/app.min.js"></script>
        </c:otherwise>
    </c:choose>

    <jsp:invoke fragment="js"/>


</body>
</html>