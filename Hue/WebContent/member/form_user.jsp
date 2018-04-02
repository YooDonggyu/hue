<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Registration Page</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../plugins/iCheck/square/blue.css">
  <!-- 통합css 추가 -->
  <link rel="stylesheet" href="../css/home.css">
  
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
    <a href="../../index2.html"><b>H</b>ue</a>
  </div>

  <div class="register-box-body">
  <c:choose>
  <c:when test="${empty sessionScope.staffVO.id}">
    <p class="login-box-msg">회원가입</p>
    </c:when>
    <c:otherwise>
    <p class="login-box-msg">개인정보 수정</p>	
    </c:otherwise>
    </c:choose>

    <form action="../../index.html" method="post">
      
      <c:choose>
      <%-- 회원가입 할때 --%>
      <c:when test="${empty sessionScope.staffVO.id}">
      
      <div class="form-group has-feedback">
      	<input type="hidden" name="image" value="/upload/image/default.png">
      </div>
      
       <div class="form-group has-feedback">
        <input type="text" class="form-control" name="name" placeholder="name">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="text" class="form-control" name="id" placeholder="id">
        <span class="glyphicon glyphicon-star form-control-feedback"></span>
      </div>
      
       <div class="form-group has-feedback">
        <input type="password" class="form-control" name="password" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Retype password">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" name="mail">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      
      </c:when>
      
      <c:otherwise>
      <%-- 개인정보 수정할 때 --%>
      
      <div class="form-group has-feedback">
      	<img id="myImg" src="${pageContext.request.contextPath}${staffVO.imagePath}">
        <input type="file" name="image">
      </div>
      
      <div class="form-group has-feedback update-hidden-box">
        <input type="hidden" class="form-control" placeholder="name" name="name" value="${staffVO.name}">
        <p>${staffVO.name}
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback update-hidden-box">
        <input type="hidden" class="form-control" placeholder="id" name="id" value="${staffVO.id}">
        <p>${staffVO.id}
        <span class="glyphicon glyphicon-star form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Password" name="password" value="${staffVO.password}">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Retype password">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback update-hidden-box">
        <input type="hidden" class="form-control" name="position" value="${staffVO.positionVO.pName}">
        <p>${staffVO.positionVO.pName}
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" name="mail" value="${staffVO.mail}">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      
      </c:otherwise>
      </c:choose>
      
      <div class="row">
	      <c:choose>
	      	<c:when test="${empty sessionScope.staffVO.id }">
	      	<div class="col-xs-4">
	          <button type="submit" class="btn btn-primary btn-block btn-flat">회원가입</button>
	        </div>
	      	</c:when>
	      	<c:otherwise>
	      	 <div class="col-xs-4">
	          <button type="submit" class="btn btn-primary btn-block btn-flat">수정</button>
	          </div>
	      	</c:otherwise>
	      </c:choose>
         <div class="col-xs-4">
         	<button type="button" class="btn btn-primary btn-block btn-flat">취소</button>
         </div>
      </div>
    </form>

    <a href="login.jsp" class="text-center">로그인하러 가기</a>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="../plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
