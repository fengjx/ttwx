<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>单图文本</title>
</head>
<body>
	<div class="main_bd" >
		<div class="media_preview_area">
			<div class="appmsg  editing">
				<div id="js_appmsg_preview" class="appmsg_content">
					<div id="appmsgItem1" data-fileid="" data-id="1" class="js_appmsg_item ">
						<h4 class="appmsg_title">
							<a onclick="return false;" href="javascript:void(0);" target="_blank">标题</a>
						</h4>
						<div class="appmsg_info">
							<em class="appmsg_date"></em>
						</div>
						<div class="appmsg_thumb_wrp">
							<img class="js_appmsg_thumb appmsg_thumb" src=""> 
							<i class="appmsg_thumb default">封面图片</i>
						</div>
						<p class="appmsg_desc"></p>

					</div>
				</div>
			</div>
		</div>
		<div class="media_edit_area" style="width: 80%;">
			<div id="js_appmsg_editor">
				<div class="appmsg_editor" style="margin-top: 0px;">
					<div class="inner">
						<div class="appmsg_edit_item">
							<label for="" class="frm_label">标题</label> 
							<span class="frm_input_box">
								<input id="news_title" type="text" class="frm_input js_title">
							</span>
						</div>
						<div class="appmsg_edit_item">
							<label for="" class="frm_label"> <strong class="title">封面</strong>
								<p class="js_cover_tip tips r">大图建议尺寸：360像素 * 200像素</p> </label>
							<div class="frm_input_box">
								<div class="upload_box">
									<div class="upload_area">
										<script id="upload_container" name="upload_container" type="text/plain"></script>
										<a id="js_appmsg_upload_cover" href="javascript:void(0);"
											onclick="upImage();" class="upload_access" width="50"
											height="22"> <i class="icon18_common upload_gray"></i> 上传
										</a>
										
									</div>
								</div>
								<p class="js_cover upload_preview" style="display: none;">
									<img src="" id="preview" >
									<a class="js_removeCover" href="javascript:void(0);" onclick="deleteImage();">删除</a>
				                </p>
							</div>
							<%--<p class="frm_tips">
								<label for="" class="frm_checkbox_label js_show_cover_pic selected">
									<i class="icon_checkbox"></i> 
									<input type="checkbox" class="frm_checkbox" checked="checked"> 封面图片显示在正文中 
								</label>
							</p>--%>
							</div>

						<%--<p>
							<a class="js_addDesc" href="javascript:void(0);" onclick="addDesc();">添加摘要</a>
						</p>--%>
						<div class="js_desc_area dn appmsg_edit_item">
							<label for="" class="frm_label">摘要</label> 
							<span class="frm_textarea_box">
								<textarea class="js_desc frm_textarea"></textarea>
							</span>
						</div>
						
						<div class="appmsg_edit_item">
							<label for="" class="frm_label"> 
								<label for="" class="frm_checkbox_label js_show_cover_pic ">
									<i class="icon_checkbox"></i> 
									<input type="checkbox" class="frm_checkbox">
									链接地址<span class="tips l">（用户点击图文跳转的URL）</span>
								</label> 
							</label> 
							<span id="span_url" style="display:none;">
								<input type="text" value="http://" id="busiapp_url" style="width:350px;">
							</span>
						</div>
						<div id="js_ueditor" class="appmsg_edit_item content_edit">
							<label for="" class="frm_label">
				                <strong class="title">正文</strong>
				                <span class="tips l">（如果不填写链接地址，可以从编辑器中编辑內容，系統会生成编辑內容的URL地址）</span>
				            </label>
							
							<div id="js_editor" class="edui_editor_wrp edui-default">
								<script id="container" name="content" type="text/plain"></script>
							</div>
							
						</div>
						
						
					</div>
					<i class="arrow arrow_out" style="margin-top: 0px;"></i> <i
						class="arrow arrow_in" style="margin-top: 0px;"></i>
					<div class="mask" style="display: none;"></div>
				</div>
			</div>
		</div>
		<div class="tool_area">
			<div class="tool_bar border tc">
				<!-- <span id="js_preview" class="btn btn_input btn_default"><button onclick="javascript:history.back();">返回</button></span>  -->
				<span id="js_submit" class="btn btn_input btn_primary"><button onclick="submitNewsForm();">保存</button>
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
	<script src="<%=resourceUrl %>/script/admin/single_news.js?v=2014091401" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		var material_id = '${id}';
	</script>
</body>
</html>