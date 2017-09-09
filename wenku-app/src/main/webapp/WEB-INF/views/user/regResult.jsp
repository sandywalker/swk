<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 注册">
    <jsp:attribute name="main">
        <div class="well">
            <p>${result.success}</p>
            <p>${result.message}</p>
        </div>
    </jsp:attribute>
</layout:default>