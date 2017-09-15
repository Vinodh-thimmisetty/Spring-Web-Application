/**
 * Javascript for usersList.jsp
 */

/* Reused Variables as GLOBAL Javascript Variables(similar to static final) */
var jsonRequest = "application/json";
var httpGET = "GET";
var httpPOST = "POST";
var httpPUT = "PUT";
var httpDELETE = "DELETE";
var datatableValues;



/* Font CSS Images for Gender Field in Data tables */
var glyphiconMale ="<span class='fa fa-male' style='color:red'></span>" ;
var glyphiconFemale = "<span class='fa fa-female' style='color:green'></span>" ;
var glyphiconTrans = "<span class='fa fa-transgender'></span>" ;

$(document).ready(function() {
	  datatableValues = $('#listOfRegisteredUsers').DataTable({
		 "autoWidth": false,
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
			            { "data": "phone" },
			            { "defaultContent": "id"}
			        ],
	    "columnDefs": [
			{
				/* Show the Images instead of text like male, female, trans gender etc. */
				"render": function(data, type, row) {
					return buildGenderGlyphicon(row.gender);
				},
				"targets": 5,
				"className": "dt-center"
			},
			{
				/* Edit the User Information based on Primary key "id" */
				"render": function(data, type, row) {
					return buildEditAndDeleteButtons(row.id);
				},
				"targets": 10,
				"className": "dt-center"
			}
		],

	});
	
	/* Trigger Actions on Update, Delete Links*/
	
	$("body").on("click","#listOfRegisteredUsers .updateUser",function(hrefEvent){
		// Block the HREF action from redirecting.
		hrefEvent.preventDefault();
	    console.log($(this).attr("href"));
	    // Data table ROW. Find the <tr> tag by traversing through Parent.
	    var userRow = $(hrefEvent.target).parents("tr");
	    var updateActionURL = $(this).attr("href"); 
	    $(userRow).find('td').each(function(){
	    	console.log($(this).html());
		     
		}); 
	    
	    var updatedUserInformation = {
	    	 	"firstName" : $(userRow).find('td')[1].innerHTML,
	    	 	"lastName" : $(userRow).find('td')[2].innerHTML, 
	    	 	"userEmail" : $(userRow).find('td')[6].innerHTML,
	    	 	"phone" : $(userRow).find('td')[9].innerHTML 
	    };
	    console.log(updatedUserInformation);
	    
	    // PUT ACTION to update the User Information
	    $.ajax({
	    		contentType: jsonRequest,
	    		url: updateActionURL,
	    		type: httpPUT,
	    		data: JSON.stringify(updatedUserInformation),
	    		dataType: 'json',
	    		async: true // Default
			})
			.done(function(data, textStatus, jqXHR) { 
				console.log("User Data is Updated");
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				 
			});
	    
	    
	    });
	
	$("body").on("click","#listOfRegisteredUsers .deleteUser",function(hrefEvent){
		// Block the HREF action from redirecting.
		hrefEvent.preventDefault();
	    console.log($(this).attr("href")); 
		// Data table ROW. Find the <tr> tag by traversing through Parent.
		var userRow = $(hrefEvent.target).parents("tr");
	    var deleteActionURL = $(this).attr("href");  
	    // DELETE ACTION to remove the User Information 
		$.ajax({
	    		contentType: jsonRequest,
	    		url: deleteActionURL,
	    		type: httpDELETE, 
	    		dataType: 'json',
	    		async: true // Default
			})
			.done(function(data, textStatus, jqXHR) { 
				console.log("deleted");
				$(userRow).remove();
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				console.log("error");
			});
	    });
	 
});

function buildGenderGlyphicon(gender) {
	if(gender == "male") {
		return glyphiconMale;
	} else if(gender == "female") {
		return glyphiconFemale;
	} else if(gender == "trans") {
		return glyphiconTrans;
	}
}

function buildEditButton(userId) {
 	return ("<a role='button' class='btn btn-success updateUser' href='/Spring-Security/admin/"
 			+ userId
 			+ "/updateUser'"
			+ ">Edit </a>"); 
}

function buildDeleteButton(userId) {
 	return ("<a role='button' class='btn btn-danger deleteUser' href='/Spring-Security/admin/"
 			+ userId
 			+ "/deleteUser'"
			+ ">Delete </a>"); 
}

function buildEditAndDeleteButtons(userId) {
 	return "<span>"+ buildEditButton(userId) + " " + buildDeleteButton(userId) + "</span>"; 
}
