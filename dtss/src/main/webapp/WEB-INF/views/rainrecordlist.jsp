<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>红包记录</title>
</head>
<body>
<table>
<h1>红包雨记录</h1>

<c:forEach items="${records}" var="temp">
<tr>
<td><c:out value="${temp.username}"/>&nbsp;&nbsp;&nbsp;</td>

<td><c:out value="${temp.luck_number}"/>&nbsp;&nbsp;&nbsp;</td>

<td><c:out value="${temp.round}"/></td>
</tr>



</c:forEach>
</table>
</body>
</html>