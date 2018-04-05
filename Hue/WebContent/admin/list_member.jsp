<%@page import="model.StaffVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file ="../template/header.jsp" %>
 
  
  <%@ include file="../template/left.jsp" %>
  

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

		<%
			ArrayList<StaffVO> list = (ArrayList<StaffVO>) request.getAttribute("staffList");
		%>
		<table class="table table-hover">
			<tr>
				<th>No</th>
				<th>사원명</th>
				<th>이메일</th>
				<th>직책</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=list.get(i).getName()%></td>
				<td><%=list.get(i).getMail()%></td>
				<td><%=list.get(i).getPositionVO().getpName()%></td>
				<td><button type="button">수정</button>
					<button type="button">삭제</button></td>
			</tr>
			<%
				}
			%>

		</table>
	</div>
	
	<%@ include file="../template/footer.jsp"%>