<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 查看文档">
    <jsp:attribute name="css">
        <style>
            .page-wrapper{
                width:100%;
                margin:20px auto;
                border:1px solid #ddd;
                overflow: hidden;
                padding:0;
            }
            iframe{
                width:100%;
                display: block;
                margin:0;
                border:none;
                overflow: hidden;
                padding:0;
            }

            .loading{
                position:fixed;
                top:50%;
                left:40%;
            }

            #viewer{
                border:none;
            }

            .floatSide{
                position: fixed;
                top:270px;
                right:20px;
                width:260px;
                background: #fff;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="main">
            <jsp:include page="../common/searchbar.jsp"/>
        <section class="container">
            <div class="row">
                <div class="col-md-12">
                    <div id="viewerInfo">
                        <h4> ${doc.title1} </h4>
                        <p class="action-info">
                            <!-- JiaThis Button BEGIN -->
                            <!-- JiaThis Button BEGIN -->
                        <span class="jiathis_style pull-right">
                            <a class="jiathis_button_qzone"></a>
                            <a class="jiathis_button_tsina"></a>
                            <a class="jiathis_button_tqq"></a>
                            <a class="jiathis_button_weixin"></a>
                        </span>
                        <!-- JiaThis Button END -->

                            文档贡献者：
                            <a href="#"> <i class="glyphicon glyphicon-user"></i>
                                <c:if test="${not empty author}">
                                    ${author.nickName}
                                </c:if>
                            </a>&nbsp;&nbsp;
                             <i class="glyphicon glyphicon-time"></i>
                            <span class="create-time"><fmt:formatDate value="${doc.createTime}" pattern="yyyy-MM-dd hh:mm"/></span></span> &nbsp;&nbsp;

                            <i class="ext ext-${doc.fileType}"></i> ${doc.fileType} &nbsp;&nbsp;
                            <span>共 ${doc.pageCount} 页</span>&nbsp;&nbsp;
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
                            <a href="#" class="btn btn-success pull-right" > <i class="glyphicon glyphicon-save"></i> 下载文档 </a>
                            <%--<a href="#" class="hand btn-tool active"><i class="glyphicon glyphicon-hand-up"></i></a>--%>
                            <%--<a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-text-size"></i></a>--%>
                            <%--<a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-fullscreen"></i></a>--%>
                            <%--<a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-zoom-in"></i></a>--%>
                            <%--<a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-zoom-out"></i></a>--%>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-menu-left" id="btnPrev"></i></a>
                            <span class="doc-page"><input type="text" class="current-page" id="currentPage" value="1"> / <span id="totalPage">${doc.pageCount}</span> </span>
                            <a href="#" class="hand btn-tool"><i class="glyphicon glyphicon-menu-right" id="btnNext"></i></a>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <section class="container">
            <div class="row">
                <div class="col-md-9">
                    <input type="hidden" id="fileType" value="${doc.fileType}">
                    <div id="viewer">
                        <div class="loading" ><img src="${ctx}/resources/img/loading.gif" alt="" id="loading" style="display:none;"></div>
                        <%--<iframe id="docPage" class="doc-page" src="http://www.baidu.com" frameborder="0"></iframe>--%>
                        <c:forEach begin="1" end="${doc.pageCount}" varStatus="status">
                            <div class="page-wrapper" data-page="${status.index}">
                                <iframe id="page${status.index}" class="doc-page doc-${doc.fileType}" data-ext="${doc.fileType}"
                                        data-src="${docServer}/${doc.id}/main/page${status.index}.html"
                                        frameborder="0" horizontalscrolling="no" verticalscrolling="no"></iframe>
                            </div>
                        </c:forEach>
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
    <jsp:attribute name="js">
        <script type="text/javascript" src="${ctx}/resources/lib/handlebars/handlebars.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/wenku.js"></script>
        <script type="text/javascript">
            (function () {

//                console.log($('#docPage').innerWidth());

                var fileType = $('#fileType').val().toLowerCase();

                var $pages = $('iframe.doc-page');
                var $loading = $('#loading');

                var $currentPage = $('#currentPage');
                var $btnPrev = $('#btnPrev');
                var $btnNext = $('#btnNext');

                var totalPage = SKUtils.toNumber($('#totalPage').text(),1);


                var pageCount = $pages.length;
                var initPageCount = pageCount>=3?3:pageCount;

                function IETester(userAgent) {
                    var UA = userAgent || navigator.userAgent;
                    if (/msie/i.test(UA)) {
                        return UA.match(/msie (\d+\.\d+)/i)[1];
                    } else if (~UA.toLowerCase().indexOf('trident') && ~UA.indexOf('rv')) {
                        return UA.match(/rv:(\d+\.\d+)/)[1];
                    }
                    return false;
                }
                //console.log(IETester()); //不传参数返回当前IE版本，如果不是IE内核浏览器，返回false
                var oldIE = IETester() == 8.0 || IETester() == 7.0 || IETester() == 6.0 || IETester() == 5.0;

                $pages.each(function(i,item){
                    var src = $(item).attr('data-src');
                    var ext = fileType.toLowerCase();
                    if (!oldIE && (ext === 'ppt'|| ext === 'pptx' || ext === 'doc' || ext === 'docx')){
                        $(item).attr('data-src',src.replace('main','modern'));
                    }
                });

                if (oldIE&&(fileType=== 'ppt'|| fileType === 'pptx')){
                    $('#viewerSide').addClass('floatSide');
                }



                $('.page-wrapper').each(function(i,item){
                    var $iframe = $('iframe',item);

                    if ($iframe.data('ext') === 'ppt'||$iframe.data('ext') === 'pptx'){
                        if (oldIE){
                            $iframe.parent().width(960);
                            $iframe.width(960);
                        }
                        $iframe.height($iframe.width()*0.75);
                    }else{
                        $iframe.height($iframe.width()*1.42);
                    }
                    var src = $iframe.data('src');
                    $iframe.attr('data-src',src+'#width='+$iframe.width());
                    //console.log($iframe.attr('data-src'));
                    if (i<initPageCount){
                        $iframe.attr('src',$iframe.attr('data-src'));
                    }
                });

                $btnPrev.on('click',function(e){
                    e.preventDefault();
                    if ($currentPage.val()>1){
                        $currentPage.val( SKUtils.toNumber($currentPage.val()) -1);
                        var id = 'page' + ($currentPage.val());
                        document.getElementById(id).scrollIntoView();
                    }
                });

                $btnNext.on('click',function(e){
                    e.preventDefault();
                    if ($currentPage.val()<totalPage){
                        $currentPage.val( SKUtils.toNumber($currentPage.val()) +1);
                        var id = 'page' + ($currentPage.val());
                        document.getElementById(id).scrollIntoView();
                    }
                });

                $(window).scroll(function() {
                    clearTimeout( $.data( this, "scrollCheck" ) );
                    var docHeight = window.innerHeight||document.documentElement.clientHeight;
                    $.data( this, "scrollCheck", setTimeout(function() {
                        $('.page-wrapper').each(function(i,item){
                            var top = $(item).offset().top - $(window).scrollTop();
                            var buff = $(item).height() / 2;

                            if (top>-buff&&top<docHeight){
                                $currentPage.val(i+1);
                                var $iframe = $('iframe',item);

                                if (!$iframe.attr('src')){
                                    $('#loading').show();
                                    $iframe.attr('src',$iframe.attr('data-src'));
                                }
                            }
                        });
                    }, 250));
                });

                $pages.on('load', function() {
                    $loading.hide();
                    //console.log($(this).outerWidth());
                });

//                document.addEventListener('copy', function(e){
//                    e.clipboardData.setData('text/plain', 'Hello, world!');
//                    e.clipboardData.setData('text/html', '<b>Hello, world!</b>');
//                    e.preventDefault(); // We want our data, not data from any selection, to be written to the clipboard
//                });


            })();
        </script>
    </jsp:attribute>
</layout:default>