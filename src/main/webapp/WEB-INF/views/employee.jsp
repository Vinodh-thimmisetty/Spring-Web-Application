<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

	<script>
		$(function() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
			$("#datepicker").datepicker();

			$("#addEmployeeAjax")
					.submit(
							function(e) {
								e.preventDefault(); // Prevent Default Submission of Form on click of Submit. We need to do something before data is sent to controller
								var formData = $("#addEmployee").serialize(); // Read all the form data and send as Request Params
								$
										.ajax(
												{
													url : '${pageContext.request.contextPath}/docs/addEmployeeAjax.tsp',
													type : 'POST',
													data : formData,
													dataType : "json",
												})
										.done(
												function(response) {
													console
															.log(
																	"Data is submitted successfully",
																	response);
												}).fail(
												function(jqXHR) {
													console.log(jqXHR.status," :: Failed::",jqXHR);
													$.each(jqXHR.responseJSON,function(key,value){
														console.log(key,"::",value);
														$("#empName").show();
													});
												});
							});

		});
	</script>


	<form:form
		action="${pageContext.request.contextPath}/docs/addEmployee.tsp"
		commandName="employee" id="addEmployee" method="POST"
		class="form-horizontal">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="empName">Employee
				Name:</label>
			<div class="col-sm-2">
				<form:input path="empName" class="form-control" />
			</div>
		</div>
		<form:errors path="empName" id="empNameLength" />

		<div class="form-group">
			<label class="control-label col-sm-2" for="emailId">Email: </label>
			<div class="col-sm-2">
				<form:input path="emailId" class="form-control" />
			</div>
		</div>
		<form:errors path="emailId" />

		<div class="form-group">
			<label class="control-label col-sm-2" for="phoneNum">Phone
				Number: </label>
			<div class="col-sm-2">
				<form:input path="phoneNum" class="form-control" />
			</div>
		</div>
		<form:errors path="phoneNum" />

		<div class="form-group">
			<label class="control-label col-sm-2" for="weight">weight: </label>
			<div class="col-sm-2">
				<form:input path="weight" class="form-control" />
			</div>
		</div>
		<form:errors path="weight" />

		<div class="form-group">
			<label class="control-label col-sm-2" for="dob">Date of
				Birth: </label>
			<div class="col-sm-2">
				<form:input path="dateOfBirth" class="form-control" id="datepicker" />
			</div>
		</div>
		<form:errors path="dateOfBirth" />

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn btn-danger">Submit</button>
			</div>
		</div>
	</form:form>

</body>
</html>