<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title!}</title>
    <meta name="description" content="${title!}">
    <link rel="icon" href="http://static.fengjx.com/ttwx-test/res/img/favicon_wx.ico">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html {
            position: relative;
            min-height: 100%;
        }
        body {
            margin-bottom: 60px;
        }
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
        .container {
            width: auto;
            max-width: 680px;
            padding: 0 15px;
        }
        .container .text-muted {
            margin: 20px 0;
        }
        .container * {
           max-width: 100%;
           width: 100%; 
         } 
         
        .rich_media_title {
          font-size: 24px;
          font-weight: 400;
          line-height: 1.4;
         }

    </style>
</head>
<body>
<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1 class="rich_media_title">${title!}</h1>
        <p class="activity-info">
            <span>${date!}</span>
            <a>
                <strong>${app_name!}</strong>
            </a>
        </p>
    </div>
    <!--###@content@###-->${content!}<!--###@content@###-->
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">技术支持 - Email: ${email}</p>
    </div>
</footer>

</body>
</html>
