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
	
	<div class="centered">
		<table class="keyvaluetable" style=" background-repeat:no-repeat; width:450px;margin:0;" cellpadding="0" cellspacing="0" border=1px>
		<tr>
		    <th>Key</th>
		    <th>Value</th>
		</tr>
		<c:forEach items="${keyValue}" var="keyValue">
			<tr>
				<td>
				<c:out value="${keyValue.key}" />
				</td>
				<td>
				<c:out value="${keyValue.value}" />
				</td>
			</tr>
		</c:forEach>
		
		</table>
		
	</div>
	
	

	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>