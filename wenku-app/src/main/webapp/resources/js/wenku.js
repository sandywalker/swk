/**
 * Created by sandy on 26/04/2017.
 */

/**
 * Handlerbars 增强
 */
Handlebars.registerHelper('selected', function(option, value){
    if (option === value) {
        return ' selected';
    } else {
        return ''
    }
});

/**
 * 示例：
 * <select name="schemaName" id="selectSchemaName" class="form-control">
    {{options plan.schemaName schemas 'name' 'title'  '全库' }}
  </select>
 @param value: 值
 @param items: 数据列表项，例如：[{name:'aa',title:'AA'},{name:'bb',title:'BB'}]
 @param valueProp: 值的属性名 例如：'name'
 @param textProp: 显示文字的属性名 例如：'title'
 @param defaultText: 默认选项，这个参数必须传，如果没有默认选项则传 null
 */
Handlebars.registerHelper('options', function(value,items,valueProp,textProp,defaultText){

    var html = '';
    if (defaultText){
        html = '<option value=""';
        if (!value){
            html += ' selected';
        }
        html+='>' + defaultText + '</option>';
    }
    for(var i in items){
        var item = items[i];
        var ov = item[valueProp];
        var ot = item[textProp];
        html += '<option value="' + ov + '" ';
        if (ov === value){
            html += ' selected';
        }
        html += '>' + ot + '</option>';
    }
    return new Handlebars.SafeString(html);
});
/**
 * 示例：
 * <select name="levels" id="levels" class="form-control">
        {{simpleOptions 1 '1,2,3,4,5,6,7' null }}
        {{simpleOptions 'a' 'a,b,c' null}}
 </select>
 @param value: 值
 @param items: 数据列表项，string 类型，多个项用 , 隔开
 @param defaultText: 默认选项，这个参数必须传，如果没有默认选项则传 null
 */
Handlebars.registerHelper('simpleOptions', function(value,items,defaultText){
    console.log(defaultText);
    var html = '';
    if (defaultText){
        html = '<option value=""';
        if (!value){
            html += ' selected';
        }
        html+='>' + defaultText + '</option>';
    }
    var itemsArray = items.split(',');
    for(var i in itemsArray){
        var item = itemsArray[i];
        html += '<option value="' + item + '" ';
        if (item == value){
            html += ' selected';
        }
        html += '>' + item + '</option>';
    }
    return new Handlebars.SafeString(html);
});


Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {

    switch (operator) {
        case '==':
            return (v1 == v2) ? options.fn(this) : options.inverse(this);
        case '===':
            return (v1 === v2) ? options.fn(this) : options.inverse(this);
        case '!=':
            return (v1 != v2) ? options.fn(this) : options.inverse(this);
        case '!==':
            return (v1 !== v2) ? options.fn(this) : options.inverse(this);
        case '<':
            return (v1 < v2) ? options.fn(this) : options.inverse(this);
        case '<=':
            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
        case '>':
            return (v1 > v2) ? options.fn(this) : options.inverse(this);
        case '>=':
            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
        case '&&':
            return (v1 && v2) ? options.fn(this) : options.inverse(this);
        case '||':
            return (v1 || v2) ? options.fn(this) : options.inverse(this);
        default:
            return options.inverse(this);
    }
});


/**
 * 全局变量，常量
 * @type {{DEFAULT_PAGE_SIZE}}
 */
var SKGlobals = (function(){
    return {
        DEFAULT_PAGE_SIZE:20
    }
})();



/**
 * 工具类
 *
 */
