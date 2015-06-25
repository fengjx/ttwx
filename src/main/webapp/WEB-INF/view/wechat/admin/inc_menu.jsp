<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script>
    var index = '${param.index}';
    window.onload = function () {
        // 设置选中效果
        if (index !== '') {
            var ul = document.getElementById('side-menu');
            var lis = ul.getElementsByTagName("li");
            lis.item(index).setAttribute("class", "active");
        }
    }
</script>
<div class="sidebar">
    <ul class="nav nav-sidebar" id="side-menu">
        <li>
            <a href="<%=domain %>/admin/wechat/setting">
                <i class="glyphicon glyphicon-cog"></i>
                <span class="menu-text"> 授权配置 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/user">
                <i class="glyphicon glyphicon-user"></i>
                <span class="menu-text"> 用户管理 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/menu">
                <i class="glyphicon glyphicon-menu-hamburger"></i>
                <span class="menu-text"> 菜单管理 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/msglog">
                <i class="glyphicon glyphicon-comment"></i>
                <span class="menu-text"> 消息管理 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/material">
                <i class="glyphicon glyphicon-edit"></i>
                <span class="menu-text"> 素材管理 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/action/keyword">
                <i class="glyphicon glyphicon-send"></i>
                <span class="menu-text"> 自动回复 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/action/subscribe">
                <i class="glyphicon glyphicon-qrcode"></i>
                <span class="menu-text"> 粉丝关注回复 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/action/default">
                <i class="glyphicon glyphicon-certificate"></i>
                <span class="menu-text"> 默认回复 </span>
            </a>
        </li>
        <li>
            <a href="<%=domain %>/admin/wechat/action/lbs">
                <i class="glyphicon glyphicon-globe"></i>
                <span class="menu-text"> LBS回复 </span>
            </a>
        </li>
    </ul>
</div>
