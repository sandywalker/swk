<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:admin title="首页">
    <jsp:attribute name="main">
        <nav class="navbar navbar-default navbar-tight">
                <div class="collapse navbar-collapse">
                    <a id="btnAdd" href="#" class="btn btn-primary navbar-btn btn-add" d="">&nbsp; <i
                            class="glyphicon glyphicon-plus"></i> 添加</a>
                    <form class="navbar-form navbar-right" id="queryForm">
                        <div class="form-group">
                            <label>标题：</label>
                            <input type="text" class="form-control" name="f_title1">
                        </div>
                        <button type="button" id="btnQuery" class="btn btn-default"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
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
                var $btnAdd = $('#btnAdd');


                var query = {};

                var dataOption = {
                    root:$dc,
                    urls:{
                        rest:ctx + '/admin/doc/products'
                    }
                };


                var tableOption = {
                    rowSelect:true,
                    checkbox:true,
                    root:$dc,
                    el:$('#dataTable'),
                    columns:[
                        {name:'id',title:'ID'},
                        {name:'title1',title:'标题'},
                        {name:'title2',title:'标题2'},
                        {name:'keywords',title:'关键词'},
                        {name:'statusStr',title:'文档状态'},
                        {name:'srcStr',title:'来源'},
                        {name:'levels',title:'级别'},
                        {name:'price',title:'价格'},
                        {name:'authorId',title:'作者ID'}
                    ],
                    actions:[
                        {classes:'btn-edit',title:'编辑',icon:'glyphicon glyphicon-edit'},
                        {classes:'btn-recommend',title:'添加首页推荐',icon:'glyphicon glyphicon-share-alt',divider:true},
                        {classes:'btn-delete',title:'删除',icon:'glyphicon glyphicon-trash'}

                    ]
                };


                var pageOption = {
                    root:$dc,
                    el:$('#pageNav')
                };

                var modalOption = {
                    id:'formModal',
                    classes:'modal-fluid',
                    title:'编辑文档',
                    buttonOk:'保存'

                };


                var dataManager = new DataManager(dataOption);
                new ListManager(tableOption);
                var pageManager = new PageManager(pageOption);
                var modal = new SKModal(modalOption);

                dataManager.initData(query);

                //Event Handlers

                $btnQuery.on('click',function(e){
                   e.preventDefault();
                   query = SKUtils.formToJson($queryForm);
                   query.page = 0;
                   query.limit = pageManager.getLimit();
                   dataManager.setQuery(query,true);
                });

                $btnAdd.on('click',function(e){
                   e.preventDefault();
                   dataManager.loadRemoteItem(0,function(item){
                       var content =  UIUtils.render('#formTemplate',item);
                       modal.show(content);
                   });
                });

                modal.on('ok',function(md){
                    var $form = $('#docForm');
                    var doc =  SKUtils.formToJson($form);
                    dataManager.saveRemoteItem(doc.id,doc,function(){
                        UIUtils.notifySuccess('保存成功！');
                        modal.hide();
                    });
                });

                $dc.on('click','.btn-edit',function(e){
                    e.preventDefault();
                    var $this = $(this);
                    var id = $this.data('id');
                    dataManager.loadRemoteItem(id,function(item){
                        var content =  UIUtils.render('#formTemplate',item);
                        modal.show(content);
                    });
                });

                $dc.on('click','.btn-delete',function(e){
                    e.preventDefault();
                    var $this = $(this);
                    UIUtils.deleteConfirm(function(){
                        var id = $this.data('id');
                        dataManager.deleteRemoteItem(id,function(result){
                            if (result.success){
                                UIUtils.notifySuccess('操作成功','数据已成功删除！');
                            }
                        });
                    });
                });

                $dc.on('click','.btn-recommend',function(e){
                   e.preventDefault();
                    var $this = $(this);
                   var id = $this.data('id');
                   $.ajax({
                       url:ctx + '/admin/doc/products/' + id + '/recommend',
                       success:function (result) {
                           if (result.success){
                               UIUtils.notifySuccess('操作成功','已成功添加首页推荐！');
                           }
                       }
                   });
                });
            })();
        </script>
    </jsp:attribute>
</layout:admin>
