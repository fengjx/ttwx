<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<link href="${resourceUrl}/css/wechart_style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		jQuery.jqtab = function(tabtit, tabcon) {
			$(tabcon).hide();
			$(tabtit + " li:first").addClass("thistab").show();
			$(tabcon + ":first").show();
			$(tabtit + " li").click(function() {
				$(tabtit + " li").removeClass("thistab");
				$(this).addClass("thistab");
				$(tabcon).hide();
				var activeTab = $(this).find("a").attr("tab");
				$("#" + activeTab).fadeIn();
				return false;
			});
		};
		/*调用方法如下：*/
		$.jqtab("#tabs", ".tab_con");
	});
</script>
<script language="javascript">
	function showPic(sUrl) {
		document.getElementById("Layer1").innerHTML = "<img src=\"" + sUrl + "\">";
		document.getElementById("Layer1").style.display = "block";
	}
	function hiddenPic() {
		document.getElementById("Layer1").innerHTML = "";
		document.getElementById("Layer1").style.display = "none";
	}
</script>

<script src="${resourceUrl}/script/wechat/admin/wechart.widget.js?v=2015053003" type="text/javascript" charset="UTF-8"></script>
<div id="tabbox">
	<ul id="tabs" class="tabs">
		<li class="thistab"><a class="ttitle" tab="tab1" href="#">标题</a></li>
		<li class=""><a class="ttitle" tab="tab2" href="#">文本</a></li>
		<li class=""><a class="ttitle" tab="tab8" href="#">图文</a></li>
		<li class=""><a class="ttitle" tab="tab3" href="#">关注</a></li>
		<li class=""><a class="ttitle" tab="tab4" href="#">分割</a></li>
		<li class=""><a class="ttitle" tab="tab5" href="#">原文</a></li>
		<li class=""><a class="ttitle" tab="tab6" href="#">其他</a></li>
		<!--<li><a href="#" tab="tab7" class="ttitle" style="color:#ff0000">春节</a></li>-->
	</ul>
