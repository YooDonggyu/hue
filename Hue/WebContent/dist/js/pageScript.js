/**
 * 
 */

$(document).ready(function () {
	  $(".index").click(function(){
		   var hNo = $(this).data('param');
		   $.ajax({
	            type:"get",
	            url:"dispatcher",
	            dataType:"json",
	            data:"command=read_detail_holiday&hNo="+hNo,
	            success:function(holidayVO){
	            	//삭제할 때 필요한 hNo를 hidden에 저장
	            	$("#hNo").val(holidayVO.hNo);
	            	//console.log("hNo: "+holidayVO.hNo);
	            	
	            	$('#detail_modal').modal('show');
	            	$("#hId").val(holidayVO.staffVO.id);
	            	$("#hName").val(holidayVO.staffVO.name);
	            	$("#hFlag").val(holidayVO.hFlag);
	            	$("#hContent").text(holidayVO.hContent);
	            	$("#hStartDate").val(holidayVO.hStartDate);
	            	$("#hEndDate").val(holidayVO.hEndDate);
	            	$("#hRegDate").val(holidayVO.hRegDate);
	            	$("#hNo").val(holidayVO.hNo);

	            	var loginId = $("#hiddenId").val();
	            	var loginPosition = $("#hiddenPosition").val();

	            	//로그인한 사람이 점장일 때
	            	if(loginPosition == "점장"){
	            		//본인것 휴가인지 확인
	            		if(loginId == holidayVO.staffVO.id){
	            			if(holidayVO.hFlag == "미처리"){
	            				console.log(1);
	            				document.getElementById("hContent").readOnly = false;
	            				document.getElementById("hStartDate").readOnly = false;
	            				document.getElementById("hEndDate").readOnly = false;	
	            			}else{
	            				console.log(2);
	            				document.getElementById("hContent").readOnly = true;
	            				document.getElementById("hStartDate").readOnly = true;
	            				document.getElementById("hEndDate").readOnly = true;	
	            				$("#updBtn").hide();
	            			}
	            			//점장이 클릭했을 때 본인것이 아님: 다른 점장 or 직원
	            		}else{
	            			//다른 점장이 쓴 것
	            			if(holidayVO.staffVO.positionVO.pName == "점장"){
	            				document.getElementById("hContent").readOnly = true;
	            				document.getElementById("hStartDate").readOnly = true;
	            				document.getElementById("hEndDate").readOnly = true;
	            				$("#updBtn").hide();
	            				$("#delBtn").hide();
	            				//직원
	            			}else{
	            				if(holidayVO.hFlag == "미처리"){
	            					console.log(3);
	            					document.getElementById("hContent").readOnly = true;
	            					document.getElementById("hStartDate").readOnly = true;
	            					document.getElementById("hEndDate").readOnly = true;
	            					$("#updBtn").hide();
	            					$("#delBtn").hide();	
	            				}else{
	            					console.log(4);
	            					document.getElementById("hContent").readOnly = true;
	            					document.getElementById("hStartDate").readOnly = true;
	            					document.getElementById("hEndDate").readOnly = true;
	            					$("#updBtn").hide();
	            				}
	            			}
	            		}
	            		//로그인한 사람이 직원일 때
	            	}else{
	            		if(holidayVO.hFlag == "미처리"){
	            			console.log(5);
	            			document.getElementById("hContent").readOnly = false;
	            			document.getElementById("hStartDate").readOnly = false;
	            			document.getElementById("hEndDate").readOnly = false;	
	            		}else{
	            			console.log(6);
	            			document.getElementById("hContent").readOnly = true;
	            			document.getElementById("hStartDate").readOnly = true;
	            			document.getElementById("hEndDate").readOnly = true;
	            			$("#updBtn").hide();
	            			$("#delBtn").hide();	
	            		}
	            	}
	            }//success   
		   });//ajax
		});//click
	  
	  $('#holiday-list').DataTable({
	      'paging'      : false,
	      'lengthChange': false,
	      'searching'   : true,
	      'ordering'    : true,
	      'info'        : false,
	      'autoWidth'   : false
	  });
	  
  })