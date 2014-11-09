/**
 * 设置授权
 */
$(function(){
	
	$("#form-auth").submit(function(){
		$(this).ajaxSubmit({
			url : domain + "/admin/setting/save",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
	        		app.alert("授权已生成！",{
	        			ok : function(){
				   			window.location.href = domain + '/admin/setting';
	        			}
	        		});
				}else{
					app.alert(res.msg?res.msg:'授权失败');
				}
	        } 
	    });
	    return false;
	});
	
	//改变当前状态
	changeState(valid_state);
	
});


function validForm(){
	
	return true;
}

function changeState (valid_state){
	if(valid_state == '0'){
		$("#step1").addClass("text-danger");
		$("#step2").removeClass("text-danger");
		$("#step3").removeClass("text-danger");
	}else if(valid_state == '1'){
		$("#step2").addClass("text-danger");
		$("#step1").removeClass("text-danger");
		$("#step3").removeClass("text-danger");
	}else if(valid_state == '2'){
		$("#step1").removeClass("text-danger");
		$("#step2").removeClass("text-danger");
		$("#step3").addClass("text-danger");
	}else{
		$("#step1").removeClass("text-danger");
		$("#step2").removeClass("text-danger");
		$("#step3").removeClass("text-danger");
	}
}