</div>
<!--标题开始 -->
<div style="display: block;" class="tab_con" id="tab1">
	<!--标题开始 -->
	<div title=""
		data-color="section.main:border-color;section.main2:border-color;section.main2:background-color;section.main3:border-color;section.main4:background-color;"
		class="element-item selected">
		<div class="content">

	<fieldset style="border: 0px; margin: 0.5em 0px; padding: 0px; box-sizing: border-box;" >
                      <h2 style="margin: 8px 0px 0px; padding: 0px; font-weight:bold;font-size:16px;line-height:28px;  max-width: 100%;color:rgb(0, 112, 192); min-height: 32px;border-bottom: 2px solid rgb(0, 112, 192); text-align: justify;"><span title="" data-original-title="" style="background-color:rgb(0, 112, 192); border-radius:80% 100% 90% 20%; color:rgb(255, 255, 255); display:block; float:left; line-height:20px; margin:0px 8px 0px 0px; max-width:100%; padding:4px 10px; word-wrap:break-word !important" placeholder="1" class="autonum">1</span><strong data-brushtype="text" class="135brush">第一标题</strong></h2>
                    </fieldset>
	</div>
	</div>


	<div title=""
		data-color="section.main:border-color;section.main2:border-color;section.main2:background-color;section.main3:border-color;section.main4:background-color;"
		class="element-item">
		<div class="content">
			<section
				style="border: 1px solid rgb(0, 187, 236); text-align: center;"
				class="main">
				<section
					style="padding: 0px; text-align: center; margin: 0px auto; min-height: 10em; color: rgb(255, 255, 255); border-color: rgb(0, 187, 236); background-color: rgb(0, 187, 236);"
					class="main2">
					<section
						style="margin: 10px; display: inline-block; border-color: rgb(0, 187, 236); padding: 5px; color: inherit;"
						data-brushtype="text" class="main3">
						<p style="color: inherit;">
							<span style="color: inherit; font-size: 24px"><strong
								style="color: inherit; font-size: 24px"><span
									style="color: inherit; font-size: 24px">俺是大标题哦，亲亲</span></strong></span>
						</p>
					</section>
				</section>
				<section style="display: inline-block; margin-top: -6em;">
					<img
						style="border-radius: 50%; color: inherit; height: 12em !important; margin: 0px; padding: 0px; width: 12em !important"
						src="http://imgqn.koudaitong.com/upload_files/2015/06/26/d3167bb56168f1a97590468cb75c574a.jpg">
				</section>
				<section style="max-width: 100%; margin: 0.5em; text-align: center;">
					<section
						style="height: 36px; display: inline-block; color: rgb(70, 70, 72); font-size: 16px; font-weight: bold; padding: 0px 10px; line-height: 36px; vertical-align: top; box-sizing: border-box !important; border-color: rgb(164, 164, 207); background-color: rgb(0, 187, 236);"
						data-brushtype="text" class="main4">
						<span style="color: rgb(255, 255, 255)">嘿嘿，人家是副标题的啦！</span>
					</section>
				</section>
				<section
					style="text-align: justy; padding: 5px 10px 15px; text-align: justify;"
					data-style="font-size: 14px; color: rgb(127, 127, 127); font-family: inherit;">
					<p style="text-align: justify;">
						<span
							style="color: rgb(127, 127, 127); font-family: 微软雅黑; font-size: 14px">我是微信编辑器哦</span>
					</p>
				</section>
			</section>
			<p>
				<br>
			</p>

		</div>
	</div>
	<div title=""
		data-color="section.main:border-color;section.main2:border-color;span.main3:background-color;section.main4:border-right-color;section.main4:border-top-color;"
		class="element-item">
		<div class="content">
			<fieldset
				style="border: 0px; margin: 0.5em 0px; padding: 0px; box-sizing: border-box;">
				<section
					style="margin-left: 1%; border: 1px solid rgb(0, 187, 236); border-top-left-radius: 0px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 0px; font-size: 1em; font-family: inherit; font-weight: inherit; text-align: inherit; text-decoration: inherit; color: rgb(52, 54, 60); background-color: rgb(255, 255, 255); box-sizing: border-box;"
					class="main">
					<section
						style="margin-top: 5%; float: left; margin-right: 8px; margin-left: -8px; font-size: 0.8em; font-family: inherit; font-style: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(255, 255, 255); background-color: transparent; border-color: rgb(0, 187, 236); box-sizing: border-box;"
						class="main2">
						<span
							style="display: inline-block; padding: 0.3em 0.5em; border-top-left-radius: 0px; border-top-right-radius: 0.5em; border-bottom-right-radius: 0.5em; border-bottom-left-radius: 0px; font-size: 1em; background-color: rgb(0, 187, 236); font-family: inherit; box-sizing: border-box;"
							class="main3"><section style="box-sizing: border-box;">
								微信助手</section></span>
						<section
							style="width: 0px; border-right-width: 4px; border-right-style: solid; border-right-color: rgb(0, 187, 236); border-top-width: 4px; border-top-style: solid; border-top-color: rgb(0, 187, 236); border-left-width: 4px !important; border-left-style: solid !important; border-left-color: transparent !important; border-bottom-width: 4px !important; border-bottom-style: solid !important; border-bottom-color: transparent !important; box-sizing: border-box;"
							class="main4"></section>
					</section>
					<section class="wxqq-borderTopColor"
						style="margin-top: 5%; padding: 0px 8px; font-size: 1.5em; font-family: inherit; font-weight: inherit; text-align: inherit; text-decoration: inherit; box-sizing: border-box;">
						<section style="box-sizing: border-box;"
							class="wxqq-borderTopColor">微信文章标题</section>
					</section>
					<section class="wxqq-borderTopColor"
						style="clear: both; box-sizing: border-box;"></section>
					<section class="wxqq-borderTopColor"
						style="padding: 8px; box-sizing: border-box;">
						<p>编辑文字的时候，提倡大家复制素材到微信公众平台素材管理里面进行编辑，本编辑器不带保存功能，大家使用时候注意！</p>
					</section>
				</section>
			</fieldset>
			<p>
				<br>
			</p>

		</div>
	</div>
	<div title=""
		data-color="section.main:border-color;section.main2:color;section.main2:border-color;span.main3:border-color;section.main4:border-bottom-color;section.main4:border-top-color;"
		class="element-item">
		<div class="content">
			<section
				style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-family: 微软雅黑;">
				<section
					style="border: none rgb(0, 187, 236); margin: 0.8em 5% 0.3em; box-sizing: border-box; padding: 0px;"
					class="main">
					<section
						style="color: rgb(0, 187, 236); font-size: 20px; letter-spacing: 3px; padding: 9px 4px 14px; text-align: center; margin: 0px auto; border: 4px solid rgb(0, 187, 236); border-top-left-radius: 8px; border-top-right-radius: 8px; border-bottom-right-radius: 8px; border-bottom-left-radius: 8px; box-sizing: border-box;"
						data-brushtype="text" class="main2">
						理念<span
							style="display: block; font-size: 10px; line-height: 12px; border-color: rgb(0, 187, 236); color: inherit; box-sizing: border-box; padding: 0px; margin: 0px;"
							data-brushtype="text" class="main3">PHILOSOPHY</span>
					</section>
					<section data-width="0px"
						style="width: 0px; margin-right: auto; margin-left: auto; border-top-width: 0.6em; border-top-style: solid; border-bottom-color: rgb(0, 187, 236); border-top-color: rgb(0, 187, 236); height: 10px; color: inherit; border-left-width: 0.7em !important; border-left-style: solid !important; border-left-color: transparent !important; border-right-width: 0.7em !important; border-right-style: solid !important; border-right-color: transparent !important; box-sizing: border-box; padding: 0px;"
						class="main4"></section>
				</section>
			</section>
			<p>
				<br>
			</p>

		</div>
	</div>
	<div title="" data-color="span.main:color"
		class="element-item">
		<div class="content">
			<section data-custom="rgb(253, 226, 216)"
				data-color="rgb(253, 226, 216)"
				style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-family: 微软雅黑;">
				<section
					style="-webkit-box-reflect: below 0px -webkit-gradient(linear, 0% 0%, 0% 100%, from(transparent), color-stop(0.2, transparent), to(rgba(250, 250, 250, 0.298039))); margin-top: 15px; line-height: 20px; box-sizing: border-box; padding: 0px;">
					<p
						style="text-align: center; color: inherit; box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;">
						<span style="color: rgb(0, 187, 236);" class="main"><strong
							style="color: inherit; font-size: 30px; border-color: rgb(216, 40, 33); box-sizing: border-box; padding: 0px; margin: 0px;"
							data-brushtype="text">文字倒影效果样式</strong></span>
					</p>
				</section>
			</section>
			<p>
				<br>
			</p>

		</div>
	</div>

	<div title=""
		data-color="section.main:border-color;section.main2:background-color;section.main3:border-color"
		class="element-item">
		<div class="content">
			<fieldset
				style="box-sizing: border-box; padding: 0; margin: 1.5em 0 0; border: none; min-width: 0; max-width: 100%; color: #3e3e3e; line-height: 25px; white-space: normal; background-color: #fff; text-align: center; word-wrap: break-word !important">
				<section
					style="box-sizing: border-box; display: inline-block; max-width: 100%; vertical-align: top; font-size: 1.2em; font-family: inherit; text-decoration: inherit; color: #2a343a; border-color: #00bbec; word-wrap: break-word !important"
					class="main">
					<section
						style="box-sizing: border-box; max-width: 100%; width: 6em; height: 6em; padding: 2.75em 0; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; background-color: #00bbec; word-wrap: break-word !important"
						class="main2">
						<section
							style="box-sizing: border-box; max-width: 100%; width: 6em; height: .3em; background-color: #fffffe; word-wrap: break-word !important"></section>
						<section
							style="box-sizing: border-box; max-width: 100%; widthth: .3em; height: 6em; margin: -3.05em 2.85em 0; background-color: #fffffe; word-wrap: break-word !important"></section>
						<section
							style="box-sizing: border-box; max-width: 100%; width: 4.8em; height: 4.8em; background-color: #fffffe; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; margin: -5.4em .6em 1.5em; word-wrap: break-word !important"></section>
					</section>
					<section
						style="box-sizing: border-box; max-width: 100%; width: 4.8em; height: 4.8em; background-color: transparent; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; margin: -5.4em .6em 1.5em; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; height: 2.5em; word-wrap: break-word !important">
							<section
								style="box-sizing: border-box; max-width: 100%; width: 3.3em; height: 2.5em; margin: auto; line-height: 2.5em; white-space: nowrap; overflow: hidden; font-size: 1.2em; font-family: inherit; text-decoration: inherit; word-wrap: break-word !important">
								<section
									style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">关键字</section>
							</section>
						</section>
						<section
							style="box-sizing: border-box; max-width: 100%; width: 3.3em; margin: -.1em auto .1em; border-bottom-width: .1em; border-bottom-style: solid; border-color: #00bbec; word-wrap: break-word !important"
							class="main3"></section>
						<section
							style="box-sizing: border-box; max-width: 100%; width: 3.6em; height: 2em; margin: auto; line-height: 2em; white-space: nowrap; overflow: hidden; word-wrap: break-word !important">
							<section
								style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">A</section>
						</section>
					</section>
				</section>
				<section
					style="box-sizing: border-box; display: inline-block; max-width: 100%; margin-left: .5em; width: 202.39px; vertical-align: middle; font-size: 1.2em; font-family: inherit; text-align: left; text-decoration: inherit; color: #2a343a; word-wrap: break-word !important">
					<section
						style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">
						<br
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">请输入文字
					</section>
				</section>
			</fieldset>
		</div>
	</div>
	<div title="" data-color="section.main:background-color"
		class="element-item">
		<div class="content">
			<fieldset
				style="box-sizing: border-box; padding: 0; margin: .5em 0; border: none; min-width: 0; max-width: 100%; color: #3e3e3e; line-height: 25px; white-space: normal; background-color: #fff; word-wrap: break-word !important">
				<section
					style="box-sizing: border-box; max-width: 100%; width: 11.3em; height: 6.9em; float: right; text-align: center; padding: 1.5em 0; background-image: url(http://imgqn.koudaitong.com/upload_files/2015/06/11/42374a2cf741254640352f99f6a079d6.png); background-size: cover; background-color: #00bbec; word-wrap: break-word !important"
					class="main">
					<section
						style="box-sizing: border-box; max-width: 100%; width: 144.63px; margin-left: 18.08px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-13deg); font-size: 18px; font-family: inherit; text-decoration: inherit; color: #fff; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">请输入标题</section>
					</section>
					<section
						style="box-sizing: border-box; max-width: 100%; width: 108.47px; margin: 5.42px 45.19px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-15deg); font-family: inherit; text-decoration: inherit; color: #fff; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">标题标题</section>
					</section>
				</section>
			</fieldset>
		</div>
	</div>
	<div title="" data-color="section.main:background-color;"
		class="element-item">
		<div class="content">
			<fieldset
				style="box-sizing: border-box; padding: 0; margin: .5em 0; border: none; min-width: 0; max-width: 100%; color: #3e3e3e; line-height: 25px; white-space: normal; background-color: #fff; word-wrap: break-word !important">
				<section
					style="box-sizing: border-box; max-width: 100%; width: 12.5em; height: 9.3em; float: right; text-align: center; padding: 1.5em 0; background-image: url(http://imgqn.koudaitong.com/upload_files/2015/06/11/5c26e09f52590bb2358bcb791ad570d6.png); background-size: cover; background-color: #00bbec; word-wrap: break-word !important"
					class="main">
					<section
						style="box-sizing: border-box; max-width: 100%; width: 160px; margin-left: 20px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-13deg); font-size: 22px; font-family: inherit; text-decoration: inherit; color: #fff; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">请输入标题</section>
					</section>
					<section
						style="box-sizing: border-box; max-width: 100%; width: 150px; margin: 10px 20px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-15deg); font-family: inherit; text-decoration: inherit; color: #fff; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">标题标题</section>
					</section>
				</section>
			</fieldset>
		</div>
	</div>
	<div title="" class="element-item">
		<div class="content">
			<fieldset
				style="box-sizing: border-box; padding: 0; margin: .5em 0; border: none; min-width: 0; max-width: 100%; color: #3e3e3e; line-height: 25px; white-space: normal; background-color: #fff; word-wrap: break-word !important">
				<section
					style="box-sizing: border-box; max-width: 100%; width: 12.5em; height: 12.5em; float: right; text-align: center; padding: 3.8em 0; background-image: url(http://imgqn.koudaitong.com/upload_files/2015/06/11/b1813b366a832c352488bce44241b947.png); background-size: cover; word-wrap: break-word !important">
					<section
						style="box-sizing: border-box; max-width: 100%; width: 140px; margin-left: 16px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-13deg); font-size: 22px; font-family: inherit; text-decoration: inherit; color: #666; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">标题标题</section>
					</section>
					<section
						style="box-sizing: border-box; max-width: 100%; width: 150px; margin: 10px 20px; white-space: nowrap; overflow: hidden; -webkit-transform: rotate(-15deg); font-family: inherit; text-decoration: inherit; color: #666; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">我是图片不能换色哦</section>
					</section>
				</section>
			</fieldset>
		</div>
	</div>
	<div title=""
		data-color="section.main:border-color;section.main:color;section.main2:border-color;"
		class="element-item">
		<div class="content">
			<fieldset
				style="box-sizing: border-box; padding: 0; margin: .8em 0 .5em; border: 0; min-width: 0; max-width: 100%; color: #3e3e3e; line-height: 25px; white-space: normal; background-color: #fff; text-align: center; clear: both; word-wrap: break-word !important">
				<section
					style="box-sizing: border-box; display: inline-block; max-width: 100%; padding: .2em .5em .15em; font-size: 2em; margin: 0 auto; background-color: rgba(255, 255, 255, .992157); border-left-width: 1px; border-left-style: solid; border-color: #00bbec; border-right-width: 1px; border-right-style: solid; text-decoration: inherit; color: #00bbec; word-wrap: break-word !important"
					class="main">
					<section
						style="box-sizing: border-box; max-width: 100%; font-size: .8em; line-height: 1em; vertical-align: top; text-decoration: inherit; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">上方标题字多一点</section>
					</section>
					<section
						style="box-sizing: border-box; max-width: 100%; margin-top: .2em; line-height: 1; word-wrap: break-word !important">
						<section
							style="box-sizing: border-box; display: inline-block; max-width: 100%; padding: 0 .5em; background-color: rgba(255, 255, 254, .992157); word-wrap: break-word !important">
							<section
								style="box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">主标题</section>
						</section>
						<section
							style="box-sizing: border-box; max-width: 100%; margin-top: -.5em; margin-bottom: .5em; border-bottom-width: 1px; border-bottom-style: solid; border-color: #00bbec; word-wrap: break-word !important"
							class="main2"></section>
					</section>
				</section>
			</fieldset>
		</div>
	</div>
	<div title=""
		data-color="section.main:border-color;section.main2:background-color;section.main4:border-color;section.main5:border-color;"
		class="element-item">
		<div class="content">
			<section
				style="margin: 0; padding: 0; border: 0 #00bbec; font-family: inherit; font-size: 1em; line-height: 25px; white-space: normal; box-sizing: border-box; max-width: 100%; text-align: center; text-decoration: inherit; color: #fff; word-wrap: break-word !important"
				class="main">
				<section
					style="margin: 0 auto; padding: 0; border: 0; box-sizing: border-box; max-width: 100%; width: 2em; height: 2em; background-color: #00bbec; border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; word-wrap: break-word !important"
					class="main2">
					<section
						style="margin: 0; padding: 0; border: 0; display: inline-block; box-sizing: border-box; max-width: 100%; font-size: 1em; line-height: 2; font-family: inherit; word-wrap: break-word !important"
						class="main3">
						<section
							style="margin: 0; padding: 0; border: 0; box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">1</section>
					</section>
				</section>
				<section
					style="margin: -1em 0 1em; padding: 0; border: 0; box-sizing: border-box; max-width: 100%; word-wrap: break-word !important">
					<section
						style="margin: 0; padding: 0; border-width: 1px 0 0; border-top-style: solid; border-color: #00bbec; box-sizing: border-box; max-width: 100%; width: 128.8px; float: left; word-wrap: break-word !important"
						class="main4"></section>
					<section
						style="margin: 0; padding: 0; border-width: 1px 0 0; border-top-style: solid; border-color: #00bbec; box-sizing: border-box; max-width: 100%; width: 128.8px; float: right; word-wrap: break-word !important"
						class="main5"></section>
				</section>
			</section>
		</div>
	</div>
	<div style="width: 100%;" class="element-list">
		<!--样式0-->
		<div data-color="span:background-color" class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0 0; font-weight: 400; font-size: 16px; max-width: 100%; white-space: normal; background-color: #fff; padding: 0; border: 0; color: #444; font-family: 微软雅黑; height: 32px; text-align: justify; line-height: 18px; word-wrap: break-word !important; box-sizing: border-box !important">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: 0 8px 0 0; padding: 4px 10px; border: 0; line-height: 28px; float: left; display: block; color: #fff; background-color: rgb(0, 187, 236)"><br
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important"></span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: 0 8px 0 0; padding: 4px 10px; border: 0; line-height: 28px; float: left; display: block; color: #fff; background-color: rgb(0, 187, 236)"><br
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important"></span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: 0 8px 0 0; padding: 4px 10px; border: 0; line-height: 28px; float: left; display: block; color: #fff; background-color: rgb(0, 187, 236)"><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">天天微信，必是精品</strong></span>
				</h2>
			</div>
		</div>
		<!--样式0-->
		<div
			data-color="section.main:border-color;section.main2:color;section.main3:background-color;section.main4:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin: 0; padding: 0; border: 0; max-width: 100%; box-sizing: border-box; color: #3e3e3e; font-family: 微软雅黑; line-height: 25px; white-space: normal; text-align: center; clear: both; word-wrap: break-word !important">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; border: .4em solid #00bbec; background-color: #f8f7f5"
						class="main">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: -.4em .5em; padding: .5em; border-top-width: .5em; border-top-style: solid; border-top-color: #f8f7f5; border-bottom-width: .5em; border-bottom-style: solid; border-bottom-color: #f8f7f5">
							<section
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
								<section
									style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-table; vertical-align: middle">
									<section
										style="max-width: 100%; display: table; vertical-align: middle; line-height: 1.5; font-size: 1em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: #00bbec; word-wrap: break-word !important; box-sizing: border-box !important"
										class="main2">
										天天微信<br
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">必是精品<br
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
									</section>
								</section>
								<section
									style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box; display: inline-block; vertical-align: middle; margin: 0; height: 3em; width: 3em; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 0; border-bottom-left-radius: 50%; background-color: #00bbec"
									class="main3">
									<section
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box; height: 2.6em; width: 2.6em; margin: .2em; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; border: .2em solid #fff; background-color: transparent">
										<section
											style="max-width: 100%; margin-top: .05em; line-height: 1; font-size: 2em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: #fff; word-wrap: break-word !important; box-sizing: border-box !important">1</section>
									</section>
								</section>
							</section>
							<section
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: .5em 0; border-top-width: 1px; border-top-style: solid; border-color: #00bbec"
								class="main4"></section>
							<section
								style="max-width: 100%; line-height: 1; font-size: .9em; font-family: inherit; text-align: inherit; text-decoration: inherit; word-wrap: break-word !important; box-sizing: border-box !important">这里可输入标题，自适应宽度</section>
						</section>
					</section>
				</fieldset>
			</div>
		</div>
		<!--样式0-->
		<div data-color="p:border-top-color;span:background-color"
			class="element-item">
			<div class="content">
				<p
					style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: normal; color: rgb(62, 62, 62); line-height: 2em; font-family: 微软雅黑; padding: 0px; border-top-color: rgb(0, 187, 236); border-top-width: 2px; border-top-style: solid; box-sizing: border-box !important;">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 5px 10px; color: rgb(255, 255, 255); font-size: 13px; display: inline-block; background-color: rgb(0, 187, 236);">这可输入标题</span>
				</p>
				<p
					style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: normal; color: rgb(62, 62, 62); font-family: 'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, Arial, sans-serif; line-height: 25px; background-color: rgb(255, 255, 255); box-sizing: border-box !important;">
				</p>
			</div>
		</div>
		<!--样式0-->
		<div data-color="h2:border-top-color;h2:color" class="element-item">
			<div class="content">
				<h2
					style="margin: 25px 0px 20px; font-weight: 100; font-size: 22px; max-width: 100%; white-space: normal; padding: 5px 0px 10px 7px; clear: both; border-top-width: 2px; border-top-style: solid; border-top-color: rgb(0, 187, 236); background-image: url(http://mat1.gtimg.com/view/todayTopic/2013/aticletitBg.png); background-color: rgb(255, 255, 255); font-family: 微软雅黑; line-height: 35px; color: rgb(0, 187, 236); word-wrap: break-word !important; box-sizing: border-box !important; background-position: 0px 100%; background-repeat: repeat no-repeat;"
					data-page-model="title">一、这可输入标题</h2>
			</div>
		</div>
		<!--样式0-->
		<div
			data-color="section.main1:border-color;section.main2:background-color"
			class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255); border-color: rgb(0, 187, 236); margin: 0.5em 0px; line-height: 1em; overflow: hidden; border-bottom-width: 1px; border-bottom-style: solid; display: inline-block; word-wrap: break-word !important; box-sizing: border-box !important;"
					class="main1">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 0.2em; height: 2.8em; line-height: 1em; display: inline-block; background-color: rgb(0, 187, 236);"
						class="main2">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(255, 255, 255); line-height: 1em; font-family: inherit; font-size: 2.5em;"
							class="main3">1</section>
					</section>
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 0.2em; color: rgb(42, 52, 58); line-height: 1em; font-family: inherit; font-size: 1.5em; display: inline-block;"
						class="main4">这可输入标题</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div
			data-color="section.main_3:background-color;section.main_4:border-left-color;section.main_4:border-right-color;"
			class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; white-space: normal; background-color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important;"
					class="main_1">
					<section
						style="max-width: 100%; margin: 0.8em 0px 0.5em; overflow: hidden; word-wrap: break-word !important; box-sizing: border-box !important;"
						class="main_2">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 2em; display: inline-block; padding: 0.3em 0.5em; color: white; text-align: center; font-size: 1em; line-height: 1.4em; vertical-align: top; background-color: rgb(0, 187, 236);"
							class="main_3">
							<strong
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">第一步</strong>
						</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 2em; width: 0.5em; display: inline-block; vertical-align: top; border-left-width: 0.5em; border-left-style: solid; border-left-color: rgb(0, 187, 236); border-right-color: rgb(0, 187, 236); border-top-width: 1em !important; border-top-style: solid !important; border-top-color: transparent !important; border-bottom-width: 1em !important; border-bottom-style: solid !important; border-bottom-color: transparent !important;"
							class="main_4"></section>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="span:background-color:" class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; white-space: normal; margin: 0.8em 0px 0.5em; word-wrap: break-word !important; box-sizing: border-box !important;">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; padding: 0.3em 0.5em; border-top-left-radius: 0.5em; border-top-right-radius: 0.5em; border-bottom-right-radius: 0.5em; border-bottom-left-radius: 0.5em; color: white; text-align: center; font-size: 1em; box-shadow: rgb(165, 165, 165) 0.2em 0.2em 0.1em; background-color: #00BBEC;"><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 1em; font-family: inherit;">1、这里输入标题</span></strong></span>
				</section>
			</div>
		</div>
		<!--样式-1 -->
		<div data-color="span:border-bottom-color;span.hao:background-color"
			class="element-item">
			<div class="content">
				<h2
					style="white-space: normal; margin: 8px 0px 0px; padding: 0px; font-size: 16px; font-weight: normal; max-width: 100%; color: rgb(0, 187, 236); height: 32px; line-height: 18px; font-family: 微软雅黑; border-bottom-color: rgb(227, 227, 227); border-bottom-width: 1px; border-bottom-style: solid; text-align: justify; word-wrap: break-word !important;">
					<span
						style="margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(0, 187, 236); line-height: 28px; border-bottom-color: rgb(0, 187, 236); border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block; word-wrap: break-word !important;"><span
						style="margin: 0px 8px 0px 0px; padding: 4px 10px; max-width: 100%; border-top-left-radius: 80%; border-top-right-radius: 100%; border-bottom-right-radius: 90%; border-bottom-left-radius: 20%; color: rgb(255, 255, 255); background-color: rgb(0, 187, 236); word-wrap: break-word !important;"
						class="hao">1</span></span><span
						style="margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(38, 38, 38); line-height: 28px; border-bottom-color: rgb(0, 187, 236); border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block; word-wrap: break-word !important;"><strong
						style="color: rgb(60, 117, 45); max-width: 100%; line-height: 24px; word-wrap: break-word !important;">这可输入标题</strong></span>
				</h2>
			</div>
		</div>
		<!--样式1 -->
		<div
			data-color="section.s0002:border-top-color;section.s0002:border-bottom-color"
			class="element-item">
			<div class="content">
				<section
					style="display: inline-block; height: 2em; max-width: 100%; line-height: 1em; box-sizing: border-box; border-top: 1.1em solid #00BBEC; border-bottom: 1.1em solid #00BBEC; border-right: 1em solid transparent;"
					class="s0002">
					<section
						style="height: 2em; margin-top: -1em; color: white; padding: 0.5em 1em; max-width: 100%; white-space: nowrap; text-overflow: ellipsis;">这里输入标题</section>
				</section>
			</div>
		</div>
		<!--样式2 -->
		<div data-color="section.s0002:background-color;section.s0003:color"
			class="element-item">
			<div class="content">
				<section style="margin: 0.8em 0 0.5em 0;">
					<section
						style="display: inline-block; width: 2.5em; height: 2.5em; border-radius: 2em; vertical-align: top; text-align: center; background-color: #00BBEC;"
						class="s0002">
						<section style="display: table; width: 100%;">
							<section
								style="display: table-cell; vertical-align: middle; font-weight: bolder; line-height: 1.3em; font-size: 2em; font-family: inherit; font-style: normal; color: rgb(255, 255, 255);">1</section>
						</section>
					</section>
					<section
						style="display: inline-block; margin-left: 0.7em; padding-top: 0.3em;">
						<section
							style="line-height: 1.4em; font-size: 1.5em; font-family: inherit; font-style: normal; color: #00BBEC;"
							class="s0003">请输入标题</section>
					</section>
				</section>
			</div>
		</div>
		<!--样式3 -->
		<div data-color="h2:color;h2 .i_num:background-color"
			class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0px 0px; padding: 0px; height: 32px; text-align: justify; color: #00BBEC; line-height: 18px; font-family: 微软雅黑; font-size: 16px; font-weight: normal; white-space: normal;">
					<span
						style="padding: 0px 2px 3px; line-height: 28px; float: left; display: block;"><span
						style="padding: 4px 10px; border-radius: 80% 100% 90% 20%; color: rgb(255, 255, 255); margin-right: 8px; background-color: #00BBEC;"
						class="i_num">1</span><strong class="ue_t">这可输入标题</strong></span>
				</h2>
			</div>
		</div>
		<!--样式4 -->
		<div data-color="h2:color;h2 .i_num:background-color"
			class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0px 0px; padding: 0px; height: 32px; text-align: justify; color: #00BBEC; line-height: 18px; font-family: 微软雅黑; font-size: 16px; font-weight: normal; white-space: normal;">
					<span
						style="padding: 0px 2px 3px; line-height: 28px; float: left; display: block;"><span
						style="padding: 4px 10px; color: #ffffff; margin-right: 8px; background-color: #00BBEC;"
						class="i_num">2</span><strong class="ue_t">这可输入标题</strong></span>
				</h2>
			</div>
		</div>
		<!--样式5 -->
		<div
			data-color="section .s001:border-top-color;section .s001:border-bottom-color"
			class="element-item">
			<div class="content">
				<section
					style="text-align: center; font-size: 1em; vertical-align: middle; white-space: nowrap;">
					<section
						style="margin: 0 1em; white-space: nowrap; height: 0; border-top: 1.5em solid #00BBEC; border-bottom: 1.5em solid #00BBEC; border-left: 1.5em solid transparent; border-right: 1.5em solid transparent;"
						class="s001"></section>
					<section
						style="margin: -2.75em 1.65em; white-space: nowrap; height: 0; border-top: 1.3em solid #ffffff; border-bottom: 1.3em solid #ffffff; border-left: 1.3em solid transparent; border-right: 1.3em solid transparent;"></section>
					<section
						style="margin: 0.45em 2.1em; white-space: nowrap; height: 0; vertical-align: middle; border-top: 1.1em solid #00BBEC; border-bottom: 1.1em solid #00BBEC; border-left: 1.1em solid transparent; border-right: 1.1em solid transparent;"
						class="s001">
						<section
							style="padding: 0 1em; margin-top: -0.5em; font-size: 1.2em; line-height: 1em; color: white; white-space: nowrap; max-height: 1em; overflow: hidden;">请输入标题</section>
					</section>
				</section>
			</div>
		</div>
		<!--样式6 -->
		<div
			data-color="h2 .i_num:background-color;h2 .i_tit:;h2&gt;span:border-bottom-color"
			class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0px 0px; padding: 0px; height: 32px; text-align: justify; color: rgb(62, 62, 62); line-height: 18px; font-family: 微软雅黑; font-size: 16px; font-weight: normal; border-bottom-color: rgb(227, 227, 227); border-bottom-width: 1px; border-bottom-style: solid; white-space: normal;">
					<span
						style="padding: 0px 2px 3px; color: rgb(0, 112, 192); line-height: 28px; border-bottom-color: #00BBEC; border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block;"><span
						style="padding: 4px 10px; border-radius: 80% 100% 90% 20%; color: rgb(255, 255, 255); margin-right: 8px; background-color: #00BBEC;"
						class="i_num">序号.</span><strong style="color: #00BBEC;"
						class="ue_t i_tit">标题党</strong></span>
				</h2>
			</div>
		</div>
		<!--样式7 -->
		<div
			data-color="h2 .i_num:background-color;h2 .i_tit:color;h2&gt;span:border-bottom-color"
			class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0px 0px; padding: 0px; height: 32px; text-align: justify; color: rgb(62, 62, 62); line-height: 18px; font-family: 微软雅黑; font-size: 16px; font-weight: normal; border: 0; white-space: normal;">
					<span
						style="padding: 0px 2px 3px; color: rgb(0, 112, 192); line-height: 28px; border-bottom-color: #00BBEC; border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block;"><span
						style="padding: 4px 10px; border-radius: 80% 100% 90% 20%; color: rgb(255, 255, 255); margin-right: 8px; background-color: #00BBEC;"
						class="i_num">序号.</span><strong style="color: #00BBEC;"
						class="ue_t i_tit">标题党</strong></span>
				</h2>
			</div>
		</div>
		<!--样式8 -->
		<div
			data-color="h2:border-bottom-color;h2&gt;span:border-bottom-color"
			class="element-item">
			<div class="content">
				<h2
					style="white-space: normal; margin: 0; font-weight: normal; font-size: 20px; max-width: 100%; padding: 1px 0; color: rgb(48, 55, 64); font-family: 微软雅黑; text-align: justify; line-height: 2px; height: 35px; border-bottom-color: #00BBEC; border-bottom-width: 1px; border-bottom-style: solid; word-wrap: break-word !important; box-sizing: border-box !important;">
					<span
						style="max-width: 100%; padding: 8px 8px 2px; border-bottom-color: #00BBEC; border-bottom-width: 20px; border-bottom-style: solid; float: left; display: block; word-wrap: break-word !important; box-sizing: border-box !important;"><strong
						style="max-width: 100%; line-height: 24px; word-wrap: break-word !important; box-sizing: border-box !important;"><strong
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">1</strong></strong></span>
					<p
						style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; min-height: 1.5em; white-space: pre-wrap; padding: 0px; line-height: 2em; word-wrap: break-word !important; box-sizing: border-box !important;">
						<span style="background-color: rgb(255, 192, 0); padding: 0 5px;"><strong>标题<span
								style="font-size: 14px; color: rgb(127, 127, 127);">副标题</span></strong></span><br
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">
					</p>
				</h2>
				<p>&nbsp;</p>
			</div>
		</div>
		<!--样式9 -->
		<div data-color="h2 .i_num:background-color" class="element-item">
			<div class="content">
				<h2
					style="margin: 8px 0px 0px; padding: 0px; height: 32px; text-align: justify; line-height: 18px; font-family: 微软雅黑; font-size: 16px; font-weight: normal; white-space: normal;">
					<span
						style="padding: 0px 2px 3px; line-height: 28px; float: left; display: block;"><span
						style="padding: 4px 10px; border-radius: 80% 100% 90% 20%; color: #ffffff; margin-right: 8px; background-color: #00BBEC;"
						class="i_num">这可输入标题</span></span>
				</h2>
			</div>
		</div>
		<!--样式10 -->
		<div data-color="p span:background-color" class="element-item">
			<div class="content">
				<p
					style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: pre-wrap; word-wrap: break-word !important; min-height: 1.5em; max-width: 100%; font-size-adjust: none; font-stretch: normal; -webkit-text-stroke-width: 0px;">
					<strong><span
						style="padding: 4px 10px; border-radius: 5px; color: rgb(255, 255, 255); font-family: 微软雅黑, Microsoft YaHei; margin-right: 8px; word-wrap: break-word !important; max-width: 100%; background-color: #00BBEC;">这可输入标题</span></strong>
				</p>
			</div>
		</div>
		<!--样式11 -->
		<div data-color="p span:background-color" class="element-item">
			<div class="content">
				<p
					style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: pre-wrap; word-wrap: break-word !important; min-height: 1.5em; max-width: 100%; font-size-adjust: none; font-stretch: normal; -webkit-text-stroke-width: 0px;">
					<strong><span
						style="padding: 4px 10px; color: rgb(255, 255, 255); font-family: 微软雅黑, Microsoft YaHei; margin-right: 8px; word-wrap: break-word !important; max-width: 100%; background-color: #00BBEC;">这可输入标题</span></strong>
				</p>
			</div>
		</div>
		<!--样式12 -->
		<div data-color="h2 span:color;h2 span:border-bottom-color"
			class="element-item">
			<div class="content">
				<h2
					style="font-family: 微软雅黑, 宋体, tahoma, arial; margin: 8px 0px 0px; padding: 0px; font-size: 12px; font-weight: normal; white-space: normal; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(227, 227, 227); height: 32px; line-height: 18px;">
					<span class="ue_t"
						style="font-family: 微软雅黑, sans-serif !important; font-size: 14px; color: #00BBEC; display: block; float: left; padding: 0px 2px 3px; line-height: 28px; border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #00BBEC; font-weight: bold;">这可输入标题</span>
				</h2>
			</div>
		</div>
		<!--样式13 -->
		<div data-color="h2 span:color;h2 span:border-bottom-color"
			class="element-item">
			<div class="content">
				<h2
					style="font-family: 微软雅黑, 宋体, tahoma, arial; margin: 8px 0px 0px; padding: 0px; font-size: 12px; font-weight: normal; white-space: normal; border: 0; height: 32px; line-height: 18px;">
					<span class="ue_t"
						style="font-family: 微软雅黑, sans-serif !important; font-size: 14px; color: #00BBEC; display: block; float: left; padding: 0px 2px 3px; line-height: 28px; border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #00BBEC; font-weight: bold;">这可输入标题</span>
				</h2>
			</div>
		</div>
		<div data-color="h2:color;h2:border-left-color" class="element-item">
			<div class="content">
				<h2 class="ue_t"
					style="margin: 5px 0px 13px 0px; padding: 0px 10px; border-width: 0px 0px 0px 5px; border-left-style: solid; border-left-color: #00BBEC; -webkit-font-smoothing: antialiased; font-size: 16px; color: #00BBEC; font-family: Georgia, Simsun, serif; line-height: 25px; white-space: normal; font-family: 微软雅黑;">这可输入标题</h2>
			</div>
		</div>
		<!--样式14 -->
		<div data-color="" class="element-item">
			<div class="content">
				<section style="text-align: center; margin: 0 1em; line-height: 1.6em;">
					<img src="${resourceUrl}/img/template/640.png"
						 style="height: 36px !important; width: 100%; vertical-align: middle;">
					<section style="color: white; font-size: 1em; margin-top: -2.1em; white-space: nowrap;">请输入标题
					</section>
				</section>
			</div>
		</div>
		<!--样式15 -->
		<div data-color="blockquote:border-left-color" class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; line-height: 25px; white-space: normal; font-size: 14px; font-family: arial, helvetica, sans-serif; margin: 5px 0px 0px; padding: 10px; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; color: rgb(255, 255, 255); border-left-color: rgb(0, 187, 236); border-left-width: 10px; border-left-style: solid; box-shadow: rgb(153, 153, 153) 2px 2px 4px; text-shadow: rgb(34, 95, 135) 0px 1px 0px; background-color: rgb(55, 57, 57); word-wrap: break-word !important; box-sizing: border-box !important;"
					data-mce-style="margin: 5px 0px 0px; padding: 10px; max-width: 100%; orphans: 2; widows: 2; line-height: 25px; font-family: arial, helvetica, sans-serif; text-shadow: #225f87 0px 1px 0px; color: #ffffff; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; box-shadow: #999999 2px 2px 4px; border-left-width: 10px; border-left-style: solid; border-left-color: #fdd000; background-color: #373939; word-wrap: break-word !important;">
					<strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-family: 微软雅黑; font-size: 16px;">1、这里输入标题</span></strong>
				</blockquote>
			</div>
		</div>
		<!--样式16-->
		<div data-color="strong.span:text-shadow" class="element-item">
			<div class="content">
				<p
					style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; background-color: rgb(255, 255, 255); box-sizing: border-box !important;">
					<strong
						style="color: rgb(255, 255, 255); max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; text-shadow: rgb(0, 187, 236) 1px 0px 4px, rgb(0, 187, 236) 0px 1px 4px, rgb(0, 187, 236) 0px -1px 4px, rgb(0, 187, 236) -1px 0px 4px;"
						glowfont="display:inline-block; color:white; text-shadow:1px 0 4px #ff0000,0 1px 4px #ff0000,0 -1px 4px #ff0000,-1px 0 4px #ff0000;filter:glow(color=#ff0000,strength=3)">请输入标题</span></strong><br>
				</p>
			</div>
		</div>
		<!--样式17-->
		<div data-color="span:background-color" class="element-item">
			<div class="content">
				<p
					style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: normal; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; background-color: rgb(255, 255, 255); box-sizing: border-box !important;">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 4px 10px; border-top-left-radius: 0.5em 4em; border-top-right-radius: 3em 1em; border-bottom-right-radius: 0.5em 2em; border-bottom-left-radius: 3em 1em; text-align: justify; color: rgb(255, 255, 255); font-family: 微软雅黑, sans-serif; box-shadow: rgb(165, 165, 165) 4px 4px 2px; background-color: rgb(0, 187, 236);"><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><strong
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">请输入标题</span></strong></strong></span>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="tab_con" id="tab2" style="display: none;">
	<div style="width: 100%;" class="element-list">

		<div title=""
			data-color="section.main:border-color;section.main:background-color;"
			class="element-item">
			<div class="content">
				<section
					style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-size: 14px; font-family: 微软雅黑;">
					<fieldset
						style="margin: 0.5em 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: rgb(62, 62, 62); line-height: 25px; white-space: normal; word-wrap: break-word !important;">
						<section data-width="100%"
							style="margin: 0px; padding: 0px; width: 100%; box-sizing: border-box; display: inline-block; text-align: center; color: inherit; word-wrap: break-word !important;">
							<img data-width="60px"
								style="box-sizing: border-box; color: inherit; height: 65px; margin: 0px auto; padding: 0px; width: 60px; visibility: visible !important; word-wrap: break-word !important;"
								src="http://imgqn.koudaitong.com/upload_files/2015/06/14/b1100368cab872507ba312794c68f8db.jpg">
						</section>
						<section
							style="margin: -2.3em 0px 0px; padding: 2em 0px 0px; max-width: 100%; box-sizing: border-box; min-height: 15em; font-size: 1em; text-decoration: inherit; color: rgb(255, 255, 255); border-color: rgb(0, 187, 236); background-image: url(http://imgqn.koudaitong.com/upload_files/2015/06/14/a5bbfee8f6458b68c5e438fa1f03024e.jpg); background-color: rgb(0, 187, 236); word-wrap: break-word !important; background-repeat: repeat repeat;"
							class="main">
							<section data-width="7em"
								style="margin: 0.3em auto; padding: 0.5em; max-width: 100%; box-sizing: border-box; width: 7em; height: 3.5em; line-height: 2em; overflow: hidden; -webkit-transform: rotate(-5deg); font-size: 32px; text-align: center; text-decoration: inherit; color: inherit; background-image: url(http://imgqn.koudaitong.com/upload_files/2015/06/14/2798a23b64e4c2fceebf966430a54152.jpg); background-size: contain; word-wrap: break-word !important; background-repeat: no-repeat no-repeat;">
								<section
									style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: inherit; word-wrap: break-word !important;">
									<span
										style="box-sizing: border-box; color: inherit; margin: 0px; max-width: 100%; padding: 0px; word-wrap: break-word !important;"
										data-brushtype="text">公告</span>
								</section>
							</section>
							<section
								style="margin: 0px; padding: 1em; max-width: 100%; box-sizing: border-box; color: inherit; word-wrap: break-word !important;">
								<section
									style="margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: inherit; word-wrap: break-word !important;">
									<p
										style="color: inherit; box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;">
										本背景可以换色哦~</p>
								</section>
							</section>
						</section>
					</fieldset>
				</section>
				<p>
					<br>
				</p>

			</div>
		</div>

		<div title=""
			data-color="section.main:background-color;section.main:border-color;"
			class="element-item">
			<div class="content">
				<section
					style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-family: 微软雅黑;">
					<section
						style="margin: 10px 0px; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; padding: 50px 0px; color: rgb(255, 255, 255); text-align: center; border-color: rgb(0, 187, 236); background-color: rgb(0, 187, 236); box-sizing: border-box;"
						class="main">
						<span
							style="border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; border: 1px solid rgb(251, 251, 251); color: inherit; font-size: 18px; line-height: 42px; padding: 10px 15px; box-sizing: border-box; margin: 0px;"
							data-brushtype="text">微信编辑器</span>
						<section
							style="margin-top: 30px; color: inherit; box-sizing: border-box; padding: 0px;">
							<p
								style="color: inherit; box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;">
								最易用的图文排版工具</p>
							<p
								style="color: inherit; box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;">
								<span
									style="color: inherit; line-height: 22.3999996185303px; border-color: rgb(218, 137, 133); box-sizing: border-box; padding: 0px; margin: 0px;">天天微信，必属精品</span>
							</p>
						</section>
					</section>
				</section>
				<p>
					<br>
				</p>

			</div>
		</div>
		<div title=""
			data-color="section.main:border-color;p.main2:border-color;span.main3:border-color;span.main3:color;strong.main4:border-color;section.main5:border-color;section.main6:border-color;section.main7:border-color;section.main7:background-color;section.main8:border-color;section.main9:background-color;section.main9:border-color;"
			class="element-item">
			<div class="content">
				<section
					style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-family: 微软雅黑;">
					<section data-width="92px"
						style="width: 92px; margin-bottom: 0px; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px;"
						class="main">
						<p
							style="text-align: center; color: inherit; line-height: 2em; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;"
							class="main2">
							<span
								style="border-color: rgb(0, 187, 236); color: rgb(0, 187, 236); font-size: 16px; box-sizing: border-box; padding: 0px; margin: 0px;"
								class="main3"><strong
								style="border-color: rgb(0, 187, 236); color: inherit; box-sizing: border-box; padding: 0px; margin: 0px;"
								class="main4">微信编辑器</strong></span>
						</p>
					</section>
					<section
						style="margin-top: 0px; padding: 0px 5px; line-height: 10px; color: inherit; border: 1px solid rgb(0, 187, 236); box-sizing: border-box;"
						class="main5">
						<section data-width="50%"
							style="padding: 0px; font-size: 16px; color: inherit; height: 8px; margin: -8px 0px 0px 140px; width: 50%; background-color: rgb(254, 254, 254); border-color: rgb(0, 187, 236); box-sizing: border-box;"
							class="main6">
							<section data-width="8px"
								style="width: 8px; height: 8px; border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; line-height: 1; box-sizing: border-box; font-size: 18px; text-decoration: inherit; border-color: rgb(0, 187, 236); display: inline-block; margin: 0px; color: rgb(255, 255, 255); background-color: rgb(0, 187, 236); padding: 0px;"
								class="main7"></section>
						</section>
						<section
							style="padding: 0px; line-height: 2em; color: rgb(62, 62, 62); margin: 15px; box-sizing: border-box;"
							data-style="text-indent: 2em;">
							<p
								style="color: inherit; box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;">
								<span
									style="color: inherit; box-sizing: border-box; padding: 0px; margin: 0px;">微信编辑器是一个在线图文编辑工具，大量节省您排版的时间，让工作更轻松高效。</span>
							</p>
						</section>
						<section data-width="65%"
							style="padding: 0px; background-color: rgb(254, 254, 254); font-size: 16px; color: inherit; text-align: right; height: 10px; margin: 0px 0px -4px 25px; width: 65%; border-color: rgb(0, 187, 236); box-sizing: border-box;"
							class="main8">
							<section data-width="8px"
								style="margin: 0px auto 1px; border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; line-height: 1; box-sizing: border-box; text-decoration: inherit; background-color: rgb(0, 187, 236); border-color: rgb(218, 137, 133); display: inline-block; height: 8px; width: 8px; color: rgb(255, 255, 255); padding: 0px;"
								class="main9"></section>
						</section>
					</section>
				</section>
				<p>
					<br>
				</p>

			</div>
		</div>
		<div title=""
			data-color="section.main:border-color;section.main2:border-color;p.main3:border-color;span.main4:border-color;p.main5:border-color;strong.main6:border-color;span.main7:border-color"
			class="element-item">
			<div class="content">
				<section
					style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px; font-family: 微软雅黑;">
					<section
						style="border: 3px solid rgb(0, 187, 236); padding: 5px; box-sizing: border-box; margin: 0px;"
						class="main">
						<section
							style="border: 1px solid rgb(0, 187, 236); padding: 15px; text-align: center; color: inherit; box-sizing: border-box; margin: 0px;"
							class="main2">
							<p
								style="color: inherit; font-size: 12px; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;"
								class="main3">
								<span
									style="font-family: arial; border-color: rgb(0, 187, 236); color: inherit; box-sizing: border-box; padding: 0px; margin: 0px;"
									class="main4">微信编辑器</span>
							</p>
							<p
								style="color: inherit; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px; margin-top: 0px; margin-bottom: 0px;"
								class="main5">
								<strong
									style="color: inherit; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px; margin: 0px;"
									class="main6"><span
									style="color: inherit; font-size: 18px; border-color: rgb(0, 187, 236); box-sizing: border-box; padding: 0px; margin: 0px;"
									class="main7">操作方便才是硬道理</span></strong>
							</p>
						</section>
					</section>
				</section>
				<p>
					<br>
				</p>

			</div>
		</div>

		<!--样式1 -->
		<div data-color="span:color" class="element-item">
			<div class="content">
				<fieldset style="border: 0px; margin: 1em 0px;">
					<span
						style="float: left; padding-right: 0.1em; font-size: 2.7em; line-height: 1em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(0, 187, 236);">请</span>这里输入文字内容...
					在线微信内容编辑器
				</fieldset>
			</div>
		</div>

		<!--样式1 -->
		<div data-color="span.main:background-color" class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: #3e3e3e; font-family: 微软雅黑; line-height: 25px; white-space: normal; background-color: #fff; padding: 0; word-wrap: break-word !important; box-sizing: border-box !important">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin-left: 1em; line-height: 1.4em">
						<span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: .8em; font-family: inherit; padding: .2em .5em; border-top-left-radius: .3em; border-top-right-radius: .3em; border-bottom-right-radius: .3em; border-bottom-left-radius: .3em; color: #fff; text-align: center; background-color: #00bbec"
							class="main">这输入标题</span>&nbsp;<span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 1.2em; padding: .2em .5em; margin-left: .3em; border-top-left-radius: 1.2em; border-top-right-radius: 1.2em; border-bottom-right-radius: 1.2em; border-bottom-left-radius: 1.2em; color: #fff; font-size: .8em; line-height: 1.2em; font-family: inherit; background-color: #ccc">微信</span>
					</section>
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin-top: -.7em; padding: 1.4em 1em 1em; border: 1px solid #c0c8d1; border-top-left-radius: .3em; border-top-right-radius: .3em; border-bottom-right-radius: .3em; border-bottom-left-radius: .3em; color: #333; font-size: 1em; font-family: inherit; background-color: #efefef">可在这输入内容，微信编辑器，微信编辑首选。</section>
				</section>
			</div>
		</div>
		<!--样式1 -->
		<div
			data-color="section.main1:border-color;section.main2:border-color;section.main3:border-color;section.main4:border-color;section.main5:border-color;"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin: .5em 0; padding: 0; border: 0; max-width: 100%; color: rgb(68, 68, 68); font-family: 微软雅黑; line-height: 25px; white-space: normal; background-color: #fff; word-wrap: break-word !important; box-sizing: border-box !important">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box; height: 1em">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 16px; width: 1.5em; float: left; border-top-width: .4em; border-top-style: solid; border-color: rgb(0, 187, 236); border-left-width: .4em; border-left-style: solid"
							class="main1"></section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 16px; width: 1.5em; float: right; border-top-width: .4em; border-top-style: solid; border-color: rgb(0, 187, 236); border-right-width: .4em; border-right-style: solid"
							class="main2"></section>
					</section>
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box; margin: -.8em .1em -.8em .2em; padding: .8em; border: 1px solid rgb(0, 187, 236); border-top-left-radius: .3em; border-top-right-radius: .3em; border-bottom-right-radius: .3em; border-bottom-left-radius: .3em"
						class="main3">
						<section
							style="max-width: 100%; word-wrap: break-word; box-sizing: border-box !important; padding: 0; margin: 0; border: none; line-height: 1.4; word-break: break-all; background-image: none; font-size: 1em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(68, 68, 68)">可在这输入内容，
							微信编辑器，微信编辑首选。</section>
					</section>
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box; height: 1em">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 16px; width: 1.5em; float: left; border-bottom-width: .4em; border-bottom-style: solid; border-color: rgb(0, 187, 236); border-left-width: .4em; border-left-style: solid"
							class="main4"></section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: 16px; width: 1.5em; float: right; border-bottom-width: .4em; border-bottom-style: solid; border-color: rgb(0, 187, 236); border-right-width: .4em; border-right-style: solid"
							class="main5"></section>
					</section>
				</fieldset>
			</div>
		</div>
		<!--样式1 -->
		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 10px; border: 6px double rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; word-break: break-all; word-wrap: break-word !important; box-sizing: border-box !important;">
					<p
						style="margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px;"
						class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<!--样式1 -->
		<div data-color="section.main:background-color" class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: #3e3e3e; font-family: 'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, Arial, sans-serif; line-height: 25px; white-space: normal; background-color: #fff; word-wrap: break-word !important; box-sizing: border-box !important">
					<section
						style="max-width: 100%; margin: 0; border: 1px solid #e2e2e2; box-shadow: #e2e2e2 0 1em .1em -.8em; font-size: 1em; line-height: 1em; padding: .5em; text-align: right; word-wrap: break-word !important; box-sizing: border-box !important">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1.2em; margin-right: .2em; font-size: .7em; font-family: inherit; color: #787c81">极速品牌传播</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1.2em; margin-right: .2em; font-size: .7em; font-family: inherit; color: #787c81">炫酷视觉，极致体验</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1.2em; margin-right: .2em; font-size: .7em; font-family: inherit; color: #787c81">快速制作H5页面</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1.2em; margin-right: .2em; font-size: .7em; font-family: inherit; color: #787c81">移动互联网品牌推广专家</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1.2em; text-align: center; margin-right: 1em; background-color: #00bbec"
							class="main">
							<section
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 1em; font-family: inherit; color: #fff">专业</section>
						</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; vertical-align: top; width: 1.2em; line-height: 1em; font-size: 1.5em; font-family: inherit">微信H5微场景</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; text-align: left; line-height: 1.6em; font-size: .8em; font-family: inherit">微信号：123456</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; text-align: left; line-height: 1.6em; font-size: .8em; font-family: inherit; color: #787c81">微信关注：微信</section>
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; text-align: left; line-height: 1.6em; font-size: .8em; font-family: inherit; color: #787c81">
							更多精彩案例<br
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">www.domain.com
						</section>
					</section>
				</section>
			</div>
		</div>
		<!--样式1 -->
		<div
			data-color="section.main:background-color;section.main2:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin-left: 10px; padding: 0px; border: 0px; max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; white-space: normal; background-color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important;">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin-left: -0.5em; line-height: 1.4em;">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; padding: 0.2em 0.5em; border-top-left-radius: 0.3em; border-top-right-radius: 0.3em; border-bottom-right-radius: 0.3em; border-bottom-left-radius: 0.3em; color: white; font-size: 0.8em; text-align: center; -webkit-transform: rotateZ(-10deg); -webkit-transform-origin: 0% 100%; background-color: #00BBEC;"
							class="main">我的观点</section>
					</section>
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin-top: -1.5em; border: 1px solid #00BBEC; font-size: 1em;"
						class="main2">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 1.4em 1em 1em;">
							<span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 1em; line-height: 1.2; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(253, 176, 0);"></span><span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 1em; line-height: 1.2; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(51, 51, 51);">可在这输入内容，
								微信编辑器，微信编辑首选。</span>
						</section>
					</section>
				</fieldset>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 15px; border: 3px dashed #00BBEC; border-radius: 10px;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 15px; border: 1px solid #00BBEC;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 15px; border: 1px solid #00BBEC; border-radius: 5px;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:border-left-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0; max-width: 100%; word-wrap: break-word; padding: 15px 25px; top: 0px; line-height: 24px; font-size: 14px; vertical-align: baseline; border-left-color: #00BBEC; border-left-width: 10px; border-left-style: solid; display: block; background-color: #efefef;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 20px 15px 15px 48px; border: 1px solid #00BBEC; border-radius: 5px; line-height: 1.5; background-image: url(https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr8ZmVegySCLnxIFNbiapIkjzQtojyF4Yc6cqozUlj2iaWK6yhTuFgUGK7ibNVaqFdMbMA1eLo2nAjVA/0/mmbizgif); background-position: 10px 11px; background-repeat: no-repeat;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0px; padding: 20px 15px 15px 48px; border: 1px solid #00BBEC; border-radius: 5px; line-height: 1.5; background-image: url(https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr8ZmVegySCLnxIFNbiapIkjfbmlrwbMmEekDcogkX6hWoIdzSgEGa6MPOAApvhC6b2qYXibCYhzsHQ/0); background-position: 10px 11px; background-repeat: no-repeat;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div
			data-color="fieldset&gt;legend&gt;p:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="padding: 15px; border: 1px dotted #00BBEC; line-height: 2em; font-family: 宋体; word-wrap: break-word !important; min-height: 1.5em; max-width: 100%; border-bottom-right-radius: 15px; border-bottom-left-radius: 10px;">
					<legend
						style="margin: 0px; padding: 0px; text-align: center; color: rgb(0, 0, 0); font-family: 微软雅黑; word-wrap: break-word !important; max-width: 100%;">
						<p
							style="margin: 0px; padding: 0px 20px 4px; color: rgb(255, 255, 255); line-height: 2em; font-size: 14px; white-space: pre-wrap; word-break: normal; word-wrap: normal; min-height: 1.5em; max-width: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; background-color: #00BBEC;">
							<strong class="ue_t" style="max-width: 100%;">请输入标题</strong>
						</p>
					</legend>
					<p
						style="margin: 0px; padding: 0px; line-height: 2em; word-break: normal; word-wrap: normal; min-height: 1.5em; max-width: 100%;">
						<span class="ue_t"
							style="line-height: 2em; word-wrap: break-word !important; max-width: 100%;">可在这输入内容，
							微信编辑器，微信编辑首选。</span>
					</p>
				</fieldset>
			</div>
		</div>

		<!--样式8 -->
		<div
			data-color="fieldset&gt;legend&gt;span:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin: 0px; padding: 5px; border: 1px solid #00BBEC;">
					<legend style="margin: 0px 10px;">
						<span class="ue_t"
							style="padding: 5px 10px; color: #ffffff; font-weight: bold; font-size: 14px; background-color: #00BBEC;">这输入标题</span>
					</legend>
					<blockquote style="margin: 0px; padding: 10px;">
						<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
					</blockquote>
				</fieldset>
			</div>
		</div>

		<!--样式9-->
		<div
			data-color="fieldset&gt;legend&gt;span:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin: 0px; padding: 5px; border: 1px solid #00BBEC;">
					<legend style="margin: 0px 10px;">
						<span class="ue_t"
							style="padding: 5px 10px; color: #ffffff; font-weight: bold; font-size: 14px; background-color: #00BBEC; border-radius: 5px;">这输入标题</span>
					</legend>
					<blockquote style="margin: 0px; padding: 10px;">
						<p class="ue_t">我的标题是圆角，如果看我和上面长得一样，那是因为你的浏览器版本被全国 N% 的电脑击败了。</p>
					</blockquote>
				</fieldset>
			</div>
		</div>

		<!--样式10-->
		<div
			data-color="fieldset&gt;legend&gt;span:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="margin: 0px; padding: 5px; border: 1px solid #00BBEC; border-radius: 5px; background-color: #efefef;">
					<legend style="margin: 0px 10px;">
						<span class="ue_t"
							style="padding: 5px 10px; color: #ffffff; font-weight: bold; font-size: 14px; background-color: #00BBEC; border-radius: 5px;">这输入标题</span>
					</legend>
					<blockquote style="margin: 0px; padding: 10px;">
						<p class="ue_t">我的标题内容区是圆角,微信在线编辑器</p>
					</blockquote>
				</fieldset>
			</div>
		</div>

		<div
			data-color="fieldset&gt;legend&gt;span&gt;strong&gt;span:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="font: 16px/24px 宋体; margin: 0px; padding: 15px; border: 1px solid #00BBEC; text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; word-wrap: break-word !important; max-width: 100%; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; box-shadow: 5px 5px 2px rgb(165, 165, 165); background-color: #efefef; -webkit-text-stroke-width: 0px;">
					<legend
						style="margin: 0px; padding: 0px; text-align: center; font-size: medium; word-wrap: break-word !important; max-width: 100%;">
						<span
							style="font-family: 微软雅黑; font-size: 14px; word-wrap: break-word !important; max-width: 100%;"><strong
							style="word-wrap: break-word !important; max-width: 100%;"><span
								style="padding: 4px 10px; border-radius: 2em 1em 4em/0.5em 3em; color: rgb(255, 255, 255); word-wrap: break-word !important; max-width: 100%; box-shadow: 4px 4px 2px rgb(165, 165, 165); background-color: #00BBEC;">这输入标题</span></strong></span>
					</legend>
					<p
						style="font: 14px/24px 微软雅黑, Microsoft YaHei; color: rgb(89, 89, 89); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; word-wrap: break-word !important; max-width: 100%; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; -webkit-text-stroke-width: 0px;">可在这输入内容，
						微信编辑器，微信编辑首选。</p>
				</fieldset>
			</div>
		</div>

		<div
			data-color="fieldset&gt;legend&gt;span&gt;strong&gt;span:background-color;fieldset:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="font: 16px/24px 宋体; margin: 0px; padding: 15px; border: 1px solid #00BBEC; text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; word-wrap: break-word !important; max-width: 100%; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: #efefef; -webkit-text-stroke-width: 0px;">
					<legend
						style="margin: 0px; padding: 0px; text-align: center; font-size: medium; word-wrap: break-word !important; max-width: 100%;">
						<span
							style="font-family: 微软雅黑; font-size: 14px; word-wrap: break-word !important; max-width: 100%;"><strong
							style="word-wrap: break-word !important; max-width: 100%;"><span
								style="padding: 4px 10px; border-radius: 2em 1em 4em/0.5em 3em; color: #ffffff; word-wrap: break-word !important; max-width: 100%; background-color: #00BBEC;">这输入标题</span></strong></span>
					</legend>
					<p
						style="font: 14px/24px 微软雅黑, Microsoft YaHei; color: rgb(89, 89, 89); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; word-wrap: break-word !important; max-width: 100%; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; -webkit-text-stroke-width: 0px;">我是IOS7风格，没阴影。</p>
				</fieldset>
			</div>
		</div>

		<div data-color="blockquote:background-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0; border-bottom: rgb(225, 225, 225) 2px dotted; text-align: justify; border-left: rgb(225, 225, 225) 2px dotted; padding-bottom: 10px; widows: 2; text-transform: none; background-color: #00BBEC; text-indent: 0px; padding-left: 20px; padding-right: 20px; font: medium/21px 微软雅黑; max-width: 100%; white-space: normal; orphans: 2; letter-spacing: normal; color: #ffffff; border-top: rgb(225, 225, 225) 2px dotted; border-right: rgb(225, 225, 225) 2px dotted; word-spacing: 0px; padding-top: 10px; box-shadow: rgb(225, 225, 225) 5px 5px 2px; border-top-left-radius: 0.5em 4em; border-top-right-radius: 3em 0.5em; -webkit-text-size-adjust: none; -webkit-text-stroke-width: 0px; border-bottom-right-radius: 0.5em 1em; border-bottom-left-radius: 0em 3em">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote:background-color" class="element-item">
			<div class="content">
				<blockquote
					style="margin: 0; border-width: 2px; font: 16px/24px 微软雅黑; padding: 10px 15px; border-radius: 5px; color: rgb(255, 255, 255); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; font-size-adjust: none; font-stretch: normal; background-color: #00BBEC; -webkit-text-stroke-width: 0px;">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div data-color="blockquote.f:background-color;" class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; margin: 0; padding: 5px 15px; color: rgb(255, 255, 255); line-height: 25px; font-weight: bold; background-color: #00BBEC; text-align: left; border-radius: 5px 5px 0 0; border: 0;"
					class="f">
					<span class="ue_t">这输入标题</span>
				</blockquote>
				<blockquote
					style="max-width: 100%; margin: 0px; padding: 10px 15px 20px 15px; border-radius: 0 0 5px 5px; border: 0; background-color: #efefef; line-height: 25px;"
					class="l">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div
			data-color="blockquote.f:background-color;blockquote.l:border-color"
			class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; margin: 0; padding: 5px 15px; color: #ffffff; line-height: 25px; font-weight: bold; background-color: #00BBEC; text-align: left; border-radius: 5px 5px 0 0; border: 0;"
					class="f">
					<span class="ue_t">这输入标题</span>
				</blockquote>
				<blockquote
					style="max-width: 100%; margin: 0px; padding: 10px 15px 20px 15px; border-radius: 0 0 5px 5px; border: 1px solid #00BBEC; line-height: 25px;"
					class="l">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div
			data-color="blockquote.f:background-color;blockquote.l:border-color"
			class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; margin: 0; padding: 5px 15px; color: #ffffff; line-height: 25px; font-weight: bold; background-color: #00BBEC; text-align: left; border-radius: 5px 5px 0 0; border: 0; display: inline-block;"
					class="f">
					<span class="ue_t">这输入标题</span>
				</blockquote>
				<blockquote
					style="max-width: 100%; margin: 0px; padding: 10px 15px; border: 1px solid #00BBEC; line-height: 25px;"
					class="l">
					<p class="ue_t">可在这输入内容， 微信编辑器，微信编辑首选。</p>
				</blockquote>
			</div>
		</div>

		<div
			data-color="section:border-color;section .s0002:background-color;"
			class="element-item">
			<div class="content">
				<section>
					<section
						style="margin: 0; border: 1px solid #00BBEC; text-align: center;">
						<section
							style="margin: 1em auto; width: 12em; height: 12em; border-radius: 6em; border: 0.1em solid #00BBEC;"
							class="s0001">
							<section
								style="width: 11em; height: 11em; margin: 0.4em auto; border-radius: 5.5em; background-color: #00BBEC; text-align: center; display: table; max-height: 11em;"
								class="s0002">
								<section
									style="display: table-cell; vertical-align: middle; font-size: 1.5em; line-height: 1.2em; margin: 1em; padding: 1em; color: white; max-height: 11em;">请输入标题</section>
							</section>
						</section>
						<section
							style="display: inline-block; margin: 1em 1em 2em; color: white; background-color: #00BBEC; font-size: 1em; line-height: 1.5em; padding: 0.5em 1em; border-radius: 1em; white-space: nowrap; max-width: 100%;"
							class="s0002">副标题</section>
					</section>
					<section
						style="margin: 0; padding: 1em; color: #000000; text-align: center; border: 1px solid #00BBEC; border-top: 0; font-size: 1em; line-height: 1.4em;">
						<p>
							请输入内容请输入内容<br>请输入内容请输入内容
						</p>
					</section>
				</section>
			</div>
		</div>

		<div
			data-color="section:border-color;section.s0001:background-color;section.s0002:border-top-color;section.s0002:border-bottom-color"
			class="element-item">
			<div class="content">
				<section>
					<section
						style="background-color: white; border: 1px solid #00BBEC; box-shadow: rgb(165, 165, 165) 0em 1em 0.1em -0.6em; line-height: 1.6em;">
						<section
							style="padding: 1em; text-align: center; font-size: 1.4em; font-weight: bold; line-height: 1.4em; color: white; background-color: #00BBEC;"
							class="s0001">请输入名称</section>
						<section style="text-align: left; margin-top: 1.5em;">
							<img src=""
								style="vertical-align: top; margin-left: 1em; width: 30px;">
							<section
								style="display: inline-block; width: 40%; margin-left: 0.5em; padding: 0.2em;">时间</section>
						</section>
						<section style="text-align: left; margin-top: 1em;">
							<img src=""
								style="vertical-align: top; margin-left: 1em; width: 30px;">
							<section
								style="display: inline-block; width: 40%; margin-left: 0.5em; padding: 0.2em;">地点</section>
						</section>
						<br> <br> <br>
					</section>
				</section>
			</div>
		</div>

		<div
			data-color="section.s0002:border-top-color;section.s0002:border-bottom-color"
			class="element-item">
			<div class="content">
				<section
					style="margin-top: 1.5em; display: inline-block; height: 2em; max-width: 100%; line-height: 1em; box-sizing: border-box; border-top: 1.1em solid #00BBEC; border-bottom: 1.1em solid #00BBEC; border-right: 1em solid transparent;"
					class="s0002">
					<section
						style="height: 2em; margin-top: -1em; color: white; padding: 0.5em 1em; max-width: 100%; white-space: nowrap; text-overflow: ellipsis;">事项1</section>
				</section>
				<section style="padding: 1em;">
					<p>
						请输入活动内容<br>请输入活动内容<br>......
					</p>
				</section>
			</div>
		</div>

		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; margin: 0px; padding: 10px 15px; border: 6px solid #00BBEC; border-top-left-radius: 50px; border-bottom-right-radius: 50px; box-shadow: rgb(165, 165, 165) 5px 5px 2px; word-wrap: break-word !important;">
					<p
						style="margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 24px; max-width: 100%; min-height: 1.5em; word-wrap: break-word !important;">看见我左上角
						和 右下角的曲线美了没；没看到的话，那是…………你的电脑又被全国 N% 的电脑击败了。微信在线编辑器</p>
				</blockquote>
			</div>
		</div>

		<div data-color="p:border-color;span:color" class="element-item">
			<div class="content">
				<p
					style="margin: 15px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; padding: 20px; border: 1px dotted rgb(0, 187, 236); text-align: justify; color: rgb(73, 68, 41); line-height: 2em; font-family: 微软雅黑; font-size: 14px; border-bottom-right-radius: 15px; border-bottom-left-radius: 10px; background-color: rgb(255, 255, 255); box-sizing: border-box !important;">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(0, 187, 236);"><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">请输入内容</strong></span>
				</p>
			</div>
		</div>

		<div data-color="span:color" class="element-item">
			<div class="content">
				<fieldset
					style="margin: 5px 0px; padding: 5px; border: 1px solid rgb(204, 204, 204); max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; white-space: normal; box-shadow: rgb(165, 165, 165) 5px 5px 2px; background-color: rgb(248, 247, 245); word-wrap: break-word !important; box-sizing: border-box !important;"
					class="comment_quote">
					<legend
						style="padding: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: 0px; line-height: 1.8em;">
						<strong
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 18px; line-height: 42.66666793823242px; text-align: center; white-space: pre-wrap;"><span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(0, 187, 236); text-shadow: rgb(201, 201, 201) 5px 3px 1px;">精彩内容</span></strong>
					</legend>
					<p
						style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;">
						请输入内容<br>
					</p>
				</fieldset>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<blockquote
					style="max-width: 100%; color: rgb(62, 62, 62); white-space: normal; line-height: 25.600000381469727px; background-color: rgb(255, 255, 255); margin: 0.2em; padding: 10px; border: 3px solid rgb(201, 201, 201); border-image-source: none; font-family: 微软雅黑; box-shadow: rgb(170, 170, 170) 0px 0px 10px; -webkit-box-shadow: rgb(170, 170, 170) 0px 0px 10px; word-wrap: break-word !important; box-sizing: border-box !important;">
					<p
						style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; box-sizing: border-box !important;">
						<span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; line-height: 25.6000003814697px;">可在这输入内容，
							微信编辑器，微信编辑首选。</span>
					</p>
				</blockquote>
			</div>
		</div>
		<div data-color="blockquote:border-color" class="element-item">
			<div class="content">
				<p>&nbsp;</p>

				<section
					style="color: rgb(51, 51, 51); font-family: sans-serif, Arial, Verdana, 'Trebuchet MS'; background: url(http://www.17sucai.com/preview/1/2014-04-08/%E6%97%B6%E9%97%B4%E8%BD%B4/images/log/line-bg.png) 104px 30px repeat-y;">
					<p style="line-height: 40px; font-size: 24px;">2014年</p>

					<h1
						style="line-height: 40px; margin-top: -40px; margin-left: 85px; padding-left: 60px; top: 0px; color: rgb(88, 166, 251); font-size: 24px; background: url(http://www.17sucai.com/preview/1/2014-04-08/%E6%97%B6%E9%97%B4%E8%BD%B4/images/log/clock.png) 0% 0% no-repeat;">微信微信营销平台更新日志</h1>

					<section style="clear: both; line-height: 32px;">
						<p style="line-height: 32px;">&nbsp;</p>

						<p style="font-size: 20px; line-height: 32px;">9月8日</p>

						<p style="margin-top: -42px; margin-left: 90px;">
							<img style="vertical-align: bottom"
								src="http://p4.qhimg.com/d/inn/05a63fc5/circle-h.png">
						</p>

						<section style="margin-left: 140px; margin-top: -32px;">
							<p style="color: rgb(99, 208, 41); font-size: 20px;">微信图文编辑器上线！</p>

							<p>提供丰富的图文样式,微信在线编辑器</p>

							<p>自由定义颜色，批量更换颜色,微信在线编辑器</p>

							<p>&nbsp;</p>
						</section>

						<p style="line-height: 32px; font-size: 20px;">9月3日</p>

						<p
							style="margin-top: -42px; margin-left: 90px; line-height: 32px;">
							<img style="vertical-align: bottom"
								src="http://p4.qhimg.com/d/inn/05a63fc5/circle-h.png">
						</p>

						<section style="margin-left: 140px; margin-top: -32px;">
							<p style="color: rgb(99, 208, 41); font-size: 20px;">中秋吃月饼游戏上线</p>

							<p>新增了一大批功能,微信在线编辑器</p>
						</section>

						<p>&nbsp;</p>

						<p>&nbsp;</p>
					</section>
				</section>

				<p>&nbsp;</p>
			</div>
		</div>
	</div>
