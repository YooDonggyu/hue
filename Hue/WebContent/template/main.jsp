<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file ="header.jsp" %>
  <%@ include file="left.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

<c:import url="${requestScope.url} }"/>

   <%--  <a href="${pageContext.request.contextPath}/dispatcher?command=read_holiday">목록 기기</a> --%>

  </div>
  <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@ include file = "footer.jsp" %>