var SKUtils = (function(){

    if (!Array.isArray) {
        Array.isArray = function(arg) {
            return Object.prototype.toString.call(arg) === '[object Array]';
        };
    }

    /**
     * 字符转成数字
     * @param numeric
     * @param fallback
     * @returns {*}
     */
    var toNumber = function(numeric, fallback) {
        return isNaN(numeric) ? (fallback || 0) : Number(numeric);
    };

    /**
     * 将数字重新限定在设定的范围
     * @param number
     * @param min
     * @param max
     * @returns {*}
     */
    var fitNumber = function(number,min,max){
        if (number<min){
            number = min;
        }
        if (number>max){
            number = max;
        }
        return number;
    };

    /**
     * 将表单对象转换成 Json 对象
     * @param $form jQuery 表单
     * @param ignoreEmpty 是否忽略空字段
     */
    var formToJson = function($form,ignoreEmpty){
        var json = _.object(_.map($form.serializeArray(), _.values));
        if (!ignoreEmpty){
            return json;
        }
        var filtered = {};
        for(var key in json){
            if (json[key]&&json[key]!==0){
                filtered[key] = json[key];
            }
        }
        return filtered;
    };

    var getFormData = function ($form) {
        var array = $form.serializeArray();
        var formData = new FormData();
        for(var i=0;i<array.length;i++){
            var data = array[i];
            formData.append(data.name,data.value);
        }
        return formData;
    };

    return {
        toNumber : toNumber,
        fitNumber: fitNumber,
        formToJson: formToJson,
        getFormData:getFormData
    }
})();


/**
 * UI工具类
 *
 */