</div>

<div class="tab_con" id="tab3" style="display: none;">
	<div style="width: 100%;" class="element-list">
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/DCE5nxPSl5QEJxRJI0dvChhaicNhf7LfFAPv4NxBgy8MxXxJKFUoS4R2iaV7fSUjUmZuGbKyDqRzMZQVJSesibHTg/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3v2KDpCJq5h6ibv6lukcf8PQcYA91VQfpSbTdQzjyofyO9oD4u0jwqAQ/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3t1CsHDz13B4iaUgncMnJNUFngThmB8iaV7o7WZ5q7QraAk58fBatf3hw/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3YfT9k626AtJ5ggI2VUqRBsDaMzOE8Y7OGjfGcnKu92vy959voCWc0g/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3YMVZq8vPMnMiaKsDVFDZcic4g2U3sBaNxBiaIicoLB6QvWhrz8fU0Aay3w/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq36p4aicZ7Ctz1xAsVYib9V7icDszVsutRgrFcp3kPvM5s5ia2PH2OYIiaEQg/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq34QZrusKq3oL67icn9F8kGEIdMzjWH0icEyYm7PjmWlCPsC5FftF7BB9g/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3qNJDzspGItOoP9IVUBYWUX25KIFsuo9EzHRgOZticVbSuKK6KO7jMsA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="section.main2:border-color;p.main2:border-color"
			class="element-item">
			<div class="content">
				<section
					style="margin-top: 15px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
					<section
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
						<section
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
							<section
								style="max-width: 100%; color: #222; font-family: 微软雅黑, arial, sans-serif; font-size: 14px; height: 75px; background-color: #fff; word-wrap: break-word !important; box-sizing: border-box !important">
								<section
									style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; border-top-left-radius: 50px; border-top-right-radius: 50px; border-bottom-right-radius: 50px; border-bottom-left-radius: 50px; padding: 5px; border: 2px solid #00bbec; margin: 0"
									class="main">
									<section
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; float: left; height: 60px; width: 60px">
										<img
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; border: 0; border-top-left-radius: 30px; border-top-right-radius: 30px; border-bottom-right-radius: 30px; border-bottom-left-radius: 30px; width: 60px !important; float: left; visibility: visible !important"
											src="" _width="60px" data-w="60" data-ratio="1"
											data-src="" class="awb_avatar"><img
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; border: 0; float: right; margin-top: -60px; width: auto !important; visibility: visible !important"
											src="" data-w="20" data-ratio="1"
											data-src="">
									</section>
									<section
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; display: inline-block; height: 60px; padding: 0 10px; line-height: 30px">
										<section
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; border-bottom-width: 1px; border-bottom-color: #767676; border-bottom-style: dashed">
											<span
												style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: #000; font-weight: bold">点击「<span
												style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: #16b3ff">箭头所指处</span>」可快速关注
											</span>
										</section>
										<section
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
											<span
												style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: #000">微信号：<span
												style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: #b00"
												class="awb_wxwechatid">XXXXXXXXX</span></span>
										</section>
									</section>
								</section>
								<section
									style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; margin: -98px 0 0 80px">
									<p
										style="margin-top: 0; margin-bottom: 0; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; padding: 0; width: 0; height: 0; border-width: 12px; border-style: solid; border-color: transparent transparent #00bbec; float: none; box-sizing: border-box !important"
										class="main">
										<br
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
									</p>
									<p
										style="margin-top: -21px; margin-bottom: 0; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; padding: 0; width: 0; height: 0; border-width: 12px; border-style: solid; border-color: transparent transparent #fff; float: none; box-sizing: border-box !important">
										<br
											style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">
									</p>
								</section>
							</section>
						</section>
					</section>
				</section>
			</div>
		</div>

		<!--样式-1 -->
		<div
			data-color="blockquote.main:background-color;span.main:background-color:;span.main2:color"
			class="element-item">
			<div class="content">
				<blockquote
					style="padding: 5px 20px; margin: 0; font-family: 微软雅黑; font-size: 16px; white-space: normal; max-width: 100%; border: 2px #42f9ff; color: #fff; text-align: center; font-weight: 700; line-height: 24px; width: 180px; background-color: #00bbec; border-top-left-radius: 15px; border-top-right-radius: 15px; box-shadow: #999 0 -1px 6px; border-bottom-left-radius: 2px; border-bottom-right-radius: 2px; text-shadow: #0a0a0a 0 -1px 2px; word-wrap: break-word !important; box-sizing: border-box !important"
					class="main">微信微信</blockquote>
				<blockquote
					style="padding: 10px; margin: 0; font-family: 微软雅黑; font-size: 12px; white-space: normal; max-width: 100%; color: #3e3e3e; border: 1px solid #ccc; line-height: 24px; background-color: #e4e4e4; border-top-left-radius: 0; border-top-right-radius: 0; box-shadow: #ccc 0 -1px 6px; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px; word-wrap: break-word !important; box-sizing: border-box !important">
					<span
						style="max-width: 100%; color: #00b050; word-wrap: break-word !important; box-sizing: border-box !important">微信号：</span><span
						style="max-width: 100%; font-weight: 700; word-wrap: break-word !important; box-sizing: border-box !important"></span><span
						style="max-width: 100%; font-weight: 700; color: #fff; padding: 2px 5px; background-color: #00bbec; word-wrap: break-word !important; box-sizing: border-box !important"
						class="main">123456</span><span
						style="max-width: 100%; color: #00bbec; padding-left: 5px; word-wrap: break-word !important; box-sizing: border-box !important"
						class="main2">(←长按复制)</span>
					<p
						style="padding: 10px 0 0; margin-top: 0; margin-bottom: 0; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; word-break: normal; color: #666; line-height: 2em; box-sizing: border-box !important">微信营销整体解决方案提供商</p>
				</blockquote>
			</div>
		</div>
		<div data-color="fieldset:border-color;span.main:background-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="padding: 5px; margin: 0px; border: 1px solid rgb(0, 187, 236); background-color: rgb(248, 247, 245); color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal;">
					<legend style="margin: 0px 10px;">
						<span
							style="padding: 5px 10px; color: rgb(255, 255, 255); font-weight: bold; font-size: 14px; background-color: rgb(0, 187, 236); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;"
							class="main"><span
							style="color: rgb(255, 255, 255); font-family: 微软雅黑; font-size: 14px; line-height: 24px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">点击微信</span><img
							style="max-width: 100%; color: rgb(255, 255, 255); font-family: 微软雅黑; font-size: 14px; line-height: 24px; white-space: normal; border: 0px; height: auto !important; word-wrap: break-word !important; box-sizing: border-box !important; visibility: visible !important;"
							src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3mNUDE1n6WMFZYgjaHtZKia3DbFqDWiaTGH6PSR38Ifa3lXFicZ57iajZTQ/0?wx_fmt=png"
							data-w="20" data-ratio="1.35"
							data-src="http://mmbiz.qpic.cn/mmbiz/xT7yN7TENODUPIKsMKdoQwbqBNWQUywCOkrPoF2lDgPERNak9vmYvMCW1VzjZSdtakKbZChnIvW5lqwibOOmnUA/0"
							data-s="300,640"><span
							style="color: rgb(255, 255, 255); font-family: 微软雅黑; font-size: 14px; line-height: 24px;">关注我哟</span></span>
					</legend>
					<blockquote style="padding: 0px; margin: 0px;">
						<p style="padding: 0px; margin-top: 0px; margin-bottom: 0px;">
							<span
								style="font-family: 微软雅黑; line-height: 24px; max-width: 100%; color: rgb(255, 192, 0); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important;"></span><span
								style="font-family: 微软雅黑; line-height: 24px; background-color: rgb(248, 247, 245); max-width: 100%; color: rgb(255, 192, 0); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important;">☀&nbsp;</span><span
								style="font-family: 微软雅黑; background-color: rgb(248, 247, 245); max-width: 100%; font-size: 12px; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;">定期推送微信营销</span><span
								style="font-family: 微软雅黑; background-color: rgb(248, 247, 245); max-width: 100%; font-size: 12px; color: rgb(255, 192, 0); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;">微信运营</span><span
								style="font-family: 微软雅黑; background-color: rgb(248, 247, 245); max-width: 100%; font-size: 12px; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;"><span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">，<span
									style="max-width: 100%; color: rgb(0, 176, 80); word-wrap: break-word !important; box-sizing: border-box !important;">微信平台搭建</span><span
									style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">，<span
										style="max-width: 100%; color: rgb(112, 48, 160); word-wrap: break-word !important; box-sizing: border-box !important;">微信游戏开发</span>，<span
										style="max-width: 100%; color: rgb(0, 176, 240); word-wrap: break-word !important; box-sizing: border-box !important;">微信在线编辑器</span>，<span
										style="max-width: 100%; color: rgb(146, 208, 80); word-wrap: break-word !important; box-sizing: border-box !important;">微信WIFI</span></span></span>等诸多优质内容，<span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">专业微信平台</span>、<span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">重服务</span>的微信运营！关注我们妥妥没错！（联系电话：40000-0000）</span><span
								style="font-family: 微软雅黑; max-width: 100%; font-size: 12px; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;"></span>
						</p>
					</blockquote>
				</fieldset>
			</div>
		</div>

		<div data-color="p:border-color;span.main:background-color"
			class="element-item">
			<div class="content">
				<p
					style="margin: 0px auto; max-width: 600px; word-wrap: normal; min-height: 1em; border: 1px solid rgb(0, 187, 236); font-family: 微软雅黑; font-size: 12px; border-top-left-radius: 2em; border-top-right-radius: 2em; border-bottom-left-radius: 2em; border-bottom-right-radius: 2em; box-sizing: border-box !important;">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding: 2px 2px 2px 6px; color: rgb(255, 255, 255); border-top-left-radius: 2em; border-bottom-left-radius: 2em; background-color: rgb(0, 187, 236);"
						class="main"><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; line-height: 0px;">&#65279;</span><img
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; visibility: visible !important;"
						src="http://i2.w.yun.hjfile.cn/doc/201311/d1cb6b2d4d3843d8ad8e77a79a337039.gif"
						data-w="22" data-ratio="0.5"
						data-ke-src="http://i2.w.yun.hjfile.cn/doc/201311/d1cb6b2d4d3843d8ad8e77a79a337039.gif"
						data-src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3PBnYLN67hOSFTAMxSBGcSSyn1QUYoPCxrrhl8jDqwbDibprIicekEUzg/0?wx_fmt=gif">&nbsp;<strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">提示</strong>：</span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding-left: 2px;"><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(127, 127, 127);">点击上方</span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding-left: 2px;">"</span><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><span
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(0, 112, 192); padding-left: 2px;">微信</span></strong><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding-left: 2px;">"</span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; padding-left: 2px; color: rgb(0, 187, 236);">↑</span><span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(127, 127, 127);">免费订阅本刊</span></span>
				</p>
			</div>
		</div>

		<div data-color="p.qipao:background-color" class="element-item">
			<div class="content">
				<p
					style="padding: 5px 20px; margin-top: auto; margin-bottom: auto; font-family: 微软雅黑; white-space: normal; font-size: medium; max-width: 100%; word-wrap: normal; min-height: 1em; line-height: 25px; text-align: center; background-color: rgb(0, 187, 236); color: rgb(255, 255, 255); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; box-sizing: border-box !important;"
					class="qipao">
					<span style="font-size: 12px;"><span
						style="font-family: 微软雅黑, 'Microsoft YaHei'; border-color: rgb(103, 61, 189);">点击“阅读全文”，了解详情</span></span>
				</p>
				<p
					style="margin: auto 55px; font-size: medium; white-space: normal; max-width: 100%; word-wrap: normal; min-height: 1em; color: rgb(62, 62, 62); line-height: 25px; border: 0px solid rgb(255, 0, 0); padding: 0px; width: auto; font-family: 微软雅黑; box-sizing: border-box !important;"
					class="qipao2">
					<span
						style="max-width: 100%; border-color: rgb(0, 187, 236) transparent transparent; border-width: 20px; border-style: solid dashed dashed; width: 50px; bottom: -60px; height: 50px; font-size: 0px; word-wrap: break-word !important; box-sizing: border-box !important;"
						class="bot"></span>
				</p>
			</div>
		</div>

		<div data-color=".awb-s2:background-color;.awb-s1:border-bottom-color"
			class="element-item">
			<div class="content">
				<section style="text-align: left; vertical-align: top;">
					<section
						style="width: 0px; margin-left: 48%; border-bottom-width: 0.8em; border-bottom-style: solid; border-bottom-color: #00BBEC; border-left-width: 0.8em !important; border-left-style: solid !important; border-left-color: transparent !important; border-right-width: 0.8em !important; border-right-style: solid !important; border-right-color: transparent !important; border-top-color: transparent !important;"
						class="awb-s1"></section>
					<section
						style="margin: 0px; height: 2.5em; border-radius: 2em; background-color: #00BBEC;"
						class="awb-s2">
						<img
							src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3nfBNaJF38ZO9FqHAHt4h4ImyfxabXb6cYHiawNfibCa2zvIAttBP2Hyg/0?wx_fmt=png"
							style="height: 1.6em; vertical-align: top; margin: 0.5em 0.6em;">
						<section
							style="display: inline-block; width: 70%; margin-top: 0.3em; text-align: center; font-size: 1.2em; white-space: nowrap; overflow: hidden;">
							<section
								style="display: inline-block; color: rgb(255, 255, 255);">点击上方</section>
							<section style="display: inline-block; color: rgb(64, 84, 115);">“蓝色字”</section>
							<section
								style="display: inline-block; color: rgb(255, 255, 255);">可关注我们！</section>
						</section>
					</section>
				</section>
			</div>
		</div>

		<div data-color="fieldset:border-color;legend:border-color"
			class="element-item">
			<div class="content">
				<fieldset
					style="white-space: normal; margin: 0px; padding: 5px 15px; border-width: 1px 0px; border-style: solid; border-color: rgb(0, 187, 236); border-image-source: none; max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; font-size: 14px; background-color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important;">
					<legend
						style="padding: 4px 10px; max-width: 100%; border: 1px solid rgb(0, 187, 236); border-image-source: none; color: rgb(34, 34, 34); font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;">
						<strong
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">如何关注</strong>
					</legend>
					<p
						style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;">①复制“微信号或ID”，在“添加朋友”中粘贴搜索号码关注。</p>
					<p
						style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;">②点击微信右上角的“+”，会出现“添加朋友”，进入“查找公众号”，输入以下公众号的名字，即可找到。</p>
				</fieldset>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3msDncwE968tctgmtA9rfSB8K1nECbCsVfVInb4dxCJCVS2Ztecpq5w/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3my2ob9d9icNmM4J4sicuibTdicicJeeTwSVvC0mRa2aHUoIrrKPZF62CVVw/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3e1m8oD3XKSciaWrCjJt3JMKQTkLPiaKibvfpOuwlImdvIssicIqYaXKVicw/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3rnowrN26pp5piaZruxPibVvmRxuAtibGAtmVG5g0ticiaA9jJDSMh51Xjpg/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/KXR88d9x9OMF2uOfDSWQicA7cn5px1DojZkbxUPvs2hibTu0iaeLlbSRDJex8YpIO9sfrrSAOAuYBTv3Puxiao2ib5Q/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3VuYiae55lygXfMwHWyT3ZodOcNG8dkO2sAU8R6VphEwUtYeLUOonickA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3rbQVibhRtRlqxhsUgsibCpa9KCcgibLuuibLgCQBmDTw2DodlQIxh23MGQ/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3uhicZeppCUKu8gS8s9MsWTqEzUpkxK9z4Jdgz2GW3NErGRibpiaAtDX4A/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3fVazEEYVJ48qISZsxbA6CDfeicW1YDYSdpFntPNB8qZbqXHnvqED0sQ/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3MLH3Da7uHhTlaS2mvR6JGdncibtYHJrm8aeypGGKFYHf2dGRhuht9Tw/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3J7wWeXvY4eoHic8f7ceDCBDRuXqGT5wzO0qtwF5aPWfURQEPyOBmFNA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3ibJgksM2ovAGszcvaeQBkScudb2M5cY1SgLm9mKL3yKlplnl3Cv7Sibw/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3I8jVqujbLwV0VqX8Xzu6tcHogRNAmUvAZfrzlVgvFx1pIjn8pfS3Bg/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/KXR88d9x9OOnxibeNCue3LlDJiboJkb2kD7wAXhlrRCfxE40SLRCic3wkib8SZaSmtrgCCBhkBticEc72DNB4TCEJhQ/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3B1tFaib4LibTl0d3iapgQPYmXFeSIWjIAxdEAGrLEI86iaAnjDyqBiatpzA/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3m6RmtLIMyjYZwhZQrMnJkiaVCiauUlq0icQYCLhgNXaLYMdr7IatKCiaBg/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq38Ya0Eu0LiaS8yzQ9ZibQ62RDx8iazMy1kpviaibaGlD9HXBrAcg5XtK4Vog/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3OQZxPX05wFHrt8k40Sc4ELhuQkurFgqibnoSQ02rG3dDJ4l9wszby8Q/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>
	</div>
