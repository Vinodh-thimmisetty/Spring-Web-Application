<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Registration</title>

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

<script src="<c:url value="/resources/js/userRegistration.js" />"></script>
<!-- include BlockUI -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.js"></script>

</head>
<body>
	<script type="text/javascript">
		// On Every Ajax Call
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	</script>
	<div class="container">

		Language : <a href="?language=en">English</a>| <a
			href="?language=zh_CN">Chinese</a> | <a href="?language=de">Deustch</a>
		| <a href="?language=fr">French</a>
		<!-- Action is submitted using Ajax after CLient Side Validations -->
		<%-- <form:form class="well form-horizontal"
			action="${pageContext.request.contextPath}/user/registration"
			method="post" id="contact_form" commandName="userRegistrationForm"> --%>
		<!-- UnComment above form tag and Comment below to Manually Validate the Server side bean validation -->
		<form:form class="well form-horizontal"
			action="${pageContext.request.contextPath}/user/registration2"
			method="post" id="contact_form2" commandName="userRegistrationForm">
			<fieldset>

				<!-- Form Name -->
				<legend>
					<center>
						<h2>
							<b>Course Registration Form</b>
						</h2>
					</center>
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
						<form:errors path="lastName" cssClass="errorMessageStyle"/>
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
	<!-- /.container -->
</body>
</html>