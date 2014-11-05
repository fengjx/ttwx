<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 素材列表包含页 material.jsp | menu.jsp-->
<!-- 分页 -->
<div class="page" style="border:1px solid #ddd;"></div>

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
<div class="page" style="border:1px solid #ddd;"></div>
