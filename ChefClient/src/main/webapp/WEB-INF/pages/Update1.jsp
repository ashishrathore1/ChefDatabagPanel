<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome</h3>
	
	
	<form name='updateDataBagItem'
		  action="updateDataBagItem" method='POST'>
		<table>
			<tr>
				<td>Key:</td>
				<td>
					<select name="itemKey">
					  <c:forEach  items="${keyValue}" var="keyValue">
					  	 <option value="${keyValue.key}">${keyValue.key}</option>
					  </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Value:</td>
				<td><input type='text' name='itemValue'></td>
			</tr>
			
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
		  </table>
		
		  <input type="hidden" name="databag" value="${databag}">
		  <input type="hidden" name="item" value="${item}">
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
</body>
</html>