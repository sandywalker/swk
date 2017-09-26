<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 上传文档" id="mySpace">

    <jsp:attribute name="css">
        <link href="${ctx}/resources/lib/webuploader/webuploader.css" rel="stylesheet"  type="text/css"/>
        <link href="${ctx}/resources/lib/sweetalert/css/sweetalert.css" rel="stylesheet"  type="text/css"/>
        <style>
            .panel-heading{
                cursor: pointer;
            }

        </style>
    </jsp:attribute>
    <jsp:attribute name="main">
        <jsp:include page="../common/searchbar.jsp"/>

        <div class="container my-container">
            <div class="row">
                <div class="col-md-12 main">
                    <h3 class="text-blue text-center"> <small class="pull-left"><a href="javascript:history.go(-1);" >返回</a> </small> 上传文档</h3>
                    <div class="row">
                        <div class="col-md-12">

                            <div class="upload-box">
                                    <table class="table uploader-list" id="uploadList">
                                        <thead>
                                            <th> 文件 </th>
                                            <th width="100"> 进度 </th>
                                            <th width="100"> 状态 </th>
                                            <th width="180"> 操作 </th>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                    <div id="thelist" class="uploader-list"></div>
                                    <div class="clearfix"></div>
                                    <div id="picker" class="pull-left">&nbsp; <i class="glyphicon glyphicon-plus"></i>  添加文档</div>
                                    <button id="btnUpload" class="btn btn-upload btn-lg pull-right disabled">&nbsp; <i class="glyphicon glyphicon-upload"></i> 开始上传</button>
                                    <%--<button type="button" class="btn-upload" id="picker"> <i class="glyphicon glyphicon-upload"></i> 上传我的文档 </button>--%>
                                <div class="clearfix"></div>
                                <p class="text-muted" style="margin-bottom:30px">从我的电脑选择要上传的文档,按住CTRL可以上传多份文档</p>
                            </div>


                            <section class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            </section>

                            <div id="uploadInfos"></div>

                            <div class="text-center">
                                <button class="btn btn-primary btn-lg" id="btnConfirm" style="display: none;">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
                            </div>

                            <hr>




                            <script id="formTemplate" type="text/x-handlebars-template">
                                <div class="panel panel-info" id="panelUpload{{id}}">
                                    <div class="panel-heading" role="tab" id="headingOne">
                                        <h4 class="panel-title" data-toggle="collapse"
                                            href="#collapse{{id}}" aria-expanded="true" aria-controls="collapse{{id}}">
                                            <a role="button">
                                                完善文件信息
                                            </a>
                                            <small> <i class="ext ext-{{ext}}"></i> {{filename}}.{{ext}}  &nbsp;&nbsp;&nbsp;&nbsp;  点击可 展开/收起</small>
                                            <span class="pull-right" id="resultInfo{{id}}"></span>
                                        </h4>
                                    </div>
                                    <div id="collapse{{id}}" class="panel-collapse collapse in" role="tabpanel"
                                         aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <form class="form-horizontal upload-form" id="uploadForm{{id}}" data-id="{{id}}">
                                                <input type="hidden" name="id" value="{{id}}">
                                                <input type="hidden" name="fileType" value="{{ext}}">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">标题：</label>
                                                    <div class="col-sm-10">
                                                        <input name="title" type="text" value="{{filename}}" class="form-control"
                                                               required="required">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">介绍：</label>
                                                    <div class="col-sm-10">
                                                        <textarea name="description" class="form-control"
                                                                  rows="3"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">售价：</label>
                                                    <div class="col-sm-10">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">¥</div>
                                                            <input type="text" class="form-control" placeholder="金额"
                                                                   value="0" min="0" max="999" style="width:80px;">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </script>


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
                    </div>




                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script type="text/javascript" src="${ctx}/resources/lib/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/validate/localization/messages_zh.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/handlebars/handlebars.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/webuploader/webuploader.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/sweetalert/js/sweetalert.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/wenku.js"></script>
        <script type="text/javascript">
            (function () {

                var source   = $("#formTemplate").html();
                var template = Handlebars.compile(source);

                var $uploadInfos = $('#uploadInfos');

                var $btnUpload = $('#btnUpload');
                var $btnConfirm = $('#btnConfirm');



                $btnConfirm.on('click',function(e){
                    e.preventDefault();
                    var $forms = $('.upload-form');
                    var count = $forms.length;
                    var complete= 0;
                    var notified = false;
                    if (count == 0){
                        return;
                    }
                    $(this).hide();
                    var valid = true;
                    $forms.each(function(i,form){
                        if (!valid){
                            return;
                        }
                        var $form = $(form);
                        var id = $form.data('id');
                        var $collapse = $('#collapse' + id);
                        var $result = $('#resultInfo'+id);
                        if (!$form.valid()){
                            valid = false;
                            return;
                        }
                        var data = SKUtils.formToJson($form,true);
                        $.ajax({
                            url: ctx + '/user/doc/upload/complete',
                            data:data,
                            success:function (result) {
                               if (result.success){
                                   $result.addClass('text-success').html('<i class="glyphicon glyphicon-ok"></i> 成功！');
                                   $collapse.removeClass('in');
                               }else{
                                   $result.addClass('text-danger').html('<i class="glyphicon glyphicon-remove"></i> 失败！');
                               }
                                complete++;
                            },
                            error:function () {
                                complete++;
                                alert('网络出现错误');
                            }
                        });
                    });

                    setInterval(function(){
                        if (!notified&&complete>0 && complete == count){
                            notified = true;
                            UIUtils.notifySuccess('完成上传','已完成所有文件的上传，请等待审核！');
                            setTimeout(function () {
                                location.href = ctx + '/user/my';
                            },800);
                        }
                    },800);

                });

                var uploader = WebUploader.create({

                    // swf文件路径
                    swf: ctx + '/resources/lib/webuploader/Uploader.swf',

                    // 文件接收服务端。
                    server: ctx + '/user/doc/doUpload',
                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: '#picker',

                    resize: false,
                    accept:{
                        title: '文档',
                        extensions: 'doc,docx,xls,xlsx,ppt,pptx,pdf'
                    }
                });



                var state = 'pending';

                uploader.on( 'fileQueued', function( file ) {
                    var tr = '<tr id="'+ file.id +'" class="item">';
                    tr+= '<td class="file">' +  '<i class="ext ext-' + file.ext + '"></i> &nbsp;' + file.name.substring(0,50) + '</td>';
                    tr+= '<td class="percent"> </td> ';
                    tr+= '<td class="state">等待上传...</td>';
                    tr+= '<td class="action">&nbsp;</td>';
                    tr+= '</tr>';
                    $('#uploadList tbody').append(tr);
                    if ($btnUpload.hasClass('disabled')){
                        $btnUpload.removeClass('disabled');
                    }
                });

                uploader.on( 'uploadProgress', function( file, percentage ) {
                    var $tr = $( '#'+file.id );
                    var $percentTd = $tr.find('.percent');
                    var $percent = $tr.find('.progress .progress-bar');

                    // 避免重复创建
                    if ( !$percent.length ) {
                        $percent = $('<div class="progress progress-striped active">' +
                            '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                            '</div>' +
                            '</div>').appendTo( $percentTd ).find('.progress-bar');
                    }

                    $tr.find('td.state').text('上传中');

                    $percent.css( 'width', percentage * 100 + '%' );
                });

                uploader.on( 'uploadSuccess', function( file,result ) {
                    var text = result.success?'已上传':'上传出错';
                    $( '#'+file.id ).find('.state').text(text);
                    if (result.success)
                    {
                        var html = '<a href="#panelUpload' + result.data.id+'" class="btn btn-info btn-sm btn-edit" data-id="'+ result.data.id +'">填写信息</a>';
                        html += '&nbsp;<a  class="btn btn-danger btn-sm btn-remove" data-id="'+ result.data.id +'">删除</a>'
                        $('#' + file.id).find('.action').html(html);
                        var html  = template(result.data);
                        $uploadInfos.append(html);
                        $btnConfirm.show();
                    }
                });

                uploader.on( 'uploadError', function( file ) {
                    $( '#'+file.id ).find('.state').text('上传出错');
                });

                uploader.on( 'uploadComplete', function( file ) {
                    $( '#'+file.id ).find('.progress').fadeOut();
                });

                $btnUpload.on( 'click', function() {
                    if ($(this).hasClass('disabled')){
                        return;
                    }
                    if ( state === 'uploading' ) {
                        uploader.stop();
                    } else {
                        uploader.upload();
                    }
                });

                $('body').on('click','.btn-remove',function (e) {
                    e.preventDefault();

                    var $this = $(this);
                    if (confirm('您确定要删除这份文档吗？')){
                        var id = $(this).data('id');
                        var panelId = '#panelUpload'+id;
                        $.ajax({
                            url:ctx + '/user/doc/upload/removeTemp/' + id,
                            success:function(result){
                                $(panelId).remove();
                                $this.closest('tr').remove();
                            }
                        });
                    }
                });





            })();


        </script>
    </jsp:attribute>
</layout:default>