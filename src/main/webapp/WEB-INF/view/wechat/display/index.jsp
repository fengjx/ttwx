<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="display"/>
<meta property="qc:admins" content="422531167706316110063757" />
<jsp:include page="/WEB-INF/view/common/inc/meta.jsp"></jsp:include>
<link href="${resourceUrl}/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="jumbotron masthead">
		<div class="container text-center">
			<h2>${appName}</h2>
			<h3>${description}</h3>
			<p class="masthead-button-links">
				<a class="btn btn-lg btn-primary btn-shadow" href="http://git.oschina.net/fengjx/ttwx" target="_blank" role="button">程序下载(v2.0.0)</a>
			</p>
		</div>
	</div>
	<div class="bc-social">
	    <div class="container">
	      <ul class="bc-social-buttons">
	        <li>
	          <i class="fa fa-qq"></i>技术交流群：（未创建）
	        </li>
	        <li>
	          <a href="http://git.oschina.net/fengjx/ttwx/wikis/pages" title="Bootstrap问答社区" target="_blank">
	            <i class="fa fa-comments"></i> 问答社区
	          </a>
	        </li>
	        <li>
	          <a href="http://weibo.com/xdfjx" title="天天微信团队" target="_blank">
	          	新浪微博：@天天微信团队
	          </a>
	        </li>
	      </ul>
	    </div>
	  </div>
	
	<div class="container">
		<div class="" style="margin-top: -50px;">
			<h3 class="page-header" style="text-align:center;">
	                  成功案例
	        </h3>
        </div>
		<div class="row">
	        <div class="col-xs-3">
	          <div class="tile">
	          	<img src="${resourceUrl}/img/ribbon.png" alt="ribbon" class="tile-hot-ribbon">
	            <img src="${resourceUrl}/img/hkwechat.png" alt="香港联通" class="tile-image">
	            <h3 class="tile-title">香港联通</h3>
	            <p></p>
	            <a class="btn btn-primary btn-large btn-block" href="javascript:void(-1);">Get Pro</a>
	          </div>
	        </div>
	
	        <div class="col-xs-3">
	          <div class="tile">
	            <img src="${resourceUrl}/img/gd10086.gif" alt="广东移动" class="tile-image">
	            <h3 class="tile-title">广东移动</h3>
	            <p></p>
	            <a class="btn btn-primary btn-large btn-block" href="javascript:void(-1);">Get Pro</a>
	          </div>
	        </div>
			
			<div class="col-xs-3">
	          <div class="tile">
	            <img src="${resourceUrl}/img/qrcode_for_gh_d7680c37887b_430.jpg" alt="简简单单" class="tile-image big-illustration">
	            <h3 class="tile-title">简简单单</h3>
	            <p></p>
	            <a class="btn btn-primary btn-large btn-block" href="javascript:void(-1);">Get Pro</a>
	          </div>
	        </div>
			
	        <div class="col-xs-3">
	          <div class="tile">
	            <img src="${resourceUrl}/img/qrcode_for_gh_d7680c37887b_430.jpg" alt="简简单单" class="tile-image">
	            <h3 class="tile-title">Free for Share</h3>
	            <p></p>
	            <a class="btn btn-primary btn-large btn-block" href="javascript:void(-1);">Get Pro</a>
	          </div>
	
	        </div>
      </div>
	</div><!-- /.container -->
	
</body>
</html>