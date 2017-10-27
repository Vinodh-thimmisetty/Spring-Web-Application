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
<title>Admin Details Dashboard</title>
<meta name="description"
	content="This page holds information of different Admins available which can be monitored by Super ADMIN">
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
	src="<c:url value="/resources/js/adminsList.js" />"></script>

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
					<li></li>
				</ul>
			</div>
			<!-- end nav bar links -->
		</nav>
		<!-- end Navigation bar -->


	</div>
	<!-- End Header -->
	<!-- List All Available Admin Details using DATATABLE with ADD/EDIT/DELETE buttons-->
	<div class="container-fluid">
		<div id="createNewAdmin" class="container-fluid">
			<form class="well form-horizontal">
				<div class="form-group row col-md-6">
					<label for="firstName" class="col-sm-2 col-form-label">First
						Name</label> <input type="text" class="form-control-plaintext"
						id=" firstName" placeholder="Vinodh">
				</div>
				<div class="form-group row col-md-6">
					<label for="lastName" class="col-sm-2 col-form-label">Last
						Name</label> <input type="text" class="form-control-plaintext"
						id="lastName" placeholder="Thimmisetty">
				</div>

				<div class="form-group row col-md-6">
					<label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
					<input type="text" class="form-control-plaintext"
						id="staticEmail" value="email@example.com">
				</div>

				<div class="form-group row">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-md-4">
						<input type="password" class="form-control" id="inputPassword"
							placeholder="Password">
					</div>
				</div>
				<!-- Text input-->
				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Last Name</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="lastName"
								placeholder="Last Name" class="form-control" type="text">
						</div>
						<form:errors path="lastName" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Username</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								name="adminUserName" placeholder="adminUserName"
								class="form-control" type="text">
						</div>
						<form:errors path="adminUserName" cssClass="errorMessageStyle" />
					</div>
				</div>
			</form>
		</div>
		<br />
		<div id="adminDetailsDatatable">
			<table
				class="table table-sm table-bordered table-hover table-responsive"
				id="listOfApplicationAdmins">
				<thead>
					<tr class="thead table-success">
						<th>Admin Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Admin Name</th>
						<th>Admin Password</th>
						<th>Admin Email</th>
						<th>Admin Phone</th>
						<th>Action</th>
					</tr>
				</thead>
				<tfoot>
					<tr class="thead table-success">
						<th>Admin Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Admin Name</th>
						<th>Admin Password</th>
						<th>Admin Email</th>
						<th>Admin Phone</th>
						<th>Action</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>

</html>