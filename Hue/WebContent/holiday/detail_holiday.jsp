<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
          <div class="modal fade" id="detail_modal" tabindex="-1" data-focus-on="input:first">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">휴가 정보 확인</h4>
              </div>

			<div class="modal-body">
				<div class="box box-warning">
					<div class="box-body">
						<form role="form" id="detailForm" method="post">
						<input type="hidden" id="hiddenId" value="${sessionScope.staffVO.id}">
						<input type="hidden" id="hiddenPosition" value="${sessionScope.staffVO.positionVO.pName}">
						<input type="hidden" name="hNo" id="hNo" >
							<!-- text input -->
							<div class="form-group" >
								<label>아이디</label>
								<input type="text" class="form-control" name="hId" id="hId" disabled="disabled"> 
								<label>이름</label>
								<input type="text" class="form-control" name="hName" id="hName" disabled="disabled"> 
								<label>상태</label>
								<input type="text" class="form-control" name="hFlag" id="hFlag" disabled="disabled"> 
								<label>휴가 출발일</label>
								<input type="date" class="form-control" name="hStartDate" id="hStartDate" readonly="readonly">
								<label>휴가 도착일</label>
								<input type="date" class="form-control" name="hEndDate" id="hEndDate" readonly="readonly">
								<label>휴가 신청일</label>
								<input type="date" class="form-control" name="hRegDate" id="hRegDate" readonly="readonly">
								<label>휴가 사유</label>
								<textarea class="form-control" name="hContent" id="hContent" rows="3" readonly="readonly"></textarea>
							</div>

							<div class="row">
								<div class="col-xs-4" id="updBtn">
									<button type="submit" class="btn btn-primary btn-block btn-flat">수정</button>
								</div>
								<div class="col-xs-4" id="confirmBtn">
									<button value="수정" class="btn btn-success btn-block btn-flat ">승인</button>
								</div>
								<div class="col-xs-4" id="denyBtn">
									<!-- <button value="수정" class="btn btn-warning btn-block btn-flat ">거절</button> -->
									<a data-toggle="modal" href="#modal-default" class="btn btn-warning btn-block btn-flat">거절</a>									
								</div>
								<div class="col-xs-4" id="delBtn">
									<a data-toggle="modal" href="#delete_holiday_modal" class="btn btn-danger">삭제</a>
								</div>
							</div>
						</form>
						<!--form 끝 -->
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!--  body 끝 -->

			  <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                
                <button type="button" class="btn btn-primary">Save changes</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
        
        