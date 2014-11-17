<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>配置授权</title>
</head>
<script type="text/javascript">
	var valid_state = '${wechatAccount.valid_state}';
</script>
<body>
	<div>
		<div class="alert alert-info">
			<p>在微信公众平台<strong>“功能”</strong>-&gt;<strong>“高级功能”</strong>-&gt;<strong>“开发模式”，</strong>将<strong>AppId和AppSecret</strong>，然后填入下边表单。</p>
			<p>通过认证的订阅号，拥有“自定义菜单”；通过认证的服务号，拥有“自定义菜单”和所有“高级接口”</p>
			<p><strong>配置步骤：</strong></p>
			<p id="step1">1、<span class="step-info">将授权信息配置到公众平台&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step2">2、<span class="step-info">从微信客户端向公众号发送：${wechatAccount.valid_code }&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step3">3、<span class="step-info">完成授权</span></p>
		</div>
		
		
		<form class="form-horizontal" id="form-auth" method="POST">
			<input type="hidden" id="id" name="id" value="${wechatAccount.id }" />
			<div class="control-group" >
				<label class="control-label" for="url">URL:</label>
				<div class="controls">
					<input readonly="readonly" type="text" class="span4 form-control" id="url" name="url" value="${wechatAccount.url }">
					<span class="text-primary">复制到公众平台<strong>“开发者中心”</strong>-><strong>“服务器配置”</strong>-><strong>“URL”</strong></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="token">TOKEN:</label>
				<div class="controls">
					<input readonly="readonly" type="text" class="span4 form-control" id="token" name="token" value="${wechatAccount.token }">
					<span class="text-primary">复制到公众平台<strong>“开发者中心”</strong>-><strong>“服务器配置”</strong>-><strong>“TOKEN”</strong></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="app_id">AppId:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="app_id" name="app_id" value="${wechatAccount.app_id }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="app_secret">应用密钥:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="app_secret" name="app_secret" value="${wechatAccount.app_secret }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<c:choose>
				<c:when test="${ null == wechatAccount.id || '' eq wechatAccount.id }">
			      	<input style="margin-left: 180px;" class="btn btn-danger" type="submit" id="btn-submit" value="&nbsp;生&nbsp;成&nbsp;授&nbsp;权&nbsp;" />
				</c:when>
				<c:otherwise>
			      	<input style="margin-left: 180px;" class="btn btn-primary" type="submit" id="btn-submit" value="&nbsp;更&nbsp;新&nbsp;授&nbsp;权&nbsp;" />
				</c:otherwise>
			</c:choose>
	      	
	      	<div class="dev_right">
	      	   <h4>账户权限 &nbsp;<span id="refresh" style="cursor: pointer;"><img title="刷新" src="<%=resourceUrl %>/img/refresh.png"></span></h4>
	      	   <table class="table" cellspacing="0">
	               <thead class="thead">
	                   <tr>
	                       <th class="table_cell type"> 服务包 </th>
	                       <th class="table_cell desc"> 内容 </th>
	                       <th class="table_cell benefit"> 状态 </th>
	                   </tr>
	               </thead>
	               <tbody class="tbody" id="list">
	                   <tr>
	                       <td class="table_cell type" rowspan="3"> 基础接口</td>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E6%99%AE%E9%80%9A%E6%B6%88%E6%81%AF">接收用户消息</a></td>
	                       <td class="table_cell benefit"> <img src="<%=resourceUrl %>/img/tick.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E8%A2%AB%E5%8A%A8%E5%93%8D%E5%BA%94%E6%B6%88%E6%81%AF">向用户回复消息</a></td>
	                       <td class="table_cell benefit"> <img src="<%=resourceUrl %>/img/tick.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E4%BA%8B%E4%BB%B6%E6%8E%A8%E9%80%81">接受事件推送</a></td>
	                       <td class="table_cell benefit"> <img src="<%=resourceUrl %>/img/tick.png"> </td>
	                   </tr>
	
	                   <tr>
	                       <td class="table_cell type">自定义菜单</td>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95%E5%88%9B%E5%BB%BA%E6%8E%A5%E5%8F%A3">会话界面自定义菜单</a></td>
	                       <td class="table_cell benefit custom_menu"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>  
	
	                   <tr>
	                       <td class="table_cell type" rowspan="9">高级接口</td>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E8%AF%AD%E9%9F%B3%E8%AF%86%E5%88%AB%E7%BB%93%E6%9E%9C">语音识别</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E5%AE%A2%E6%9C%8D%E6%B6%88%E6%81%AF">客服接口</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF">OAuth2.0网页授权</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E7%94%9F%E6%88%90%E5%B8%A6%E5%8F%82%E6%95%B0%E7%9A%84%E4%BA%8C%E7%BB%B4%E7%A0%81">生成带参数二维码</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E5%9C%B0%E7%90%86%E4%BD%8D%E7%BD%AE">获取用户地理位置</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF">获取用户基本信息</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%B3%E6%B3%A8%E8%80%85%E5%88%97%E8%A1%A8">获取关注者列表</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%88%86%E7%BB%84%E7%AE%A1%E7%90%86%E6%8E%A5%E5%8F%A3">用户分组接口</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	                   <tr>
	                       <td class="table_cell desc"><a target="_blank" href="http://mp.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E4%B8%8B%E8%BD%BD%E5%A4%9A%E5%AA%92%E4%BD%93%E6%96%87%E4%BB%B6">上传下载多媒体文件</a></td>
	                       <td class="table_cell benefit advanced"><img src="<%=resourceUrl %>/img/error.png"></td>
	                   </tr>
	               </tbody>
	           </table>
	      	</div>
		</form>
	
	</div>
	
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/setting.css" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl %>/script/admin/setting.js?v=20141005" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
</body>
</html> 