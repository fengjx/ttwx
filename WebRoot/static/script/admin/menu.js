/**
 * 菜单管理
 */
//树形菜单
var menu_treegrid;
//当前选择的节点
var selectNode;
//菜单编辑弹出框
var menuDialog;
//添加 / 修改菜单
var menuForm;

var msg = {
	a : "你可以先添加一个菜单，然后开始为其设置响应动作",
	b : "发布成功，如没能查看到更新效果，请重新关注微信查看",
	c : "已有子菜单，无法设置动作",
	d : "订阅者点击该菜单会收到一下消息",
	e : "订阅者点击该菜单会跳转到以下链接"
}

$(function(){
	
	init();
	
	menu_treegrid = $('#menu_treegrid').treegrid({
		url : domain + '/admin/menu/load',
		toolbar : [{
		    text:'添加',
		    iconCls:'icon-add',
		    handler:function(){
		    	appMenu();
		    }
		},{
		    text:'修改',
		    iconCls:'icon-edit',
		    handler:function(){
		    	updatedMenu();
		    }
		},{
		    text:'刪除',
		    iconCls:'icon-remove',
		    handler:function(){
		    	removeMenu();
			}
		},{
		    text:'刷新',
		    iconCls:'icon-reload',
		    handler:function(){
				menu_treegrid.treegrid('reload');
			}
		},{
		    text:'发布',
		    iconCls:'icon-ok',
		    handler:function(){
				release();
			}
		}],
		fit : true,
		fitColumns : true,
		rownumbers : true,		//是否显示行号
		idField : 'id',
		treeField : 'name',
		frozenColumns : [ [ {
			title : 'id',
			field : 'id',
			hidden : true
		},{
			field : 'menu_level',
			hidden : true
		},{
			field : 'type',
			hidden : true
		},{
			field : 'url',
			hidden : true
		},{
			field : 'app_id',
			hidden : true
		},{
			field : 'app_name',
			hidden : true
		},{
			field : 'app_description',
			hidden : true
		},{
			field : 'material_id',
			hidden : true
		},{
			field : 'action_id',
			hidden : true
		},{
			field : 'action_type',
			hidden : true
		},{
			field : 'xml_data',
			hidden : true
		}] ],
		columns : [ [ {
			field : 'name',
			title : '菜单名称',
			width : 100
		}, {
			field : 'state',
			title : '动作',
			width : 30,
			formatter : function(value,row,index){
				var res = "";
				if(row.children){
					res = "";
				}else if(row.type=="click"){
					res = "发送消息";
				}else if(row.type=="view"){
					res = "跳转页面";
				}else{
					res = "未设置";
				}
				return res;
			}
		}] ],
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onLoadSuccess : function(row, data) {
			fjx.closeProgress();
			selectNode = undefined;
			$("#action_none").find("p").html(msg.a);
			showActionContent('action_none');
		},
		onSelect : function(node){
			selectNode = node;		//给选中的节点（全局）赋值，方便其他方法调用
			thowSetting(node);
		}
	});
});


/**
 * 初始化
 */
function init(){
	
	menuForm = $('#menuForm').form();
	menuDialog = $('#menuDialog').show().dialog({
		modal : true,
		buttons : [ {
			text : '确定',
			handler : function() {
				menuForm.form('submit', {
					url : domain + '/admin/menu/save',
					success : function(data) {
						var res = $.evalJSON(data);
						menuDialog.dialog('close');
						if(res && '1' == res.code){
							fjx.showMsg('菜单编辑成功！');
						}else{
							$.messager.alert('提示',res?res.msg:'菜单编辑失败！',	'error');
						}
						menu_treegrid.treegrid('reload');
					},
					onSubmit : function(){
						$.messager.progress({
							text : '数据提交中....',
							interval : 100
						});	
					}
				});
			}
		} ]
	}).dialog('close');
	
}

/**
 * 发布菜单，同步菜单数据到微信
 */
