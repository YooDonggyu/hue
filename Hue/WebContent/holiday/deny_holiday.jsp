<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="modal-default">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">거절 사유를 입력하세요</h4>
			</div>
			<div class="modal-body">
				<input type="text" name="denyHolidayReason" class="form-control" id="denyReason">
			</div>
			<div class="modal-footer">				
				<button type="button" class="btn btn-warning" id="denyHolidayBtn">거절</button>
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->