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
				
					<h4>남은휴가일수 : <span id="remainHoliday"></span></h4>
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

