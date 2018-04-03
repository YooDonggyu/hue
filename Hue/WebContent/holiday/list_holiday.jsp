<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<!-- DataTables -->
<%@ include file ="/template/header.jsp" %>
  <%@ include file="/template/left.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
     <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title">휴가 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
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
              	<div class="col-xs-12">
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
  </div>
  <!-- /.content-wrapper -->          
</div>

<%@ include file = "/template/footer.jsp" %>
<%@ include file="detail_holiday.jsp" %>
<%@ include file="delete_holiday.jsp" %>
    