var UIUtils = (function($){

    var MODAL_TEMPLATE =
        '<div class="modal fade" tabindex="-1" role="dialog">'
        +'<div class="modal-dialog" role="document">'
        +'<div class="modal-content">'
        +'<div class="modal-header">'
        +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
        +'<h4 class="modal-title">&nbsp;</h4>'
        +'</div>'
        +'<div class="modal-body">'
        +'</div>'
        +'<div class="modal-footer">'
        +'<button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>'
        +'<button type="button" class="btn btn-primary btn-ok">确定</button>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'</div>';



    var setAttr = function(name,value){
        if (value){
            return  ' ' + name + '="' + value + '"';
        }
        return '';
    };

    var nodeBegin = function (nodeName,attrNames,attrValues){
        var out = '<' + nodeName;
        if (Array.isArray(attrNames)&&Array.isArray(attrValues)) {
            for (var i = 0; i < attrNames.length; i++) {
                out += setAttr(attrNames[i], attrValues[i]);
            }
        }else{
            out +=  setAttr(attrNames,attrValues);
        }
        out +='>';
        return out;
    };

    var nodeEnd = function (nodeName) {
        return '</' + nodeName + '>';
    };


    var renderTableHead = function(columns,actions,option){
        var out = '<tr>';
        if (option.checkbox){
            out += nodeBegin('th','width',15);
            out += nodeBegin('input',['type','class'],['checkbox','check-all']);
            out += nodeEnd('th');
        }
        if (actions&&actions.length){
            out += nodeBegin('th','width',40);
            out += '&nbsp;';
            out += nodeEnd('th');
        }
        for(var j=0;j<columns.length;j++){
            var col = columns[j];
            if (col.sortable){
                col.classes = col.classes||'';
                col.classes += ' sort';
                col.classes = col.classes.trim();
            }
            var attrNames = ['width','class','data-name'];
            var attrValues = [col.width,col.classes,col.name];
            out += nodeBegin('th',attrNames,attrValues);
            out += $.isFunction(col.title)?col.title():col.title;
            if (col.sortable){
                out += ' <span class="caret"></span>';
            }
            out += nodeEnd('th');
        }
        out += '</tr>';

        return out + '</thead>';
    };

    var renderTableBody = function(columns,actions,items,option){

        var getValue = function(v){
            if (typeof v== 'undefined' || v==null){
                return '';
            }else{
                return v;
            }
        };

        var out = '';
        for(var i=0, l=items.length; i<l; i++) {
            var item = items[i];
            var id = item.id||i;//设置 tr 的 data-id 属性，默认取 item.id 如果没有则用 i 值
            out += nodeBegin('tr','data-id',id);
            if (option.checkbox){
                out += '<td>';
                out += nodeBegin('input',['type','class'],['checkbox','check']);
                out += nodeEnd('td');
            }
            if (actions&&actions.length){
                out +='<td class="text-center">';
                out += nodeBegin('div','class','dropdown');
                out += '<a href="javascript:" data-toggle="dropdown"> <i class="glyphicon glyphicon-pencil"></i></a>';
                out += nodeBegin('ul','class','dropdown-menu');
                for(var k = 0;k<actions.length;k++){
                    var action = actions[k];
                    out += '<li>';
                    out += nodeBegin('a',['href','class','data-id'],['javascript:',action.classes,id]);
                    out += nodeBegin('i','class',action.icon);
                    out += nodeEnd('i');
                    out += ' ' + action.title;
                    out += nodeEnd('a');
                    out += '</li>';
                    if (action.divider){
                        out += '<li role="separator" class="divider"></li>';
                    }
                }
                out += nodeEnd('ul');
                out += nodeEnd('div');
                out += '</td>';
            }
            for(var j=0;j<columns.length;j++){
                var col = columns[j];
                out += nodeBegin('td','class',col.classes);
                if (col.value){
                    out += $.isFunction(col.value)?col.value(item):col.value;
                }else{
                    if (col.name.indexOf('.')>=0){
                        var names = col.name.split('.');
                        var f = item;
                        for(var n = 0;n<names.length;n++){
                            f = f[names[n]];
                        }
                        out += getValue(f);
                    }
                    out += getValue(item[col.name]);

                }
                out += nodeEnd('td');
            }
            out = out + '</tr>';
        }
        return out;
    };

    var renderPage = function(page){
        if (page.totalElements == 0){
            return '';
        }
        var out = '<ul class="pagination">';
        out += '<li';
        if (page.first){
            out +=  setAttr('class','disabled');
        }
        out += '><a href="javascript:" class="btn-page-first" arial=label="首页" title="首页"><i class="glyphicon glyphicon-fast-backward"></i></a></li>';

        out += '<li';
        if (page.first){
            out += setAttr('class','disabled');
        }
        out += '><a href="javascript:" class="btn-page-prev"  aria-label="上一页" title="上一页"><i class="glyphicon glyphicon-step-backward"></i></a></li>';

        out += '<li class="inputs page-number">' +
            '<a href="javascript:">' +
            '<span class="text-muted">&nbsp;第 </span>' +
            '<input type="text" class="input-page-number" value="' + (page.number + 1) +'" />' +
            '<span class="text-muted"> 页&nbsp; / 共 ' + page.totalPages + ' 页 &nbsp;</span>' +
            '</a>' +
            '</li>';

        out +='<li';
        if (page.last){
            out += setAttr('class','disabled');
        }
        out += '><a href="javascript:" class="btn-page-next" aria-label="下一页" title="下一页"><i class="glyphicon glyphicon-step-forward"></i></a></li>';
        out +='<li';
        if (page.last){
            out += setAttr('class','disabled');
        }
        out += '><a href="javascript:" class="btn-page-last"  aria-label="末页" title="末页"><i class="glyphicon glyphicon-fast-forward"></i></a>';
        out += '<li><a href="javascript:" class="btn-refresh" aria-label="刷新" title="刷新"><i class="glyphicon glyphicon-refresh"></i></a></li>';
        out += '<li class="inputs page-size">' +
            '<a href="javascript:">' +
            '<span class="text-muted">&nbsp;每页 </span>' +
            '<input type="text" class="input-page-size" value="' + page.size + '"/>' +
            '<span class="text-muted"> 条&nbsp;</span>' +
            '</a> </li>';
        out += '<li class="labels disabled"><a href="javascript:"><span class="text-muted"> &nbsp; 共 ' + page.totalElements + ' 条数据</a></li>';

        return out;
    };

    var initTable = function($table,columns,actions,option){
        $table.html('');
        var $thead = $('<thead>' + renderTableHead(columns,actions,option) + '</thead>');
        var $tbody = $('<tbody></tbody>');
        $table.append($thead).append($tbody);
    };

    var loadTableData = function($table,columns,actions,items,option){
        var $tbody = $table.children('tbody');
        $tbody.html(renderTableBody(columns,actions,items,option));
    };


    var renderModal = function(id,title,content,option){
        var opt = option||{};
        var $modal = $('#'+id);
        if ($modal.length){
            return $modal;
        }
        $modal = $(MODAL_TEMPLATE);
        $modal.attr('id',id);
        if (opt.classes){
            $modal.find('.modal-dialog').addClass(opt.classes);
        }
        var $title = $modal.find('.modal-title');
        var $body = $modal.find('.modal-body');
        var $foot = $modal.find('.modal-footer');
        $title.html(title);
        $body.html(content);

        if (opt){
            if (opt.buttonOk){
                var $btnOK = $foot.find('.btn-ok');
                $btnOK.text(opt.buttonOk);
            }
            if (opt.buttonClose){
                var $btnClose = $foot.find('.btn-close');
                $btnClose.text(opt.buttonClose);
            }
        }
        $modal.appendTo($('body'));
        return $modal;
    };

    var showModal = function(id,title,content,option){
        var $modal = renderModal(id,title,content,option);
        $modal.modal('show');
        return $modal;
    };

    var render = function (templateId,context) {
        var source = $(templateId).html();
        var template = Handlebars.compile(source);
        return template(context);
    };

    var notify = function (type,title,text,timer) {
        var duration = timer||500;
        swal({
            type:type,
            title: title,
            timer: duration,
            showConfirmButton: false
        });
    };

    var notifySuccess = function (title,text) {
        notify('success',title,text);
    };

    var notifyError = function (title,text) {
        notify('error',title,text);
    };

    var warn = function(title,text){
        swal({
            title: title,
            text: text,
            type: "warning",
            confirmButtonText: "关闭"
        });
    };

    var confirm = function(title,text,callback){
        swal({
                title: title,
                text: text,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            },
            callback
        );
    };

    var deleteConfirm = function(callback){
        return confirm('删除确认','删除的数据无法恢复，您确定删除吗？',callback);
    };

    /**
     * 设置预览图
     * @param fileId input file 的 id
     * @param previewId  预览 img 元素的 id
     * @returns {boolean}
     */
    var setImagePreview = function (fileId,previewId) {
        var docObj = document.getElementById(fileId);

        var imgObjPreview = document.getElementById(previewId);
        if (docObj.files && docObj.files[0]) {
            // 火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '300px';
            imgObjPreview.style.height = 'auto';
            // imgObjPreview.src = docObj.files[0].getAsDataURL();

            // 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            if (window.URL) {
                //FF4+
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
            } else if (window.webkitURL) {
                //Chrome8+
                imgObjPreview.src = window.webkitURL.createObjectURL(docObj.files[0]);
            };
        } else {
            // IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            // 必须设置初始大小
            localImagId.style.width = '300px';
            localImagId.style.height = 'auto';
            // 图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters
                    .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    };




    return {
        initTable:initTable,
        loadTableData:loadTableData,
        render:render,
        renderModal:renderModal,
        renderPage:renderPage,
        notify:notify,
        notifySuccess:notifySuccess,
        notifyError:notifyError,
        warn:warn,
        confirm:confirm,
        deleteConfirm:deleteConfirm,
        setImagePreview:setImagePreview

    };

})(jQuery);

