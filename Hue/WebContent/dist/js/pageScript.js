/**
 * 
 */

$(function () {
	  $('#holoday-list').DataTable({
	      'paging'      : false,
	      'lengthChange': false,
	      'searching'   : true,
	      'ordering'    : true,
	      'info'        : false,
	      'autoWidth'   : false
	  });
	  
	  $(".index").click(function(){
		   var hNo = $(this).data('param');
		   $.ajax({
	            type:"post",
	            url:"${pageContext.request.contextPath}/dispatcher",
	            dataType:"json",
	            data:"command=read_detail_holiday&hNo="+hNo,
	            success:function(holidayVO){
	               if(holidayVO== null){
	                  //
	               }else{
	                  $("#hName").val(holidayVO.staffVO.name);
	                  $("#hFlag").val(holidayVO.hFlag);
	                  $("#hContent").text(holidayVO.hContent);
	                  $("#hStartDate").val(holidayVO.hStartDate);
	                  $("#hEndDate").val(holidayVO.hEndDate);
	                  $("#hNo").val(holidayVO.hNo);
	                  
	                  if(holidayVO.staffVO.positionVO.pName == "점장"){
	                     if(holidayVO.hFlag == "미처리" || holidayVO.hFlag == "승인"){
	                        //삭제
	                        document.getElementById("hContent").readOnly = true;
	                        document.getElementById("hStartDate").readOnly = true;
	                        document.getElementById("hEndDate").readOnly = true;
	                        $("#updBtn").hide();
	                     }else{
	                        //x
	                        document.getElementById("hContent").readOnly = true;
	                        document.getElementById("hStartDate").readOnly = true;
	                        document.getElementById("hEndDate").readOnly = true;
	                        $("#updBtn").hide();
	                        $("#delBtn").hide();
	                     }
	                  }else{
	                     if(holidayVO.hFlag == "미처리"){
	                        //수정 및 삭제
	                        document.getElementById("hContent").readOnly = false;
	                        document.getElementById("hStartDate").readOnly = false;
	                        document.getElementById("hEndDate").readOnly = false;
	                     }else{
	                        //x
	                        document.getElementById("hContent").readOnly = true;
	                        document.getElementById("hStartDate").readOnly = true;
	                        document.getElementById("hEndDate").readOnly = true;
	                        $("#updBtn").hide();
	                        $("#delBtn").hide();
	                     }
	                     
	                  }
	               }
	            }   
	         });//ajax
		});//click
  })