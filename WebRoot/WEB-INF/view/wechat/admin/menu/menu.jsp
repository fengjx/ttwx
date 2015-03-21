<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>菜单管理</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/css/menu.css?v=2014111501" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/script/admin/menu.js?v=2015032201" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" style="height:25px;">
			<h3>可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。编辑中的菜的那不会马上被用户看到，请放心调试。</h3>
		</div>
        <div data-options="region:'west'" style="width:300px" title="菜單管理">
        	<table id="menu_treegrid">
        		<!-- js加载菜单列表 -->
		    </table>
		    <div id="menuDialog" style="display: none;overflow: hidden;width:250px;">
				<form id="menuForm" method="post">
					<table class="tableForm">
						<input type="hidden" name="id" value=""/>
						<input type="hidden" name="parent_id" value=""/>
						<input type="hidden" name="menu_level" value=""/>
						<tr>
							<th>菜单名称</th>
							<td><input name="name" class="easyui-validatebox" required="true" /></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 右键菜单开始 -->
			<div id="menu" class="easyui-menu" style="width:120px;display: none;">
				<div onclick="appMenu();" iconCls="icon-add">添加</div>
				<div onclick="updatedMenu();" iconCls="icon-edit">編輯</div>
				<div onclick="removeMenu();" iconCls="icon-remove">刪除</div>
			</div>
			<!-- 右键菜单结束 -->
        </div>
        
        
        <div id="action_setting" data-options="region:'center'" title="动作设置">
        	
        	<div class="action_content default jsMain" id="action_none" style="display: block;">
                <p class="action_tips">你可以先添加一个菜单，然后开始为其设置响应动作</p>
            </div>
        	
        	<div class="action_content init jsMain" style="display: none;" id="action_index">
                <p class="action_tips">请选择订阅者点击菜单后，公众号做出的相应动作</p>
                <a href="javascript:void(0);" id="sendMsg" onclick="showActionContent('action_edit');">
                	<i class="icon_menu_action send"></i>
                	<strong>发送信息</strong></a>
                <a href="javascript:void(0);" id="goPage" onclick="showActionContent('action_url');">
                	<i class="icon_menu_action url"></i>
                	<strong>跳转到页面</strong>
                </a>
            </div>
        	
        	<div class="action_content send jsMain" id="action_edit"  style="display: none;">
        		<!-- 菜单点击消息动作 -->
        		<jsp:include page="/WEB-INF/view/wechat/admin/menu/action_inc.jsp">
					<jsp:param name="req_type" value="event"/>
					<jsp:param name="event_type" value="CLICK"/>
				</jsp:include>
        	</div>
        	
        	<div class="action_content url jsMain" id="action_url" style="display: none;">
                 <%--<p class="action_tips">订阅者点击该子菜单会跳到以下链接</p>--%>
                 <p class="action_tips">订阅者点击该子菜单会跳转到以下链接</p>
                 <div class="frm_control_group">
                 	<table>
                 		<tr>
                 			<td>
	                 			<span class="">
			                    	链接地址：
			                    	<input class="field" type="text" value="http://" id="busiapp_url" style="width:350px;">
			                    </span>
		                    </td>
                 		</tr>
                 		</table>
                 </div>
                <div class="tool_bar">
		 			<span class="btn btn_input btn_default">
			    	 	<button onclick="showActionContent('action_index')">返回</button>
					 </span>
			    	 <span class="btn btn_input btn_primary">
			    	 	<button onclick="submitMsgActionForm('url');">保存</button>
					 </span>
                </div>
            </div>
        	
        	
        	<div class="action_content sended jsMain" id="view" style="display: none;">
        		<div style="float: left; width: 100%">
                	<div class="action_tips" style="float: left;">
                		訂閲者點擊該子菜單會收到一下信息
                	</div>
                	<div style="float:right;">
	                	<a class="easyui-linkbutton" onclick="updateMsgView();" data-options="plain:true,iconCls:'icon-edit'" href="#" id="btn">&nbsp;修&nbsp;&nbsp;改&nbsp;</a>
                	</div>
                </div>
                <div class="clear"></div>
                <div class="msg_wrp" id="viewDiv">
					<!-- js加载预览效果 -->                 	
                </div>
             </div>
		</div>
		
    </div>
	
</body>
</html> 