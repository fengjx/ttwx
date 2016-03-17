<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
<div id="side-menu" class="sidebar responsive">
    <ul class="nav nav-sidebar nav-list">
        <li>
            <a href="${adminPath}/portal/guestbook">
                <i class="glyphicon glyphicon-comment menu-icon"></i>
                <span class="menu-text"> 意见反馈 </span>
            </a>
            <b class="arrow"></b>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left icon-arrow-left"></i>
    </div>
</div>
