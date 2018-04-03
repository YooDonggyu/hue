<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal fade" id="delete_holiday_modal">
  <div class="modal-dialog">
    <div class="modal-content">
 	  <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">해당 항목을 삭제하시겠습니까?</h4>
      </div>
      <div class="modal-footer">
        <!-- <input type="hidden" id="hNo" value="2"> -->
        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger deleteHoliday">휴가 삭제</button>
      </div>
    </div>
  </div>
</div>
   
<script type="text/javascript">
$(document).ready(function(){
	$(".deleteHoliday").click(function() {
		var hNo=$("#hNo").val();
		
		$.ajax({
			type:"get",
			dataType:"json",   
			url:"${pageContext.request.contextPath}/dispatcher?command=delete_holiday",
			data:{ hNo : hNo },
			success:function(data){
				if(data.flag=="fail"){
					alert("삭제 실패!");
					location.replace("${pageContext.request.contextPath}/dispatcher?command=read_holiday");
				}else{
					alert("삭제 성공!");
					location.replace("${pageContext.request.contextPath}/dispatcher?command=read_holiday");
				}
			}
		});	
	})
});
</script>

