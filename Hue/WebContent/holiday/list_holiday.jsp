<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<!-- DataTables -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
              <table id="holoday-list" class="table table-hover table-bordered">
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
                			<td>${holiday.hNo}</td> <td>${holiday.staffVO.name}</td> <td>${holiday.hStartDate} ~ ${holiday.hEndDate}</td> <td>${holiday.hRegDate}</td> <td>${holiday.hFlag}</td> <td>${holiday.hReason}</td>
                		</tr>
                	</c:forEach>
                </tbody>
              </table>
              <c:set value="${requestScope.listVO.pagingBean }" var="bean"/>
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

<%@ include file = "../template/footer.jsp" %>

<!-- DataTables -->
<script src="${pageContext.request.contextPath}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- page script -->
<script src="${pageContext.request.contextPath}/dist/js/pageScript.js"></script>
    