function release(){
	$.ajax({
		url :  domain + '/admin/menu/release',
		cache : false,
		dataType : "json",
		success : function(res) {
			fjx.closeProgress();
			if(res && '1' == res.code){
				fjx.showMsg(msg.b);
			}else{
				$.messager.alert('提示',	res?res.msg:'发布失败！','error');
			}
		}
		//beforeSend : function () {
		//	fjx.progress("正在发布，请稍等！");
		//},
		//complete : function(){
		//	fjx.closeProgress();
		//},
		//error : function(){
		//	fjx.closeProgress();
		//}
	});
}

/**
 * 添加菜单
 */
function appMenu() {
	menuForm.form('clear');
	if(!selectNode){
		var roots = menu_treegrid.treegrid('getRoots');
		if(roots && roots.length > 2){
			$.messager.alert('提示',	'最多可3个一級菜单',	'warning');
			return false;
		}
		menuForm.form('load', {
			'menu_level' : 1
		});
	}else if(parseInt(selectNode.menu_level) == 2){
		$.messager.alert('提示','最多只允许添加到二級菜单',	'warning');
		return false;
	}else{
		var children = menu_treegrid.treegrid('getChildren',selectNode.id);
		if(children && children.length > 4){
			$.messager.alert('提示',	'最多可创建5个二級菜单',	'warning');
			return false;
		}
		menuForm.form('load', {
			'menu_level' : parseInt(selectNode.menu_level)+1,
			'parent_id' : selectNode.id
		});
	}
	menuDialog.dialog('open').dialog({
		title : '添加菜单'
	});
}

/**
 * 修改菜单名称
 */
function updatedMenu() {
	if (selectNode) {
		menuDialog.dialog('open').dialog({
			title : '修改菜单'
		});
		menuForm.form('clear');
		menuForm.form('load', {
			'id' : selectNode.id,
			'parent_id' : selectNode.parent_id,
			'name' : selectNode.name,
			'menu_level' : selectNode.menu_level
		});
	}else{
		$.messager.alert('提示', '请选择要编辑的菜单！', 'warning');
	}
}
/**
 * 删除菜单
 */
