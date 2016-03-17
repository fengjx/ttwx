<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<div>
	<button id="btn-refresh" type="button" class="btn btn-primary btn-block">
		<i class="glyphicon glyphicon-refresh"></i>
		刷&nbsp;&nbsp;&nbsp;&nbsp;新
	</button>
</div>
<div id="appmsgList" class="appmsg_list">
	<div class="appmsg_col">
		<div id="appmsgList1" class="inner">
			<span class="create_access"> 
				<i class="icon42_common add_gray"></i> 
				<a href="${domain}/admin/wechat/material/single"><i class="icon_appmsg_create"></i><strong>单图消息</strong></a>
				<a href="${domain}/admin/wechat/material/multiple"><i class="icon_appmsg_create multi"></i><strong>多图消息</strong></a>
			</span>
		</div>
	</div>
	&nbsp;
	<div class="appmsg_col">
		<div id="appmsgList2" class="inner">
			<!-- js加载 -->			
		</div>
	</div>
</div>
<div>
	<button id="btn-more" type="button" class="btn btn-outline btn-info btn-block">
		<i class="glyphicon glyphicon-circle-arrow-down"></i>
		更&nbsp;&nbsp;&nbsp;&nbsp;多
	</button>
</div>
<script>
	var curPage = 1;
	var pageSize = 4;
	var showType = '${param.showType}';
	$(function(){
		$("#btn-refresh").click(function () {
			$("#btn-more").show();
			loadMaterials(1);
		});
		$("#btn-more").click(function () {
			loadMaterials(curPage+1,true);
		});
		loadMaterials(curPage);
	});
	function loadMaterials(page,isAppend){
		$.ajax({
			url : '${domain}/admin/wechat/material/page?msg_type=news',
			data : "pageNumber="+page+"&pageSize="+pageSize,
			cache : false,
			dataType : "json",
			success : function(data) {
				curPage = page;
				if(!(curPage < data.totalPage)){
					$("#btn-more").hide();
				}
				// 是否追加数据
				if(!isAppend){
					// 删除当前素材列表，重新加载
					$(".inner").find("div").remove();
				}
				var html;
				$.each(data.list, function(i,rowData){
					var material_id = rowData.id;
					var msg_type = rowData.msg_type;
					var in_time = rowData.in_time;
					var file_name = rowData.file_name;
					html = xml2NewsHtml(rowData.xml_data,in_time,material_id,showType,file_name);
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
				url : '${domain}/admin/wechat/material/delete',
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
</script>
