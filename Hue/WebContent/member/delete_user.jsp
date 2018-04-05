<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
          <div class="modal fade" id="delete_modal" tabindex="-1" data-focus-on="input:first">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">정말로 탈퇴하시겠습니까?</h4>
              </div>

			<div class="modal-body">
				<div class="box box-default">
					<div class="box-body">
						<form role="form" id="deleteForm" method="post">
							<label>탈퇴시 모든 휴가기록은 같이 삭제 됩니다.</label><br>
							<div>비밀번호를 입력하세요</div>
							
							<div class="form-group" >
								<input type="password" class="form-control" name="password" id="password" required="required"> 
							</div>
							
								<div class="form-group">
									<button  id="deleteOkBtn" class="btn btn-block btn-danger ">삭제</button>
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
        
        