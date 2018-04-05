	$(document).ready(function(){
		
		function go(id){
			$.ajax({
				type:"post",
	               url:"dispatcher",
	               data:"command=delete_user&id="+id,
	               success:function(result){
	            	   console.log(1);
	               		return;
	               }
   			});
		}
		
		var userId ;
		$(".deleteButton").click(function(){
			userId = $(this).val();
			$('#delete_modal').modal('show');
		});//delete modal open
		
		$("#deleteOkBtn").click(function(){
			//login Id
			var loginId = $("#hideId").val();
			
			//delete Id
			var id = userId;
			var password = $("#password").val();
			$.ajax({
	               type:"post",
	               url:"dispatcher",
	               data:"command=login&staffId="+loginId+"&staffPw="+password,
	               success:function(result){
	               		if(result == 'ok'){
	               			go(id);
	               			console.log(2);
	               			location.href="dispatcher?command=read_total_user";
	               		}else{
	               			alert('비밀번호가 일치하지 않습니다.');
	               			$(this).dialog( "close" );
	               		}
	               }
			});
		});//deleteOkBtn
	});
	
	
	//<a href="#" onClick=location.href('http://www.maya23.wo.ro/')>링크</a> 