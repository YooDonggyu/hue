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
  <!-- iCheck -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
  <!-- 통합css 추가 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/home.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body class="hold-transition register-page">
<div class="register-box">
  <div class="register-logo">
  	<c:choose>
  	<c:when test="${empty sessionScope.staffVO}">
  	 <a href="${pageContext.request.contextPath }/member/login.jsp"><b>H</b>ue</a>
  	</c:when>
  	<c:otherwise>
    <a href="${pageContext.request.contextPath }/dispatcher?command=login_view"><b>H</b>ue</a>
    </c:otherwise>
    </c:choose>
  </div>

  <div class="register-box-body">
  <c:choose>
  	<c:when test="${empty sessionScope.staffVO}">
      <p class="login-box-msg">회원가입</p>
    </c:when>
    
    <c:otherwise>
      <p class="login-box-msg">개인정보 수정</p>	
    </c:otherwise>
   </c:choose>

    <c:choose>
    <%-- 회원가입 할때 --%>
      <c:when test="${empty sessionScope.staffVO}">
        <form action="${pageContext.request.contextPath}/dispatcher" method="post" id="registerForm">
          <input type="hidden" name="command" value="create_user">
          <input type="hidden" id="checkIdFlag" value="false">
      
          <div class="form-group has-feedback">
      	    <input type="hidden" name="image" value="/upload/image/default.png">
          </div>
      
          <div class="form-group has-feedback">
            <input type="text" class="form-control" name="name" placeholder="name" required="required">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="text" class="form-control" name="id" id="id" placeholder="id" required="required">
            <span class="glyphicon glyphicon-star form-control-feedback"></span>
          </div>
      
          <div id="checkIdResult" class="form-group has-feedback">
          </div>
      
          <div class="form-group has-feedback">
            <input type="password" class="form-control password" name="password" placeholder="Password" required="required">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="password" class="form-control confirmPassword" name="password_check" placeholder="Retype password" required="required">
            <span class="glyphicon glyphicon-saved form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="mail" required="required">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
      
          <div class="row">
            <div class="col-xs-6">
              <button type="submit" class="btn btn-primary btn-block btn-flat registerButton">회원가입</button>
          </div>
          <div class="col-xs-6">
       	    <button type="button" class="btn btn-primary btn-block btn-flat logincancelButton">취소</button>
          </div>
        </div>
      </form>
    </c:when>
      
    <c:otherwise>
    
      <%-- 개인정보 수정할 때 --%>
      <form action="${pageContext.request.contextPath}/dispatcher" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="update_user">
      
          <div id="image-holder" class="form-group has-feedback  text-center">
	  	    <label for="profileUpload"><img id="profile" src="${pageContext.request.contextPath}${staffVO.imagePath}" alt="프로필 사진" title="사진 업로드" class="thumb-image"/></label>
	      </div>
	      <div id="profile-border" class="form-group has-feedback">
	  	    <input id="profileUpload" type="file" name="image" accept="image/*"/>
   	      </div>
      
          <div class="form-group has-feedback update-hidden-box">
            <input type="hidden" class="form-control" placeholder="name" name="name" value="${staffVO.name}">
              <p>${staffVO.name}
              <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback update-hidden-box">
            <input type="hidden" class="form-control" placeholder="id" id = "id" name="id" value="${staffVO.id}">
              <p>${staffVO.id}
           	 <input type="hidden" id="hideId" value="${staffVO.id}">
              <span class="glyphicon glyphicon-star form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="password" class="form-control password" name="password" required="required" placeholder="Password" value="${staffVO.password}">
              <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="password" class="form-control confirmPassword" name="password_check" required="required" placeholder="Retype password">
              <span class="glyphicon glyphicon-saved form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback update-hidden-box">
            <p>${staffVO.positionVO.pName}
            <span class="glyphicon glyphicon-briefcase form-control-feedback"></span>
          </div>
      
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" required="required" name="mail" value="${staffVO.mail}">
              <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
        <div class="row">
          <div class="col-xs-4">
            <button type="submit" class="btn btn-primary btn-block btn-flat registerButton">수정</button>
          </div>
          <div class="col-xs-4">
   	        <button type="button" class="btn btn-primary btn-block btn-flat cancelButton">취소</button>
          </div>
          <div class="col-xs-4">
          	<!-- <a data-toggle="modal" href="#modal-default" class="btn btn-block btn-danger deleteButton" >탈퇴</a> -->
          	<c:if test="${staffVO.positionVO.pName != '관리자' }">
            	<!-- 관리자이면 탈퇴 불가 -->
            	<button type="button" class="btn btn-danger btn-block btn-flat deleteButton" value="${staffVO.id}">탈퇴</button>
            </c:if>
   	      </div>
   	    </div>
   	  </form>
    </c:otherwise>
   </c:choose>
  </div>
 <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<!-- imageView -->
<script src="${pageContext.request.contextPath}/dist/js/imageUpload.js"></script>
<!-- formCheck -->
<script src="${pageContext.request.contextPath}/dist/js/registerFormCheck.js"></script>
<!-- deleteStaff -->
<script src="${pageContext.request.contextPath}/dist/js/deleteStaff.js"></script>

<%@ include file="delete_user.jsp" %>
</body>
</html>