</div>

<div class="tab_con" id="tab4" style="display: none;">
	<div style="width: 100%;" class="element-list">

		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPjhXdiaw8ibquYYw8MI1InGKpr6JV7kJAvBybMCebbQbMh7ZLL2GMGPTV9KhMcwJOMHIkyDibyJsTIhA/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPjhXdiaw8ibquYYw8MI1InGKpF17tUGszhvf58vVQTZjfpzD1icTlNCcz4ibTFjhST44tH0dYQpagg5ag/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPhuxibIOsThcH7HF1lpQ0YvkYedyfd5kNTYhg6ZcVyV0J6M2N6lYlueToTr0ebAzibPibn8I8v3Qiandw/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPjhXdiaw8ibquYYw8MI1InGKpNkaSe1ryqXSFkphB7EQLFT34NHCA4oQPh4QrJdNvQS11MiatXLV2CDQ/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/yqVAqoZvDibFmpaG1bHyLIFpk6h6SygXFmcfFdC6hYdLWyF4byicIc9ia7OzuUnOoN2PzTrCn8ILN9YmI11pQZOicQ/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPhuxibIOsThcH7HF1lpQ0YvkUeBWCIdSiamRg8cFeYM8zl7TrvI2X7tsGhOpb2y56aFJwYibiaqUxeDNg/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPhuxibIOsThcH7HF1lpQ0YvkicGBV51TmkSCfh6T9DmLKx7v6tXmulYESt4kdEUWNOW0eW6N82zCqYg/0/mmbizgif">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPiaB5wTN4rt8SiaLwWPOGjzszGdVn0IBuhK3YdplBpxYKRFzCKicnWRZFVMdkLw1gMv8Rcuy1vNJEXNA/0">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPia3RFX6Mvw06kePJ7HbmI7bibbbzG3uqZ8S8QicFSnqobkGSPq4X5D9btObvy1zy6EyibLlplicicszCSg/0?wx_fmt=png">
				</p>
			</div>
		</div>
		<!--样式-0 -->
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudCxhicVdD2GDlvfZSnCRbKEdic5ul1RvXMG9zRZNZPlXsdMktH471NSlpg0iccTcfvc7uMFUwRHibcCw/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; color: rgb(62, 62, 62); font-family: 'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, Arial, sans-serif; line-height: 25px; white-space: normal; background-color: rgb(255, 255, 255); margin: 0.5em 0px; border-top-style: dotted; border-top-width: 1px; border-color: rgb(0, 201, 50); word-wrap: break-word !important; box-sizing: border-box !important;"
					class="tn-Powered-by-XIUMI"></section>
				<p
					style="margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); font-family: 'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, Arial, sans-serif; line-height: 25px; background-color: rgb(255, 255, 255); box-sizing: border-box !important;">
					<br>
				</p>
			</div>
		</div>
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%" src="">
				</p>
			</div>
		</div>
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3qiaEPlAYy8iaicRvzj1icRvFyaxwhN92gj1VGP4DIsZ71DVzEmGKehM21g/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq32q7RrfoMfeN5ZAIP3NPVCFv7nHABKSvJ2mchuujj85zziafptcrBQDA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3icp0qCHwh8WL0G1YPib8Eibqxibbnudk4sgnjqBuoj7ciaiaofV3uTg0vJyA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3fB0biaicZUwG4RWvgwy8CHAlibicomftPwiazq2lz0o9dbMLXBsrfUmBnmg/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3iajxh0aTgUk4bmsNZjsiamJSTWazjrpCgtObspbwURLpDP8cegpwVTjA/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3y3BTpMFB1N9JJEMXXs7WHQrWib1OX65NpjVN1POy1T8lnCUorNwibzwQ/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3UYok4TTGsyQRoWZkuqWHUmNib7OE1sBTMoTdEoNcJ45BZeg2robQnSQ/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3RfygZYDWf0nFNib5iclyRs7M7xiaSicvibPxz8toENPH9dia1ZlYPYrm5UNQ/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/jZlB3L4cckX0gAhfFZrBHHODjVdTtCnw4ErS97dmE14s1d4D2WypOjA8MTKJrRCcLXSosibh5bUbZUibP2uHRwyQ/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP813MfewdMGP0CRRUqTgCGJO4tKibZJLcrVESQxnmTNDFSzuTCnVjV6iaxw/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP81jDYTckoPrfeX088lekl55f4B43DyAGgGfvtCXmEmg8KtLjtQ7yMLiaw/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP81KAjkO1amv78GtEQkLiag8ibQ7NibgTdnj0tUpmhMo2DsYvZkLAQzH8edw/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP81jUpsBzUCDBCf1wr2YyIhQoqRTQ8D5ArKPDE0ZFSdicuP8ug1LJcbokA/0">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3V9snSZ9J0LPay4mY55B8JIPHZ1ib2QE3fmufRBKb0I2ORdW5uD1EShA/0?wx_fmt=png">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQucjcTBOveSkhXRIjKuOVTB7MLQ6r4icCHia44WwE3UluuYqqBOfiavUia1z1CPUib1flQT4yIaXwWd5hjg/0?wx_fmt=jpeg">
				</p>
			</div>
		</div>


		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img width="100%"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3D4VRxbEQJGoQWYoPoAjUGsne6iaQ4tJGpHWHXPwbJB2cEichnrIZhcyQ/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color=".awb-s1:border-color;" class="element-item">
			<div class="content">
				<hr style="margin: 0px; border: 0; border-top: 5px #00BBEC solid;"
					class="awb-s1">
			</div>
		</div>

		<div data-color=".awb-s1:border-color;" class="element-item">
			<div class="content">
				<hr style="margin: 0px; border: 0; border-top: 5px #00BBEC dashed;"
					class="awb-s1">
			</div>
		</div>

		<div data-color=".awb-s1:border-color;" class="element-item">
			<div class="content">
				<hr style="margin: 0px; border: 0; border-top: 5px #00BBEC dotted;"
					class="awb-s1">
			</div>
		</div>

		<div data-color=".awb-s1:border-color;" class="element-item">
			<div class="content">
				<hr style="margin: 0px; border: 0; border-top: 5px #00BBEC double;"
					class="awb-s1">
			</div>


		</div>
		<div data-color="section:background-color;" class="element-item">
			<div class="content">
				<section
					style="height: 10px; background: url(https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3HQERBFK78fDeFwOe9kichDZkPLFwHXorLjP61iaaicAldlVKRrEshpPAw/0?wx_fmt=png) repeat-x #00BBEC;"></section>
			</div>
		</div>
	</div>
