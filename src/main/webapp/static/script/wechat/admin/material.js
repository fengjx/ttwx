/**
 * 素材管理
 * 2014年3月12日
 * fengjx
 */
// 当前页
var curPage;
$(function(){
	
	loadMaterials(1);

});

/**
 * 加载素材
 */
function loadMaterials(page,isAppend){
	var pageSize = 5;
	$.ajax({
		url :  adminPath + '/wechat/material/page?msg_type=news',
		data : "pageNumber="+page+"&pageSize="+pageSize,
		cache : false,
		dataType : "json",
		success : function(data) {
			// 是否清空之前数据
			if(!isAppend){
				// 删除当前素材列表，重新加载
				$(".inner").find("div").remove();
			}
			var html;
			$.each(data.list, function(i,rowData){
				var material_id = rowData.id;
				var msg_type = rowData.msg_type;
				var in_time = rowData.in_time;
				var file_name=rowData.file_name;
				html = xml2NewsHtml(rowData.xml_data,in_time,material_id,'edit',file_name);
				if(i%2 == 0){	
					$("#appmsgList2").append(html);
				}else{
					$("#appmsgList1").append(html);
				}
	  		});
		}
	});
}

/**
 * 删除素材
 * @param id
 */
function deleteMaterial(id){
	app.confirmModal("确定要刪除素材？删除后无法恢复！", function () {
		$.ajax({
			url :  adminPath + '/wechat/material/delete',
			data : "id="+id,
			cache : false,
			dataType : "json",
			success : function(data) {
				if(data && '1' == data.code){
					app.ok("删除成功！");
					// 删除当前素材列表，重新加载
					$(".inner").find("div").remove();
					loadMaterials(1);
				}else{
					app.error(data?data.msg:'删除失败！');
				}
			}
		});
	});
};