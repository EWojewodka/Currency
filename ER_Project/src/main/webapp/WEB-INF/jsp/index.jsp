<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="/css/main.css">
<link
	href='http://fonts.googleapis.com/css?family=Corben&subset=latin,latin-ext'
	rel='stylesheet' type='text/css'>
<title>Currency</title>
</head>
<body>
	<div class="header">Waluty</div>
	<div id="right-nav-menu">
		<ul>
			<a href="/document/update"><li>Update documents</li></a>
			<a href="/charts/settings"><li>Show charts</li></a>
			<a href="/document/showAll"><li>Show documents</li></a>
			<a href="/history/all"><li>Show document's history</li></a>
			<a href="/history/last"><li>Show last update</li></a>
		</ul>
	</div>
	<div class="container">
		<c:forEach items="${model }" var="models">
				${models } </br>
		</c:forEach>
	</div>

</body>
</html>