</div>

<div class="tab_con" id="tab5" style="display: none;">
	<div style="width: 100%;" class="element-list">

		<!--样式1-->
		<div data-color="" class="element-item">
			<div class="content">
				<section
					style="max-width: 100%; font-family: inherit; font-size: 1em; padding: .7em 0; line-height: 1.4em; border-top-width: 1px; border-top-style: solid; border-top-color: #3f474e; font-family: 微软雅黑; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #3f474e; font-style: italic; color: #3f474e; word-wrap: break-word !important; box-sizing: border-box !important">
					<span
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 12px"><strong
						style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important"><em
							style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important">点击“<span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 16px; color: #c0504d">阅读原文</span>”体验一次简单不过的微信编辑体验，不用太久，不用太难，<span
								style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; font-size: 16px; color: #9bbb59">瞬间</span>即可！
						</em></strong></span>
				</section>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<section>
					<section
						style="height: 8em; white-space: nowrap; overflow: hidden;">
						<img
							src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3jx3mEndgOXP3ibxYMusKuyrA2761tRBDLG2CZDS4L4pkPiabefhv833Q/0?wx_fmt=png"
							style="max-width: 100%; max-height: 100%;">
					</section>
					<section
						style="height: 2em; margin: -7.2em 0.5em 5.5em; font-size: 1em; line-height: 1.6em; padding: 0.5em;">
						<span
							style="color: inherit; white-space: nowrap; overflow: hidden; font-size: 0.9em; font-family: inherit; font-style: normal;">点击下方</span><span
							style="color: rgb(64, 84, 115); white-space: nowrap; overflow: hidden; font-size: 0.9em; font-family: inherit; font-style: normal;">“阅读原文”</span><span
							style="color: inherit; white-space: nowrap; overflow: hidden; font-size: 0.9em; font-family: inherit; font-style: normal;">查看更多</span>
					</section>
				</section>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<blockquote
					style="white-space: normal; text-align: center; padding: 12px 8px; background-color: rgb(45, 163, 7); margin: 0px; max-width: 100%; border-radius: 5px;">
					<span style="max-width: 100%; color: white;"><strong
						style="max-width: 100%;">点击左下角查看更多</strong></span>
				</blockquote>
				<p>
					<img
						style="border-width: 0px; height: auto !important; visibility: visible !important;"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq374DEwPznJeLv0rlm9e7KoPC0fY7rZ1MZHhyLrT7dUQHlJKkvuLYqow/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p
					style="margin-top: 0px; margin-bottom: 0px; padding: 0px; min-height: 1.5em; word-wrap: break-word; word-break: normal; white-space: pre-wrap; line-height: 36px; font-family: 微软雅黑; text-align: center; background-color: rgb(0, 0, 0); color: rgb(255, 255, 255); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; text-shadow: rgb(69, 117, 58) 0px 1px 1px;">点击左下角查看更多</p>
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3ZXtPP2mky4t4GDibtlgiaicIeVtQgRx4Opg2ibcnrhq4IBdX0eEuCE9mRw/0?wx_fmt=gif">
				</p>
			</div>
		</div>

		<div
			data-color=".awb-s1:background-color;.awb-s3:border-color;.awb-s4:color"
			class="element-item">
			<div class="content">
				<section>
					<section
						style="margin: 0px; height: 0.1em; background-color: #00BBEC;"
						class="awb-s1"></section>
					<section
						style="margin: 0.3em 0px; height: 0.3em; background-color: #00BBEC;"
						class="awb-s1"></section>
					<section
						style="margin: 0; background-color: white; border: 1px solid #00BBEC; box-shadow: #e2e2e2 0em 1em 0.1em -0.8em; font-size: 1em; line-height: 1.6em; padding: 0.5em;"
						class="awb-s3">
						<span
							style="color: inherit; font-size: 1em; font-family: inherit; font-style: normal;">点击下方</span><span
							style="color: rgb(64, 84, 115); font-size: 1em; font-family: inherit; font-style: normal;">“阅读原文”</span><span
							style="color: inherit; font-size: 1em; font-family: inherit; font-style: normal;">查看更多</span>
					</section>
					<section style="color: #00BBEC; font-size: 2em;" class="awb-s4">↓↓↓</section>
				</section>
			</div>
		</div>

		<div data-color=".awb-s1:background-color;.awb-s2:border-top-color"
			class="element-item">
			<div class="content">
				<p
					style="margin-top: 0px; margin-bottom: 0px; padding: 0px; min-height: 1.5em; word-wrap: break-word; word-break: normal; white-space: pre-wrap; line-height: 36px; font-family: 微软雅黑; text-align: center; background-color: #00BBEC; color: #ffffff; border-radius: 5px;"
					class="awb-s1">点击左下角查看更多</p>
				<p
					style="margin: -5px 0 0 50px; display: inline-block; border-left-width: 1em; border-left-style: solid; border-left-color: transparent !important; border-right-width: 1em; border-right-style: solid; border-right-color: transparent !important; border-top-width: 1.5em !important; border-top-style: solid !important; border-top-color: #00BBEC;"
					class="awb-s2"></p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP81lDG16baWTZcqDITZBZ3BlyicuKb2GbyvibvkSqU0ER9EtKaEtL72YhEw/0"
						style="height: auto !important;">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/6xsuhALdNEr0ibLDATPbiaQoI0OyJzZP81UXUmbPYz4qyVKL7WpUic11M6eHazOaD9jb3icQTqsL1CJIe9IDS0jAZA/0"
						style="height: auto !important;">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3AR9hvgbZgNqjU6pJ4Iq9SnKkO0e0rdNRgurZx6wGMvjia2KwMHzfcNw/0?wx_fmt=gif"
						style="height: auto !important;">
				</p>
			</div>
		</div>
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufaFonP5uahBudGuINbPKsAK4yj4llvXKPgXpIMxKpToG1dlmae5XEwyol7SiaX4u7K3gjTFuic3Whg/0?wx_fmt=gif"
						style="height: auto !important;">
				</p>
			</div>
		</div>
		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3XG6TtnvtgiaJQIfog0be4o0SYAIDAMSQtnnXfm8rP1AFBY4kadMLdibg/0?wx_fmt=gif"
						style="height: auto !important;">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufaFonP5uahBudGuINbPKsALeKOvnhykkEIjOu3oMBk4diadMKEDQayIcNjPFwPlDSgGa8DcAE8EVQ/0?wx_fmt=gif"
						style="height: auto !important;">
				</p>
			</div>
		</div>

		<div data-color="" class="element-item">
			<div class="content">
				<p>
					<img
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQufpoja8icV1RNvaDQ7ZryBq3eDrpBicLicv1gBF89W3klKic1D1wxgxZb9a22MxQRttBw03jYf30Yic17g/0?wx_fmt=gif"
						style="height: auto !important;">
				</p>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/view/wechat/admin/_wechart_template_tab6.jsp"></jsp:include>
