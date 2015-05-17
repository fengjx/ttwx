/**
 * 用户注册
 */
$(function(){
	
	$("#form-register").submit(function(){
		$(this).ajaxSubmit({
			url : domain + "/register",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
	        		app.alert("注册成功，请登录邮箱"+$("#email").val()+"进行验证！",{
						ok: function () {
				   			window.location.href = domain + "/login";
					        return false;
					    }
					});
				}else{
					//alert(res.msg?res.msg:'注册失败');
					app.alert("注册失败",{
						ok: function () {
							$("#btn-register").button('reset');
							app.closeDialog();
					        return false;
					    }
					});
				}
	        } 
	    });
	    return false;
	});
	
	$("#username").focusout(function(){
		var username = $(this).val();
		if(username && $.trim(username) !== ""){
			validUsername(username);
    	}
	});
	
	$("#email").focusout(function(){
		var email = $(this).val();
		if(email && $.trim(email) !== ""){
			validEmail(email);
    	}
	});
	
});


function validUsername(username){
	$("#username").attr("valid","0");
	$.ajax({
	   type: "POST",
	   url: domain + "/validUser",
	   data: "username="+username,
	   dataType : 'text',
	   success: function(res){
	   		if(res && "true" == res){
	   			$("#username").attr("valid","0");
				$("#username").parent().addClass("has-error");
				app.tip("username","用户名已存在！");
			}else{
				$("#username").attr("valid","1");
				$("#username").parent().removeClass("has-error");
				$("#username").parent().addClass("has-success");
			}
	   },
	   error : function(XMLHttpRequest, textStatus, errorThrown){
	   		app.alert("用户名校验失败");
	   },
	   beforeSend : function(XMLHttpRequest) {
	   }
	});
}

function validEmail(email){
	$("#email").attr("valid","0");
	$.ajax({
	   type: "post",
	   url: domain + "/validEmail",
	   data: "email="+email,
	   dataType : 'text',
	   success: function(res){
	   		if(res && "true" == res){
	   			$("#email").attr("valid","0");
				$("#email").parent().addClass("has-error");
				app.tip("email","该邮箱已被使用！");
			}else{
				$("#email").attr("valid","1");
				$("#email").parent().removeClass("has-error");
				$("#email").parent().addClass("has-success");
			}
	   },
	   error : function(XMLHttpRequest, textStatus, errorThrown){
	   		app.alert("邮箱校验失败");
	   },
	   beforeSend : function(XMLHttpRequest) {
	   }
	});
}


function validForm(){
	var username = $("#username").val();
	if(!username || $.trim(username) === ""){
		app.alert("用户名不能为空！");
		return false;
	}else{
		var username_valid = $("#username").attr("valid");
    	if(username_valid === "0"){
    		app.alert("用户名已存在，请重新输入");
			$("#username").focus();
    		return false;
    	}
	}
	var email = $("#email").val();
	if(!email || $.trim(email) === ""){
		app.alert("邮箱不能为空！");
		return false;
	}else{
		var email_valid = $("#email").attr("valid");
    	if(email_valid === "0"){
    		app.alert("邮箱已存在，请重新输入");
			$("#email").focus();
    		return false;
    	}
	}
	var pwd = $("#pwd").val();
	if(!pwd || $.trim(pwd) === ""){
		app.alert("密码不能为空！");
		return false;
	}else{
		var pwd2 = $("#pwd2").val();
		if(pwd2 == ""){
			app.alert("请再次输入密码！");
			$("#pwd2").focus();
			return false;
		}
		if(pwd != pwd2){
			app.alert("两次输入的密码不一致！");
			return false;
		}
	}
	$("#btn-register").button('loading');
	return true;
}
