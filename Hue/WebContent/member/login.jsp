<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Log in</title>
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
  <!-- <link rel="stylesheet" href="../../plugins/iCheck/square/blue.css"> -->

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo"> 	 
    <a href="#"><b>H</b>ue</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form  id="loginForm" >
    	<input type="hidden" name="command" value="login">
      	<div class="form-group has-feedback">
	        <input type="text" name="staffId" id="staffId" class="form-control" placeholder="Id">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
     	 <div class="form-group has-feedback">
	        <input type="password" name="staffPw" id="staffPw" class="form-control" placeholder="Password">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
      	<div id="loginFailInfo"></div>
	    <div class="row">
    <!--   <div class="col-xs-">
		        <div class="checkbox icheck">
		          <label>
		            <input type="checkbox"> Remember Me
		          </label>
		        </div>
	        </div> -->
        <!-- /.col -->
        <div class="col-xs-12">
          <button type="button" id="loginBtn" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

  <!--   <div class="social-auth-links text-center">
      <p>- OR -</p>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
        Facebook</a>
      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
        Google+</a>
    </div> -->
    <!-- /.social-auth-links -->
    
    <a href="form_user.jsp" class="text-center">Register a new membership</a>
    <a href="#">I forgot my password</a><br>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<!-- <script src="../../plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script> -->
<!-- loginBtn -->
  <script type="text/javascript">
	$(document).ready(function(){
		$("#loginBtn").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/dispatcher",
				data:$("#loginForm").serialize(),
				success:function(data){
					if(data=="fail"){
						$("#loginFailInfo").html("아이디와 비밀번호를 확인하세요.").css("color","red");
					}else{
						location.href="${pageContext.request.contextPath}/dispatcher?command=login_view";
					}
				}	
			});//ajax
		
		});//click
		$("#loginForm").keypress(function(e){
			if(e.keyCode===13){
				$("#loginBtn").click();
			}
		});
		
	});//ready
</script>
</body>
</html>
    