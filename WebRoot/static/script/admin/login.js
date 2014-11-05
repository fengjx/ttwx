
/**
 * 用户登陆
 */

$(function(){
	if(window.parent != window){
		window.parent.location = window.location;
	}
	
	if ($.cookie('loginName')) {
		$("#inputUserId").val($.cookie('loginName'));
		$("#remember").attr("checked",true);
	}else{
		$("#inputUserId").val('');
		$("#remember").attr("checked",false);
	}
	$("#btn-login").button('reset');
});


function userlogin(){
	if(valid()){
		$("#btn-login").button('loading');
		var inputUserId = $("#inputUserId").val();
		var inputPwd = $("#inputPwd").val();
		$.ajax({
			url :  curUrl + '/wechat/busi/login_userLogin.action',
			data :"paramMap.userid="+inputUserId+"&paramMap.pwd="+inputPwd,
			dataType : 'json',
			success : function(res) {
				if(res && res.state == 'success'){
					window.location.href=curUrl + '/wechat/admin/main.action';
				}else{
					alert(res.msg?res.msg:'登錄失敗');
					$("#btn-login").button('reset');
				}
			},
			error:function(){
				alert('登錄請求異常');
				$("#btn-login").button('reset');
			}
		});
		
		//記住帳號
		if($("#remember").attr("checked")){
			$.cookie('loginName', inputUserId, { expires: 14 });
		}else{
			$.cookie('loginName', null); 
		}
		
	}	
}
function valid(){
	var UserId=document.getElementById("inputUserId").value
	var Pwd=document.getElementById("inputPwd").value
	if(UserId == ""){
		alert('請輸入用戶名!');
		return false;
	}
	if(Pwd == ""){
		alert('請輸入密碼!');
		return false;
	}
	return true;
}
