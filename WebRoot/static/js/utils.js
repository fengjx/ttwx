

var app = $.extend({}, app);/* 全局对象 */
/**
 * 将json字符串转成json对象
 * 
 * @param {}
 *            jsonStr
 * @return {}
 */
function serialiJson(jsonStr) {
	return eval('(' + jsonStr + ')');
}

/**
 * 动态创建json对象
 * 
 * @param {}
 *            jsonObj
 * @param {}
 *            key
 * @param {}
 *            value
 */
function createJson(jsonObj, key, value) {
	// 如果 value 被忽略
	if (typeof value === "undefined") {
		// 删除属性
		delete jsonObj[key];
	} else {
		// 添加 或 修改
		jsonObj[key] = value;
	}
	return jsonObj;
}
/**
 * 得到url路径 /开头标识本项目路径，其他表示外部系统路径（直接返回）
 * 
 * @param {}
 *            targetUrl
 * @param {}
 *            domain
 * @return {}
 */
function getUrl(targetUrl, domain) {
	if (!targetUrl || targetUrl == '') {
		return '';
	}
	if (/^\//.test(targetUrl)) { // 如果是/开头
		return domain + targetUrl;
	}
	return targetUrl;
}

// 验证手机号码合法性
function validateMobile(value) {
	var reg1 = /^13\d{9}$/;
	var reg2 = /^15[0-35-9]\d{8}$/;
	var reg3 = /^18[05-9]\d{8}$/;
	if (reg1.test(value) || reg2.test(value) || reg3.test(value)) {
		return true;
	}
	return false;
}

// 验证邮箱合法性
function validateMail(value) {
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (reg.test(value)) {
		return true;
	}
	return false;
}

// 验证固话合法性
function validateTel(value) {
	var reg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if (reg.test(value)) {
		return true;
	}
	return false;
}

// 验证邮编合法性
function validatePostal(value) {
	var reg = /^[a-zA-Z0-9 ]{3,12}$/;
	if (reg.test(value)) {
		return true;
	}
	return false;
}

/**
 * 时间戳转换时间
 * 
 * @param {}
 *            tm
 * @return {} 如：2011-3-16 16:50:43 格式
 */
function getLocalTime(tm) {
	var tt;
	if (tm.toString().length == 13) {
		tt = new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-")
				.replace(/日/g, " ").replace(/\//g, "-");

	} else {
		tt = new Date(parseInt(tm) * 1000).replace(/年|月/g, "-").replace(/日/g,
				" ").replace(/\//g, "-");
	}
	return tt;
}

/**
 * 时间戳转换时间
 * 
 * @param {}
 *            tm
 * @return {} 如：2011年3月16日 16:50:43 格式
 */
function getLocalTimeCN(tm) {
	var tt
	if (tm.toString().length == 13) {
		tt = new Date(parseInt(tm)).toLocaleString().replace(/\//g, "-");
	} else {
		tt = new Date(parseInt(tm) * 1000).toLocaleString().replace(/\//g, "-");
	}
	return tt;
}

var art;
app.loadingModal = function(title,opt){
	app.closeDialog();
	var _title = title||"正在请求...";
	var _opt = $.extend({"title":_title,"width":"200"}, opt||{});
	art = dialog(_opt);
	art.showModal();
}

app.loading = function(title,opt){
	app.closeDialog();
	var _opt = $.extend({"title":"正在请求...","width":"200"}, opt||{});
	art = dialog(_opt);
	art.show();
}

app.alert = function(msg, opt){
	app.closeDialog();
	var _opt = $.extend({
	    title: '提示',
	    width:"200",
	    content: msg,
	    cancel: false,
	    ok: function () {}
	}, opt||{});
	art = dialog(_opt);
	art.show();
}

app.alertModal = function(msg, opt){
	app.closeDialog();
	var _opt = $.extend({
	    title: '提示',
	    width:"200",
	    content: msg,
	    cancel: false,
	    ok: function () {}
	}, opt||{});
	art = dialog(_opt);
	art.showModal();
}

app.tusiModal = function(msg, time, opt){
	app.closeDialog();
	var _opt = $.extend({"content":msg,"width":"200"}, opt||{});
	art = dialog(_opt);
	art.showModal();
	if(time){
		setTimeout(function(){
			app.closeDialog();
		},time * 1000)
	}
}

app.tusiAlert = function(msg, time, opt){
	app.closeDialog();
	var _opt = {"content":msg,"width":"200"};
	art = dialog(_opt);
	art.show();
	if(time){
		setTimeout(function(){
			app.closeDialog();
		},time * 1000)
	}
}

app.tip = function(id, msg, time, opt){
	app.closeDialog();
	var d = dialog({
	    quickClose: true,// 点击空白处快速关闭
	    content: msg,
	    onclose: function () {
			$('#'+id).focus();
		},
		follow : $('#'+id)[0]
	});
	d.show();
	setTimeout(function(){
		app.closeDialog();
	},time * 1000)
}

app.closeDialog = function(){
	if(art){
		art.close().remove();
		art = undefined;
	}
}

app.page = function () {



}
	



