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
				<div class="box box-default">
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
						<div class="form-group">
							<div class="row form-group" >
								<div class="col-xs-6" id="updBtn">
									<button type="submit" class="btn btn-block btn-default  ">수정</button>
								</div>
								
								<div class="col-xs-6" id="delBtn">
									<a data-toggle="modal" href="#delete_holiday_modal" class="btn btn-block btn-danger">삭제</a>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-6" id="confirmBtn">
									<button  class="btn btn-block btn-primary ">승인</button>
								</div>
								<div class="col-xs-6" id="denyBtn">
									<a data-toggle="modal" href="#modal-default" class="btn btn-block btn-primary">거절</a>									
								</div>
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

			
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
        
        