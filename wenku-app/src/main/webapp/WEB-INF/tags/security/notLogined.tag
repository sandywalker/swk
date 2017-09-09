<%@ tag pageEncoding="UTF-8" %>
<%@ tag import="com.wenku.util.SpringContextUtils" %>
<%@ tag import="com.wenku.security.Guard" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
    Guard guard = SpringContextUtils.getBean(Guard.class);
    if (!guard.isLogined(request)){
%>
    <jsp:doBody/>
<%
    }
%>