/**
 * Modal
 */

(function($){
    function SKModal(option){
        this.option = option;
        this.events = option.events||{ok:null,cancel:null};
        this.modal = UIUtils.renderModal(option.id,option.title,option.content,option);
        this.modalBody = this.modal.find('.modal-body');
        this.modeFoot = this.modal.find('.modal-footer');
        if (option.hideFooter){
            this.modeFoot.remove();
        }
        this.handleEvents();
    }

    SKModal.prototype.show = function(content){
        if (content){
            this.modalBody.html(content);
        }
        this.modal.modal('show');
    };

    SKModal.prototype.hide = function(){
        this.modal.modal('hide');
    };


    SKModal.prototype.handleEvents = function(){
        var self = this;
        var $modal = self.modal;
        $modal.on('click','.btn-ok',function(e){
            e.preventDefault();
            if (self.events.ok){
                self.events.ok(self);
            }
        });
    };

    SKModal.prototype.on = function(eventName,callback){
        this.events[eventName] = callback;
    };

    window.SKModal = SKModal;


})(jQuery);


/**
 * DataManager
 *
 */

(function($){

    function DataManager(option){
        this.init(option)
    }

    DataManager.prototype.init= function(option){
        this.query = $.extend({page:1,size:SKGlobals.DEFAULT_PAGE_SIZE},option.query);
        this.$root = typeof option.root === 'string'?$(option.root):option.root;
        this.urls = $.extend({list:'',item:''},option.urls);
        this.handleEvents();
    };

    /**
     * 获取列表数据的 URL
     * @returns {string|Array|*}
     */
    DataManager.prototype.getListUrl = function(){
        return this.urls.rest||this.urls.list;
    };

    DataManager.prototype.getItemUrl = function(id){
        var url = this.urls.rest||this.urls.item;
        return url + '/' + id;
    };



    DataManager.prototype.initData = function(query){
        this.setQuery(query,true);
    };

    /**
     * 修正 中的 page 参数，兼容 spring
     * @param query
     * @private
     */
    DataManager.prototype._fixQuery= function(query){
        if (typeof query.page == 'undefined' ||query.page==null){
            query.page = 1;
        }
        if (!query.size){
            query.size = SKGlobals.DEFAULT_PAGE_SIZE;
        }
        return query;
    };

    /**
     * 设置查询参数
     * @param query 查询参数
     * @param reload 是否重新查询数据
     */
    DataManager.prototype.setQuery = function(query,reload){
        this.query = this._fixQuery(query);
        this.$root.trigger('data.query.change');
        if (reload){
            this.loadRemoteList(query);
        }
    };

    DataManager.prototype.reload = function(){
        this.loadRemoteList(this.query);
    };

    DataManager.prototype.getQuery = function(){
        return this.query;
    };

    /**
     * 如果是 page 则直接返回，如果是数据列表，则封装一个 Page 出来，保持组件兼容性
     * @param list
     */
    DataManager.prototype.wrapPage = function(data){
        if (Array.isArray(data)){
            var page = {number:0,size:data.length,totalElements:data.length,totalPages:1};
            page.content = data;
            return page;
        }
        return data;
    };

    /**
     * 修正数据列表中字段为 json 字符串的数据转换成 json 对象
     * @param list
     */
    DataManager.prototype.fixData = function(list){
        if (!list){
            return;
        }
        for(var i=0;i<list.length;i++){
            if (list[i].fullInfo){
                list[i].fullInfo = JSON.parse(list[i].fullInfo);
            }
        }
    };



    /**
     * 加载远程数据
     * @param query
     */
    DataManager.prototype.loadRemoteList = function(query,callback){
        var self = this;

        self.query = self._fixQuery(query);
        self.$root.trigger('data.list.loading');
        $.ajax({
            url:self.getListUrl(),
            data:query,
            success:function (data) {
                var page = self.wrapPage(data);
                self.fixData(page.content);
                self.$root.trigger('data.list.loaded',page);
                if (callback){
                    callback(page);
                }
            },
            error:function(jqXHR,textStatus){
                self.$root.trigger('data.list.loadError');
                alert('远程错误：'+ textStatus);
            }
        });
    };



    DataManager.prototype.loadRemoteItem = function (id,callback) {
        var self = this;
        self.$root.trigger('data.item.loading');
        $.ajax({
            url:self.getItemUrl(id),
            success:function (item) {
                self.$root.trigger('data.item.loaded',item);
                if (callback){
                    callback(item);
                }
            },
            error:function(jqXHR,textStatus){
                self.$root.trigger('data.item.loadError');
                alert('远程错误：'+ textStatus);
            }
        });
    };

    DataManager.prototype.saveRemoteItem = function (id,item,callback) {
        var self = this;
        self.$root.trigger('data.item.saving');
        var options = {
            url:self.getItemUrl(id),
            type:'POST',
            data:item,
            success:function (item) {
                self.$root.trigger('data.list.changed');
                self.$root.trigger('data.item.saved',item);
                if (callback){
                    callback(item);
                }
            },
            error:function(jqXHR,textStatus){
                self.$root.trigger('data.item.saveError');
                alert('远程错误：'+ textStatus);
            }
        };
        if (item.constructor == FormData){
            options.contentType = false;
            options.enctype = 'multipart/form-data';
            options.processData = false;
        }
        $.ajax(options);
    };

    DataManager.prototype.deleteRemoteItem = function (id,callback) {
        var self = this;
        self.$root.trigger('data.item.deleting');
        $.ajax({
            url:self.getItemUrl(id),
            type:'DELETE',
            success:function (result) {
                self.$root.trigger('data.list.changed');
                self.$root.trigger('data.item.deleted');
                if (callback){
                    callback(result);
                }
            },
            error:function(jqXHR,textStatus){
                self.$root.trigger('data.item.deleteError');
                alert('远程错误：'+ textStatus);
            }
        });
    };

    /**
     * 绑定自定义事件和 DOM 事件
     */
    DataManager.prototype.handleEvents = function() {
        var self = this;
        var $root = this.$root;

        $root.on('data.page.change',function(e,context){
            self.query.page = context.number+1; //分页对象是 0 开始的，spring mvc 的 pageable 需要 + 1 才能正常绑定
            self.query.size = context.size;
            self.loadRemoteList(self.query);
        });

        $root.on('data.sort.change',function(e,sort){
            self.query.sort = sort;
            self.loadRemoteList(self.query);
        });

        $root.on('data.list.changed',function(){
            self.loadRemoteList(self.query);
        });
    };

    window.DataManager = DataManager;


})(jQuery);


