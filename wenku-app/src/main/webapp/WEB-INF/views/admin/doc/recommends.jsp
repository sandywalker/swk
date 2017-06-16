<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:admin title="首页">
    <jsp:attribute name="css">
        <style>
            .cover{
                max-width:100px;
                max-height: 50px;
            }
        </style>
    </jsp:attribute>
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
                        rest:ctx + '/admin/doc/recommends'
                    }
                };

                var coverValue = function(item){
                    return '<img class="cover" src="/resources/img/recommend/' + item.id + '.jpg">';
                };


                var tableOption = {
                    rowSelect:true,
                    checkbox:true,
                    root:$dc,
                    el:$('#dataTable'),
                    columns:[
                        {name:'cover',title:'封面',width:110,value:coverValue},
                        {name:'id',title:'ID',width:80},
                        {name:'did',title:'文档 ID',width:80},
                        {name:'title',title:'标题'},
                        {name:'url',title:'链接地址'},
                        {name:'orderNumber',title:'排序',width:120}
                    ],
                    actions:[
                        {classes:'btn-edit',title:'编辑',icon:'glyphicon glyphicon-edit',divider:true},
                        {classes:'btn-delete',title:'删除',icon:'glyphicon glyphicon-trash'}
                    ]
                };


                var pageOption = {
                    root:$dc,
                    el:$('#pageNav')
                };

                var modalOption = {
                    id:'formModal',
                    title:'编辑推荐',
                    buttonOk:'保存'
                };


                var dataManager = new DataManager(dataOption);
                new ListManager(tableOption);
                var pageManager = new PageManager(pageOption);
                var modal = new SKModal(modalOption);
                dataManager.initData(query);




                $dc.on('click','.btn-edit',function(e){
                    e.preventDefault();
                    var $this = $(this);
                    var id = $this.data('id');
                    dataManager.loadRemoteItem(id,function(item){
                        var content =  UIUtils.render('#recommendFormTemplate',item);
                        modal.show(content);
                    });
                });

                modal.on('ok',function(md){
                    var $form = $('#recommendForm');
                    var id = $('#recommendId').val();
                    var formData = SKUtils.getFormData($form);
                    formData.append('cover',$('#cover')[0].files[0]);
                    dataManager.saveRemoteItem(id,formData,function(){
                        UIUtils.notifySuccess('保存成功！');
                        modal.hide();
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

            })();
        </script>
    </jsp:attribute>
</layout:admin>
