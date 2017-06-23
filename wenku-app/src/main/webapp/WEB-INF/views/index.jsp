<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <link href="${ctx}/resources/css/app.css" rel="stylesheet" type="text/css">
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href="${ctx}/resources/css/app.min.css" type="text/css">
        </c:otherwise>
    </c:choose>

    <!--[if lt IE 9]>
    <script src="${ctx}/resources/js/respond.min.js"></script>
    <![endif]-->

    <title> 找文档，就上搜文酷！ </title>


</head>
<body id="home">
    <div id="homeC">
        <div id="homelg">
            <img src="${ctx}/resources/img/logo.png" alt="">
        </div>
        <div id="homeSh">
            <input type="text" id="homeQ"><input type="submit" id="homeSm" value="搜一下">
        </div>
        <div id="homeRec">
            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/1.jpg" alt="">
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 2015 GRE复习指导----高频机经阅读...</a>
                    </li>
                    <li>
                        <i class="ext ext-ppt"></i>
                        <a href="#"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 2015国家公务员考试行测模拟试卷</a>
                    </li>
                </ul>
            </div>
            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/2.png" alt="" >
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 2015 GRE复习指导----高频机经阅读...</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 高中语文知识点笔记-part4-山...</a>
                    </li>
                    <li>
                        <i class="ext ext-pdf"></i>
                        <a href="#"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                </ul>
            </div>

            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/4.png" alt="">
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 雅思写作备考指导-高分写作技巧提高篇</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="#"> 2015国家公务员考试行测模拟试卷</a>
                    </li>
                    <li>
                        <i class="ext ext-xls"></i>
                        <a href="#"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</body>
</html>