<div
	style="display: none; overflow-y: auto; overflow-x: hidden; background: white; border: 1px solid #d4d4d4; padding: 10px 10px 10px 10px; width: 515px; float: left"
	id="tab7">
	<div style="width: 100%;" class="element-list">
		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item ">
			<div class="content">
				<p>
					<br>
				</p>
				<section
					style="box-sizing: border-box; color: #2c3e50; font-family: 微软雅黑; font-size: 15px; line-height: 21px; white-space: normal; background-color: #fefefe">
					<hr
						style="box-sizing: content-box; height: 0; margin: 0; border-width: 2px; border-color: #a50003; border-top-style: solid">
					<section
						style="box-sizing: border-box; margin-top: -18px; text-align: center">
						<p
							style="box-sizing: border-box; margin: 0 5px; line-height: 36px; color: #fff; background-color: #a50003; display: inline-block; width: 36px; height: 36px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-right-radius: 18px; border-bottom-left-radius: 18px; background-position: initial initial; background-repeat: initial initial">新</p>
						&nbsp;
						<p
							style="box-sizing: border-box; margin: 0 5px; line-height: 36px; color: #fff; background-color: #a50003; display: inline-block; width: 36px; height: 36px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-right-radius: 18px; border-bottom-left-radius: 18px; background-position: initial initial; background-repeat: initial initial">春</p>
						&nbsp;
						<p
							style="box-sizing: border-box; margin: 0 5px; line-height: 36px; color: #fff; background-color: #a50003; display: inline-block; width: 36px; height: 36px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-right-radius: 18px; border-bottom-left-radius: 18px; background-position: initial initial; background-repeat: initial initial">快</p>
						&nbsp;
						<p
							style="box-sizing: border-box; margin: 0 5px; line-height: 36px; color: #fff; background-color: #a50003; display: inline-block; width: 36px; height: 36px; border-top-left-radius: 18px; border-top-right-radius: 18px; border-bottom-right-radius: 18px; border-bottom-left-radius: 18px; background-position: initial initial; background-repeat: initial initial">乐</p>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item">
			<div class="content">
				<section
					style="box-sizing: border-box; background-color: #a50003; padding: 5px">
					<section
						style="box-sizing: border-box; border: 1px solid #fff; padding: 10px 20px">
						<p
							style="box-sizing: border-box; margin-top: 0; margin-bottom: 10.5px; line-height: 1.4; color: #fff; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #a50003">
							<span
								style="box-sizing: border-box; font-family: 微软雅黑; font-size: 24px"><strong
								style="box-sizing: border-box">新年快乐</strong></span>&nbsp;&nbsp;&nbsp;<span
								style="box-sizing: border-box; font-size: 14px"><strong
								style="box-sizing: border-box">// HAPPY 2015'S NEW YEAR
									//</strong></span>
						</p>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item">
			<div class="content">
				<section
					style="box-sizing: border-box; border: 5px solid #a50003; padding: 5px">
					<section
						style="box-sizing: border-box; border: 1px solid #a50003; padding: 15px 20px">
						<p
							style="box-sizing: border-box; margin-top: 0; margin-bottom: 10.5px; line-height: 1.4; color: #a50003; text-align: center; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #a50003">
							<span
								style="box-sizing: border-box; font-family: 微软雅黑; font-size: 48px"><strong
								style="box-sizing: border-box">羊年大吉</strong></span>
						</p>
						<p
							style="box-sizing: border-box; margin-top: 0; margin-bottom: 10.5px; line-height: 1.4; color: #a50003; text-align: center">
							<span
								style="box-sizing: border-box; font-family: 微软雅黑; font-size: 18px"><strong
								style="box-sizing: border-box">HAPPY 2015'S NEW YEAR</strong></span>
						</p>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item">
			<div class="content">
				<section
					style="box-sizing: border-box; border-style: solid; border-width: 1px 1px 1px 60px; border-color: #a40000; color: #a40000; padding-left: 20px; padding-top: 10px; font-family: 微软雅黑">
					<section
						style="box-sizing: border-box; border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #a40000; clear: both; float: left">
						<section
							style="box-sizing: border-box; font-size: 24px; font-weight: 700; float: left">
							New /<br style="box-sizing: border-box">Spring
						</section>
						<section
							style="box-sizing: border-box; font-size: 20px; margin-left: 45px; margin-top: 5px; font-weight: 700; float: left">
							春.<br style="box-sizing: border-box">新年快乐2015
						</section>
					</section>
					<p
						style="box-sizing: border-box; margin-top: 0; margin-bottom: 10.5px; line-height: 1.4; clear: both">&nbsp;</p>
					<p
						style="box-sizing: border-box; margin-top: 0; margin-bottom: 10.5px; line-height: 1.4">
						微信 &nbsp;&nbsp; HAPPY NEW YEAR&nbsp;<em
							style="box-sizing: border-box">!!!</em>
					</p>
				</section>
			</div>
		</div>
	</div>
