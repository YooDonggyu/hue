<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Hue Project</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
  <!-- fullCalendar -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/fullcalendar/dist/fullcalendar.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/fullcalendar/dist/fullcalendar.print.min.css" media="print">
  <!-- DataTable -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- jQuery 3 -->
  <script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>
  <!-- Bootstrap 3.3.7 -->
  <script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="${pageContext.request.contextPath}/bower_components/jquery-ui/jquery-ui.min.js"></script>
  <!-- SlimScroll -->
  <script src="${pageContext.request.contextPath}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
  <!-- FastClick -->
  <script src="${pageContext.request.contextPath}/bower_components/fastclick/lib/fastclick.js"></script>
  <!-- AdminLTE App -->
  <script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
  <!-- DataTables -->
  <script src="${pageContext.request.contextPath}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
  <script src="${pageContext.request.contextPath}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
  <!-- SlimScroll -->
  <script src="${pageContext.request.contextPath}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
  <!-- 휴가 신청,상세보기,삭제 Script -->
  <script src="${pageContext.request.contextPath}/dist/js/pageScript.js" ></script>
  <!-- fullCalendar -->
  <script src="${pageContext.request.contextPath}/bower_components/moment/moment.js"></script>
  <script src="${pageContext.request.contextPath}/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
</head>

<c:choose>
  <c:when test="${sessionScope.staffVO != null}">
		
  <header class="main-header">
    <!-- Logo --> 
      <a href="${pageContext.request.contextPath}/dispatcher?command=login_view" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>休</b></span>
            <!-- logo for regular state and mobile devices -->
              <span class="logo-lg"><b>H</b>ue</span>
        </a>
        
    <!-- Header Navbar: style can be found in header.less -->
      <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${pageContext.request.contextPath}${staffVO.imagePath}" class="user-image" alt="User Image">
                <span class="hidden-xs">${staffVO.name} ${staffVO.positionVO.pName}님</span>
                <input type="hidden" id="hideId" value="${staffVO.id}">
            </a>
        <ul class="dropdown-menu">
          <!-- User image -->
            <li class="user-header">
              <img src="${pageContext.request.contextPath}${staffVO.imagePath}" class="img-circle" alt="User Image">
                <p> ${staffVO.name}
                  <small>${staffVO.positionVO.pName}</small>
                </p>
            </li>
              
              <!-- Menu Footer-->
                <li class="user-footer">
                  <div class="pull-left">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=read_user" class="btn btn-default btn-flat">MyPage</a>
                  </div>
                  <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=logout" class="btn btn-default btn-flat">Sign Out</a>
                  </div>
                </li>
              </ul>
            </li>
          <!-- Control Sidebar Toggle Button -->
          </ul>
        </div>
      </nav>
    </header>
  </c:when>
  <c:otherwise>
    <jsp:forward page="/member/login.jsp"/>
  </c:otherwise>
</c:choose>

