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
    <a href="${pageContext.request.contextPath }/index.jsp"><b>H</b>ue</a>
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

      <c:choose>
      <%-- 회원가입 할때 --%>
      <c:when test="${empty sessionScope.staffVO.id}">
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
      
      <div id="checkIdResult" class="form-group has-feedback">
      </div>
      
      <div class="form-group has-feedback">
        <input type="text" class="form-control" name="id" id="id" placeholder="id" required="required">
        <span class="glyphicon glyphicon-star form-control-feedback"></span>
      </div>
      
       <div class="form-group has-feedback">
        <input type="password" class="form-control password" name="password" placeholder="Password" required="required">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control confirmPassword" placeholder="Retype password" required="required">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" name="mail" required="required">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      
      <div class="row">
      <div class="col-xs-4">
        <button type="submit" class="btn btn-primary btn-block btn-flat registerButton">회원가입</button>
      </div>
      <div class="col-xs-4">
       	<button type="button" class="btn btn-primary btn-block btn-flat">취소</button>
       <a href="${pageContext.request.contextPath }/login.jsp" class="text-center">로그인하러 가기</a>
      </div>
      </div>
      </form>
      </c:when>
      
      <c:otherwise>
      <%-- 개인정보 수정할 때 --%>
      <form action="${pageContext.request.contextPath}/dispatcher" method="post" enctype="multipart/form-data">
      <input type="hidden" name="command" value="update_user">
      
      <div id="image-holder" class="form-group has-feedback">
	  	<img id="profile" src="${pageContext.request.contextPath}${staffVO.imagePath}" class="thumb-image"/>
	  </div>
	  <div id="profile-border" class="form-group has-feedback">
	  	프로필 사진   
	  	<input id="profileUpload" type="file" name="image" accept="image/*"/> 
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
        <input type="password" class="form-control password" required="required" placeholder="Password" name="password" value="${staffVO.password}">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control confirmPassword" required="required" placeholder="Retype password">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback update-hidden-box">
        <p>${staffVO.positionVO.pName}
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
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
   	   <button type="button" class="btn btn-primary btn-block btn-flat">취소</button>
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

<script type="text/javascript">
$(document).ready(function(){
	$("#id").keyup(function(){
		var idValue=$(this).val();
		console.log(idValue);
		if(idValue.length<4||idValue.length>10){
			$("#checkIdFlag").val("false");
			$("#checkIdResult").html("아이디는 4자이상 10자 이하만 가능!").css("color","red");
		}else{
			$.ajax({
				type:"get",
				dataType:"json",   
				url:"${pageContext.request.contextPath}/dispatcher?command=check_id",
				data:$("#registerForm").serialize(),
				success:function(data){
					if(data.flag=="fail"){
						$("#checkIdFlag").val("false");
						$("#checkIdResult").html("사용불가!").css("color","red");
					}else{
						$("#checkIdFlag").val("true");
						$("#checkIdResult").html("사용가능!").css("color","blue");
						$("#id").css("border-color","#d2d6de");
					}//else
				}//success
			});//ajax						
		}//else
	})//$("#id").keyup(function()
	
	$(".registerButton").click(function(){
		var checkIdValue=$("#checkIdFlag").val();
		var password=$(".password").val();
		var confirmPassword=$(".confirmPassword").val();
		if(checkIdValue=="false"){
			$("#id").css("border-color","red");
			$("#id").focus();
			return false;
		}else if(password!=confirmPassword){
			$(".password").addClass('update-error-box');
			$(".password").focus();
			alert('패스워드가 일치하지 않습니다.');
			return false;
		}else if(password==confirmPassword){
			$(".password").removeClass('update-error-box');
			$(".password").addClass('input-normal-box');
		}
	})//registerButton
});//ready
</script>

</body>
</html>
