$(document).ready(function() {
	$('#listOfRegisteredUsers').DataTable({
		"paging" : true, 
		 "order": [[0,"asc"]],
		"ajax":{"url":"/Spring-Security/admin/listAllUsers","dataSrc":""}, 
	    "columns": [
			            { "data": "id" },
			            { "data": "firstName" },
			            { "data": "lastName" },
			            { "data": "userName" },
			            { "data": "userPassword" },
			            { "data": "gender" },
			            { "data": "userEmail" },
			            { "data": "country" },
			            { "data": "state" },
			            { "data": "phone" }
			        ]

	});
});