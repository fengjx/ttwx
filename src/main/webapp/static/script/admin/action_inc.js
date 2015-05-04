
//添加消息动作
var msgActionForm;
//扩展应用api
var busiapi_combobox;
//扩展web应用
var busiweb_combobox;
//选择素材弹出框
var materialDialog;

$(function(){


	var url = domain+'/admin/extapp/list?app_type=api&msg_type='+req_type;
	if (extType !== '') {
		url = domain+'/admin/extapp/list?app_type=restful';
	}
	//加载业务接口列表
	busiapi_combobox = $('#busiapp_id').combobox({
		url: url,
        method: 'post',
        valueField:'id',
        textField:'name',
        groupField:'group_name',
        editable:false
	}); 
	//加载web应用列表
	busiweb_combobox = $('#busiapp_url').combobox({   
		url: domain+'/admin/extapp/list?app_type=web',
        method: 'get',
        valueField:'app_url',
        textField:'name',
        groupField:'group_name'
	}); 
	
	msgActionForm = $('#msgActionForm').form();
	materialDialog = $('#materialDialog').show().dialog({
		modal : true,		//模态窗口
		resizable : false,	//不能调整大小
		width:800,
		height:450,
		buttons : [ {
			text : '确定',
			handler : function() {
				var selectedView = $("div[class='appmsg selected']");
				var newsId = selectedView.attr("id");
				if(!selectedView || !newsId || newsId == ''){
					$.messager.alert('提示', '请选择素材', 'warning');
					return false;
				}
				$("#newsId").val(newsId);
				var preview_news = selectedView.removeClass("selected").html();
				$("#preview_news").html(preview_news);
				materialDialog.dialog("close");
			}
		} ]
	}).dialog('close');
	
	//选中图文素材效果
	$(".appmsg").live('click', function() {
		$(".appmsg").removeClass('selected');
		$(this).addClass("selected");
	});
});

//清除数据
function clearData(){
	msgActionForm.form("clear");
	$("#preview_news").html("");
	$(".field").val('');
}

/**
 * 选择素材
 */
function openMaterialDialog(){
	initMaterials();
	materialDialog.dialog('open').dialog({
		title : '选择素材'
	});
	
}

function initMaterials(){
	
	loadMaterials(1,3);
	$(".inner").find("div").remove();//将上一页html代码清除
	$('.page').pagination({
		showPageList:false,
		pageNumber:1,
		onSelectPage:function(pageNumber, pageSize){
			$(".inner").find("div").remove();		//将上一页html代码清除
			loadMaterials(pageNumber,pageSize);
		}
	});
}

/**
 * 加载素材
 */
function loadMaterials(page,rows){
	
	$.ajax({
		url :  domain + '/admin/material/page?msg_type=news',
		data : "page="+page+"&rows="+rows,
		cache : false,
		dataType : "json",
		success : function(data) {
			$('.page').pagination({
				total:data.total,
				pageSize:rows,
				pageNumber:page
			});
			var html = "";
			$.each(data.rows, function(i,rows){
				var material_id = rows.id;
				var msg_type = rows.msg_type;
				var in_time = rows.in_time;
				html = xml2NewsHtml(rows.xml_data,in_time,material_id,'select');	//将xml数据转成html代码（wechatUtils.js）
				if(i%2 == 0){	
					$("#appmsgList2").append(html);
				}else{
					$("#appmsgList1").append(html);
				}
	  		});
		}
	});
}