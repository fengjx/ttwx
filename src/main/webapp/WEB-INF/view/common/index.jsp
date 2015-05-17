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
    <div class="col-sm-3 col-md-2 sidebar">
      <ul class="nav nav-sidebar" id="side-menu">
        <li>
          <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
        </li>
        <li>
          <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="glyphicon arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="flot.html">Flot Charts</a>
            </li>
            <li>
              <a href="morris.html">Morris.js Charts</a>
            </li>
          </ul>
          <!-- /.nav-second-level -->
        </li>
        <li>
          <a href="tables.html"><i class="fa fa-table fa-fw"></i> Tables</a>
        </li>
        <li>
          <a href="forms.html"><i class="fa fa-edit fa-fw"></i> Forms</a>
        </li>
        <li>
          <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="glyphicon arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="panels-wells.html">Panels and Wells</a>
            </li>
            <li>
              <a href="buttons.html">Buttons</a>
            </li>
            <li>
              <a href="notifications.html">Notifications</a>
            </li>
            <li>
              <a href="typography.html">Typography</a>
            </li>
            <li>
              <a href="icons.html"> Icons</a>
            </li>
            <li>
              <a href="grid.html">Grid</a>
            </li>
          </ul>
          <!-- /.nav-second-level -->
        </li>
        <li>
          <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="glyphicon arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="#">Second Level Item</a>
            </li>
            <li>
              <a href="#">Second Level Item</a>
            </li>
            <li>
              <a href="#">Third Level <span class="glyphicon arrow"></span></a>
              <ul class="nav nav-third-level">
                <li>
                  <a href="#">Third Level Item</a>
                </li>
                <li>
                  <a href="#">Third Level Item</a>
                </li>
                <li>
                  <a href="#">Third Level Item</a>
                </li>
                <li>
                  <a href="#">Third Level Item</a>
                </li>
              </ul>
              <!-- /.nav-third-level -->
            </li>
          </ul>
          <!-- /.nav-second-level -->
        </li>
      </ul>
    </div>

    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li><a href="#">Library</a></li>
        <li class="active">Data</li>
      </ol>
      <h2 class="sub-header">Section title</h2>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>#</th>
            <th>Header</th>
            <th>Header</th>
            <th>Header</th>
            <th>Header</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>1,001</td>
            <td>Lorem</td>
            <td>ipsum</td>
            <td>dolor</td>
            <td>sit</td>
          </tr>
          <tr>
            <td>1,002</td>
            <td>amet</td>
            <td>consectetur</td>
            <td>adipiscing</td>
            <td>elit</td>
          </tr>
          <tr>
            <td>1,003</td>
            <td>Integer</td>
            <td>nec</td>
            <td>odio</td>
            <td>Praesent</td>
          </tr>
          <tr>
            <td>1,003</td>
            <td>libero</td>
            <td>Sed</td>
            <td>cursus</td>
            <td>ante</td>
          </tr>
          <tr>
            <td>1,004</td>
            <td>dapibus</td>
            <td>diam</td>
            <td>Sed</td>
            <td>nisi</td>
          </tr>
          <tr>
            <td>1,005</td>
            <td>Nulla</td>
            <td>quis</td>
            <td>sem</td>
            <td>at</td>
          </tr>
          <tr>
            <td>1,006</td>
            <td>nibh</td>
            <td>elementum</td>
            <td>imperdiet</td>
            <td>Duis</td>
          </tr>
          <tr>
            <td>1,007</td>
            <td>sagittis</td>
            <td>ipsum</td>
            <td>Praesent</td>
            <td>mauris</td>
          </tr>
          <tr>
            <td>1,008</td>
            <td>Fusce</td>
            <td>nec</td>
            <td>tellus</td>
            <td>sed</td>
          </tr>
          <tr>
            <td>1,009</td>
            <td>augue</td>
            <td>semper</td>
            <td>porta</td>
            <td>Mauris</td>
          </tr>
          <tr>
            <td>1,010</td>
            <td>massa</td>
            <td>Vestibulum</td>
            <td>lacinia</td>
            <td>arcu</td>
          </tr>
          <tr>
            <td>1,011</td>
            <td>eget</td>
            <td>nulla</td>
            <td>Class</td>
            <td>aptent</td>
          </tr>
          <tr>
            <td>1,012</td>
            <td>taciti</td>
            <td>sociosqu</td>
            <td>ad</td>
            <td>litora</td>
          </tr>
          <tr>
            <td>1,013</td>
            <td>torquent</td>
            <td>per</td>
            <td>conubia</td>
            <td>nostra</td>
          </tr>
          <tr>
            <td>1,014</td>
            <td>per</td>
            <td>inceptos</td>
            <td>himenaeos</td>
            <td>Curabitur</td>
          </tr>
          <tr>
            <td>1,015</td>
            <td>sodales</td>
            <td>ligula</td>
            <td>in</td>
            <td>libero</td>
          </tr>
          <tr>
            <td>1,015</td>
            <td>sodales</td>
            <td>ligula</td>
            <td>in</td>
            <td>libero</td>
          </tr>
          <tr>
            <td>1,015</td>
            <td>sodales</td>
            <td>ligula</td>
            <td>in</td>
            <td>libero</td>
          </tr>
          <tr>
            <td>1,015</td>
            <td>sodales</td>
            <td>ligula</td>
            <td>in</td>
            <td>libero</td>
          </tr>
          <tr>
            <td>1,015</td>
            <td>sodales</td>
            <td>ligula</td>
            <td>in</td>
            <td>libero</td>
          </tr>
          </tbody>
        </table>
        <nav>
          <ul class="pagination">
            <li>
              <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
              <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</div>
<footer class="footer">
  <div class="container" style="text-align: center;">
    <p >Place sticky footer content here.</p>
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

