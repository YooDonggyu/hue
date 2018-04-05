	$(document).ready(function(){
		$(".deleteButton").click(function(){
			$('#delete_modal').modal('show');
		});//delete modal open
		
		$("#deleteOkBtn").click(function(){
			//login Id
			var loginId = $("#hideId").val();
			
			//delete Id
			var id = $("#id").val();
			var password = $("#password").val();
			$.ajax({
	               type:"post",
	               url:"dispatcher",
	               data:"command=login&staffId="+loginId+"&staffPw="+password,
	               success:function(result){
	               		if(result == 'ok'){
	               			location.href="dispatcher?command=delete_user&id="+id;
	               		}else{
	               			alert('비밀번호가 일치하지 않습니다.');
	               			$(this).dialog( "close" );
	               		}
	               }
			});
		});//deleteOkBtn
	});