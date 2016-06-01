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
	
	<div class="container">
		<h1 class="well">Data Items</h1>
		<div class="col-lg-12 well">
			<div class="row">
				<form name='updateDataBagItem'  action="updateDataBagItem" method='POST'>
					<div class="col-sm-12">
						<label>Key:</label>
						<select class="form-control" name="itemKey">
						    <option>--select-Key</option>
						    <c:forEach items="${keyValue}" var="keyValue">
								<option value="${keyValue.key}">${keyValue.key}</option>
							</c:forEach>
						</select> <br> <br>
						<div class="form-group">
							<label class="sr-only">Value</label> <input type="text"
								name="itemValue" placeholder="Value..."
								class="form-password form-control">
						</div>
						<br/>
						<br/>
						<input type="hidden" name="databag" value="${databag}">
		 				<input type="hidden" name="item" value="${item}">
						<button name="submit" type="submit" class="btn btn-primary" >Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	

	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>