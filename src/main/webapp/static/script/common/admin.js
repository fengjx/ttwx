/**
 * 包含easyui的扩展和常用工具方法
 * 
 * @author
 */

var fjx = $.extend({}, fjx);/* 全局对象 */

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
