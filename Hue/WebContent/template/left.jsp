<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar" style="height: auto;">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}${staffVO.imagePath}" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info" >
				<div>${staffVO.name}</div>
				<div>${staffVO.positionVO.pName}</div>
				<a href="${pageContext.request.contextPath}/dispatcher?command=read_user" style="color: #A4A4A4">MyPage</a>
				<a href="${pageContext.request.contextPath}/dispatcher?command=logout" style="color: #A4A4A4">로그아웃</a>
				<%-- <span style="color: #A4A4A4 ;font-size: 9px ">남은 휴가일수 : ${requestScope.hCount }</span>	 --%>			
				<br><br>
				<span id="restHolidayCnt" style="color: #A4A4A4 ;font-size: 9px ">남은 휴가일수 : ${sessionScope.staffVO.positionVO.pHolidayCount-requestScope.useHoliday}</span>				
			</div>
		</div>
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">Menu</li>
          <c:choose>
            <c:when test="${sessionScope.staffVO.positionVO.pName == '관리자'}">
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>직원 </span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i>직원 목록</a></li>
                <li><a href="#"><i class="fa fa-circle-o"></i>삭제 목록</a></li>
              </ul>
            </li>
            </c:when>
            <c:otherwise>
              <li class="treeview">
          	    <a href="#">
                  <i class="fa fa-dashboard"></i> <span>휴가 </span>
                  <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i>
                  </span>
                </a>
                <ul class="treeview-menu">
                  <li><a href="${pageContext.request.contextPath}/dispatcher?command=read_holiday">
            	    <i class="fa fa-circle-o"></i>휴가 목록</a></li>
                  <li><a role="button" data-toggle="modal" data-staff-id="${sessionScope.staffVO.id}" href="#update_holiday_modal">
            	    <i class="fa fa-circle-o"></i>휴가 신청</a></li>
                </ul>
              </li>
            </c:otherwise>
          </c:choose>
        </ul>
	</section>
  <!-- /.sidebar -->
</aside>

<%@ include file="../holiday/form_holiday.jsp" %>