/**
 *  PageManager
 *  分页从 0 开始
 */
(function($){

    function PageManager(option){
        this.init(option);
    }

    PageManager.prototype.init = function (option) {
        this.page = {};
        this.$root = typeof option.root === 'string'?$(option.root):option.root;
        this.$pageNav = typeof  option.el === 'string'?$(option.el):option.el;
        this.pageSize = SKGlobals.DEFAULT_PAGE_SIZE;
        if (!this.$pageNav.hasClass('page-nav')){
            this.$pageNav.addClass('page-nav');
        }
        this.handleEvents();
    };

    PageManager.prototype.goto = function(number){
        if (number<0||number>=this.page.totalPages){
            return;
        }
        this.$root.trigger('data.page.change',{number:number,size:this.pageSize});
    };

    PageManager.prototype.refresh = function(page){
        this.page = page;
        var pageHtml = UIUtils.renderPage(page);
        this.$pageNav.html(pageHtml);
    };

    PageManager.prototype.getPageSize = function(){
        return this.pageSize;
    };

    PageManager.prototype.reset = function () {
        this.$pageNav.empty();
    };



    PageManager.prototype.handleEvents = function () {
        var self = this;
        var $pageNav = this.$pageNav;
        var $root = this.$root;


        $pageNav.on('click','.btn-page-first',function(e){
            e.preventDefault();
            if (self.page.number<=0){
                return;
            }
            self.goto(0);
        });

        $pageNav.on('click','.btn-page-prev',function(e){
            e.preventDefault();
            if (self.page.number<=0){
                return;
            }
            self.goto(self.page.number - 1);
        });


        $pageNav.on('click','.btn-page-next',function(e){
            e.preventDefault();
            if (self.page.number>=self.page.totalPages-1){
                return;
            }
            self.goto(self.page.number + 1);
        });

        $pageNav.on('click','.btn-page-last',function(e){
            e.preventDefault();
            if (self.page.number>=self.page.totalPages){
                return;
            }
            self.goto(self.page.totalPages - 1);
        });

        $pageNav.on('click','.btn-refresh',function(e){
            e.preventDefault();
            if (self.page.number<0||self.page.number>=self.page.totalPages){
                return;
            }
            self.goto(self.page.number);
        });

        $pageNav.on('click','.btn-refresh',function(e){
            e.preventDefault();
            if (self.page.number<0||self.page.number>=self.page.totalPages){
                return;
            }
            self.goto(self.page.number);
        });

        $pageNav.on('keyup','.input-page-number',function(e){
            e.preventDefault();
            if (e.keyCode !== 13){
                return;
            }
            var number = SKUtils.toNumber($(this).val(),1) - 1;
            number = SKUtils.fitNumber(number,0,self.page.totalPages - 1);
            self.goto(number);
        });

        $pageNav.on('keyup','.input-page-size',function(e){
            e.preventDefault();
            if (e.keyCode !== 13){
                return;
            }
            var pageSize = SKUtils.toNumber($(this).val(),20);
            if (pageSize<=1){
                pageSize = 20;
            }
            if (pageSize>=2000){
                pageSize = 2000;
            }
            self.page.number = 0;
            self.pageSize = pageSize;
            self.goto(self.page.number);
        });


        $root.on('data.list.loaded',function(event,page){
            self.refresh(page);
        });



    };

    window.PageManager = PageManager;

})(jQuery);


