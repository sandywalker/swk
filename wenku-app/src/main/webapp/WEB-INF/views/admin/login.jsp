<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="app_title"/></title>
    <link href="${ctx}/resources/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        html,body{
            height:100%;
        }
        body{
            background: url("${ctx}/resources/img/bg/login.jpg");
            background-size: 100%;
            position: relative;
        }

        .login-mask,.login-main{
            position: absolute;
            left:50%;
            top:50%;
            width:500px;
            height:380px;
            margin-left:-250px;
            margin-top:-200px;
            border-radius: 10px;
        }

        .login-mask{
            background: black;
            opacity: .6;
            filter:Alpha(opacity=60);
            z-index: 0;
        }

        .login-main{
            z-index: 99;
        }

        .logo{
            color:#fff;
            font-size:58px;
            text-align: center;
            height:100px;
            padding-top:30px;
        }

        #username,#password,#submit{
            display: block;
            position: absolute;
            background:#555;
            background:rgba(100,100,100,.3);
            left:125px;
            height:45px;
            width:290px;
            color:#fff;
            border:none;
        }

        #username:focus,#password:focus{
            outline: none;
        }

        #iconUser,#iconPass{
            position: absolute;
            left:80px;
            width:45px;
            height:45px;
            background:#555;
            background:rgba(100,100,100,.3);
            color:#eee;
            line-height: 45px;
            text-align: center;
        }


        #username,#iconUser{
            top:140px;
        }

        #password,#iconPass{
            top:200px;
        }

        #submit{
            left:80px;
            top:260px;
            width:335px;
            background:orangered;
            border:none;
        }

        .login-error{
            margin-top:5px;
            color:red;
            text-align: center;
        }
    </style>
</head>

<body>
    <div class="login-main">
        <div class="logo"><i class="glyphicon glyphicon-globe"></i></div>
        <div id="loginForm">
            <div class="login-error">
                ${error}
            </div>
            <div>
                <form id="form" action="${ctx}/admin/login" method="post">
                    <div id="iconUser"><i class="glyphicon glyphicon-user"></i></div>
                    <input type="text" id="username" name="username" placeholder="用户名">

                    <div id="iconPass"><i class="glyphicon glyphicon-lock"></i></div>
                    <input type="password" id="password" name="password" placeholder="密码">
                    <button id="submit" type="submit">登录</button>
                </form>
            </div>
        </div>
    </div>
    <div class="login-mask"></div>
</body>
</html>