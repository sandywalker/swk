<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="app_title"/> 后台</title>
        <link href="${ctx}/resources/lib/hoe/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/resources/lib/hoe/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/resources/lib/hoe/css/hoe.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/resources/lib/hoe/css/hoe-theme-color.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/resources/lib/hoe/css/extra.css" rel="stylesheet" type="text/css">

        <style>

            #frameContent{
                margin:0;
                padding:0;
                border:none;
                width:100%;
                height:100%;
            }

            #headerTitle{
                text-align:center;
                font-size: 16px;
                font-weight:bold;
                color:white;
                /*border-bottom:1px solid #ddd;*/
            }

            .hiding{
                display: none;
            }



            .logout:link,.logout:visited{
                color:#eee;
            }

            .hoe-right-header a{
                color:#eee;
            }

            .hoe-right-header a.logout i{
                color:deepskyblue;
            }

            .hoe-right-header .badge{
                background: #e00;
            }


            .hoe-right-header .dropdown-menu li{
                display: block;
                float:none;
            }


        </style>


        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->


    </head>
    <body hoe-navigation-type="vertical" hoe-nav-placement="left" theme-layout="wide-layout" theme-bg="bg9">
    <div id="hoeapp-wrapper" class="hoe-hide-lpanel" hoe-device-type="desktop">
        <header id="hoe-header" hoe-lpanel-effect="shrink" hoe-color-type="logo-bg9">
            <div class="hoe-left-header">
                <a href="#">
                    <i class="fa fa-university"></i>
                    <span><spring:message code="app_title"/></span>
                </a>
            </div>
            <div class="hoe-right-header" hoe-position-type="relative" hoe-color-type="header-bg9" >
                <div class="pull-right">
                    <a class="logout btn" href="${ctx}/admin/logout">
                        <span class="text-danger"><i class="glyphicon glyphicon-off"></i></span> 退出系统 &nbsp;&nbsp;
                    </a>
                </div>
                <span class="hoe-sidebar-toggle"><a href="#"></a></span>
                <div id="headerTitle"></div>
            </div>

        </header>
        <div class="message-center hiding" id="messageCenter"></div>

        <div id="hoeapp-container" hoe-color-type="lpanel-bg9" hoe-lpanel-effect="shrink" >
            <aside id="hoe-left-panel" hoe-position-type="absolute">
                <div class="profile-box">
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="img-circle" src="${ctx}/resources/img/icon/user.jpg">
                        </a>
                        <div class="media-body">
                            <h5 class="media-heading"> <span id="user" >Admin</span></h5>
                            <small>
                                &nbsp;
                            </small>
                        </div>
                    </div>
                </div>
                <jsp:include page="menu.jsp"/>
            </aside>
            <section id="main-content">
                <iframe src="" frameborder="0" id="frameContent"></iframe>
            </section>
        </div>
    </div>
    <script>window.ctx = '${ctx}';</script>
    <script type="text/javascript" src="${ctx}/resources/lib/hoe/js/1.11.2.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/hoe/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/hoe/js/hoe.js"></script>

    <script type="text/javascript">
        (function(){

            var $main = $('#main-content');
            var $frame = $('#frameContent');
            var $header = $('#hoe-header');
            var $fmenus = $('.function-menu');
            var $headTitle = $('#headerTitle');


            $(window).resize(function() {
                refreshUI();
            });

            $fmenus.on('click',function(e){
                e.preventDefault();
                var $this = $(this);
                var url = $this.data('url');
                if (url.indexOf('/')==0){
                    url= window.ctx + url;
                }
                $frame.attr("src",url);
                $headTitle.text($this.data('title'));
            });

            init();

            function init(){
                refreshUI();
                $frame.attr("src",'${ctx}/admin/dashboard');
            }

            function refreshUI(){
                $main.height(document.documentElement.clientHeight-$header.height()-2);
            }





        })();
    </script>

    </body>

</html>