$(document).ready(function() { 
    
	// Auto Suggest the State Names if INDIA is selected in Country dropdown
	/*$("#listOfCountries").change(function() {
	    if($(this).find("option:selected").text() == "INDIA"){

	  	  $( "#stateName" ).autocomplete({
	  	      source: "/Spring-Security/user/autoSuggestIndianStates"
	  	    });
	  	
	    }
	});
	
	$("#resetFormData").click(function(){
		$("form")[ 0 ].reset(); 
		$('#contact_form').data('bootstrapValidator').resetForm();
	})
	 
	$('#contact_form').bootstrapValidator({
	// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		firstName : {
			validators : {
				stringLength : {
					min : 2,
				},
				notEmpty : {
					message : 'Please enter your First Name'
				}
			}
		},
		lastName : {
			validators : {
				stringLength : {
					min : 2,
				},
				notEmpty : {
					message : 'Please enter your Last Name'
				}
			}
		},
		gender : {
			validators : { 
				notEmpty : {
					message : 'Please enter your Gender'
				}
			}
		},
		userName : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Username'
				}
			}
		},
		userPassword : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Password'
				}
			}
		},
		userPasswordConfirm : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please confirm your Password'
				},
				identical:{
					field: 'userPassword',
					message : 'The password and its confirm are not the same'
				},
			}
		},
		userEmail : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Email Address'
				},
				emailAddress : {
					message : 'Please enter a valid Email Address'
				}
			}
		},
		phone : {
			validators : {
				stringLength : {
					min : 10,
					max : 10,
				},
				notEmpty : {
					message : 'Please enter your Phone No.'
				}
			}
		},
		country : {
			validators : {
				stringLength : {
					min : 3,
				},
				notEmpty : {
					message : 'Please select your country'
				}
			}
		},
		state : {
			validators : {
				stringLength : {
					min : 3,
				},
				notEmpty : {
					message : 'Please select your state'
				}
			}
		}
	}
	}).on('success.form.bv', function(e) {
		$('#success_message').slideDown({
			opacity : "show"
		}, "slow") // Do something ...
		$('#contact_form').data('bootstrapValidator').resetForm();

		// Prevent form submission
		e.preventDefault();

		// Get the form instance
		var $form = $(e.target);

		// Get the BootstrapValidator instance
		var bv = $form.data('bootstrapValidator');

		// Use Ajax to submit form data
		$.post($form.attr('action'), $form.serialize(), function(result) {
			$("#success_message").hide();
			for(var i=0;i<result.length;i++){ 
				$.each(result[i],function(key,value) {
					 //console.log(key, ":<>:",value); 
					// Enabling Server Side Validation Logs   
					$(".form-group .input-group :text,:password").each(function(){ 
						if($.trim($(this).attr("name")) == $.trim(key)){ 
							
							 var $selectedField = $(this).parent().parent();
							 $selectedField.parent().addClass("has-feedback has-error"); 
							 
							if(!$selectedField.find('small').attr("data-bv-for")){  
								 $selectedField .append($('<i/>') 
			                             .addClass('form-control-feedback bv-icon-input-group glyphicon glyphicon-remove')
			                             .attr('data-bv-icon-for', key));
							} 

							 $selectedField.append($('<small/>')
	     	                            .addClass('help-block') 
	     	                            .attr('data-bv-validator', "notEmpty")
	    	                            .attr('data-bv-for', key)
	    	                            .attr('data-bv-result', "NOT_VALIDATED").html(value)); 
							return;
						} 
					});
				}); 
			}
			
			 
		
		}, 'json');
	}); */
});

//If Javascript is Disabled --> Change the contact form id to contact_form2 and test it

//
$(document).ready(function() { 
    
	// Auto Suggest the State Names if INDIA is selected in Country dropdown
	$("#listOfCountries").change(function() {
	    if($(this).find("option:selected").text() == "INDIA"){

	  	  $( "#stateName" ).autocomplete({
	  	      source: "/Spring-Security/user/autoSuggestIndianStates"
	  	    });
	  	
	    }
	});
	
	$("#resetFormData").click(function(){
		$("form")[ 0 ].reset(); 
		$('#contact_form2').data('bootstrapValidator').resetForm();
	})
	 
	$('#contact_forms2').bootstrapValidator({
	// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		firstName : {
			validators : {
				stringLength : {
					min : 2,
				},
				notEmpty : {
					message : 'Please enter your First Name'
				}
			}
		},
		lastName : {
			validators : {
				stringLength : {
					min : 2,
				},
				notEmpty : {
					message : 'Please enter your Last Name'
				}
			}
		},
		gender : {
			validators : { 
				notEmpty : {
					message : 'Please enter your Gender'
				}
			}
		},
		userName : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Username'
				}
			}
		},
		userPassword : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Password'
				}
			}
		},
		userPasswordConfirm : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please confirm your Password'
				},
				identical:{
					field: 'userPassword',
					message : 'The password and its confirm are not the same'
				},
			}
		},
		userEmail : {
			validators : {
				stringLength : {
					min : 8,
				},
				notEmpty : {
					message : 'Please enter your Email Address'
				},
				emailAddress : {
					message : 'Please enter a valid Email Address'
				}
			}
		},
		phone : {
			validators : {
				stringLength : {
					min : 10,
					max : 10,
				},
				notEmpty : {
					message : 'Please enter your Phone No.'
				}
			}
		},
		country : {
			validators : {
				stringLength : {
					min : 3,
				},
				notEmpty : {
					message : 'Please select your country'
				}
			}
		},
		state : {
			validators : { 
				notEmpty : {
					message : 'Please select your state'
				}
			}
		}
	}
	}).on('success.form.bv', function(e) {
		$('#success_message').slideDown({
			opacity : "show"
		}, "slow") 
 
	}); 
});
//
