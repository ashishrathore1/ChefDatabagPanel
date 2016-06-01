<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome</h3>

	<a href="Update">Update Key Value</a>
	
	<form name='viewDataBagItem'
		  action="viewDataBagItem" method='POST'>
		<table>
			<tr>
				<td>Item:</td>
				<td>
					<select name="item">
						<c:forEach items="${allDataBagItems}" var="dataItems">
								<option value="${dataItems}">${dataItems}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan='2'>
				<input name="view" type="submit" value="view" />
				<input name="update" type="submit" value="update" onclick="javascript: form.action='viewDataBagItemKeyValue';"/>
				</td>
			</tr>
		  </table>
		  <input type="hidden" name="databag" value="${databag}">
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
</body>
</html>