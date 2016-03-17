<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册邮箱验证</title>
  </head>
  <body>
    ${userEmail!}，您好：<br>
    	请点击以下链接进行激活：<a href="${validUrl}">${validUrl}</a>
        <p>账号：${username}，密码：${decrypPwd}</p>
    	<p>--------</p>
    	<p>${appName}</p>
  </body>
</html>