<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
<div id="side-menu" class="sidebar responsive">
    <ul class="nav nav-sidebar nav-list">
        <li>
            <a href="${adminPath}/sys/ext">
                <i class="icon-retweet menu-icon"></i>
                <span class="menu-text"> 接口管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/sys/dict">
                <i class="icon-credit-card menu-icon"></i>
                <span class="menu-text"> 字典管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="javascript:void (0);">
                <i class=" icon-list menu-icon"></i>
                <span class="menu-text"> 菜单管理 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="">
                    <a href="${adminPath}/sys/menu">
                        <i class="menu-icon fa fa-caret-right"></i>
                        查看
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="${adminPath}/sys/menu/form">
                        <i class="menu-icon fa fa-caret-right"></i>
                        添加
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
        <li>
            <a href="${adminPath}/sys/role">
                <i class=" icon-unlock menu-icon"></i>
                <span class="menu-text"> 角色管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/sys/user">
                <i class="icon-user menu-icon"></i>
                <span class="menu-text"> 用户管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/sys/dict">
                <i class="icon-credit-card menu-icon"></i>
                <span class="menu-text"> 系统日志 </span>
            </a>
            <b class="arrow"></b>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon icon-arrow-left"></i>
    </div>
</div>
