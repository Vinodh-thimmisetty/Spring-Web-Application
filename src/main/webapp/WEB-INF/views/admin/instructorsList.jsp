<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<script type="text/javascript"
	src="/Spring-Security/webjars/tablesorter/js/jquery.tablesorter.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/tablesorter/css/theme.default.css" />

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
	<div class="container">
	 	<form:form class="well form-horizontal"
			action="${pageContext.request.contextPath}/admin/addNewAdmin"
			method="post" id="contact_form2" commandName="applicationAdmin">
			<fieldset>

				<!-- Form Name -->
				<legend  style="text-align: center;">  
							<b>Add new Instructor</b> 
				</legend>
				<br>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">First Name</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="firstName"
								placeholder="First Name" class="form-control" type="text">
						</div>
						<form:errors path="firstName" cssClass="errorMessageStyle" />
					</div>
				</div>

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
								class="glyphicon glyphicon-user"></i></span> <input name="userName"
								placeholder="Username" class="form-control" type="text">
						</div>
						<form:errors path="userName" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Radio Fields -->
				<div class="form-group">
					<label class="col-md-4 control-label">Gender</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<label class="radio-inline"> <input id="genderMale"
								name="gender" type="radio" value="male"> Male
							</label> <label class="radio-inline"> <input id="genderFeMale"
								name="gender" type="radio" value="female"> Female
							</label> <label class="radio-inline"> <input id="genderTrans"
								name="gender" type="radio" value="trans"> Trans Gender
							</label>
						</div>
						<form:errors path="gender" cssClass="errorMessageStyle" />
					</div>
				</div>


				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Password</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input name="userPassword"
								placeholder="Password" class="form-control" type="password">
						</div>
						<form:errors path="userPassword" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Confirm Password</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								name="userPasswordConfirm" placeholder="Confirm Password"
								class="form-control" type="password">
						</div>
						<form:errors path="userPasswordConfirm"
							cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label">E-Mail</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span> <input
								name="userEmail" placeholder="E-Mail Address"
								class="form-control" type="text">
						</div>
						<form:errors path="userEmail" cssClass="errorMessageStyle" />
					</div>
				</div>
				<!-- Country Drop down -->
				<div class="form-group">
					<label class="col-md-4 control-label">Country</label>
					<div class="col-md-4 selectContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-globe"></i></span>
							<form:select path="country" id="listOfCountries"
								class="form-control selectpicker">
								<form:option value="">Select your Country</form:option>
								<form:options items="${countriesList}" />
							</form:select>
						</div>
						<form:errors path="country" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">State</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-home"></i></span> <input name="state"
								placeholder="State" id="stateName" class="form-control"
								type="text">
						</div>
						<form:errors path="state" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">Contact No.</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-earphone"></i></span> <input name="phone"
								placeholder="1234567890" class="form-control" type="text">
						</div>
						<form:errors path="phone" cssClass="errorMessageStyle" />
					</div>
				</div>

				<!-- Select Basic -->

				<!-- Success message -->
				<div class="alert alert-success" role="alert" id="success_message">
					Success <i class="glyphicon glyphicon-thumbs-up"></i> Success!.
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<br>
						<button type="submit" id="saveFormData" class="btn btn-success">
							SUBMIT <span class="glyphicon glyphicon-send"></span>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="resetFormData" class="btn btn-danger">
							RESET <span class="glyphicon glyphicon-refresh"></span>
						</button>
					</div>
				</div>

			</fieldset>
		</form:form>
	</div>
</body>

</body>

</html>