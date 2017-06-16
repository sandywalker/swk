<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:admin title="首页">
    <jsp:attribute name="main">
        <nav class="navbar navbar-default navbar-tight">
                <div class="collapse navbar-collapse">

                </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 tight">
                    <div id="dataContainer">
                        <table id="dataTable" class="table table-bordered table-hover">

                        </table>
                        <nav class="navbar navbar-default navbar-tight">
                                <div class="collapse navbar-collapse">
                                    <div id="pageNav">

                                    </div>
                                </div>
                        </nav>

                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="templates.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script type="text/javascript">
            (function () {
                var $dc = $('#dataContainer');

                var query = {};

                var dataOption = {
                    root:$dc,
                    urls:{
                        rest:ctx + '/admin/doc/checkLogs'
                    }
                };


                var tableOption = {
                    rowSelect:true,
                    checkbox:true,
                    root:$dc,
                    el:$('#dataTable'),
                    columns:[
                        {name:'id',title:'ID'},
                        {name:'did',title:'文档 ID'},
                        {name:'uid',title:'用户 ID'},
                        {name:'cid',title:'审核人 ID'},
                        {name:'resultStr',title:'审核结果'},
                        {name:'message',title:'审核说明'},
                        {name:'createTime',title:'审核时间'}
                    ]
                };


                var pageOption = {
                    root:$dc,
                    el:$('#pageNav')
                };


                var dataManager = new DataManager(dataOption);
                new ListManager(tableOption);
                var pageManager = new PageManager(pageOption);
                dataManager.initData(query);

            })();
        </script>
    </jsp:attribute>
</layout:admin>
