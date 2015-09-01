<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
<div class="sidebar responsive">
    <ul class="nav nav-sidebar nav-list" id="side-menu">
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
    </ul>
</div>
