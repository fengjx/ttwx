
/**
 * 用户登录
 * 2014年9月28日 fengjx
 */

$(function(){
	
	if(window.parent != window){
		window.parent.location = window.location;
	}
	
	$("#form-login").submit(function(){
		$(this).ajaxSubmit({
			url : domain + "/login",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
		   			window.location.href = domain;
				}else{
					app.alert(res.msg?res.msg:'登录失败',{
						ok: function () {
							$("#btn-login").button('reset');
							refreshValidCode();
						}
					});
				}
	        } 
	    });
	    return false;
	});
	
	$("#ref-valid-img").click(function(){
		refreshValidCode();
	});
});


function validForm(){
	var username = $("#username").val();
	if(!username || username == ''){
		app.alert("请输入用户名");
		$("#username").focus();
		return false;
	}
	var pwd = $("#pwd").val();
	if(!pwd || pwd == ''){
		app.alert("请输入密码");
		$("#pwd").focus();
		return false;
	}
	var valid_code = $("#valid_code").val();
	if(!valid_code || valid_code == ''){
		app.alert("请输入验证码");
		$("#valid_code").focus();
		return false;
	}
	$("#btn-login").button('loading');
	return true;
}

function refreshValidCode(){
	$("#valid-img").attr("src",domain+"/common/verification_code.jpg?_id="+new Date());
}

