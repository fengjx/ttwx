/**
 * 包含easyui的扩展和常用工具方法
 * 
 * @author
 */

var fjx = $.extend({}, fjx);/* 全局对象 */

$.parser.auto = true;
$(function() {
	$.parser.parse(window.document);
	window.setTimeout(function() {
		if (self != parent) {
			window.setTimeout(function() {
				parent.app.closeDialog();
			}, 500);
		}else{
			app.closeDialog();
		}
	}, 1);
	$.parser.auto = true;
});

fjx.changeTheme = function(themeName) {/* 更换主题 */
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};
if ($.cookie('easyuiThemeName')) {
	fjx.changeTheme($.cookie('easyuiThemeName'));
}

// 扩展easyui 的tree组件方法，得到实心节点
$.extend($.fn.tree.methods, {
	getCheckedExt : function(jq) {
		var checked = $(jq).tree("getChecked");
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
					var node = $.extend({}, $.data(this, "tree-node"),
							{
								target : this
							});
					checked.push(node);
				});
		return checked;
	},
	getSolidExt : function(jq) {
		var checked = [];
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
					var node = $.extend({}, $.data(this, "tree-node"),
							{
								target : this
							});
					checked.push(node);
				});
		return checked;
	}
});

$.fn.panel.defaults.onBeforeDestroy = function() {/* tab关闭时回收内存 */
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		} else {
			$(this).find('.combo-f').each(function() {
						var panel = $(this).data().combo.panel;
						panel.panel('destroy');
					});
		}
	} catch (e) {
	}
};

$.fn.form.defaults.ajax = true;
$.fn.panel.defaults.loadingMessage = '数据加载中，请稍候....';
$.fn.datagrid.defaults.loadMsg = '数据加载中，请稍候....';

var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.statusText.split('<script')[0]);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
//扩展datagrid刷新方法myReload，刷新数据并取消选中
$.extend($.fn.datagrid.methods, {
	myReload : function(jq){
		$(jq).datagrid("reload").datagrid("uncheckAll").datagrid("unselectAll");
	}
});
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

var easyuiPanelOnMove = function(left, top) {/* 防止超出浏览器边界 */
	if (left < 0) {
		$(this).window('move', {
					left : 1
				});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;

$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {/* 扩展验证两次密码 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

$.extend($.fn.datagrid.defaults.editors, {
			combocheckboxtree : {
				init : function(container, options) {
					var editor = $('<input/>').appendTo(container);
					options.multiple = true;
					editor.combotree(options);
					return editor;
				},
				destroy : function(target) {
					$(target).combotree('destroy');
				},
				getValue : function(target) {
					return $(target).combotree('getValues').join(',');
				},
				setValue : function(target, value) {
					$(target).combotree('setValues', fjx.getList(value));
				},
				resize : function(target, width) {
					$(target).combotree('resize', width);
				}
			}
		});

// 设置，默认日历控件日期格式为 yyyy-MM-dd
$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};
$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};

fjx.showMsg = function(msg) {
	$.messager.show({
		title : '提示',
		msg : msg,
		timeout : 5000,
		showType : 'slide'
	});
};

fjx.progress = function(msg) {
	var text = msg || "数据提交中....";
	$.messager.progress({
				text : text,
				interval : 100
			});
}

fjx.closeProgress = function() {
	window.setTimeout(function() {
		$.messager.progress('close');
		if (self != parent) {
			window.setTimeout(function() {
				parent.$.messager.progress('close');
			}, 500);
		}
	}, 1);
};
/**
 * 获得项目根路径
 * 
 * 使用方法：fjx.bp();
 */
fjx.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

/**
 * 增加formatString功能
 * 
 * 使用方法：fjx.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 */
fjx.fs = function(str) {
	for (var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * 增加命名空间功能
 * 
 * 使用方法：fjx.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
fjx.ns = function() {
	var o = {}, d;
	for (var i = 0; i < arguments.length; i++) {
		d = arguments[i].split(".");
		o = window[d[0]] = window[d[0]] || {};
		for (var k = 0; k < d.slice(1).length; k++) {
			o = o[d[k + 1]] = o[d[k + 1]] || {};
		}
	}
	return o;
};

/**
 * 生成UUID
 */
fjx.random4 = function() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
fjx.UUID = function() {
	return (fjx.random4() + fjx.random4() + "-" + fjx.random4() + "-"
			+ fjx.random4() + "-" + fjx.random4() + "-" + fjx.random4()
			+ fjx.random4() + fjx.random4());
};

/**
 * 获得URL参数
 */
fjx.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

fjx.getList = function(value) {
	if (value) {
		var values = [];
		var t = value.split(',');
		for (var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

fjx.png = function() {
	var imgArr = document.getElementsByTagName("IMG");
	for (var i = 0; i < imgArr.length; i++) {
		if (imgArr[i].src.toLowerCase().lastIndexOf(".png") != -1) {
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
					+ imgArr[i].src + "', sizingMethod='auto')";
			imgArr[i].src = "images/blank.gif";
		}
		if (imgArr[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = imgArr[i].currentStyle.backgroundImage.substring(5,
					imgArr[i].currentStyle.backgroundImage.length - 2);
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
					+ img + "', sizingMethod='crop')";
			imgArr[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};
fjx.bgPng = function(bgElements) {
	for (var i = 0; i < bgElements.length; i++) {
		if (bgElements[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = bgElements[i].currentStyle.backgroundImage.substring(5,
					bgElements[i].currentStyle.backgroundImage.length - 2);
			bgElements[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
					+ img + "', sizingMethod='crop')";
			bgElements[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};

fjx.isLessThanIe8 = function() {/* 判断浏览器是否是IE并且版本小于8 */
	return ($.browser.msie && $.browser.version < 8);
};

fjx.resHandler = function(res,successMsg,failMsg) {
	if(res && '1' === res.code){
		fjx.showMsg(res.msg?res.msg:successMsg?successMsg:'操作成功！');
	}else{
		$.messager.alert('提示',res?res.msg:failMsg?failMsg:'操作失败！','error');
	}
};

/**
 * 格式化oracle返回的日期字符串(2013-12-26T10:14:14 --> 2013-12-26 10:14:14)
 * @param {} val
 * @return {}
 */
function formattime(val) {
	if (val) {
		return val.replace('T', ' ');
	}
	return '';
}
/* 定义jquery AJAX 默认设置 */
$.ajaxSetup({
	type : 'post',
	dataType : "json",
	cache : false,
	async : true,
	headers : {
		"Request-Flag" : "ajax"
	},
	dataFilter : function(data, type) {
		// 对Ajax返回的原始数据进行预处理
		if (type === "json") {
			var _data = $.parseJSON(data);
			if (_data && _data.code == "-1") { // -1表示登陆超时
				app.alert("登陆超时，请重新登陆！",{
					ok : function(){
						window.location.href = domain + "/login";
					}					
				});
				return false;
			}
		}
		return data // 返回处理后的数据
	},
	success : function(res) {
		fjx.resHandler(res);
	},
	beforeSend : function () {
		fjx.progress("正在处理，请稍等。。。");
	},
	complete : function(){
		fjx.closeProgress();
	},
	error : function(){
		fjx.closeProgress();
	}
});
