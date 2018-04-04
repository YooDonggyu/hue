/**
 * 
 */
function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

$(document).ready(function(){
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

	            	var loginId = $("#hiddenId").val();
	            	var loginPosition = $("#hiddenPosition").val();

	            	//로그인한 사람이 점장일 때
	            	if(loginPosition == "점장"){
	            		//본인것 휴가인지 확인
	            		if(loginId == holidayVO.staffVO.id){
	            			if(holidayVO.hFlag == "미승인"){
	            				console.log(1);
	            				document.getElementById("hContent").readOnly = false;
	            				document.getElementById("hStartDate").readOnly = false;
	            				document.getElementById("hEndDate").readOnly = false;	
	            				$("#updBtn").show();
	            				$("#delBtn").show();
	            				$("#confirmBtn").show();
	            				$("#denyBtn").show();
	            			}else if(holidayVO.hFlag == "승인"){
	            				console.log(2);
	            				document.getElementById("hContent").readOnly = true;
	            				document.getElementById("hStartDate").readOnly = true;
	            				document.getElementById("hEndDate").readOnly = true;	
	            				$("#updBtn").hide();
	            				$("#delBtn").show();
	            				$("#confirmBtn").hide();
	            				$("#denyBtn").hide();
	            			}else{
         					console.log(3);
	            				document.getElementById("hContent").readOnly = true;
	            				document.getElementById("hStartDate").readOnly = true;
	            				document.getElementById("hEndDate").readOnly = true;
	            				$("#updBtn").hide();
	            				$("#delBtn").hide();
	            				$("#confirmBtn").hide();
	            				$("#denyBtn").hide();
	            			}
	            			//점장이 클릭했을 때 본인것이 아님: 다른 점장 or 직원
	            		}else{
	            			//다른 점장이 쓴 것
	            			if(holidayVO.staffVO.positionVO.pName == "점장"){
         					console.log(4);
	            				document.getElementById("hContent").readOnly = true;
	            				document.getElementById("hStartDate").readOnly = true;
	            				document.getElementById("hEndDate").readOnly = true;
	            				$("#updBtn").hide();
	            				$("#delBtn").hide();
	            				$("#confirmBtn").hide();
	            				$("#denyBtn").hide();
	            				//직원
	            			}else{
	            				if(holidayVO.hFlag == "미승인"){
	            					console.log(5);
	            					document.getElementById("hContent").readOnly = true;
	            					document.getElementById("hStartDate").readOnly = true;
	            					document.getElementById("hEndDate").readOnly = true;
	            					$("#updBtn").hide();
	            					$("#delBtn").hide();
		            				$("#confirmBtn").show();
		            				$("#denyBtn").show();
	            				}else if(holidayVO.hFlag == "승인"){
	            					console.log(6);
		            				document.getElementById("hContent").readOnly = true;
		            				document.getElementById("hStartDate").readOnly = true;
		            				document.getElementById("hEndDate").readOnly = true;	
		            				$("#updBtn").hide();
		            				$("#delBtn").show();
		            				$("#confirmBtn").hide();
		            				$("#denyBtn").hide();
		            			}else{
	            					console.log(7);
		            				document.getElementById("hContent").readOnly = true;
		            				document.getElementById("hStartDate").readOnly = true;
		            				document.getElementById("hEndDate").readOnly = true;
		            				$("#updBtn").hide();
		            				$("#delBtn").hide();
		            				$("#confirmBtn").hide();
		            				$("#denyBtn").hide();
		            			}
	            			}
	            		}
	            		//로그인한 사람이 직원일 때
	            	}else{
	            		if(holidayVO.hFlag == "미승인"){
	            			console.log(5);
	            			document.getElementById("hContent").readOnly = false;
	            			document.getElementById("hStartDate").readOnly = false;
	            			document.getElementById("hEndDate").readOnly = false;	
         				$("#updBtn").show();
         				$("#delBtn").show();
         				$("#confirmBtn").hide();
         				$("#denyBtn").hide();
	            		}else{
	            			console.log(6);
	            			document.getElementById("hContent").readOnly = true;
	            			document.getElementById("hStartDate").readOnly = true;
	            			document.getElementById("hEndDate").readOnly = true;
	            			$("#updBtn").hide();
	            			$("#delBtn").hide();
	            			$("#confirmBtn").hide();
         				$("#denyBtn").hide();
	            		}
	            	}
	            }//success   
		   });//ajax
		});//click
	//휴가 승인
	  $("#confirmBtn").click(function() {
		  var hFlag=$("#confirmBtn button").text();
		  var hNo=$("#hNo").val();
		  if (confirm("휴가를 승인하시겠습니까?")) {
			  $.ajax({
					type:"get",
					url:"dispatcher",
					dataType:"json",
					data:"command=update_holiday_flag&hNo="+hNo+"&status="+hFlag,
					success:function(data){
						if(data.flag=="ok")	{
							location.href="dispatcher?command=read_holiday";
						}
					}
				});
			}
			else {
				return false;
			}
	  });
	//휴가 승인
	  $("#confirmBtn").click(function() {
		  var hFlag=$("#confirmBtn button").text();
		  var hNo=$("#hNo").val();
		  if (confirm("휴가를 승인하시겠습니까?")) {
			  $.ajax({
					type:"get",
					url:"dispatcher",
					dataType:"json",
					data:"command=update_holiday_flag&hNo="+hNo+"&status="+hFlag,
					success:function(data){
						if(data.flag=="ok")	{
							location.href="dispatcher?command=read_holiday";
						}
					}
				});
			}
			else {
				return false;
			}
	  });
	//휴가 거절
	  $("#denyHolidayBtn").click(function() {
		  var hFlag=$("#denyHolidayBtn").text();
		  var hNo=$("#hNo").val();
		  var reason=$("#denyReason").val();
		 
		$.ajax({
				type:"get",
				url:"dispatcher",
				dataType:"json",
				data:"command=update_holiday_flag&hNo="+hNo+"&status="+hFlag+"&denyHolidayReason="+reason,
				success:function(data){
					if(data.flag=="ok")	{
						location.href="dispatcher?command=read_holiday";
					}
				}
			});
	  });
	  
	$(".deleteHoliday").click(function() {
		var hNo=$("#hNo").val();
		
		$.ajax({
			type:"get",
			dataType:"json",   
			url:"dispatcher?command=delete_holiday",
			data:{ hNo : hNo },
			success:function(data){
				if(data.flag=="fail"){
					alert("삭제 실패!");
				}else{
					location.replace("dispatcher?command=read_holiday");
				}
			}
		});	
	});
	

		$("#update_holiday_modal").on("show.bs.modal", function (e) {
			var voId = $(e.relatedTarget).data('staff-id');
			/*var voId="${sessionScope.staffVO.id}";*/
			$.ajax({
				type:"get",
				dataType:"json",
				url:getContextPath()+"/dispatcher?command=remain_holiday",
				data:{ voId : voId },
				success:function(data){
					remainDate=data.result;
					if(data.result>0){
						
						$("#remainHoliday").html(data.result);
					}else{
						alert("사용할 수 있는 휴가가 없습니다.");
						$('#update_holiday_modal').modal('hide');
					}
				}//success
			})//ajax
		});//modal show
		
	$("#registerButton").click(function(){
		 var remainDate= $("#remainHoliday").text();
		 	var startDate = $( "input[name='holidayStartDate']" ).val(); 
	        var startDateArr = startDate.split('-');
	        var endDate = $( "input[name='holidayEndDate']" ).val(); 
	        var endDateArr = endDate.split('-');
	        var todayDate = new Date();
	        var startDateCompare = new Date(startDateArr[0], parseInt(startDateArr[1])-1, startDateArr[2]);
	        var endDateCompare = new Date(endDateArr[0], parseInt(endDateArr[1])-1, endDateArr[2]);
	        if(startDateCompare.getTime() > endDateCompare.getTime()) {
	            alert("시작날짜와 종료날짜를 확인해주세요.");
	            return false;
	        }if((endDateCompare.getDate()-startDateCompare.getDate()+1)>remainDate){
	        	alert("사용가능한 휴가일수를 확인해 주세요.");
	        	return false;
	        } if(startDateCompare.getDate()>=todayDate.getDate()&startDateCompare.getMonth()>=todayDate.getMonth()&startDateCompare.getYear()>=todayDate.getYear()){
	        	return true;
	        }else{
	        	alert("오늘 이후의 일자를 선택해주세요!");
	        	return false;
	        }
		});
	
	$('#holiday-list').DataTable({
		  "order": [[ 0, 'desc' ]],
	      'paging'      : false,
	      'lengthChange': false,
	      'searching'   : false,
	      'ordering'    : true,
	      'info'        : false,
	      'autoWidth'   : false
	  });
	});//document.ready();





	  
	 
	
	
	  
