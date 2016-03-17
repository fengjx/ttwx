<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>多图文本</title>
	<link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css" />
	<link href="${resourceUrl}/css/appmsg_edit.css?v=2015053001" rel="stylesheet" type="text/css" />
	<jsp:include page="/WEB-INF/view/common/inc/ueditor.jsp"></jsp:include>
	<script	src="${resourceUrl}/script/wechat/admin/multiple_news.js?v=2015053003" type="text/javascript"></script>
	<script type="text/javascript">
		var material_id = '${id}';
	</script>
</head>
<body class="no-skin">
	<div class="breadcrumbs" id="breadcrumbs">
		<ol class="breadcrumb">
			<li>微信管理</li>
			<li>素材管理</li>
			<li class="active">添加素材</li>
		</ol>
	</div>

	<div class="page-content">
		<div class="main_bd">
		<div class="media_preview_area">
			<div class="appmsg multi editing">
				<div class="appmsg_content" id="js_appmsg_preview">
					<div class="js_appmsg_item" data-id="1" data-fileid=""
						id="appmsgItem1">
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
								<a href="javascript:;" onclick="editAppmsg(1);" data-id="1"
								   class="icon18_common edit_gray js_edit">编辑</a>
							</div>
						</div>
					</div>

					<div class="appmsg_item js_appmsg_item " data-id="2"
						data-fileid="" id="appmsgItem2">
						<!-- 缓存编辑器内容 -->
						<input type="hidden" class="appmsgContent" id="appmsgContent2"
							data-id="2" value="" />
						<!-- 缓存自定义URL，select=1表示选择了自定义菜单 -->
						<input type="hidden" class="appmsgUrl" id="appmsgUrl2"
							data-id="2" value="" select="" /> <img src=""
							id="appmsg_thumb2" class="js_appmsg_thumb appmsg_thumb">
						<i class="appmsg_thumb default">缩略图</i>
						<h4 class="appmsg_title" id="appmsg_title2">
							<a target="_blank" href="javascript:void(0);">标题</a>
						</h4>
						<div class="appmsg_edit_mask">
							<a href="javascript:void(0);" onclick="editAppmsg(2);"
								data-id="2" class="icon18_common edit_gray js_edit">编辑</a> <a
								href="javascript:void(0);" onclick="deleteAppmsg(2);"
								data-id="2" class="icon18_common del_gray js_del">删除</a>
						</div>
					</div>
					<!-- js加载更多 -->
				</div>
				<div class="appmsg_add">
					<a href="javascript:void(0);" id="js_add_appmsg"
						onclick="addAppmsg();"> &nbsp; <i
						class="icon24_common add_gray">添加一条</i>
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
								<input id="news_title" type="text" class="frm_input js_title" style="border: 0px solid; padding: 0px 0px 0px">
							</span>
						</div>
						<div class="appmsg_edit_item">
							<label class="frm_label" for=""> <strong class="title">封面</strong>
								<p class="js_cover_tip tips r">大图建议尺寸：360像素 * 200像素</p>
							</label>
							<div class="frm_input_box">
								<div class="upload_box">
									<div class="upload_area">
										<script id="upload_container" name="upload_container"
												type="text/plain"></script>
										<a class="upload_access" onclick="upImage();" href="javascript:void(0);"
										   id="js_appmsg_upload_cover" width="50" height="22">
											<i class="icon18_common upload_gray"></i> 上传
										</a>
										<ul class="upload_file_box" style="display: none;"></ul>
									</div>
								</div>
								&nbsp;
								<p class="js_cover upload_preview" style="display: none;">
									<img src="" id="preview">
									<a onclick="deleteImage();" href="javascript:void(0);" class="js_removeCover">删除</a>
								</p>
							</div>
						</div>
						<div class="appmsg_edit_item">
							<label for="" class="frm_label">
								<label for="" class="frm_checkbox_label js_show_cover_pic ">
									<i class="icon_checkbox"></i>
									<input type="checkbox" class="frm_checkbox"> 链接地址：<span class="tips l">（用户点击图文跳转的URL）</span>
								</label>
							</label>
							<span id="span_url" style="display: none;">
								<sys:extapp id="busiapp_url" name="busiapp_url" appType="web" allowInput="true" />
							</span>
						</div>
						<div class="appmsg_edit_item content_edit" id="js_ueditor">
							<label class="frm_label" for=""> <strong class="title">正文</strong>
								<span class="tips l">（如果不填写链接地址，可以从编辑器中编辑內容，系统会生成编辑內容的URL地址）</span>
							</label>
							<div class="edui_editor_wrp edui-default" id="js_editor">
								<button id="btn-template" type="button" class="btn btn-primary btn-block">
									选择模板
								</button>
								<!-- 文本编辑器 -->
								<script id="container" name="content" type="text/plain"></script>
							</div>
						</div>
					</div>
				</div>
			</div>
			<i class="arrow arrow_out" style="margin-top: 0px;"></i>
			<i class="arrow arrow_in" style="margin-top: 0px;"></i>
			<div class="mask" style="display: none;"></div>
		</div>
		<div class="tool_area">
			<div class="tool_bar border tc">
				<span style="margin-right: 40px;" id="js_submit">
					<button class="btn btn-primary btn-sm"	onclick="submitNewsForm();">保存</button>
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

	<form id="news_form" action="" method="POST">
		<input type="hidden" id="materialId" name="id" value="${id}"/>
		<input type="hidden" id="msgTyype" name="msg_type" value="news"/>
		<input type="hidden" id="xml_data" name="xml_data" value=""/>
		<input type="hidden" id="contentsJson" name="contentsJson"/>
		<input type="hidden" id="fileName" name="file_name" value="${fname}">
		<input type="hidden" id="msgFlag" name="msgFlag" value="">
		<input type="hidden" id="wxUserId" name="wxUserId" value="">
	</form>

	<div id="templateModal" class="modal" tabindex="-1" role="dialog" >
		<div class="modal-dialog" style="width: 550px;">
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



