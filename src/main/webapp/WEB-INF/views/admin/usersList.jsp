<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">

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

<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/font-awesome/css/font-awesome.css" />

<script type="text/javascript"
	src="/Spring-Security/webjars/popper.js/dist/umd/popper.js"></script>



<script type="text/javascript"
	src="/Spring-Security/webjars/datatables/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/datatables/css/dataTables.jqueryui.css" />
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/datatables/css/dataTables.bootstrap4.css" />



<!-- Custom JS -->
<script type="text/javascript"
	src="<c:url value="/resources/js/usersList.js" />"></script>

<style type="text/css">
#success_message {
	display: none;
}

.errorMessageStyle {
	color: #a94442;
	font-size: 85%;
	display: block;
	margin-top: 5px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
<body>
	<div class="container-fluid">
		<table
			class="table table-sm table-bordered table-hover table-responsive"
			id="listOfRegisteredUsers">
			<thead>
				<tr class="thead table-success">
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>User Password</th>
					<th>Gender</th>
					<th>User Email</th>
					<th>User Country</th>
					<th>User State</th>
					<th>User Phone</th>
					<th>Action</th>
				</tr>
			</thead>
			<tfoot>
				<tr class="thead table-success">
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>User Password</th>
					<th>Gender</th>
					<th>User Email</th>
					<th>User Country</th>
					<th>User State</th>
					<th>User Phone</th>
					<th>Action</th>
				</tr>
			</tfoot>
		</table>
	</div>
</body>

</body>

</html>