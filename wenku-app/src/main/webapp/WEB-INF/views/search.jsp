<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷">
    <jsp:attribute name="main">
            <jsp:include page="common/searchbar.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <br>
                        <p> 关键词 <span class="text-red"> "Java" </span> 共搜索到 2100 个文档！</p>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="filters">
                            <div class="filter">
                                <div class="key">文件格式</div>
                                <div class="value">
                                    <ul>
                                        <li class="selected"><a href="#">全部</a></li>
                                        <li><a href="#"><i class="ext ext-doc"></i> doc</a></li>
                                        <li><a href="#"><i class="ext ext-pdf"></i> pdf</a></li>
                                        <li><a href="#"><i class="ext ext-xls"></i> xls</a></li>
                                        <li><a href="#"><i class="ext ext-ppt"></i> ppt</a></li>
                                    </ul>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="filter">
                                <div class="key">页数</div>
                                <div class="value">
                                    <ul>
                                        <li class="selected"><a href="#">全部</a></li>
                                        <li><a href="#"> 1-8 页</a></li>
                                        <li><a href="#"> 9-100 页</a></li>
                                        <li><a href="#"> 100 页以上</a></li>
                                    </ul>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="topbar">
                            <ul class="pager">
                                <li class="disabled"><a href="#"><</a></li>
                                <li><a href="#">></a></li>
                            </ul>
                            <div class="infos">共找到 2100 个文档 &nbsp;&nbsp; <strong> 1 / 20 </strong> </div>
                            <div class="sorts">
                                <a href="#" class="sort">综合排序</a>
                                <a href="#" class="sort">热度</a>
                                <a href="#" class="sort">时间</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="search-results">
                            <ul>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc1.jpg" alt="">
                                            <div class="caption"> 110 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1"> <span class="high">Java</span>  7从入门到精通</a></h2>
                                            <p>Java学习笔记在JavaWorld技术论坛,为了方便更多的人学习Java,本书以原有的笔记文件为基础,加入新的主题以及更多的范例,以图书的形式呈现于世,为喜爱Java的读者提供了一本很好的学习教材。内容有Java JDK6中加强了jav...</p>

                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc3.jpg" alt="">
                                            <div class="caption"> 21 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1"><span class="high">Java</span>  学习笔记下</a></h2>

                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc2.jpg" alt="">
                                            <div class="caption"> 82 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1"><span class="high">Java</span>  编码标准及规范手册</a> </h2>

                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc4.jpg" alt="">
                                            <div class="caption"> 1029 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1">《<span class="high">Java</span>  编程思想 第四版》 英文版</a></h2>
                                            <p>《java编程思想 第四版》 英文版_63...</p>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc5.jpg" alt="">
                                            <div class="caption"> 23 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1"><span class="high">Java</span>  Java经典入门教程</a> </h2>
                                            <p><《java编程思想 第四版》 英文版_63...</p>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="doc-main">
                                        <div class="cover">
                                            <img src="${ctx}/resources/img/demo/doc6.jpg" alt="">
                                            <div class="caption"> 23 页</div>
                                        </div>
                                        <div class="info">
                                            <h2> <a class="pull-right link-fav" href="#"> <i class="glyphicon glyphicon-heart"></i> 收藏</a>
                                                <a href="/doc/view/1"><span class="high">Java</span>  程序设计试读</a> </h2>
                                            <p><《java编程思想 第四版》 英文版_63...</p>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="info-footer">
                                            贡献者：<a href="#"> <i class="glyphicon glyphicon-user"></i> 文库大人</a> &nbsp;&nbsp; <i class="glyphicon glyphicon-time"></i> <span class="create-time">2016-10-12 10:21</span> &nbsp;&nbsp; 格式： <i class="ext ext-pdf"></i> pdf
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <nav aria-label="Page navigation">
                                <ul class="pagination pull-right">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; 上一页</span>
                                        </a>
                                    </li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true"> 下一页 &raquo; </span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                                <br><br>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
    </jsp:attribute>
</layout:default>