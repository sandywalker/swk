<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 上传文档" id="mySpace">
    <jsp:attribute name="main">
        <jsp:include page="../common/searchbar.jsp"/>

        <div class="container my-container">
            <div class="row">
                <div class="col-md-12 main">
                    <h3 class="text-blue">上传文档</h3>
                    <hr>
                    <br>
                    <div class="row">
                        <div class="col-md-1">&nbsp;</div>
                        <div class="col-md-10">
                            <div class="upload-box">
                            <button type="button" class="btn-upload"> <i class="glyphicon glyphicon-upload"></i> 上传我的文档 </button>
                            <p class="text-muted">从我的电脑选择要上传的文档：按住CTRL可以上传多份文档</p>
                            </div>

                            <hr>

                            <p>&nbsp;</p>
                            <h5>温馨提示</h5>

                            <ol class="upload-hint">
                                <li>您可以上传日常积累和撰写的文档资料，如模板、总结，每次可上传≤20份，每份≤200MB，支持多种文档类型：doc,docx,ppt,pptx,xls,xlsx,vsd,pot,pps,rtf
                                    wps,pdf,txt,epub</li>
                                <li>上传涉及侵权内容的文档将会被移除。如何判断文档是否侵权？查看文库协议和用户规则</li>
                                <li>上传有问题需要帮助？查看文库帮助和意见反馈</li>
                                <li>为营造绿色网络环境，严禁上传含有淫秽色情及低俗信息等文档，让我们一起携手共同打造健康文库</li>
                            </ol>
                        </div>
                        <div class="col-md-1">&nbsp;</div>
                    </div>




                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
</layout:default>