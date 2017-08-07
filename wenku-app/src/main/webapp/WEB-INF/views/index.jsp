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

    <style>
        html,body{
            height:100%;
        }
    </style>

    <title> 找文档，就上搜文酷！ </title>


</head>
<body id="home">
    <div id="homeHead" class="header">
        欢迎访问搜文库！ <a href="/register">注册</a>  &nbsp; | &nbsp; <a href="login">登录</a> &nbsp;&nbsp;&nbsp;
    </div>
    <div id="homeC">
        <div id="homelg">
            <img src="${ctx}/resources/img/logo.png" alt="">
        </div>
        <div id="homeSh">
            <form action="/search">
                <input type="text" name="query" id="homeQ"><input type="submit" id="homeSm" value="搜一下">
            </form>
        </div>
        <div id="homeRec">
            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/1.jpg" alt="">
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 2015 GRE复习指导----高频机经阅读...</a>
                    </li>
                    <li>
                        <i class="ext ext-ppt"></i>
                        <a href="/doc/view/1"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 2015国家公务员考试行测模拟试卷</a>
                    </li>
                </ul>
            </div>
            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/1.jpg" alt="" >
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 2015 GRE复习指导----高频机经阅读...</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 高中语文知识点笔记-part4-山...</a>
                    </li>
                    <li>
                        <i class="ext ext-pdf"></i>
                        <a href="/doc/view/1"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                </ul>
            </div>

            <div class="col">
                <div class="cover">
                    <img src="${ctx}/resources/img/demo/1.jpg" alt="">
                </div>
                <ul>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 雅思写作备考指导-高分写作技巧提高篇</a>
                    </li>
                    <li>
                        <i class="ext ext-doc"></i>
                        <a href="/doc/view/1"> 2015国家公务员考试行测模拟试卷</a>
                    </li>
                    <li>
                        <i class="ext ext-xls"></i>
                        <a href="/doc/view/1"> 雅思写作备考指导-高分写作技巧</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>


    <div id="homeFoot">
        <nav>
            <a href="http://www.sowenku.com/"
                data-linkid="1" target="_blank">意见反馈</a><span> | </span><a
                href="http://www.sowenku.com"
                data-linkid="2" target="_blank">使用帮助</a><span> | </span><a
                href="http://www.sowenku.com"
                data-linkid="3" target="_blank">免责声明</a><span> | </span><a
                href="http://www.sowenku.com/"
                data-linkid="4" target="_blank">站长平台</a><span> | </span><a
                href="http://www.sowenku.com"
                data-linkid="5" target="_blank">推广合作</a>
        </nav>
        <p>©2017 sowenku.com&nbsp;&nbsp;文酷科技旗下搜索服务&nbsp;&nbsp;京ICP备XXXXXX号-19&nbsp;&nbsp;<a target="_blank"
                                                                                            href="http://www.sowenku.com/">京公网安备11000002000022号</a>
        </p></div>

</body>
</html>


