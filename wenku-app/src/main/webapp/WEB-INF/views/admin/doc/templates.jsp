<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script id="formTemplate" type="text/x-handlebars-template">
    <form id="docForm">
        <input type="hidden" name="id" value="{{id}}">
        <div class="form-group">
            <label >主标题</label>
            <input type="text" class="form-control" name="title1" value="{{title1}}">
        </div>
        <div class="form-group">
            <label >备用标题</label>
            <input type="text" class="form-control" name="title2" value="{{title2}}" >
        </div>
        <div class="form-group">
            <label >关键词</label>
            <input type="text" class="form-control" name="keywords" placeholder="关键词" value="{{keywords}}" >
        </div>
        <div class="form-group">
            <label >简介</label>
            <textarea class="form-control" name="description" rows="5">{{description}}</textarea>
        </div>
        <div class="form-group">
            <label >摘要</label>
            <textarea class="form-control" name="abstract" rows="5">{{abstract}}</textarea>
        </div>

        <div class="form-group">
            <label >级别</label>
            <select class="form-control" name="levels">
                {{simpleOptions levels '1,2,3,4,5,6,7,8,9,10' null}}
            </select>
        </div>

        <div class="form-group">
            <label >价格</label>
            <div class="input-group">
                <div class="input-group-addon">¥</div>
                <input type="text" class="form-control" id="price" name="price" value="{{price}}" placeholder="金额">
            </div>
        </div>

        <div class="form-group">
            <label >来源</label>
            <input type="text" readonly value="{{srcStr}}">
            <label >状态</label>
            <input type="text" readonly value="{{statusStr}}">
        </div>

        <div class="form-group">

        </div>
    </form>
</script>

<script id="recommendFormTemplate" type="text/x-handlebars-template">
    <form id="recommendForm">
                <input type="hidden" name="id" id="recommendId" value="{{id}}">
                <div class="form-group">
                    <label ></label>
                    <input type="text" class="form-control" id="title" name="title" value="{{title}}">
                </div>
                <div class="form-group">
                    <label >链接地址</label>
                    <input type="text" class="form-control" id="url" name="url" value="{{url}}" >
                </div>
                <div class="form-group">
                    <label >排序</label>
                    <input type="text" class="form-control" id="orderNumber" name="orderNumber" value="{{orderNumber}}" >
                </div>

                <div class="form-group">
                    <input type="file" name="cover" id="cover" onchange="javascript:UIUtils.setImagePreview('cover','preview');" />
                </div>
                <img alt="暂无预览" id="preview" width="300px" src="/resources/img/recommend/{{id}}.jpg" class="img-polaroid" />
    </form>
</script>