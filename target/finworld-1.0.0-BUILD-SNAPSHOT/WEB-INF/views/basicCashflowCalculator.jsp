<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	

<form action="/<%=StaticConstants.Context_ROOT %>/cashflowCalculator/calculateBasicCashflow.html" method="post" id="basicCashflowCalcForm">
	<div id='basic_cashflow_cal_header_div'>
	    <h4>Cash-Flow Details <small>Please provide us your cash-flow details</small></h4>
	</div>
	<hr>
	<div id='basic_cashflow_cal_form_div'>		
		<div class="container">
		    <div class="row">
	            <div class="col-lg-3">
	                <div class="form-group">
	                    <label for="currentAge">Current Age</label>
						<div class="form-inline">       
							<div class="form-group">									
							    <input type="text" id="currentAge" name="currentAge" class="form-control input-sm" onkeypress="return validateYear(this);" 
							    	style="width:100px"  placeholder="Age" required maxlength="2" data-validation-required-message="Age is required" >
							</div>
						</div>
	                </div>
	                <div class="form-group">
	                    <label for="incomeAmount">Yearly Income</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="incomeAmount" name="incomeAmount" 
	                        onkeypress="return numericValidation(this);" placeholder="Income Amount" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="incomeInflation">Income Increment Rate</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="incomeInflation" name="incomeInflation" maxlength="3" 
	                        onkeypress="return numericValidation(this);" placeholder="Expense Inflation Rate" >
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
   	                <div class="form-group">
	                    <label for="incomeToYear">Income Till Year</label>
						<div class="form-group">									
						    <input type="text" id="incomeToYear" name="incomeToYear" class="form-control input-sm" onkeypress="return validateYear(this);"
						    	style="width:100px"  placeholder="YYYY" required maxlength="4" data-validation-required-message="Year is required" >
						</div>
	                </div>
	                <div class="form-group">
	                    <label for="expenseAmount">Yearly Expense</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="expenseAmount" name="expenseAmount" 
	                        onkeypress="return numericValidation(this);" placeholder="Expense Amount" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="expenseInflation">Expense Inflation Rate</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="expenseInflation" name="expenseInflation" maxlength="3" 
	                        onkeypress="return numericValidation(this);" placeholder="Expense Inflation Rate" >
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
   	                <div class="form-group">
	                    <label for="expenseToYear">Expense Till Year</label>
						<div class="form-group">									
						    <input type="text" id="expenseToYear" name="expenseToYear" class="form-control input-sm" onkeypress="return validateYear(this);"
						    	style="width:100px"  placeholder="YYYY" required maxlength="4" data-validation-required-message="Year is required" >
						</div>
	                </div>
	                
	                <div class="form-group">
	                	<input type="submit" name="calculateBasicFlow" id="calculateBasicFlow" value="Calculate" class="btn btn-info pull-right" onclick="CalculateBasicCashFlow();"/>
	                </div>	
	            </div>
	            <div class="col-lg-8 col-md-push-1">
		            <div class="col-md-12">
	            		<div id='basicCalcResultDiv'>

						</div>		            	
		            </div>
		        </div>    
		    </div>
		</div>
	</div>
</form>
	
</body>
<script type="text/javascript">
function CalculateBasicCashFlow(){
	alert('test');
	var context= '<%= StaticConstants.Context_ROOT%>';	
	var requestParam = "";
	var $form = $('#basicCashflowCalcForm');
	
	$.ajax({ 	    	
    	type: $form.attr('method'), 
    	
    	data: requestParam,	        
        
    	url: $form.attr('action'),
    	
        success: function(response) {
        	alert('response');
             $('#basicCalcResultDiv').html(response); 
        }
    });

}
</script>
</html>