</div>

<div class="tab_con" id="tab8" style="display: none;">
	<div style="width: 100%;" class="element-list">
		<!--样式0-->
		<div data-color="section.main:color" class="element-item ">
			<div class="content">
				<section
					style="border: none; margin: 0.8em 0px 0.3em; box-sizing: border-box; padding: 0px; display: table; width: 100%;">
					<section
						style="line-height: 0; box-sizing: border-box; color: inherit;">
						<img alt="QQ20150518-2.png" title="1431955658052031737.png"
							style="border: 0px; box-sizing: border-box; display: inline-block; width: 100%; max-width: 100%; height: auto !important; color: inherit;"
							src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudlGPWdtETkGkLZxjTibiaygibiccOgosdiceKnUuFQK5NhianxqTdnmkCk0vx8l2SgEeMnFSO5jfzYI6xg/0?wx_fmt=png">
					</section>
					<section
						style="width: 30%; display: inline-block; float: left; text-align: right; margin: 15px 0px 0px; padding: 0px; box-sizing: border-box; color: inherit;">
						<section
							style="float: right; text-align: center; margin-top: -15px; box-sizing: border-box; color: inherit;">
							<section
								style="width: 1px; height: 1.2em; margin-left: 13px; background-color: rgb(102, 102, 102); box-sizing: border-box; color: inherit;"></section>
							<section
								style="width: 2em; height: 2em; border: 1px solid rgb(102, 102, 102); border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; line-height: 2em; font-size: 1em; font-weight: inherit; text-decoration: inherit; box-sizing: border-box; color: inherit;">
								<section style="box-sizing: border-box; color: inherit;"
									class="pBrush">巴</section>
							</section>
							<section
								style="width: 2em; height: 2em; border: 1px solid rgb(102, 102, 102); margin-top: 2px; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; line-height: 2em; font-size: 1em; font-weight: inherit; text-decoration: inherit; box-sizing: border-box; color: inherit;">
								<section style="box-sizing: border-box; color: inherit;"
									class="pBrush">瑶</section>
							</section>
						</section>
					</section>
					<section
						style="width: 65%; float: left; margin-top: 20px; line-height: 1.5em; padding-left: 20px; font-size: 1em; font-weight: inherit; text-decoration: inherit; color: rgb(58, 188, 255); box-sizing: border-box; border-color: rgb(58, 188, 255);">
						<section
							style="box-sizing: border-box; border-color: rgb(58, 188, 255); color: inherit;">
							<section
								style="box-sizing: border-box; font-size: 175%; margin: 5px 0px; border-color: rgb(58, 188, 255); color: inherit;"
								class="main">海上人家</section>
							<section
								style="box-sizing: border-box; font-size: 16px; border-color: rgb(58, 188, 255); color: inherit;"
								class="main">巴瑶族，唯一的海上民族</section>
						</section>
					</section>
				</section>
			</div>
		</div>


		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item ">
			<div class="content">
				<section
					style="border: 0px; margin: 0px 0px 0.5em; box-sizing: border-box; clear: both; overflow: hidden; padding: 0px; text-align: left;">
					<img width="100%" alt="QQ20150518-1.png"
						title="1431955423316080051.png"
						style="box-sizing: border-box; color: inherit; float: right; max-width: 100%; height: auto !important;"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudlGPWdtETkGkLZxjTibiaygibgPNj3TnWeiaS3w9dsDlGg9uJnTibyS4mnwdte1K4Dnn2OCUPsu0vjq0g/0?wx_fmt=png">
					<section
						style="display: inline-block; font-size: 2em; z-index: 100; padding: 0.1em 0.5em; margin: -1.5em 0px 0px; line-height: 1.2; box-sizing: border-box; float: right; text-align: right; font-weight: inherit; text-decoration: inherit; color: rgb(255, 255, 238); border-color: rgb(170, 220, 246); background-color: rgb(58, 188, 255);"
						class="main">
						<section style="box-sizing: border-box; color: inherit;">
							<section
								style="box-sizing: border-box; border-color: rgb(58, 188, 255); color: inherit;">
								<p
									style="font-size: 0.8em; border-color: rgb(58, 188, 255); color: inherit; padding: 5px;">
									金门大桥</p>
								<p
									style="border-color: rgb(58, 188, 255); color: inherit; font-size: 16px; padding: 10px 5px;">
									夕阳中的金门大桥雄姿</p>
							</section>
						</section>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="section.main:background-color" class="element-item ">
			<div class="content">
				<section
					style="border: 0px; margin: 0px 0px 0.5em; box-sizing: border-box; width: auto; clear: both; overflow: hidden; padding: 0px;">
					<img width="100%" alt="Unknown.jpeg"
						title="1431955122055009122.jpeg"
						style="box-sizing: border-box; color: inherit; float: left; max-width: 100%;"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudlGPWdtETkGkLZxjTibiaygibX9FNlD11nufQWamwYzLXGsm3JTokkts2gaQGf72A66YrfEaibHpzwfw/0?wx_fmt=jpeg">
					<section
						style="display: inline-block; font-size: 2em; z-index: 100; padding: 10px; margin-top: -1.5em; box-sizing: border-box; float: left; font-weight: inherit; text-decoration: inherit; color: #FFF; background-color: rgb(60, 40, 34);"
						class="main">
						<section style="box-sizing: border-box; color: inherit;">
							<p
								style="box-sizing: border-box; text-align: left; line-height: 32px;">
								繁华都市</p>
							<p
								style="box-sizing: border-box; color: inherit; display: inline !important; float: none; font-size: 12px; font-weight: inherit; line-height: 32px; text-decoration: inherit; font-family: '微软雅黑';">
								2015/S</p>
						</section>
					</section>
				</section>
			</div>
		</div>
		<!--样式0-->
		<div data-color="" class="element-item ">
			<div class="content">
				<section
					style="color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal; border: 0px; box-sizing: border-box; width: 518px; clear: both; padding: 0px;">
					<img width="100%"
						style="border: 0px; box-sizing: border-box; height: auto !important;"
						title="1431958266871076389.png"
						src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudlGPWdtETkGkLZxjTibiaygibQzWL9Y9OnpShptULGRUbaJ2rIzcSeTxakJOH5sStNEDKRZD5wLeYibg/0?wx_fmt=png">
					<section
						style="box-sizing: border-box; width: 9em; float: right; margin-top: -5em; margin-right: 1em; -webkit-transform: rotate3d(0, 0, 1, 15deg); opacity: 0.99;">
						<img width="100%" style="border: 0px;"
							src="https://mmbiz.qlogo.cn/mmbiz/h1VV7TJtQudlGPWdtETkGkLZxjTibiaygibc0z607FktdehHCGHjRWAiafe7qPTQyVmGngTlUgj1Ac8sZ5jic64bLgA/0?wx_fmt=png">
					</section>
					<section
						style="box-sizing: border-box; color: rgb(102, 102, 102); font-size: 0.9em; font-weight: inherit; text-align: inherit; text-decoration: inherit; width: 518px; padding: 10px 0px;">
						<section style="box-sizing: border-box;" class="pBrush">
							美味海鲜</section>
					</section>
				</section>
			</div>
		</div>

		<!--样式0-->
		<div data-color="section.main:border-color" class="element-item ">
			<div class="content">
				<section style="margin: 1em; font-size: 16px; text-align: center;">
					<section
						style="display: inline-block; padding: 1px; border: 1px solid rgb(58, 188, 255); font-size: 14px; border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; height: 200px; width: 200px;"
						data-style="line-height:24px; font-size:14px" class="main">
						<section
							style="padding: 1px; border: 1px solid rgb(58, 188, 255); border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; height: 196px; width: 196px; color: inherit;"
							data-style="line-height:24px;color:rgb(89, 89, 89); font-size:14px"
							class="main">
							<section
								style="padding: 2px; border: 1px solid rgb(58, 188, 255); border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-right-radius: 100%; border-bottom-left-radius: 100%; height: 192px; width: 192px; color: inherit;"
								data-style="line-height:24px; font-size:14px" class="main">
								<img width="100%"
									style="border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; color: inherit; height: auto !important;"
									src="https://mmbiz.qlogo.cn/mmbiz/cZV2hRpuAPjFyMrRy9J00JLdWSKC8mLZpsTtU31XOojhYhohT7tlBuXAfqRWpjAZYzdj6MsFJHSE8JbvWicuhaw/0">
							</section>
						</section>
					</section>
				</section>
			</div>
		</div>
	</div>
