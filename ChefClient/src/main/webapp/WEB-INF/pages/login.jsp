<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CHEF DATABAG </title>

<!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"  >
        <link rel="stylesheet" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>"  >
		<link rel="stylesheet" href="<c:url value="/resources/css/form-elements.css"/>" >
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" >		
</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<!-- <h1>
							<strong>CHEF</strong> Login Form
						</h1> -->
						<div class="description">
							<!-- <p>
								TRUST IS LIKE A PAPER ONCE IT'S CRUMPLED IT CAN'T BE PERFECT
							</p> -->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h2>Chef Databag Panel</h2>
								<p>Enter your Snapdeal AD username (without "@snapdeal.com") and password to login</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="checkUser" method='POST' class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label> <input
										type="text" name="username" placeholder="Username"
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label> <input
										type="password" name="password" placeholder="Password"
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn">Sign in!</button>
																
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>" ></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.backstretch.min.js"/>"></script>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>


	<%-- "<c:url value="/resources/css/main.css" />"   "<c:url value="/resources/js/scripts.js"/>"   --%>
	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>
</html>
