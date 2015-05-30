/**
 * 素材管理
 * 2014年3月12日
 * fengjx
 */

//分页对象
var page;

$(function(){
	
	loadMaterials(1);
	//删除按钮点击事件
	$(".js_del").on("click",function(){
		var id = $(this).attr("data-id");
		if(id && id != ''){
			deleteMaterial(id);
		}
	});
	
});


/**
 * 加载素材
 */
function loadMaterials(page){
	
	$.ajax({
		url :  domain + '/admin/wechat/material/page?msg_type=news',
		data : "pageNumber="+page+"&pageSize=10",
		cache : false,
		dataType : "json",
		success : function(data) {
			var html = "";
			$.each(data.list, function(i,rowData){
				var material_id = rowData.id;
				var msg_type = rowData.msg_type;
				var in_time = rowData.in_time;
				html = xml2NewsHtml(rowData.xml_data,in_time,material_id,'edit');	//将xml数据转成html代码（wechatUtils.js）
				if(i%2 == 0){	
					$("#appmsgList2").append(html);
				}else{
					$("#appmsgList1").append(html);
				}
	  		});
		}
	});
}

function deleteMaterial(id){
	$.messager.confirm('请确认', '您要刪除素材？删除后无法恢复！', function(r) {
		if (r) {
			$.ajax({
				url :  domain + '/admin/material/delete',
				data : "id="+id,
				cache : false,
				dataType : "json",
				success : function(data) {
					if(data && '1' == data.code){
						fjx.showMsg('删除成功！');
						$(".inner").find("div").remove();
						loadMaterials(1,3);
					}else{
						$.messager.alert('提示', res?res.msg:'删除失败！','error');
					}
				}
			});
		}
	});
}