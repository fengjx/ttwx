<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<script type="text/javascript" charset="UTF-8">
	function logout() {
		window.location.href = '<%=domain %>/loginout';
	}
	function returnIndex(){
		window.location.href = '<%=domain %>';
	}
	function toWeixin(){
		window.open("https://mp.weixin.qq.com");
	}
</script>
<div>
	<img alt="" src="<%=resourceUrl %>/img/wechat_logo.png" width="48px" height="48px">
</div>

<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a>
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">操作</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<!-- <div onclick="userInfo();">個人信息</div> -->
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 100px;">
			<div onclick="fjx.changeTheme('default');">default</div>
			<div onclick="fjx.changeTheme('bootstrap');">bootstrap</div>
			<div onclick="fjx.changeTheme('gray');">gray</div>
			<div onclick="fjx.changeTheme('cupertino');">cupertino</div>
			<!-- <div onclick="fjx.changeTheme('black');">black</div> -->
			<!-- <div onclick="fjx.changeTheme('dark-hive');">dark-hive</div> -->
			<div onclick="fjx.changeTheme('metro');">metro</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 120px; display: none;">
	<!--  
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="returnIndex();">返回首页</div>
	-->
	<div onclick="returnIndex();" >首页</div>
	<div onclick="toWeixin();" >微信公众平台</div>
	<div onclick="logout();">退出系統</div>
</div>