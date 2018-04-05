$(document).ready(function(){
	$("#id").keyup(function(){
		var idValue=$(this).val().replace(/ /g, "");
		if(idValue.length<4||idValue.length>10){
			$("#checkIdFlag").val("false");
			$("#checkIdResult").html("아이디는 4자이상 10자 이하만 가능합니다.").css("color","red");
		}else{
			$.ajax({
				type:"get",
				dataType:"json",   
				url:"../dispatcher?command=check_id",
				data: { id:idValue },
				success:function(data){
					if(data.flag=="fail"){
						$("#checkIdFlag").val("false");
						$("#checkIdResult").html("사용 불가해요ㅠㅠ").css("color","red");
					}else{
						$("#checkIdFlag").val("true");
						$("#checkIdResult").html("사용 가능합니다!").css("color","blue");
						$("#id").css("border-color","#d2d6de");
					}//else
				}//success
			});//ajax						
		}//else
	})//keyup
	
	$(".confirmPassword").keyup(function() {
		var password=$(".password").val();
		var confirmPassword=$(".confirmPassword").val();
		if(password!=confirmPassword){
			$(".password").removeClass('update-ok-box');
			$(".password").addClass('update-error-box');
		}else if(password==confirmPassword){
			$(".password").removeClass('update-error-box');
			$(".password").addClass('update-ok-box');
			$(".confirmPassword").removeClass('update-error-box');
		}
	});
	
	$(".registerButton").click(function(){
		var checkIdValue=$("#checkIdFlag").val();
		var password=$(".password").val();
		var confirmPassword=$(".confirmPassword").val();
		if(checkIdValue=="false"){
			$("#id").css("border-color","red");
			$("#id").focus();
			return false;
		}else if(password!=confirmPassword){
			$(".password").addClass('update-error-box');
			$(".password").focus();
			$(".confirmPassword").addClass('update-error-box');
			alert('패스워드가 일치하지 않습니다.');
			return false;
		}
	})//registerButton
	
	$(".cancelButton").click(function(){
		location.href="dispatcher?command=login_view";
	})
	$(".logincancelButton").click(function(){
		location.href="login.jsp";
	})
});//ready