<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
<div id="side-menu" class="sidebar responsive">
    <ul class="nav nav-sidebar nav-list">
        <li>
            <a href="${adminPath}/wechat/setting">
                <i class="glyphicon glyphicon-cog menu-icon"></i>
                <span class="menu-text"> 授权配置 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/wechat/user">
                <i class="glyphicon glyphicon-user menu-icon"></i>
                <span class="menu-text"> 用户管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/wechat/msglog">
                <i class="glyphicon glyphicon-comment menu-icon"></i>
                <span class="menu-text"> 消息管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/wechat/material">
                <i class="glyphicon glyphicon-edit menu-icon"></i>
                <span class="menu-text"> 素材管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="${adminPath}/wechat/menu">
                <i class="glyphicon glyphicon-menu-hamburger menu-icon"></i>
                <span class="menu-text"> 菜单管理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="javascript:void(0);">
                <i class="glyphicon glyphicon-send menu-icon"></i>
                <span class="menu-text"> 自动回复 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="">
                    <a href="${adminPath}/wechat/action/keyword">
                        <i class="menu-icon fa fa-caret-right"></i>
                        查看
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="${adminPath}/wechat/action/keywordAdd">
                        <i class="menu-icon fa fa-caret-right"></i>
                        添加
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
        <li>
            <a href="${adminPath}/wechat/action/subscribe">
                <i class="glyphicon glyphicon-qrcode menu-icon"></i>
                <span class="menu-text"> 粉丝关注回复 </span>
            </a>
        </li>
        <li>
            <a href="${adminPath}/wechat/action/default">
                <i class="glyphicon glyphicon-certificate menu-icon"></i>
                <span class="menu-text"> 默认回复 </span>
            </a>
        </li>
        <li>
            <a href="${adminPath}/wechat/action/lbs">
                <i class="glyphicon glyphicon-globe menu-icon"></i>
                <span class="menu-text"> LBS回复 </span>
            </a>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left icon-arrow-left"></i>
    </div>
</div>
