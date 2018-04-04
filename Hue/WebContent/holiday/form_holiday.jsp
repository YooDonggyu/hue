<%@page import="model.StaffVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="update_holiday_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">휴가 신청</h4>
			</div>
			<form action="${pageContext.request.contextPath}/dispatcher"
				method="post" id="holidayRegisterForm">
				<input type="hidden" name="command" value="create_holiday">
				<input type="hidden" name="voId" value="${sessionScope.staffVO.id}">
				<div class="modal-body">
				
					<p>휴가를 신청해주세요.&hellip;</p>
					<span id="remainHoliday">남은 휴가일수 : </span>
					<input type="text" name="name" value="${sessionScope.staffVO.name}"
						id="holidayName" class="form-control" readonly="readonly" />

					<div class="row">
						<div class="col-md-6 mb-3">
							<input type="date" name="holidayStartDate" id="holidayStartDate"
								required="required" class="form-control" value=""></input>
						</div>
						<div class="col-md-6 mb-3">
							<input type="date" name="holidayEndDate" id="holidayEndDate"
								required="required" class="form-control"></input>
						</div>
					</div>
					<textarea name="holidayContent" id="holidayContent"
						class="form-control" required="required" placeholder="신청사유"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-default pull-left cancleButton"
						data-dismiss="modal">취소</button>
					<input type="submit" class="btn btn-default pull-right"
						id="registerButton" value="신청"></input>
				</div>
			</form>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>
<!-- /.content-wrapper -->

<script>
var remainDate=0;
	$(document).ready(function() {
		$("#update_holiday_modal").on("show.bs.modal", function () {
			var voId = ${sessionScope.staffVO.id};
			alert(voId);
			$.ajax({
				type:"get",
				dataType:"json",
				url:"${pageContext.request.contextPath}/dispatcher?command=remain_holiday",
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
	})
})
</script>