/**
 * ListManager
 */
(function($){


    var loadBox = '<div class="loader-box" style="display: none;"> <div class="loader">正在加载...</div> </div>';

    function ListManager(option){
        this.init(option);
    }

    /**
     * 初始化
     * @param option
     */
    ListManager.prototype.init = function(option){
        this.checkOption(option);
        this.$root = typeof option.root === 'string'?$(option.root):option.root;
        this.$table = typeof  option.el === 'string'?$(option.el):option.el;
        this.sortList = [];
        this.checkbox = option.checkbox||false;
        this.rowSelect = option.rowSelect || false;
        this.showLoading = true;
        if (option.showLoading === false){
            this.showLoading = false;
        }
        this.columns = option.columns;
        this.actions = option.actions;
        this.selectedIds = [];

        this.initUI();
        this.handleEvents();
    };


    ListManager.prototype.setColumns = function(columns){
        this.reset();
        this.columns = columns;
        this.initUI();
    };

    ListManager.prototype.reset = function(){
        this.clearSelect(false);
    };

    ListManager.prototype.clear = function(){
        this.$table.find('tbody').html('');
    };

    /**
     * 初始化 UI
     */
    ListManager.prototype.initUI = function(){
        UIUtils.initTable(this.$table,this.columns,this.actions,{checkbox:this.checkbox});
        this.$root.css('position','relative');
        if (!this.$loadBox){
            this.$loadBox = $(loadBox).appendTo(this.$root);
        }
    };

    /**
     * 验证参数是否合法
     * @param option
     */
    ListManager.prototype.checkOption = function (option) {
        if (!option.root){
            throw 'Error: root must exists';
        }
        if (!option.el){
            throw 'Error: el must exists';
        }
    };


    /**
     * 显示加载进度图标
     */
    ListManager.prototype.onLoading = function(){
        if (this.showLoading){
            this.$loadBox.fadeIn();
        }
    };

    /**
     * 关闭加载进度图标
     */
    ListManager.prototype.hideLoading = function(){
        if (this.showLoading){
            this.$loadBox.fadeOut();
        }
    };




    /**
     * 根据 JSON 数据渲染表格数据
     * @param data
     */
    ListManager.prototype.renderList = function(data){
        this.data = data;
        var tableHtml = UIUtils.loadTableData(this.$table,this.columns,this.actions,data,{checkbox:this.checkbox});
        this.$table.html(tableHtml);
    };


    /**
     * 清除所有已选择的数据 ID，并更新 UI
     */
    ListManager.prototype.clearSelect = function(refreshUI){
        this.selectedIds = [];
        if (refreshUI){
            this.updateSelectedRowsUI();
        }
    };

    /**
     * 更新选择 ID
     * @param ids 要处理的 ID 列表
     * @param flag true -> 添加, false -> 删除
     */
    ListManager.prototype.updateSelectedIds = function(ids,flag){
        if (flag === true){
            this.selectedIds = _.union(this.selectedIds,ids);
        }else{
            this.selectedIds = _.difference(this.selectedIds,ids);
        }
    };


    /**
     * 高亮所选择的行
     * @param $tr
     * @param flag
     */
    ListManager.prototype.highlightSelected = function($tr,flag){
        $tr.toggleClass('warning',flag);
    };

    /**
     * 根据已选择的数据 ID 更新行，在数据翻页的时候调用
     */
    ListManager.prototype.updateSelectedRowsUI = function(){
        var self = this;
        var $trs = self.$table.find('tbody tr');
        var ids = self.selectedIds;
        $trs.each(function(i,item){
            var checked = item.checked;
            var $tr = $(item);
            var $check = $tr.find('input.check');
            var id = $tr.data('id');
            var exist = _.indexOf(ids,id) >=0;
            if (checked){
                if (!exist){
                    $check.prop('checked',false);
                    self.highlightSelected($tr,false);
                }
            }else{
                if (exist){
                    $check.prop('checked',true);
                    self.highlightSelected($tr,true);
                }
            }
        });
    };


    /**
     * 改变排序规则
     * @param name 排序字段
     * @param dir 排序方式  asc|desc
     */
    ListManager.prototype.changeSort = function(name,dir){
        var self = this;
        var indexOfSort = function(name){
            for(var i=0;i<self.sortList.length;i++){
                var sort = self.sortList[i];
                if (sort.name === name){
                    return i;
                }
            }
            return -1;
        };
        var idx = indexOfSort(name);
        if (idx==-1){
            if (dir) {
                var sort = {name: name, dir: dir};
                self.sortList.push(sort);
            }
        }else{
            if (dir){
                self.sortList[idx].dir = dir;
            }else{
                self.sortList.splice(idx,1);
            }
        }
    };

    /**
     * 从排序数组中得到排序字符串
     * @returns {string}
     */
    ListManager.prototype.getSortStr = function(){
        var sorts = _.map(this.sortList,function (item) {
            return item.name + ' ' + item.dir;
        });
        return sorts.join(',');
    };

    /**
     * 绑定自定义事件和 DOM 事件
     */
    ListManager.prototype.handleEvents = function(){
        var self = this;
        var $root = this.$root;
        var $table = this.$table;



        $root.on('data.list.loaded',function(e,page){
            self.hideLoading();
            self.renderList(page.content);
            self.updateSelectedRowsUI();
            $table.find('input.check-all').prop('checked',false);
        });


        $root.on('data.list.loading',function(){
            self.onLoading();
        });

        $root.on('data.list.loadError',function(){
            self.hideLoading();
        });

        $root.on('data.query.change',function(){
            self.clearSelect();
        });


        $table.on('click','input.check-all',function(){
            var $this = $(this);
            var $checks = $('.check',$table);
            var $trs = $table.find('tbody tr');
            var checked = $this.is(':checked');
            $checks.prop('checked',checked);
            var ids = [];
            $checks.each(function(i,item){
                var $tr = $(item).closest('tr');
                ids.push($tr.data('id'));
            });
            self.updateSelectedIds(ids,checked);
            self.highlightSelected($trs,checked);

        });

        $table.on('click','input.check',function(){
            var checked = this.checked;
            var $tr = $(this).closest('tr');
            var id = $tr.data('id');
            self.updateSelectedIds([id],checked);
            self.highlightSelected($tr,checked);
        });

        //row select
        if (self.rowSelect&&self.checkbox){
            $table.on('click','tbody>tr',function(e){
                if (e.target.nodeName.toLocaleLowerCase() !== 'td'){
                    return;
                }
                var $tr = $(this);
                var id = SKUtils.toNumber($tr.data('id'));
                var $check = $tr.find('input.check');
                var checked = !$check[0].checked;
                $check.prop('checked',checked);
                self.updateSelectedIds([id],checked);
                self.highlightSelected($tr,checked);
            });
        }


        //sort
        $table.on('click','th.sort',function(){
            var $th = $(this);
            var name = $th.data('name');
            var changeClass = function(classes){
                $th.attr('class',classes);
            };
            if ($th.hasClass('sort-asc')){
                changeClass('sort sort-desc');
                self.changeSort(name,'desc');
            }else if ($th.hasClass('sort-desc')){
                changeClass('sort');
                self.changeSort(name);
            }else{
                changeClass('sort sort-asc');
                self.changeSort(name,'asc');
            }
            $root.trigger('data.sort.change',self.getSortStr());
        });

    };


    window.ListManager = ListManager;


})(jQuery);



