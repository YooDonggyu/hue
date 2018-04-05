<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title">직원 목록</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <table id="holiday-list" class="table table-hover table-bordered">
            <thead>
            <tr>
				<th>No</th>
				<th>동무</th>
				<th>사원명</th>
				<th>이메일</th>
				<th>직책</th>
				<th></th>
			</tr>
            </thead>
            <tbody>
            	<c:forEach var="staff" items="${requestScope.staffListVO.list}" varStatus="status">
            		<tr>
            			<td>${status.count}</td>
            			<td>
            			<div class="user-panel">
            				<div class="image">
            					<img src="${pageContext.request.contextPath}${staff.imagePath}" class="img-circle" alt="User Image">
            				</div>
            			</div>
            				
            			</td>
            			<td>${staff.name}</td>
            			<td>${staff.mail}</td>
            			
            			<td>
	            			<form action="${pageContext.request.contextPath}/dispatcher" method="get">
	            				<input type="hidden" name="command" value="update_user_position">
		            			<input type="hidden" name="staffId" value="${staff.id}">
		            			<select name="pname" class="custom-select">
		            				<c:forEach var="position" items="${requestScope.positionList}">
		            					<option value="${position.pId }" 
		            					<c:if test="${staff.positionVO.pName == position.pName}">
		            						selected
		            					</c:if>
		            					>${position.pName}</option>
		            				</c:forEach>
		            			</select>
		            			
	            				<input type="submit" class="btn btn-primary btn-sm" value="수정">
	            			</form>
            			</td>          			
            			<td>
            				<button type="button" class="btn btn-danger btn-sm deleteButton" id="id" value="${staff.id}">탈퇴</button>
            			</td>
            		</tr>
            	</c:forEach>
            </tbody>
          </table>
          <c:set value="${requestScope.staffListVO.pagingBean }" var="bean"/>
          <div class="row">
          	<div class="col-xs-12 text-center">
          		<ul class="pagination">  
		<%--prevButton --%>
			<c:if test="${bean.isPreviousPageGroup()==true}">
				<li><a href="dispatcher?command=read_total_user&pageNo=${bean.getStartPageOfPageGroup()-1}">&laquo;</a></li>
			</c:if>
		<%--pageButton --%>
		<c:forEach var="page" begin="${bean.getStartPageOfPageGroup()}" end="${bean.getEndPageOfPageGroup()}">
			<c:choose>
			<c:when test="${bean.nowPage == page}"><li class="active"><a href="#">${page}</a></li></c:when>
			<c:otherwise>
				<li><a href="dispatcher?command=read_total_user&pageNo=${page}">${page}</a></li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--nextButton --%>
		<c:if test="${bean.isNextPageGroup()==true}">
			<li><a href="dispatcher?command=read_total_user&pageNo=${bean.getEndPageOfPageGroup()+1}">&raquo;</a></li>
		</c:if>
	</ul>
          	</div>
          	<!-- /.col -->
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
    </div>
    <!-- /.col -->
  </div>
  <!-- /.row -->
</section>
<!-- /.content -->

<!-- deleteStaff -->
<script src="${pageContext.request.contextPath}/dist/js/deleteStaff.js"></script>
<%@ include file="../member/delete_user.jsp" %>