<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- "<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"   -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/mycss/style.css" />" type="text/css" media="all" />
<link rel="stylesheet" href="<c:url value="/resources/mycss/style1.css" />" type="text/css" media="all" />
<link rel="stylesheet" href= "<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" >
</head>
<body>
	<jsp:include page="bodyHeader.jsp"></jsp:include>
	
	<div class="container" align="center">                
	   <img src="<c:url value="/resources/myimages/pic-chef-logo.png" />" class="img-responsive" alt="Cinque Terre" width="250" height="236"> 
		<br/>
		<br/>
		<form name='loginForm' action="welcome" method='POST' class="login-form">
			<button type="submit" class="btn">DataBags</button><br>
			<br>
			<h4>To get access to this tool, please contact devops@snapdeal.com</h4>
		</form>
	</div>
	

	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>