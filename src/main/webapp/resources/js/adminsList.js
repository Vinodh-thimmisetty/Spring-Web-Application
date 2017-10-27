/**
 * Javascript for adminList.jsp
 */

/* Constant Global Variable Declarations */
var usersListDatatable;

/* DOM Loading JQuery function */

/* similar to $(document).ready(function(){ // logic begins here}); */
$(function() {
	/* Load Admins list Data-table */
	usersListDatatable = $("#listOfApplicationAdmins").Datatable({
						/* Set Default Sorting Column*/
						"order" : [[0,"desc"]],
						/* Set Pagination at bottom right*/
						"paging" : true,
						/* Load available Admin Details */
						"ajax" : {"url":"/Spring-Security/admin/listAllAdmins","dataSrc":""}, 
						/* List Columns for mapping to Table*/
						"columns" : [
									 	{ "data": "adminId" },
							            { "data": "firstName" },
							            { "data": "lastName" },
							            { "data": "adminName" },
							            { "data": "adminPassword" }, 
							            { "data": "adminEmail" }, 
							            { "data": "phone" },
							            { "defaultContent": "id"}
									],
						/* Add any extra functionalities to be performed on above mentioned columns*/
						"columnDefs" : [
											{
												/* Add buttons to Inline Edit & Delete Admin */
												"render": function(data, type, row) {
													return buildEditAndDeleteButtons(row.id);
												},
												"targets": 7,
 											}
							
									   ],
						/* Add Any Extra Button on Top Left Corner*/
						"buttons" : [
										{
											extend: "create"
										}
									
									]			   
						

						});
	
	/* Perform JQuery Events like onClick(), onBlur etc. */

});

/* Independent functions which will be called as and when required */
  
function buildEditButton(adminId) {
 	return ("<a role='button' class='btn btn-success updateUser' href='/Spring-Security/admin/"
 			+ adminId
 			+ "/updateAdmin'"
			+ ">Edit </a>"); 
}

function buildDeleteButton(adminId) {
 	return ("<a role='button' class='btn btn-danger deleteUser' href='/Spring-Security/admin/"
 			+ adminId
 			+ "/deleteAdmin'"
			+ ">Delete </a>"); 
}

function buildEditAndDeleteButtons(adminId) {
 	return "<span>"+ buildEditButton(adminId) + " " + buildDeleteButton(adminId) + "</span>"; 
}