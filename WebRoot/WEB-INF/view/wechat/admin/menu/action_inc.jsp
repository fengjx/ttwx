<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<script type="text/javascript">
	var extType = '${param.extType}';
	var req_type = '${param.req_type}';
	var event_type = '${param.event_type}';
</script>
<script src="<%=resourceUrl %>/script/admin/action_inc.js?v=2014050901" type="text/javascript" charset="UTF-8"></script>
	<form id="msgActionForm" method="post" onsubmit="return false;">
 		<!-- 这个参数区分是更新还是保存 -->
 		<input type="hidden" id="editType" name="editType" value="">
 		
 		<input type="hidden" id="msgActionId" name="id" value="">
 		<input type="hidden" id="extType" name="ext_type" value="">
 		<input type="hidden" id="msgReqType" name="req_type" value="">
 		<input type="hidden" id="eventType" name="event_type" value="">
 		<input type="hidden" id="msgActionType" name="action_type" value="">
 		<input type="hidden" id="msgRespType" name="materiaMsgType" value="">
 		<input type="hidden" id="msgMaterialId" name="materiaId" value="">
 		<input type="hidden" id="msgExtAppId" name="extAppId" value="">
 		
 		<c:if test="${param.req_type eq 'text' }">
 			关键字：<input class="" type="text" id="msgKeyWord" name="key_word" value=""/>
 		</c:if>
 		
 		<c:if test="${param.event_type eq 'CLICK' }">
 			<input type="hidden" id="menuId" name="menuId" value="">
	 		<input type="hidden" id="menuType" name="menuType" value="">
	 		<input type="hidden" id="menuUrl" name="menuUrl" value="">
 		</c:if>
 		<input type="hidden" id="txtContent" name="materiaContent" value="">
 	</form>
      		
	<div id="edit_tabs" class="easyui-tabs" data-options="fit:true" style="height:390px">
        <div title="<span class='tt-inner tab_text'><i class='icon_msg_sender'></i>文字</span>" style="padding:10px">
            <div style="width: 510px">
	            <div id="js_textArea">
	            	<textarea class="textarea field" id="replyText" name="replyText" ></textarea>
	           	</div>
	            <div class="editor_toolbar">
			        <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch"></a>
			        <span style="margin-left: 342px;">还可以输入<em>600</em>字</span>
			    </div>
			    <div class="clear"></div>
			    <div id="txt_btn" style="float: left;">
			    	<c:if test="${ !('hide' eq param.btn_return) }">
				    	 <span class="btn btn_input btn_default">
				    	 	<button onclick="showActionContent('action_index')">返回</button>
						 </span>
					 </c:if>
			    	 <span class="btn btn_input btn_primary">
			    	 	<button onclick="submitMsgActionForm('text')">保存</button>
					 </span>
			    </div>
		    </div>
        </div>
        <div title="<span class='tt-inner tab_img'><i class='icon_msg_sender'></i>图片</span>" style="padding:10px">
            <p>暂不支持</p>
        </div>
        <div title="<span class='tt-inner tab_audio'><i class='icon_msg_sender'></i>语音</span>" style="padding:10px">
        	<p>暂不支持</p>
        </div>
        <div title="<span class='tt-inner tab_video'><i class='icon_msg_sender'></i>视频</span>" style="padding:10px">
        	<p>暂不支持</p>
        </div>
        <div title="<span class='tt-inner tab_appmsg'><i class='icon_msg_sender'></i>图文</span>" style="padding:10px">
       		<input class="field" type="hidden" id="newsId" value="">
       		<div style="width: 100%;">
	       		图文预览
	       		<div class="clear"></div>
	       		<div style="float: left;">
	       			<div id="preview_news" style="min-height:300px;max-height:500px; width:350px;float: left; border: solid 1px #E0ECFF;">
			    		<!-- js加载 预览效果 -->
			    	</div>
			    	<div style="height:35px; width:410px; float: left;margin-top: 270px; ">
	       				<span class="btn btn_input btn_default">
							<input type="hidden" id="newsId" name="newsId" style="width:250px;">
				    	 	<button onclick="openMaterialDialog();">选择</button>
						 </span>
						 <c:if test="${ !('hide' eq param.btn_return) }">
							 <span class="btn btn_input btn_default">
				    	 		<button onclick="showActionContent('action_index');">返回</button>
							 </span>
						 </c:if>
				    	 <span class="btn btn_input btn_primary">
				    	 	<button onclick="submitMsgActionForm('news');">保存</button>
						 </span>
	       			</div>
	       		</div>
	       		<%--<div class="clear"></div>
	    		<div id="txt_btn" style="float: left;">
			    	 <span class="btn btn_input btn_default">
			    	 	<button onclick="showActionContent('action_index');">返回</button>
					 </span>
			    	 <span class="btn btn_input btn_primary">
			    	 	<button onclick="submitMsgActionForm('news');">保存</button>
					 </span>
			    </div> --%>
		   </div>
        </div>
        <div title="<span class='tt-inner tab_commondity_appmsg'><i class='icon_msg_sender'></i>扩展插件</span>" style="padding:10px">
			<div style="width: 500px;">
				<div>
					选择插件：
					<input class="field" type="text" id="busiapp_id" style="width:250px;">
		    	</div>
		    	<div style="height:200px;border: solid 1px #E0ECFF;">
		    		<!-- js加载 预览效果 -->
		    	</div>
	    		<div id="txt_btn" style="float: left;">
	    			<c:if test="${ !('hide' eq param.btn_return) }">
				    	 <span id="js_preview" class="btn btn_input btn_default">
				    	 	<button onclick="showActionContent('action_index');">返回</button>
						 </span>
					 </c:if>
			    	 <span id="js_submit" class="btn btn_input btn_primary">
			    	 	<button onclick="submitMsgActionForm('api');">保存</button>
					 </span>
			    </div>
		    </div>
        </div>
    </div>
    
    <div id="materialDialog" style="display: none;">
		<div style="width:99.5%;">
			<%@include file="/WEB-INF/view/wechat/admin/material/material_list_inc.jsp" %>
		</div>
	</div>
    
    <style type="text/css">
		<!--
		.textarea {
			width: 507px;
			height: 120px;
			resize: none;
		}
		-->
	</style>
