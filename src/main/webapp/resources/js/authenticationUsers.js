/**
 * Javascript for authhomepage.jsp
 */

/* Reused Variables as GLOBAL Javascript Variables(similar to static final) */
var jsonRequest = "application/json";
var httpGET = "GET";
var httpPOST = "POST";
var httpPUT = "PUT";
var httpDELETE = "DELETE";
var contextPath = "/Spring-Security/admin/";
var deleteAction = "/deleteAdmin";

$(document).ready(function() {

	// To pass something to Modal Window on click of something
	$('#deleteAdminConfirmation').on('show.bs.modal', function(event) {
		console.log(this);
		let deleteButton = $(event.relatedTarget);
		let userId = $(deleteButton[0]).data('userid');
		console.log(userId);
		$(this).find('.modal-footer .btn-danger').val(userId);
	});


	// Get the selected value from modal window on closing
	$('#deleteAdminConfirmation .modal-footer button').on('click', function(event) {
		  var $button = $(event.target); // The clicked button
		  	console.log(this); 
			console.log($button);
		  $(this).closest('.modal').one('hidden.bs.modal', function() {
		    // Fire if the button element 
		    console.log('The button that closed the modal is: ', $button);
		  });
		});
	
	 
});

/* Delete the Admin From Database and clears the row from Table */
function deleteAdmin(clickEvent) {
	let currentObject = $(clickEvent);
	let currentRow = currentObject.parents("tr");
	let userId = currentObject.val();
	let deleteActionURL = contextPath + userId + deleteAction;
	console.log(deleteActionURL);
	//
	$('#deleteAdminConfirmation').show();
	
	// Ajax to Delete the Record
	$.ajax({
		contentType : jsonRequest,
		url : deleteActionURL,
		type : httpDELETE,
		dataType : 'json',
		async : true
	// Default
	}).done(function(data, textStatus, jqXHR) {
		console.log("deleted");
		currentRow.remove();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("error");
	});

	 }
