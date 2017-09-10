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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src=https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- Google fonts - Roboto -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
<!-- theme stylesheet-->
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- Font Awesome CDN-->
<!-- you can replace it by local Font Awesome-->
<script src="https://use.fontawesome.com/99347ac47f.js"></script>
<!-- Font Icons CSS-->
<link rel="stylesheet"
	href="https://file.myfontastic.com/da58YPMQ7U5HY8Rb6UxkNf/icons.css">
<!-- Tweaks for older IEs-->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

</head>
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
	<script>
		$(function() {
			$("#datepicker").datepicker();

			$('#newCourse').on('hidden.bs.modal', function() {
				$(this).find('form').trigger('reset');
			})

		});
	</script>
	<!-- Load the Header Navigation Bar -->
	<nav class="navbar navbar-default">
		<!-- Top Header content with Full Length of screen -->
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navigationLink-collpase"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- Brand LOGO -->
				<a class="navbar-brand" href="#">Vinodh</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navigationLink-collpase">
				<form class="nav navbar-form navbar-right">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Link</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Dropdown <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul></li>
					</ul>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	<!-- Load the Left Navigation Bar -->
	<!-- Load the Body Content -->
	<div class="container">
		<button class="btn btn-info btn-md" type="button" data-toggle="modal"
			data-target="#newCourse">Create Course</button>

		<!-- Model Popup -->
		<div id="newCourse" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal Content -->
				<div class="modal-content">
					<!-- Modal Content Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Add New Course</h4>
					</div>
					<!-- Modal Content Body -->
					<div class="modal-body">

						<form:form class="well form-horizontal" role="form"
							action="${pageContext.request.contextPath}/admin/addNewCourse"
							method="post" id="addNewCourse"
							commandName="userRegistrationForm">
							<div class="form-group">
								<label class="col-md-6 control-label" for="course-info">Course
									Information</label>
							</div>
							<br />
							<!-- Text input-->
							<div class="form-group">
								<label class="col-sm-3 control-label" for="courseName">Name</label>
								<div class="col-sm-6">
									<input name="courseName" placeholder="Course Name"
										class="col-sm-6 form-control" type="text">
									<form:errors path="courseName" cssClass="errorMessageStyle" />
								</div>
							</div>

							<!-- Text Area -->
							<div class="form-group">
								<label for="course-desc" class="col-sm-3 control-label">Description</label>
								<div class="col-sm-6">
									<textarea class="form-control" id="course-desc"
										placeholder="Course Description"></textarea>
									<form:errors path="course-desc" cssClass="errorMessageStyle" />
								</div>
							</div>

							<!-- Date input -->
							<div class="form-group">
								<!-- Date input -->
								<label class="col-sm-3 control-label" for="courseDate">Start
									Date</label>
								<div class="col-sm-6">
									<input class="form-control" id="datepicker"
										name="courseStartDate" placeholder="MM/DD/YYY" type="text" />
									<form:errors path="courseStartDate"
										cssClass="errorMessageStyle" />
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label for="course-price" class="col-sm-3 control-label">Price</label>
								<div class="col-sm-6">
									<input name="course-price" placeholder="Price in Rupees"
										class="col-sm-6 form-control" type="text">
									<form:errors path="course-price" cssClass="errorMessageStyle" />
								</div>
							</div>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-sm-3 control-label" for="course-tech">Name</label>
								<div class="col-sm-6">
									<input name="courseTechnology" placeholder="Java"
										class="col-sm-6 form-control" type="text">
									<form:errors path="courseTechnology"
										cssClass="errorMessageStyle" />
								</div>
							</div>
						</form:form>
					</div>
					<!-- Modal Content Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#closeConfirmation">Close</button>
						<button type="button" class="btn btn-success">Create</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Model Popup Leave Confirmation on Click of CANCEL button-->
		<div id="closeConfirmation" class="modal fade" role="dialog"
			data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<!-- Modal Content -->
				<div class="modal-content">
					<!-- Modal Content Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">If you leave, all unsaved data will
							be lost.</h4>
					</div>
					<!-- Modal Content Body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">Edit</button>
						<button type="button" class="btn btn-danger">Close</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- Load the Footer Navigation Bar-->
</body>
</html>