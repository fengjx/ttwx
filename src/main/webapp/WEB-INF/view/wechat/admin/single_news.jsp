<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>单图文本</title>
<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
<link href="${resourceUrl}/css/material.css?v=2014030901"
	rel="stylesheet" type="text/css" />
<link href="${resourceUrl}/css/appmsg_edit.css?v=2014030901"
	rel="stylesheet" type="text/css" />
<jsp:include page="/WEB-INF/view/common/inc/ueditor.jsp"></jsp:include>
<script src="${resourceUrl}/js/jquery.json-2.4.min.js"
	type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.xml2json.js"
	type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.form.js" type="text/javascript"></script>
<script
	src="${resourceUrl}/script/wechat/admin/single_news.js?v=2015053003"
	type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		var material_id = '${id}';
	</script>
</head>
<body class="no-skin">
	<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
	<div class="main-container">
		<jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp"></jsp:include>
		<div class="main-content">
			<div id="context" class="main-content-inner">
				<div class="breadcrumbs" id="breadcrumbs">
					<ol class="breadcrumb">
						<li><a href="${adminPath}">后台管理</a></li>
						<li><a href="${adminPath}/wechat">平台管理</a></li>
						<li><a href="${adminPath}/wechat/material">素材管理</a></li>
						<li class="active">添加素材</li>
					</ol>
				</div>
				<div class="page-content">
					<div class="main_bd">
					<div class="media_preview_area">
						<div class="appmsg  editing">
							<div id="js_appmsg_preview" class="appmsg_content">
								<div id="appmsgItem1" data-fileid="" data-id="1"
									class="js_appmsg_item ">
									<h4 class="appmsg_title">
										<a onclick="return false;" href="javascript:void(0);"
											target="_blank">标题</a>
									</h4>
									<div class="appmsg_info">
										<em class="appmsg_date"></em>
									</div>
									<div class="appmsg_thumb_wrp">
										<img class="js_appmsg_thumb appmsg_thumb" src=""> <i
											class="appmsg_thumb default">封面图片</i>
									</div>
									<p class="appmsg_desc"></p>

								</div>
							</div>
						</div>
					</div>
					<div class="media_edit_area">
						<div id="js_appmsg_editor">
							<div class="appmsg_editor" style="margin-top: 0px;">
								<div class="inner">
									<div class="appmsg_edit_item">
										<label for="" class="frm_label">标题</label> <span
											class="frm_input_box"> <input id="news_title"
											type="text" class="frm_input js_title">
										</span>
									</div>
									<div class="appmsg_edit_item">
										<label for="" class="frm_label"> <strong class="title">封面</strong>
											<p class="js_cover_tip tips r">大图建议尺寸：360像素 * 200像素</p>
										</label>
										<div class="frm_input_box">
											<div class="upload_box">
												<div class="upload_area">
													<script id="upload_container" name="upload_container"
														type="text/plain"></script>
													<a id="js_appmsg_upload_cover" href="javascript:void(0);"
														onclick="upImage();" class="upload_access" width="50"
														height="22"> <i class="icon18_common upload_gray"></i>
														上传
													</a>

												</div>
											</div>
											<p class="js_cover upload_preview" style="display: none;">
												<img src="" id="preview"> <a class="js_removeCover"
													href="javascript:void(0);" onclick="deleteImage();">删除</a>
											</p>
										</div>
										<%--<p class="frm_tips">
                                        <label for="" class="frm_checkbox_label js_show_cover_pic selected">
                                            <i class="icon_checkbox"></i>
                                            <input type="checkbox" class="frm_checkbox" checked="checked"> 封面图片显示在正文中
                                        </label>
                                    </p>--%>
									</div>

									<p>
										<a class="js_addDesc" href="javascript:void(0);"
											onclick="addDesc();">添加摘要</a>
									</p>
									<div class="js_desc_area dn appmsg_edit_item"
										style="display: none;">
										<label for="" class="frm_label">摘要</label> <span
											class="frm_textarea_box"> <textarea
												class="js_desc frm_textarea"></textarea>
										</span>
									</div>

									<div class="appmsg_edit_item">
										<label for="" class="frm_label"> <label for=""
											class="frm_checkbox_label js_show_cover_pic "> <i
												class="icon_checkbox"></i> <input type="checkbox"
												class="frm_checkbox"> 链接地址<span class="tips l">（用户点击图文跳转的URL）</span>
										</label>
										</label> <span id="span_url" style="display: none;">
											<sys:extapp id="busiapp_url" name="busiapp_url" appType="web" allowInput="true" />
										</span>
									</div>
									<div id="js_ueditor" class="appmsg_edit_item content_edit">
										<label for="" class="frm_label"> <strong class="title">正文</strong>
											<span class="tips l">（如果不填写链接地址，可以从编辑器中编辑內容，系統会生成编辑內容的URL地址）</span>
										</label>
										<div id="js_editor" class="edui_editor_wrp edui-default">
											<button id="btn-template" type="button" class="btn btn-primary btn-block">
												选择模板
											</button>
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
							<span id="js_submit" style="margin-right: 40px;">
								<button class="btn btn-primary btn-sm" onclick="submitNewsForm(0);">保存</button>
							</span>
							<span id="js_preview">
								<button class="btn btn-info btn-sm" onclick="submitNewsForm(1);">预览消息</button>
							</span>
							<span id="js_both" style="margin-right: 40px;">
								<button class="btn btn-success btn-sm" onclick="submitNewsForm(2);">保存并群发</button>
							</span>
						</div>
					</div>
				</div>
				</div>
				<form id="news_form" method="POST">
					<input type="hidden" id="materialId" name="id" value="${id}" /> <input
						type="hidden" id="msgTyype" name="msg_type" value="news" /> <input
						type="hidden" id="xml_data" name="xml_data" value="" /> <input
						type="hidden" id="contentsJson" name="contentsJson" />
						<input type="hidden" id="fileName" name="file_name" value="${fname}">
						<input type="hidden" id="msgFlag" name="msgFlag" value="">
						<input type="hidden" id="wxUserId" name="wxUserId" value="">
				</form>
			</div>
		</div>
	</div>


	<div id="templateModal" class="modal" tabindex="-1" role="dialog" >
		<div class="modal-dialog" style="width: 550px;right: 413px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">应用编辑</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/WEB-INF/view/wechat/admin/wechart_template.jsp"></jsp:include>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>
</html>

