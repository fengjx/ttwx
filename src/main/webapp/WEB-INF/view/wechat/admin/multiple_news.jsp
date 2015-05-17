<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>多图文本</title>
</head>
<body>

	<div class="main_bd">
		<div class="media_preview_area">
			<div class="appmsg multi editing">
				<div class="appmsg_content" id="js_appmsg_preview">
					<div class="js_appmsg_item" data-id="1" data-fileid=""	id="appmsgItem1">
						<!-- 缓存编辑器内容 -->
						<input type="hidden" class="appmsgContent" id="appmsgContent1" data-id="1" value=""/>
						<!-- 缓存自定义URL，select=1表示选择了URL -->
						<input type="hidden" class="appmsgUrl" id="appmsgUrl1" data-id="1" value="" select=""/>
						<div class="appmsg_info">
							<em class="appmsg_date"></em>
						</div>
						<div class="cover_appmsg_item">
							<h4 class="appmsg_title" id="appmsg_title1">
								<a target="_blank" onclick="return false;" href="javascript:void(0);">标题</a>
							</h4>
							<div class="appmsg_thumb_wrp">
								<img src="" id="appmsg_thumb1" class="js_appmsg_thumb appmsg_thumb"> 
								<i class="appmsg_thumb default">封面图片</i>
							</div>
							<div class="appmsg_edit_mask">
								<a href="javascript:;" data-id="1" class="icon18_common edit_gray js_edit" onclick="return false;">编辑</a>
							</div>
						</div>
					</div>
					
					
					<div class="appmsg_item js_appmsg_item " data-id="2" data-fileid="" id="appmsgItem2">
						<!-- 缓存编辑器内容 -->
						<input type="hidden" class="appmsgContent" id="appmsgContent2" data-id="2" value=""/>
						<!-- 缓存自定义URL，select=1表示选择了自定义菜单 -->
						<input type="hidden" class="appmsgUrl" id="appmsgUrl2" data-id="2" value="" select=""/>
						<img src="" id="appmsg_thumb2" class="js_appmsg_thumb appmsg_thumb"> 
						<i class="appmsg_thumb default">缩略图</i>
						<h4 class="appmsg_title" id="appmsg_title2">
							<a target="_blank" href="javascript:void(0);" onclick="return false;">标题</a>
						</h4>
						<div class="appmsg_edit_mask">
							<a href="javascript:void(0);" onclick="return false;" data-id="2" class="icon18_common edit_gray js_edit">编辑</a>
							<a href="javascript:void(0);" onclick="return false;" data-id="2" class="icon18_common del_gray js_del">删除</a>
						</div>
					</div>
					
					<!-- js加载更多 -->
					
				</div>
				<div class="appmsg_add">
					<a href="javascript:void(0);" id="js_add_appmsg" onclick="addAppmsg();"> &nbsp; 
						<i class="icon24_common add_gray">添加一条</i> 
					</a>
				</div>
			</div>
		</div>
		<div class="media_edit_area">
			<div id="js_appmsg_editor">
				<div class="appmsg_editor" style="margin-top: 0px;">
					<div class="inner">
						<div class="appmsg_edit_item">
							<label class="frm_label" for="">标题</label> 
							<span class="frm_input_box">
								<input id="news_title" type="text" class="frm_input js_title">
							</span>
						</div>
						<div class="appmsg_edit_item">
							<label class="frm_label" for=""> <strong class="title">封面</strong>
								<p class="js_cover_tip tips r">大图建议尺寸：360像素 * 200像素</p> </label>
							<div class="frm_input_box">
								<div class="upload_box">
									<div class="upload_area">
										<script id="upload_container" name="upload_container" type="text/plain"></script>
										<a class="upload_access" onclick="upImage();" href="javascript:void(0);" id="js_appmsg_upload_cover"	width="50" height="22">
											<i class="icon18_common upload_gray"></i> 上传
										</a>
										<ul class="upload_file_box" style="display: none;"></ul>
									</div>
								</div>
								&nbsp;
								<p class="js_cover upload_preview" style="display: none;">
									<img src="" id="preview" >
									<a onclick="deleteImage();" href="javascript:void(0);" class="js_removeCover">删除</a>
								</p>
							</div>
							<%--<p class="frm_tips">
								<label class="frm_checkbox_label js_show_cover_pic selected"
									for=""> <i class="icon_checkbox"></i> <input
									type="checkbox" checked="" class="frm_checkbox">
									封面图片显示在正文中 </label>
							</p>--%>
							</div>
						<div class="appmsg_edit_item">
							<label for="" class="frm_label"> 
								<label for="" class="frm_checkbox_label js_show_cover_pic ">
									<i class="icon_checkbox"></i> 
									<input type="checkbox" class="frm_checkbox">
									链接地址：<span class="tips l">（用户点击图文跳转的URL）</span>
								</label> 
							</label> 
							<span id="span_url" style="display:none;">
								<input type="text" value="http://" id="busiapp_url" style="width:350px;">
							</span>
						</div>
						<div class="appmsg_edit_item content_edit" id="js_ueditor">
							<label class="frm_label" for=""> <strong class="title">正文</strong>
							<span class="tips l">（如果不填写链接地址，可以从编辑器中编辑內容，系统会生成编辑內容的URL地址）</span>
							</label>
							<div class="edui_editor_wrp edui-default" id="js_editor">
								<!-- 文本编辑器 -->
								<script id="container" name="content" type="text/plain"></script>
								
							</div>
						</div>
					</div>
					<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
					<i class="arrow arrow_in" style="margin-top: 0px;"></i>
					<div class="mask" style="display: none;"></div>
				</div>
			</div>
		</div>
		<div class="tool_area">
			<div class="tool_bar border tc">
				<!-- <span class="btn btn_input btn_default" id="js_preview"><button onclick="javascript:history.back();">返回</button></span> -->
				<span class="btn btn_input btn_primary" id="js_submit"><button onclick="submitAppMsg();">保存</button>
				</span>
			</div>
		</div>
	</div>



	<form id="news_form" action="" method="POST">
		<input type="hidden" id="materialId" name="id" value="${id}"/>
		<input type="hidden" id="msgTyype" name="msg_type" value="news"/>
		<input type="hidden" id="xml_data" name="xml_data" value=""/>
		<input type="hidden" id="contentsJson" name="contentsJson"/>
	</form>
	
	
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<%@include file="/WEB-INF/view/common/inc/ueditor.jsp"%>
	<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/appmsg_edit.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=domain %>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=domain %>/dwr/interface/materialService.js"></script>
	<script src="<%=resourceUrl %>/script/admin/multiple_news.js?v=2014052303" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		var material_id = '${id}';
	</script>
	
</body>