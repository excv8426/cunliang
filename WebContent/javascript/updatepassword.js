/**
 * 判断两次输入的密码是否相同。*/
function samepassword(){
	if($("#password_input").val()==$("#oldpassword_input").val()){
		$("#password_input").tooltip('hide');
		updatepassword();
		return true;
	}else{
		$("#password_input").tooltip('show');
		return false;
	}
}
/**
 * 更改密码。*/
function updatepassword(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/updatepassword.do",
		data : {password:$("#password_input").val()},
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				if((data.exception!=null)||(data.exception!=undefined)){
					$("#myModal").find(".modal-body").find("p").html(data.exception);
				}else if(data.error=="nullproperty"){
					$("#myModal").find(".modal-body").find("p").html("密码不能为空。");
				}else{
					$("#myModal").find(".modal-body").find("p").html("修改密码成功。");
				}
				$('#myModal').modal();
			}
		}
	});
}
$(function(){
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
	$("#updatepassword_button").bind("click",function(){
		samepassword();
	});
	$("#password_input").tooltip({
		placement:"right",
		title:"密码不一致。",
		trigger:"manual"
	});
});