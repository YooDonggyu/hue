<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


     
      <!-- sidebar menu: : style can be found in sidebar.less -->
      
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
          <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-aqua-active">
            	
              <h3 class="widget-user-username">${staffVO.name}</h3>
              <h5 class="widget-user-desc">${staffVO.positionVO.pName}</h5>
            </div>
            <div class="widget-user-image">
              <img class="img-circle" src="${pageContext.request.contextPath}${staffVO.imagePath}" alt="User Avatar">
            </div>
            <div class="box-footer">
              <div class="row">
                <div class="col-sm-6 border-right">
                  <div class="description-block">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=read_user" style="color:black">MyPage</a>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                
                <div class="col-sm-6">
                  <div class="description-block">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=logout" style="color:black">로그아웃</a>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.widget-user -->

      
      <ul class="sidebar-menu" data-widget="tree">
      
      
      
        <li class="header">Menu</li>
        <c:choose>
        <c:when test="${sessionScope.staffVO.positionVO.pName ==  '관리자' }">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>직원 </span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../../index.html"><i class="fa fa-circle-o"></i> 직원 목록</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i> TEMP</a></li>
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
            <li><a href="${pageContext.request.contextPath}/dispatcher?command=read_holiday"><i class="fa fa-circle-o"></i> 휴가 목록</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i> 휴가 신청</a></li>
          </ul>
        </li>
        </c:otherwise>
        </c:choose>
       
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

