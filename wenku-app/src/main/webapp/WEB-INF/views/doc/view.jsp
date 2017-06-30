<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 查看文档">
    <jsp:attribute name="css">
        <style>
        </style>
    </jsp:attribute>
    <jsp:attribute name="main">
            <jsp:include page="../common/searchbar.jsp"/>
        <section class="container">
            <div class="row">
                <div class="col-md-12">
                    <div id="viewerInfo">
                        <h4> Java  7从入门到精通 </h4>
                        <p class="action-info">
                            <!-- JiaThis Button BEGIN -->
                            <!-- JiaThis Button BEGIN -->
                        <span class="jiathis_style pull-right">
                            <a class="jiathis_button_qzone"></a>
                            <a class="jiathis_button_tsina"></a>
                            <a class="jiathis_button_tqq"></a>
                            <a class="jiathis_button_weixin"></a>
                        </span>
                        <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                        <!-- JiaThis Button END -->

                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a>&nbsp;&nbsp;
                             <i class="glyphicon glyphicon-time"></i>
                            <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp;
                            <i class="ext ext-pdf"></i> pdf &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-eye-open"></i> 121 &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-heart-empty"></i> 53 &nbsp;&nbsp;
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <section data-spy="affix" data-offset-top="200" id="viewerTools">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="toolbar">
                            <a href="#" class="btn btn-success pull-right"> <i class="glyphicon glyphicon-save"></i> 下载文档 </a>
                            <a href="#" class="hand btn-tool active"><i class="glyphicon glyphicon-hand-up"></i></a>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-text-size"></i></a>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-fullscreen"></i></a>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-zoom-in"></i></a>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-zoom-out"></i></a>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-menu-left"></i></a>
                            <span class="doc-page"><input type="text" class="current-page" value="1"> / 21 </span>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-menu-right"></i></a>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <section class="container">
            <div class="row">
                <div class="col-md-9">
                    <div id="viewer">

                    </div>
                </div>
                <div class="col-md-3">
                    <div id="viewerSide" data-spy="affix" data-offset-top="245">
                        <h5> <i class="glyphicon glyphicon-star text-orange"></i> 相关文档推荐</h5>
                        <ul>
                            <li> <i class="ext ext-doc"></i> <a href="#">芳名斋预测实例精解</a></li>
                            <li> <i class="ext ext-doc"></i> <a href="#">Java实例</a></li>
                            <li> <i class="ext ext-pdf"></i> <a href="#">Java开发实例大全（基础卷）</a> </li>
                            <li> <i class="ext ext-pdf"></i> <a href="#"> java网络编程精解</a> </li>
                            <li> <i class="ext ext-doc"></i> <a href="#">工作流与JBPM开发实例精解</a> </li>
                            <li> <i class="ext ext-doc"></i> <a href="#">Jnx5数控编程精解与实例</a> </li>
                            <li> <i class="ext ext-pdf"></i> <a href="#">Java开发实例大全（基础卷）</a> </li>
                            <li> <i class="ext ext-pdf"></i> <a href="#"> 一掌经实例 精解</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <br><br><br><br>

    </jsp:attribute>
</layout:default>