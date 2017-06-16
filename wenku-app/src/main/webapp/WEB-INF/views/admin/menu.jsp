<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav panel-list">
    <li class="active">
        <a href="javascript:void(0)" class="function-menu" data-title="首页" data-url="/admin/dashboard">
            <i class="fa fa-tachometer"></i>
            <span class="menu-text"  >首页</span>
            <span class="selected"></span>
        </a>
    </li>
    <li class="hoe-has-menu">
        <a href="javascript:void(0)">
            <i class="fa fa-files-o"></i>
            <span class="menu-text">文档管理</span>
            <span class="selected"></span>
        </a>
        <ul class="hoe-sub-menu">
            <li>
                <a href="javascript:void(0)" class="function-menu" data-title="文档审核" data-url="/admin/doc/uploadHome">
                    <span class="menu-text">文档审核</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" class="function-menu" data-title="审核日志" data-url="/admin/doc/checkLogHome">
                    <span class="menu-text">审核日志</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" class="function-menu" data-title="成品文档" data-url="/admin/doc/productHome">
                    <span class="menu-text">成品文档</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" class="function-menu" data-title="首页推荐" data-url="/admin/doc/recommendHome">
                    <span class="menu-text">首页推荐</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" class="function-menu" data-title="垃圾文档" data-url="/admin/doc/trashHome">
                    <span class="menu-text">垃圾文档</span>
                    <span class="selected"></span>
                </a>
            </li>
        </ul>
    </li>
</ul>


