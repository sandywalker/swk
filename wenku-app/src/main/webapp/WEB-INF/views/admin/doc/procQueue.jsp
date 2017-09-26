<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:admin title="首页">
    <jsp:attribute name="main">
        <nav class="navbar navbar-default navbar-tight">
                <div class="collapse navbar-collapse">
                    <button class="btn btn-default navbar-btn" id="btnReproc" >重新转换</button>
                    <%--<form class="navbar-form navbar-right" id="queryForm">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>标题：</label>--%>
                            <%--<input type="text" class="form-control" name="key">--%>
                        <%--</div>--%>
                        <%--<button type="button" id="btnQuery" class="btn btn-default"><i class="glyphicon glyphicon-search"></i> 查询</button>--%>
                    <%--</form>--%>
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
                var $queryForm = $('#queryForm');
                var $btnQuery = $('#btnQuery');


                var query = {};

                var dataOption = {
                    root:$dc,
                    urls:{
                        rest:ctx + '/admin/doc/proc/queues'
                    }
                };

                var tableOption = {
                    rowSelect:true,
                    checkbox:true,
                    root:$dc,
                    el:$('#dataTable'),
                    columns:[
                        {name:'id',title:'ID'},
                        {name:'uid',title:'用户 ID'},
                        {name:'did',title:'文档 ID'},
                        {name:'fileType',title:'文件类型'},
                        {name:'state',title:'状态'},
                        {name:'createTime',title:'创建时间'},
                        {name:'msg',title:'消息备注'}
                    ]
                };


                var pageOption = {
                    root:$dc,
                    el:$('#pageNav')
                };



                var dataManager = new DataManager(dataOption);
                var listManager = new ListManager(tableOption);
                var pageManager = new PageManager(pageOption);

                dataManager.initData(query);

                //Event Handlers

//                $btnQuery.on('click',function(e){
//                   e.preventDefault();
//                   query = SKUtils.formToJson($queryForm);
//                   query.page = 0;
//                   query.limit = pageManager.getLimit();
//                   dataManager.setQuery(query,true);
//                });


//                modal.on('ok',function(md){
//                    var $form = $('#docForm');
//                    var doc =  SKUtils.formToJson($form);
//                    dataManager.saveRemoteItem(doc.id,doc,function(){
//                        UIUtils.notifySuccess('保存成功！');
//                        modal.hide();
//                    });
//                });

                $('#btnReproc').on('click',function (e) {
                    e.preventDefault();

                    if (!listManager.selectedIds.length){
                        alert('请选择要处理的文档');
                        return;
                    }
                    $.ajax({
                        url: ctx + '/admin/doc/proc/reproc',
                        data:{ids:listManager.selectedIds.join(',')},
                        success:function (result) {
                            listManager.clearSelect(true);
                            dataManager.reload();
                            if (!result.success){
                                alert('错误：' + result.message);
                            }
                        }
                    });
                });

//                $dc.on('click','.btn-reproc',function(){
//                    var $this = $(this);
//                    var id = $this.data('id');
//                    dataManager.loadRemoteItem(id,function(item){
//                        var content =  UIUtils.render('#formTemplate',item);
//                        modal.show(content);
//                    });
//                });


            })();
        </script>
    </jsp:attribute>
</layout:admin>
