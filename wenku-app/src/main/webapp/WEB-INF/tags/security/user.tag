<%@ tag pageEncoding="UTF-8" %>
<%@ tag import="com.wenku.util.SpringContextUtils" %>
<%@ tag import="com.wenku.security.Guard" %>
<%@ tag import="com.wenku.user.model.User" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="property" type="java.lang.String" %>

<%
    Guard guard = SpringContextUtils.getBean(Guard.class);
    if (guard.isLogined(request)){
        User user = guard.getLoginUser(request);
        String prop = property!=null?property:"nickName";
        String text = "";
        if ("loginName".equals(prop)){
            text = user.getLoginName();
        }else if ("nickName".equals(prop)){
            text = user.getNickName();
        } else if ("email".equals(prop)) {
            text = user.getEmail();
        }else if ("phone".equals(prop)) {
            text = user.getPhone();
        }
        %>
            <%=text%>
        <%
    }
%>



