<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Any Tracking related stuff or CSS or JS content must load in HEAD section -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Encoding -->
<meta charset="utf-8">
<!-- Support for IE recent version(Google Query) -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Title of a Page -->
<title>Course Registration Dashboard</title>
<meta name="description"
	content="This page holds information of different Courses available which can be monitored by ADMIN">
<!-- To ensure proper rendering and touch zooming on mobile devices -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">

<!-- Any JavaScript is advised to place at the end of the document so the pages load faster -->
<!-- jQuery library -->
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/jquery-ui/jquery-ui.css">

<script type="text/javascript"
	src="/Spring-Security/webjars/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript"
	src="/Spring-Security/webjars/bootstrapvalidator/js/bootstrapValidator.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/bootstrap/css/bootstrap.css">

<script type="text/javascript"
	src="/Spring-Security/webjars/tablesorter/js/jquery.tablesorter.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/tablesorter/css/theme.default.css" />

<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/font-awesome/css/font-awesome.css" />


<style type="text/css">
</style>
</head>
<body>
	<!-- begin Header -->
	<div id="header">

		<!-- begin  Navigation bar-->
		<nav class="navbar navbar-light navbar-fixed-top"
			style="background-color: #e3f2fd;">

			<!-- Hamburger Menu starts-->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#adminNavbarToggler" aria-controls="adminNavbarToggler"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Hamburger Menu ends-->

			<!-- begin nav bar links -->
			<div class="collapse navbar-collapse" id="adminNavbarToggler">

				<!-- begin Nav bar Brand -->
				<div class="navbar-header">
					<a href="#" class="navbar-brand"> <img
						src="https://v4-alpha.getbootstrap.com/assets/brand/bootstrap-solid.svg"
						width="30" height="30" class="d-inline-block align-top" alt="">
						<b>Course Admin</b>
					</a>
				</div>
				<!-- end Nav bar Brand -->

				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li>
						<form class="form-inline my-2 my-lg-0">
							<input class="form-control mr-sm-2" type="text"
								placeholder="Search">
							<button class="btn btn-outline-success my-2 my-sm-0"
								type="submit">Search</button>
						</form>
					</li>
				</ul>
			</div>
			<!-- end nav bar links -->
		</nav>
		<!-- end Navigation bar -->
	</div>
	<!-- End Header -->
</body>
</html>