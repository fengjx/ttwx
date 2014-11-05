<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/url.jsp" %>
<script type="text/javascript" charset="UTF-8">
	var centerTabs;
	function addTab(opts) {
		var options = $.extend({
			title : '',
			content : '<iframe src="' + opts.src + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>',
			closable : true,
			iconCls : '',
			height: 700
		}, opts);
		if (centerTabs.tabs('exists', options.title)) {
			centerTabs.tabs('close', options.title);
		}
		centerTabs.tabs('add', options);
		return true;
	};
	$(function() {
		centerTabs = $('#centerTabs').tabs({
			border : false,
			fit : true,
			height:'auto'

		});
		
		
		<%-- setTimeout(function() {
			var src = '<%=path%>/order/order!apprOrder.action?class=ddgl';
			centerTabs.tabs('add', {
				title : '訂單管理',
				content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
				closable : true,
				iconCls : ''
			});
		}, 0); --%>
		
		<c:if test="${empty sessionScope.sys_user_login_key.wechatPublicAccount }">
			setTimeout(function() {
				app.alertModal("你还没有获得授权，请先配置！",{
					okValue: '生成授权配置',
					ok : function(){
						var src = domain + '/admin/setting';
						centerTabs.tabs('add', {
							title : '配置授权',
							content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>',
							closable : true,
							iconCls : ''
						});
					}
				});
			}, 0);
		</c:if>
	});
</script>
<div id="centerTabs" style="height: 1000px;"></div>