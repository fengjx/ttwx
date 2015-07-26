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
            <a href="<%=domain %>/admin/system/ext">
                <i class="glyphicon glyphicon-open"></i>
                <span class="menu-text"> 接口管理 </span>
            </a>
        </li>
    </ul>
</div>
