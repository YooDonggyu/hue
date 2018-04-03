<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<script type="text/javascript">
	$(document).ready(function(){
		$(".index").click(function(){
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
		});
	});	
</script> -->
   
   
          <div class="modal fade" id="detail_modal">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">휴가 정보 확인</h4>
              </div>

			<div class="modal-body">

				<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">${sessionScope.staffVO.name}</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<form role="form">
							<!-- text input -->
							<div class="form-group">
								<label>이름</label>
								<input type="text" class="form-control" id="hName" disabled="disabled"> 
								<label>상태</label>
								<input type="text" class="form-control" id="hFlag" disabled="disabled"> 
								<label>휴가 출발일</label>
								<input type="date" class="form-control" id="hStartDate" >
								<label>휴가 도착일</label>
								<input type="date" class="form-control" id="hEndDate" >
								<label>휴가 사유</label>
								<textarea class="form-control" id="hContent" rows="3" ></textarea>
							</div>
							<input type="hidden" id="hNo" value="">
							
							<div class="row" id="updBtn" >
								<button value="수정" class="btn btn-primary btn-block btn-flat " >수정</button>
							</div>
							<div class="row" id="delBtn" >
								<button value="삭제" class="btn btn-primary btn-block btn-flat " >삭제</button>
							</div>
							<div class="row" id="listBtn" >
								<button value="목록" class="btn btn-primary btn-block btn-flat " >목록</button>
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
        