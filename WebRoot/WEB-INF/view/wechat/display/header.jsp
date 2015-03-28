<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<<script type="text/javascript">
<!--
	function unFabu(){
		app.alert('文档正在整理中，我们将会第一时间发布！');
	}
	
	
//-->
</script>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand hidden-sm" href="<%=domain %>"><%=appName %></a>
    </div>
    <div class="navbar-collapse collapse" role="navigation">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">文档<b class="caret"></b></a>
          <ul class="dropdown-menu">
         	 <li>
              <a href="https://mp.weixin.qq.com" target="_blank">关于微信</a>
            </li>
            <li>
              <a href="javascript:void(-1);" onclick="unFabu()">开发框架</a>
            </li>
            <li>
              <a href="http://git.oschina.net/fengjx/ttwx/wikis/home" target="_blank">二次开发</a>
            </li>
          </ul>
        </li>
        <li><a href="<%=domain %>/contact">联系我们</a></li>
        <%-- <li><a href="<%=domain %>/about">关于我们</a></li> --%>
        <li><a href="http://fengjxblog.sinaapp.com/?page_id=2" target="_blank">关于我们</a></li>
        <form role="search" class="navbar-form navbar-left">
		  <div class="form-group">
		  	<input type="text" placeholder="搜索" class="form-control">
		  </div>
		  <button class="btn btn-default" type="submit">查找</button>
		</form>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<c:choose>
			<c:when test="${!empty sessionScope.sys_user_login_key }">
				<li><a target="_blank" href="<%=domain %>/admin/main">后台管理</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<%=domain %>/login">登录</a></li>
        		<li><a href="<%=domain %>/register">注册</a></li>
			</c:otherwise>
		</c:choose>
      </ul>
    </div>
  </div>
</div>
