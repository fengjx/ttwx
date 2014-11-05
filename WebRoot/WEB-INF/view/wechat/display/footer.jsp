<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer class="footer ">
  <div class="container">
    <div class="row footer-top">
     <div class="col-sm-6 col-lg-6">
        <h4>
	        <p>本网站所列开源项目的文档全部由<a href="<%=curUrl %>/about">天天微信团队</a>成员整理。</p>
        	<script type="text/javascript">
				(function(){
				var p = {
				url:location.href,
				showcount:'1',/*是否显示分享总数,显示：'1'，不显示：'0' */
				desc:'免费、开源、支持二次开发扩展的微信发布平台，让你在微信开发中更专注于业务',/*默认分享理由(可选)*/
				summary:'',/*分享摘要(可选)*/
				title:'天天微信发布管理平台',/*分享标题(可选)*/
				site:'',/*分享来源 如：腾讯网(可选)*/
				pics:'', /*分享图片的路径(可选)*/
				style:'101',
				width:199,
				height:30
				};
				var s = [];
				for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
				}
				document.write(['<a version="1.0" class="qzOpenerDiv" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?',s.join('&'),'" target="_blank">分享</a>'].join(''));
				})();
				</script>
			<script src="http://qzonestyle.gtimg.cn/qzone/app/qzlike/qzopensl.js#jsdate=20111201" charset="utf-8"></script>
        </h4>
      </div>
    
    
      <div class="col-sm-6  col-lg-5 col-lg-offset-1">
        <div class="row about">
          <div class="col-xs-4">
            <h4>友情链接</h4>
            <ul class="list-unstyled">
              <li><a target="_blank" href="https://mp.weixin.qq.com/">微信公众平台</a></li>
              <li><a target="_blank" href="http://blog.csdn.net/lyq8479">柳峰技术博客</a></li>
              <li><a target="_blank" href="http://fengjxblog.sinaapp.com/">微信团队主页</a></li>
            </ul>
          </div>
          <div class="col-xs-4">
            <h4>联系方式</h4>
            <ul class="list-unstyled">
              <li><a href="http://weibo.com/xdfjx" title="阿豆微信官方微博" target="_blank">新浪微博</a></li>
              <li><a href="mailto:xd-fjx@qq.com">电子邮件</a></li>
            </ul>
          </div>
          <div class="col-xs-4">
            <h4>个人微信</h4>
            <ul class="list-unstyled">
              <li>
              	<img alt="fengjx_2013" src="<%=resourceUrl %>/img/fengjx_2013.png" height="115">
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <hr/>
    <div class="row footer-bottom">
      <ul class="list-inline text-center">
        <li><a href="http://www.miibeian.gov.cn/" target="_blank">Copyright@2014</a></li>
        <li>Email:xd-fjx@qq.com &nbsp;By:FengJianxin</li>
      </ul>
    </div>
  </div>
</footer>
