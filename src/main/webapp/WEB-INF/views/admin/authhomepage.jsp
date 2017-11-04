<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/jquery-ui/jquery-ui.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>


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

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>
<title>Super Admin Dashboard</title>


<style type="text/css">
header, footer {
	color: white;
	background-color: lightgreen;
}

nav ul li {
	display: inline;
}

section {
	color: black;
	background-color: white;
}

aticle {
	color: grey;
	background-color: green;
}

aside {
	color: pink;
	background-color: cyan;
}

address {
	color: red;
	background-color: yello;
}

/* Highlight the table rows: Even row in pink, odd row in cyan 
#adminsInfoList tbody tr:nth-child(even){
	background: pink;
	
}
#adminsInfoList tbody tr:nth-child(odd){
	background: lightgreen;
}
*/
.table-bordered thead {
	border: 3px solid green !important;
	padding: auto !important;
	margin: auto !important;
}

.table-bordered tbody {
	border: 3px solid orange !important;
	padding: auto !important;
	margin: auto !important;
}

.table-bordered th {
	border: 2px solid green !important;
	padding: auto !important;
	margin: auto !important;
}

.table-bordered td {
	border: 2px solid black !important;
	padding: auto !important;
	margin: auto !important;
}

.form-group {
	margin-right: 10px !important;
}
</style>

<script src="/Spring-Security/resources/js/authenticationUsers.js"></script>

</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<h2 class="navbar-header">Header</h2>
			<ul class="">
				<li class="navbar-item">1</li>
				<li class="navbar-item">2</li>
				<li class="navbar-item">3</li>
			</ul>
		</nav>
	</header>

	<div class="container jumbotron"
		style="margin-top: 20px; margin-bottom: 20px">
		<h2 style="text-align: left; margin-bottom: 20px; margin-top: -40px">Add/Update
			Admin Authentication</h2>
		<form class="form-inline" name="saveNewAdmin">
			<div class="form-group">
				<input id="adminName" name="adminName" type="text"
					class="form-control" placeholder="search for admin.."
					list="usersList" required="required">
				<datalist id="usersList">
					<c:forEach items="${registeredUsers}" var="eachUser">
						<option label="${eachUser.firstName} ${eachUser.lastName}"
							value="${eachUser.id}" />
					</c:forEach>
				</datalist>
			</div>
			<div class="form-group">
				<label class="custom-control custom-radio"> <input
					id="radio1" name="adminOrUser" type="radio"
					class="custom-control-input" value="APPLICATION_ADMIN" checked>
					<span class="custom-control-indicator"></span> <span
					class="custom-control-description">Admin</span>

				</label> <label class="custom-control custom-radio"> <input
					id="radio2" name="adminOrUser" type="radio"
					class="custom-control-input" value="APPLICATION_USER"> <span
					class="custom-control-indicator"></span> <span
					class="custom-control-description">User</span>
				</label>
			</div>
			<div class="form-group">
				<button id="saveNewAdminOrUser" name="saveNewAdminOrUser"
					class="btn btn-danger" type="button" value="Search">Submit</button>
			</div>
		</form>
		<p id="invalidUser"
			style="color: red; text-align: left; display: none;">Please enter
			a valid User</p>
	</div>

	<div class="container jumbotron">
		<h2 style="text-align: center; margin-bottom: 20px; margin-top: -50px">List
			of Admins with their Roles</h2>
		<table
			class="tablesorter table table-bordered table-hover table-responsive"
			id="adminsInfoList" style="background: white; border: 2px solid red">
			<thead style="background: white; border: 3px solid green">
				<tr>
					<th>Admin First Name</th>
					<th>Admin Email</th>
					<th>Admin Phone</th>
					<th>Admin Role</th>
					<th>Application</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${users}" var="eachUser">
					<tr>
						<td>${eachUser.userFirstName}</td>
						<td>${eachUser.userEmail}</td>
						<td>${eachUser.userPhone}</td>
						<td>${eachUser.role}</td>
						<td>${eachUser.applicationName}</td>
						<td><button class="btn btn-danger deleteUser" id="${eachUser.userId}"
								data-toggle="modal" data-target="#deleteAdminConfirmation"
								value="${eachUser.userId}" data-userid="${eachUser.userId}"
								onclick="deleteAdmin(this);">Delete</button></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<div class="container">
		<div class="modal fade" role="dialog" id="deleteAdminConfirmation">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Are you sure to Delete the Admin ?</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<p>Note: Once you remove, corresponding user is not allowed to
							access the application</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal"
							autofocus="autofocus" value="close" id="cancel-delete-button">Close</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							value="delete" id="confirm-delete-button">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<h2>Footer</h2>
		<address style="text-align: center">
			Vinodh Kumar Thimmsetty<br> Shollinganallur<br> Chennai<br>
			India
		</address>
	</footer>

</body>
</html>