</div>

<div class="clearfix"></div>
<!--
<div contenteditable="true"
	style="float: left; width: 100%; height: 195px; overflow-y: auto; overflow-x: hidden; background: white; margin-top: 10px; padding: 0px; border: 1px solid #d4d4d4;"
	id="preview_content"></div>
  -->

<script type="text/javascript">
	$(document).ready(function() {
		var hash = window.location.hash || "#site";
		if (hash) {
			var lis = $("#nav_lis>li"), divs = $("#nav_uls>div");
			lis.each(function(index, v) {
				if (hash == v.getAttribute("data-hash")) {
					v.className = "hover";
					//.var divs = $("#nav_uls>div");
					for (var i = 0, ci; ci = divs[i]; i++) {
						if (index == ci.getAttribute("data-index")) {
							$(ci).addClass("show");
						} else {
							$(ci).removeClass("show");
						}
					}
				}
			});
		}
		$("#nav_lis").on("mouseover", function(e) {
			$(this).find("li").removeClass("hover");
			e.target.className = "hover";
			var index = e.target.getAttribute("data-index");
			//
			var divs = $("#nav_uls>div");
			for (var i = 0, ci; ci = divs[i]; i++) {
				if (index == ci.getAttribute("data-index")) {
					$(ci).addClass("show");
				} else {
					$(ci).removeClass("show");
				}
			}
		});
	});
</script>
