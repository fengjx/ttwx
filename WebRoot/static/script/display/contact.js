
/**
 * 联系我们
 */
$(function(){
	
	if(window.parent != window){
		window.parent.location = window.location;
	}
	
	$("#form-guestbook").submit(function(){
		$(this).ajaxSubmit({
			url : domain + "/contact/guestbook/save",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
		   			app.alert(res.msg?res.msg:'发送成功，我们会在第一时间查看',{
						ok: function () {
				   			window.location.reload();
						}
					});
				}else{
					app.alert(res.msg?res.msg:'发送失败，请稍后再试！');
				}
				$("#btn-guestbook").button('reset');
	        } 
	    });
	    return false;
	});
	
	$("#ref-valid-img").click(function(){
		refreshValidCode();
	});
});


function validForm(){
	var name = $("#name").val();
	if(!name || name == ''){
		app.alert("请输入姓名",{
			onclose: function () {
		        $("#name").focus();
		    }
		});
		return false;
	}
	var email = $("#email").val();
	if(!email || email == ''){
		app.alert("请输入邮箱",{
			onclose: function () {
		        $("#email").focus();
		    }
		});
		return false;
	}
	var msg = $("#msg").val();
	if(!msg || msg == ''){
		app.alert("请编辑你要发送的信息",{
			onclose: function () {
		        $("#msg").focus();
		    }
		});
		return false;
	}
	$("#btn-guestbook").button('loading');
	return true;
}

