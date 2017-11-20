<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Support for IE recent version(Google Query) -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<!-- To ensure proper rendering and touch zooming on mobile devices -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">
<title><tiles:getAsString name="title" /></title>

<!-- Always Load JQUERY before loading the Bootstrap -->
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery/jquery.min.js"></script>

<script type="text/javascript"
	src="/Spring-Security/webjars/bootstrap/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/bootstrap/css/bootstrap.css">

</head>
<body>
	<header id="header">
		<tiles:insertAttribute name="header" />
	</header>

	<section id="body">
		<tiles:insertAttribute name="body" />
	</section>

	<section id="footer">
		<tiles:insertAttribute name="footer" />
	</section>
</body>
</html>