<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"
%><!DOCTYPE html>
<html lang="en">
<head>
<title>Material Admin - Login</title>
<!-- BEGIN META -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="your,keywords">
<meta name="description" content="Short explanation about this website">
<!-- END META -->

<!-- BEGIN STYLESHEETS -->
<link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300italic,400italic,300,400,500,700,900" />
<link type="text/css" rel="stylesheet" href="/css/theme-default/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="/css/theme-default/materialadmin.css" />
<link type="text/css" rel="stylesheet" href="/css/theme-default/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="/css/theme-default/material-design-iconic-font.min.css" />
<!-- END STYLESHEETS -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script type="text/javascript" src="/libs/utils/html5shiv.js"></script>
<script type="text/javascript" src="/libs/utils/respond.min.js"></script>
<![endif]-->

<!-- BEGIN JAVASCRIPT -->
<script integrity="sha384-Pn+PczAsODRZ2PiGg0IheRROpP7lXO1NTIjiPo6cca8TliBvaeil42fobhzvZd74" crossorigin="anonymous" type="text/javascript" src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script integrity="sha384-9tt8DlZQhE63eBuKml9tnMclfDeo/8/wstzUrBQStZZkCCvwfw78IiV+r9o600g2" crossorigin="anonymous" type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script integrity="sha384-4Kp4aQ6UNeqsJ/ithPcxYnnIGt/QJJ64J9QtfDAJZUTaePAIPm9aaBdu7Gw84oGs" crossorigin="anonymous" type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/libs/spin.js/spin.min.js"></script>
<script integrity="sha384-jSg1dzUV3EsJKaORytph0yWTvX+68V4BAhlKR2pWCswsaPWrc19OgfGGzYcU0nUo" crossorigin="anonymous" type="text/javascript" src="http://cdn.staticfile.org/autosize.js/1.18.17/jquery.autosize.min.js"></script>
<script type="text/javascript" src="/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
<script type="text/javascript" src="http://www.ohdave.com/rsa/BigInt.js"></script>
<script type="text/javascript" src="http://www.ohdave.com/rsa/Barrett.js"></script>
<script type="text/javascript" src="http://www.ohdave.com/rsa/RSA.js"></script>
<script type="text/javascript" src="/core/cache/63d0445130d69b2868a8d28c93309746.js"></script>
<script type="text/javascript" src="/core/demo/demo.js"></script>
<!-- END JAVASCRIPT -->

<script type="text/javascript">

var modulus  = "${rsaModulus}";
var exponent = "${rsaExponent}";

function encryptPassword(form)
{
	if (modulus == "" || exponent == "")
	{
		window.alert("RSA密钥对未准备好");

		return false;
	}

	setMaxDigits(130);

	var key = new RSAKeyPair(exponent, "", modulus);
	var password = $("#password", form).val();
	$("#encrypted", form).val(encryptedString(key, password));

	return true;
}

</script>
</head>

<body class="menubar-hoverable header-fixed">
<!-- BEGIN LOGIN SECTION -->
<section class="section-account">
<div class="img-backdrop" style="background-image: url('/images/img16.jpg')"></div>
<div class="spacer"></div>
<div class="card contain-sm style-transparent"><div class="card-body"><div class="row">
<div class="col-sm-6">
<br /><span class="text-lg text-bold text-primary">MATERIAL ADMIN</span>
<br /><br /><c:if test="${formLoginEnable}">
<form class="form floating-label" action="${loginUrl}" accept-charset="UTF-8" method="post" onsubmit="return encryptPassword(this);">
							<div class="form-group">
								<input type="text" class="form-control" id="username" name="${usernameParameter}">
								<label for="username">Username</label>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="password">
								<input type="hidden" id="encrypted" name="${passwordParameter}" />
								<label for="password">Password</label>
								<p class="help-block"><a href="#">Forgotten?</a></p>
							</div>
							<br/>
							<div class="row">
								<div class="col-xs-6 text-left">
									<div class="checkbox checkbox-inline checkbox-styled">
										<label>
											<input type="checkbox" name="${formLoginRememberMeParameter}" /> <span>Remember me</span>
										</label>
									</div>
								</div><!--end .col -->
								<div class="col-xs-6 text-right"><s:csrfInput />
									<button class="btn btn-primary btn-raised" type="submit">Login</button>
								</div><!--end .col -->
							</div><!--end .row -->
</form></c:if></div>
<div class="col-sm-5 col-sm-offset-1 text-center">
<br /><br /><h3 class="text-light">No account yet?</h3>
<a class="btn btn-block btn-raised btn-primary" href="#">Sign up here</a> 
<br /><br /><h3 class="text-light">or</h3>
<p><a href="#" class="btn btn-block btn-raised btn-info"><i class="fa fa-facebook pull-left"></i>Login with Facebook</a></p>
<p><a href="#" class="btn btn-block btn-raised btn-info"><i class="fa fa-twitter pull-left"></i>Login with Twitter</a></p>
</div></div></div></div></section>
<!-- END LOGIN SECTION -->
</body>
</html>