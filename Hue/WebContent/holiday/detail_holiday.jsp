<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   
   
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
						<h3 class="box-title">General Elements</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<form role="form">
						
							<!-- text input -->
							<div class="form-group">
								<label>Text</label> <input type="text" class="form-control"
									placeholder="Enter ...">
							</div>
							<div class="form-group">
								<label>Text Disabled</label> <input type="text"
									class="form-control" placeholder="Enter ..." disabled>
							</div>

							<!-- textarea -->
							<div class="form-group">
								<label>Textarea</label>
								<textarea class="form-control" rows="3" placeholder="Enter ..."></textarea>
							</div>
							<div class="form-group">
								<label>Textarea Disabled</label>
								<textarea class="form-control" rows="3" placeholder="Enter ..."
									disabled></textarea>
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
        
        
        
<script type="text/javascript">
	$(document).ready(function(){
		$("")
		
	});
</script>
        
   