<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title">휴가 목록</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
          	<div class="col-sm-6">
          		분류:&ensp;<select id="condition">
          			<option value="">전체</option>
          			<option value="미승인">미승인</option>
          			<option value="승인">승인</option>
          			<option value="거절">거절</option>
          		</select>
          	</div>
          	<!-- ./col -->
          	<div class="col-sm-6">
          		<span class="pull-right">총 사용가능한 휴가일수: ${sessionScope.staffVO.positionVO.pHolidayCount}</span> 
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          <div class="row">
          	<div class="col-sm-6 pull-right">
          		<span class="pull-right">사용한 휴가일수: ${requestScope.useHoliday} </span> 
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          <div class="row">
          	<div class="col-sm-6 pull-right">
          		<span id="extraHoliday" class="pull-right">남은 휴가일수: ${sessionScope.staffVO.positionVO.pHolidayCount-requestScope.useHoliday}</span> 
          	</div>
          	<!-- ./col -->
          </div>
          <!-- /.row -->
          
          <table id="holiday-list" class="table table-hover table-bordered">
            <thead>
            <tr>
              <th>NO</th>
              <th>신청자</th>
              <th>휴가 일자</th>
              <th>신청 일자</th>
              <th>승인 여부</th>
              <th>비고</th>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="holiday" items="${requestScope.listVO.list}" >
            		<tr class="index" data-param="${holiday.hNo}">
            			<td>${holiday.hNo}</td>
            			<td>${holiday.staffVO.name}</td>
            			<td>${holiday.hStartDate} ~ ${holiday.hEndDate}</td>
            			<td>${holiday.hRegDate}</td>
            			<td>${holiday.hFlag}</td>
            			<td>${holiday.hReason}</td>
            		</tr>
            	</c:forEach>
            </tbody>
          </table>
          <c:set value="${requestScope.listVO.pagingBean }" var="bean"/>
          <div class="row">
          	<div class="col-xs-12 text-center">
          		<ul class="pagination">  
		<%--prevButton --%>
			<c:if test="${bean.isPreviousPageGroup()==true}">
				<li><a href="dispatcher?command=read_holiday&pageNo=${bean.getStartPageOfPageGroup()-1}">&laquo;</a></li>
			</c:if>
		<%--pageButton --%>
		<c:forEach var="page" begin="${bean.getStartPageOfPageGroup()}" end="${bean.getEndPageOfPageGroup()}">
			<c:choose>
			<c:when test="${bean.nowPage == page}"><li class="active"><a href="#">${page}</a></li></c:when>
			<c:otherwise>
				<li><a href="dispatcher?command=read_holiday&pageNo=${page}">${page}</a></li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--nextButton --%>
		<c:if test="${bean.isNextPageGroup()==true}">
			<li><a href="dispatcher?command=read_holiday&pageNo=${bean.getEndPageOfPageGroup()+1}">&raquo;</a></li>
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


<!-- condition script -->
<script>
$(document).ready(function(){
	var select = '${param.condition}';
	//rendering
	$( '#condition option' ).each(function() {
		if($( this ).val()==select){
			$( this ).prop('selected',true); 
		} else{
			$( this ).prop('selected',false);
		}
	});
	
	$( '#condition' ).change(function() {
		var condition=$(this).val();
		if(condition!=null && condition!=''){
			location.href='dispatcher?command=read_holiday&condition=' + condition;
		} else{
			location.href='dispatcher?command=read_holiday';
		}
	});
});
</script>


<%@ include file="detail_holiday.jsp" %>
<%@ include file="delete_holiday.jsp" %>
<%@ include file="deny_holiday.jsp" %>
    