function removeMenu() {
	if (selectNode) {
		$.messager.confirm('请确认', '您要刪除删除【'+selectNode.name+'】？', function(r) {
			if (r) {
				$.ajax({
					url :  domain + '/admin/menu/delete',
					data : 'id='+selectNode.id,
					cache : false,
					dataType : "json",
					success : function(res) {
						if(res && '1' == res.code){
							fjx.showMsg('删除成功！');
							$("#action_none").find("p").html(msg.a);
							showActionContent('action_none');
						}else{
							$.messager.alert('提示',	res?res.msg:'刪除失败','error');
						}
						//刷新菜单
						menu_treegrid.treegrid('reload');
					}
				});
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的菜单', 'warning');
	}
}

/**
 * 显示菜单设置
 */
function thowSetting(node){
	//清除数据
	clearData();
	if(node.children){//有子菜单
		$("#action_none").find("p").html(msg.c);
		showActionContent('action_none');
		return false;
	}
	if(!node.type){//没有设置菜单动作
		showActionContent('action_index');
		return false;
	}
	var tips = "";
	var viewHtml = "";		//预览效果HTML
	if(node.type == 'click'){//菜单动作为点击类型
		tips = msg.d;
		if(node.action_type == 'material'){//数据源从素材读取
			var json = $.xml2json(node.xml_data);
			var msgType = json.MsgType;
			if(msgType == "text"){		//文本消息
				viewHtml = json.Content;
			}else if(msgType == "news"){	//图文消息
				viewHtml = xml2NewsHtml(node.xml_data,node.action_time, node.material_id);
			}
		}else if(node.action_type == 'api'){
			if(!node.app_name){
				viewHtml = "插件已经被删除，请重新配置！";
			}else{
				viewHtml = "从接口【"+node.app_name+"】中获得数据";
			}
		}
	}else if(node.type == 'view'){//菜单动作为点击链接
		tips = msg.e;
		viewHtml = node.url+"<a target='_blank' class='easyui-linkbutton' href='"+node.url+"'>&nbsp;查&nbsp;&nbsp;看&nbsp;</a>";
		
	}
	$("#view").find(".action_tips").html(tips);
	$("#viewDiv").html(viewHtml);
	$.parser.parse('#viewDiv');
	showActionContent('view');
}

/**
 * 显示内容
 */
function showActionContent(id){
	$(".action_content").hide();
	$("#"+id).show();
}


/**
 * 提交菜单响应规则
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType){
	msgActionForm.form('submit', {
		url : domain + '/admin/action/save',
		success : function(data) {
			fjx.closeProgress();
			var res = $.evalJSON(data);
			if(res && '1' === res.code){
				clearData();
				fjx.showMsg('设置成功！');
				var nodeId = selectNode.id;
				$.ajax({
					url :  domain + '/admin/menu/load',
					cache : false,
					async : false,
					dataType : "json",
					success : function(data) {
						menu_treegrid = menu_treegrid.treegrid('loadData',data);
					}
				});
				menu_treegrid.treegrid('select',nodeId);
			}else{
				$.messager.alert('提示',res?res.msg:'设置失败！','error');
			}
		},
		onSubmit : function(){   
			var menuId = selectNode.id;
			$("#menuId").val(menuId);
			if(selectNode.action_id){	//更新菜单才可能有action
				$("#msgActionId").val(selectNode.action_id);
			}
			$("#menuType").val("click");
			$("#materiaMsgType").val(respType);		//响应消息类型
			$("#msgReqType").val(req_type);
			$("#eventType").val(event_type);
			if(respType === 'text'){
				$("#msgActionType").val("material");
				var txtContent = $.trim($("#replyText").val());
				if(!txtContent || "" == txtContent){
					$.messager.alert('提示', '请输入要回复的內容', 'warning');
					return false;
				}
				$("#txtContent").val(txtContent);
			}
			if(respType === 'news'){
				$("#msgActionType").val("material");
				var newsId = $("#newsId").val();
				if(!newsId || newsId == ''){
					$.messager.alert('提示', '请选择素材', 'warning');
					return false;
				}
				$("#msgMaterialId").val(newsId);
			}
			if(respType === 'api'){
				$("#msgActionType").val("api");
				var app_id = busiapi_combobox.combobox("getValue");
				if(!app_id){
					$.messager.alert('提示', '请选择业务接口', 'warning');
					return false;
				}
				$("#msgExtAppId").val(app_id);
			}
			if(respType === 'url'){
				$("#menuType").val("view");
				var app_url = busiweb_combobox.combobox("getValue");
				//如果app_url，表示用户没有选择应用的链接地址
				if(!app_url){
					//读取用户输入的URL
					app_url = busiweb_combobox.combobox("getText");
				}
				if(!app_url || '' == app_url){
					$.messager.alert('提示', '请填写URL或选择应用', 'warning');
					return false;
				}
				$("#menuUrl").val(app_url);
			}
			$.messager.progress({
				text : '数据提交中....',
				interval : 100
			});
		}
	});
}

function updateMsgView(){
	clearData();
	$("#editType").val("edit");
	$("#msgActionId").val(selectNode.action_id);
	if(selectNode.type == 'click'){//菜单动作为点击类型
		var tabIndex;
		if(selectNode.action_type == 'material'){//数据源从素材读取
			var json = $.xml2json(selectNode.xml_data);
			var msgType = json.MsgType;
			if(msgType == "text"){		//文本消息
				tabIndex = 0;
				$("#replyText").val(json.Content);
			}else if(msgType == "news"){	//图文消息
				tabIndex = 4;
				newsId = selectNode.material_id;
				viewHtml = xml2NewsHtml(selectNode.xml_data,selectNode.action_time, selectNode.material_id);
				$("#preview_news").html(viewHtml);				
			}
		}else if(selectNode.action_type == 'api'){
			tabIndex = 5;
			if (selectNode.app_id) {
				busiapi_combobox.combobox("select",selectNode.app_id);
			}
		}
		$("#edit_tabs").tabs("select",tabIndex);
		showActionContent("action_edit");
		
	}else if(selectNode.type == 'view'){//菜单动作为点击链接
		tips = msg.e;
		busiweb_combobox.combobox("setValue",selectNode.url);
		showActionContent("action_url");
	}
}