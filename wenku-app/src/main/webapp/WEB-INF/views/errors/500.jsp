<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isErrorPage="true" %>
<%@ include file="../../taglibs.jsp" %>
<c:set var="ctx" value="<%=request.getContextPath()%>" scope="request" />
<c:set var="host" value="${ctx}" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>搜文库 500</title>
    <style>
        .box{
            border:1px solid #ddd;
            width:60%;
            margin:100px auto 20px auto;
            padding:20px 0;
            background: white;
            text-align: center;
        }

        .text-sm{
            font-size: 12px;
            color:#999;
        }

        #errMsg{
            width:60%;
            margin:0 auto;
            display: none;
        }

        .egg:hover{
            text-decoration: none;
        }
    </style>


</head>
<body>

    <div class="box">
        <p class="text-center"><span id="errHint">网络出现异常，请稍后重试！</span> <a href="javascript:history.back();" >返回上一个页面</a></p>

    </div>
    <div id="errMsg">
        <p><%=exception.getMessage()%></p>
        <p class="text-sm">
            <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
                ${trace} <br>
            </c:forEach>
        </p>
    </div>
    <script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.1.11.2.min.js"></script>
    <script type="text/javascript">
    (function () {
        $('.egg').on('click',function(e){
            e.preventDefault();
            $('#errMsg').css({'display':'block'});
        });
        $('#errHint').on('dblclick',function(e){
            e.preventDefault();
            $('#errMsg').toggle();
        })
    })();
    </script>

</body>
</html>
