<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>admin</title>
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-12 col-md-12">
      <h2 class="sub-header">总览</h2>
      <div class="row">
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-comments icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">26</div>
                  <div>New Comments!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-green">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-tasks icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">12</div>
                  <div>New Tasks!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-yellow">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-shopping-cart icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">124</div>
                  <div>New Orders!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-red">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-desktop icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">13</div>
                  <div>Support Tickets!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>
<footer class="footer navbar-fixed-bottom">
  <hr/>
  <div class="container" style="text-align: center;">
    <div class="row footer-top">
      <p>使用chrome浏览器体验最佳效果 | 技术支持 - QQ: 466516623, Email: xd-fjx@qq.com</p>
    </div>
  </div>
</footer>

<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
<script>

  $(function () {
    $('#side-menu').metisMenu({
      toggle: false
    });
  });


</script>
</body>
</html>

