<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="common.jsp"></jsp:include>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.min.css" />

    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

<div class="page-header">
    <h2>Cash-Flow Details <small>Please provide us your cash-flow details</small></h2>
</div>

	<div id='cashflow_cal_page_div'>

<!-- Registration form - START -->
<div class="container">
    <div class="row">
        <form method="post" action='/<%=StaticConstants.Context_ROOT %>/registration/register.html' id="registrationForm">
            <div class="col-lg-6">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                <div class="form-group">
                    <label for="firstName">Enter First Name</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Enter First Name" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName">Enter Last Name</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Enter Last Name" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="primaryPhoneNo">Enter Phone No</label>
                    <div class="input-group">
                        <input type="text" maxlength="10" class="form-control" id="primaryPhoneNo" name="primaryPhoneNo" placeholder="Enter Phone Number" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="primaryEmailId">Enter Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="primaryEmailId" name="primaryEmailId" placeholder="Enter Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPassword">Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Enter Password" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmedPassword">Confirm Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="confirmedPassword" name="confirmedPassword" placeholder="Confirm Password" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
   <!--              <div class="form-group">
                    <label for="InputMessage">Enter Message</label>
                    <div class="input-group">
                        <textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div> -->
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
            </div>
        </form>
        <div class="col-lg-5 col-md-push-1">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong>
                </div>
                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check all page inputs.</strong>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Registration form - END -->

</div>

</body>
<script>
$(document).ready(function() {
	bindEventWithForm();
	});
function bindEventWithForm(){
	var $form = $('#registrationForm');
	
	$form.submit(function() { // catch the form's submit event
	
	    $.ajax({ 	    	
	    	type: $form.attr('method'), 
	    	
	    	data: $form.serialize(),	        
	        
	    	url: $form.attr('action'),
	    	
	        success: function(response) { 
	             $('#maincontainer').html(response); 
	        }
	    });
	
	    return false; // cancel original event to prevent form submitting
	});
	
}
</script>
</html>

