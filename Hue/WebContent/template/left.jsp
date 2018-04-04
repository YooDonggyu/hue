<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- sidebar menu: : style can be found in sidebar.less -->

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar" style="height: auto;">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}${staffVO.imagePath}" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info" >
				<%-- <p>${staffVO.name}</p>
				<p>${staffVO.positionVO.pName}</p> --%>
				<a href="${pageContext.request.contextPath}/dispatcher?command=read_user" style="color: #A4A4A4">MyPage</a>
				<a href="${pageContext.request.contextPath}/dispatcher?command=logout" style="color: #A4A4A4">로그아웃</a>
				<br><br>
				<span style="color: #A4A4A4 ;font-size: 9px ">남은 휴가일수 :</span>				
			</div>
		</div>
		<!-- Widget: user widget style 1 -->
		<%-- <div class="box box-widget widget-user">
			<!-- Add the bg color to the header using any of the bg-* classes -->
			<div class="widget-user-header bg-aqua-active">

				<h3 class="widget-user-username">${staffVO.name}</h3>
				<h5 class="widget-user-desc">${staffVO.positionVO.pName}</h5>
			</div>
			<div class="widget-user-image">
				<img class="img-circle"
					src="${pageContext.request.contextPath}${staffVO.imagePath}"
					alt="User Avatar">
			</div>
			<div class="box-footer">
				<div class="row">
					<div class="col-sm-6 border-right">
						<div class="description-block">
							<a
								href="${pageContext.request.contextPath}/dispatcher?command=read_user"
								style="color: black">MyPage</a>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->

					<div class="col-sm-6">
						<div class="description-block">
							<i class="fa fa-laptop"></i> <a
								href="${pageContext.request.contextPath}/dispatcher?command=logout"
								style="color: black">로그아웃</a> <i
								class="fa fa-angle-left pull-right"></i>
						</div>
						<!-- /.description-block -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
		</div> --%>
		<!-- /.widget-user -->


		<ul class="sidebar-menu" data-widget="tree">



			<li class="header">Menu</li>

			<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
					<span>휴가</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="../dispatcher?command=create_holiday"><i
							class="fa fa-circle-o"></i> 휴가신청</a></li>
					<li><a href="/member/list_holiday.jsp"><i
							class="fa fa-circle-o"></i> 휴가목록</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-table"></i>
					<span>관리</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="../tables/simple.html"><i
							class="fa fa-circle-o"></i>직원관리</a></li>
					<li><a href="../tables/data.html"><i
							class="fa fa-circle-o"></i>휴가관리</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>

