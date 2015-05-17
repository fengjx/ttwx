<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<%=resourceUrl %>/jQuery-Paging/jquery.paging.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/jQuery-Paging/jquery.bootstrap-paging.js?2015042501" type="text/javascript" charset="UTF-8"></script>
<!-- 素材列表包含页 material.jsp | menu.jsp-->
<!-- 分页 -->
<ul class="pagination">
	<li class="am-pagination-prev ">
		<<
	</li>
	<li>
		<
	</li>
	<li>
		#n
	</li>
	<li>
		#n
	</li>
	<li>
		#c
	</li>
	<li>
		#n
	</li>
	<li>
		#n
	</li>
	<li>
		>
	</li>
	<li>
		>>
	</li>
</ul>


<div id="appmsgList" class="appmsg_list">
	<div class="appmsg_col">
		<div id="appmsgList1" class="inner">
			<span class="create_access"> 
				<i class="icon42_common add_gray"></i> 
				<a target="_blank" href="<%=domain %>/admin/material/single"><i class="icon_appmsg_create"></i><strong>单图消息</strong></a> 
				<a target="_blank" href="<%=domain %>/admin/material/multiple"><i class="icon_appmsg_create multi"></i><strong>多图消息</strong></a> 
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

<!-- 分页 -->
<ul class="pagination">
	<li class="am-pagination-prev ">
		<<
	</li>
	<li>
		<
	</li>
	<li>
		#n
	</li>
	<li>
		#n
	</li>
	<li>
		#c
	</li>
	<li>
		#n
	</li>
	<li>
		#n
	</li>
	<li>
		>
	</li>
	<li>
		>>
	</li>
</ul>


<script>

	$(function(){
		$(".pagination").easyPaging(500, {
			"perpage": 15,
			"elements": 0,
			onSelect: function(page) {
				$("#page-display").remove();
				$(".am-pagination").before("<small id='page-display' class='am-fl'>第"+page+"页，显示"+(this.slice[0]+1)+"~"+this.slice[1]+"条，共"+this.number+"条</small>");
			}
		});
	});

</script>