<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.achhabra.finworld.util.StaticConstants"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script type="text/javascript">
		    
	  		var myApp = angular.module('myApp',[]);

			myApp.controller('myCtrl',function($scope,$http){			    
				$scope.changeCashFlowType = function(cashFlowType) {
			        if(cashFlowType == "Income"){
	 	    		    $http({method: 'GET', 
	 	    		    	//data: 'cashFlowType': cashFlowType ,
		    		    	url: '/finworld/loadAjaxData/inflowCashflowType.html?cashflowType='+cashFlowType
	    		    	}).success(function(data, status, headers, config) {
	    		    		$scope.cashFlowTypes = data; 
			        	}).error(function(data, status, headers, config) {
				          	// called asynchronously if an error occurs
				          	// or server returns response with an error status.
			        	});		     
			        	
			        }else{
	 	    		    $http({method: 'GET', 
	 	    		    	//data: 'cashFlowType': cashFlowType ,
		    		    	url: '/finworld/loadAjaxData/outflowCashflowType.html?cashflowType='+cashFlowType
	    		    	}).success(function(data, status, headers, config) {
	    		    		$scope.cashFlowTypes = data; 
			        	}).error(function(data, status, headers, config) {
				          	// called asynchronously if an error occurs
				          	// or server returns response with an error status.
			        	});		     
			        	
			        }
			    }
			});

			var incomeRowCount = 0;
			var expenseRowCount = 0;
			var prefixIncome ="income";
			var prefixExpense = "expense";
			function addCashFlowRow(){
				var table = null;	
				var cashFlowType = $("input[name='cashFlowType']:checked").val();
				//var omg = document.querySelector('input[name = "cashFlowType"]:checked').value;
				var rowCount = 0;
				var tableType= "";
				if(cashFlowType!=null && cashFlowType !='undefind'){
					if(cashFlowType=="Income"){
						//rowCount= $('#incomeTable tbody tr').length;
						rowCount = incomeRowCount;
						table = document.getElementById("incomeTable");
						tableType = prefixIncome;
					}else{
						//rowCount= $('#expenseTable tbody tr').length;
						rowCount = expenseRowCount; 
						table = document.getElementById("expenseTable");
						tableType = prefixExpense;
					}
				    var validateValues= true;
				    
				    var type= $("#inExpType option:selected").text();
				    var type_val= $("select[name=inExpType]").val();
				    
				    var amount= document.getElementById("inExpAmount");
				    
				    /* var freq= $("select[name=inExpFreq]").text(); */
				    var freq= $("#inExpFreq option:selected").text();	    
				    var freq_val = $("select[name=inExpFreq]").val();
				    
				    var fromMonth= $("select[name=inExpFromMonth]").val();
				    var fromYear= document.getElementById("inExpFromYear");
				    
				    var toMonth= $("select[name=inExpToMonth]").val();
				    var toYear= document.getElementById("inExpToYear");
				    
				    var inflation= document.getElementById("inExpInflation");
				    var msg="";
				    
				    if(isInvalidDropdown(type)){
				    	validateValues=false;
				    	msg += "Type cannot be Blank." +"\n";
				    } 
				    
				    if(isInvalidTextBox(amount)){
				    	validateValues=false;
				    	msg += "amount cannot be Blank." +"\n";
				    }

				    if(isInvalidDropdown(freq)){
				    	validateValues=false;
				    	msg += "freq cannot be Blank." +"\n";
				    }
				    
				    if(isInvalidDropdown(fromMonth)){	    	
				    	validateValues=false;
				    	msg += "from Month cannot be Blank." +"\n";
				    }

				    if(isInvalidTextBox(fromYear)){
				    	validateValues=false;
				    	msg += "from Year cannot be Blank." +"\n";
				    }

				    if(isInvalidDropdown(toMonth)){	    	
				    	validateValues=false;
				    	msg += "To Month cannot be Blank." +"\n";
				    }
				    
				    if(isInvalidTextBox(toYear)){
				    	validateValues=false;
				    	msg += "to Year cannot be Blank." +"\n";
				    }

				    if(isInvalidTextBox(inflation)){
				    	validateValues=false;
				    	msg += "inflation cannot be Blank." +"\n";
				    }	            
				    if(!validateValues){
				    	alertMessage(msg);
				    }else{    
						var row = table.insertRow(rowCount+1);
					    var cell1 = row.insertCell(0);
					    var cell2 = row.insertCell(1);
					    var cell3 = row.insertCell(2);
					    var cell4 = row.insertCell(3);
					    var cell5 = row.insertCell(4);
					    var cell6 = row.insertCell(5);
					    var cell7 = row.insertCell(6);
					    if(tableType == prefixIncome){
						    incomeRowCount = incomeRowCount+1;
					    }
					    if(tableType == prefixExpense){
						    expenseRowCount = expenseRowCount+1;
					    }
					    
				    	cell1.innerHTML = "<div style='display:none' id='"+ tableType +"_type_"+ rowCount +"'>"+ type_val +" </div> <div>"+type +" </div>";
				    	cell2.innerHTML = "<div id='"+ tableType +"_amount_"+ rowCount +"'>"+amount.value +" </div>";
				    	cell3.innerHTML = "<div style='display:none' id='"+ tableType +"_freq_"+ rowCount +"'>"+freq_val +" </div> <div>"+freq +" </div>";
				    	cell4.innerHTML = "<div id='"+ tableType +"_fromDate_"+ rowCount +"'>"+fromMonth + "/" + fromYear.value +" </div>";
				    	cell5.innerHTML = "<div id='"+ tableType +"_toDate_"+ rowCount +"'>"+toMonth + "/" + toYear.value +" </div>";
				    	cell6.innerHTML = "<div id='"+ tableType +"_inflation_"+ rowCount +"'>"+inflation.value +" </div>";
				    	cell7.innerHTML = "<a href='#'><span class='glyphicon glyphicon-remove'></span></a>";

				    	type.value="";
					    amount.value="";
					    freq="";
					    fromMonth="";
					    fromYear.value="";
					    toMonth="";
					    toYear.value="";
					    inflation.value="";
				    }    
				}else{
					alertMessage('Please Selete Cash Flow Type.');
				}
			}
			function CalculateCashFlow(){
				
				var context= '<%= StaticConstants.Context_ROOT%>';	
				var requestParam = "incomeRowCount="+ incomeRowCount + "&expenseRowCount="+ expenseRowCount;
				
				for(var i=0;i<incomeRowCount;i++){
					var id= prefixIncome + "_type_"+i;		
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixIncome + "_amount_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixIncome + "_freq_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixIncome + "_fromDate_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixIncome + "_toDate_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixIncome + "_inflation_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}		
				}
				
				for(var i=0;i<expenseRowCount;i++){
					var id= prefixExpense + "_type_"+i;		
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixExpense + "_amount_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixExpense + "_freq_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixExpense + "_fromDate_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixExpense + "_toDate_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}
					
					id= prefixExpense + "_inflation_"+i;
					if(document.getElementById(id)){
						requestParam = requestParam + "&" + id + "=" + document.getElementById(id).innerHTML;
					}		
				}
				
			//alert(requestParam);
				var $form = $('#cashFlowForm');
				
				$.ajax({ 	    	
			    	type: $form.attr('method'), 
			    	
			    	data: requestParam,	        
			        
			    	url: $form.attr('action'),
			    	
			        success: function(response) { 
			             $('#cashflow_cal_result_div').html(response); 
			        }
			    });

			}
		</script>
	</head>	
<body>

<div id='cashflow_cal_page_div'>
	<div id='cashflow_cal_header_div'>
	    <h4>Cash-Flow Details <small>Please provide us your cash-flow details</small></h4>
	</div>
	<hr>
	<div id='cashflow_cal_form_div' ng-app="myApp" ng-controller="myCtrl">		
		<div class="container">
		    <div class="row">
	            <div class="col-lg-3">
	                <div class="form-group">
	                    <label for="cashFlowType">Check Either Income or Expense</label>
	                    <div class="input-group">
				            <label class="radio-inline">
						      <input type="radio" name="cashFlowType" id="cashFlowType" value="Income" 
						      ng-click="changeCashFlowType('Income')" 
						      ng-model="cashFlowType"/>Income
						    </label>
						    <label class="radio-inline">
						      <input type="radio" name="cashFlowType" id="cashFlowType" value="Expense" 
						      ng-click="changeCashFlowType('Expense')" 
						      ng-model="cashFlowType"/>Expense
						    </label>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="inExpType">Income/Expense Type</label>
	                    
<!-- 				<div data-ng-init="getPersonDataFromServer()">
					<b>Person Data:</b> 
					<select id="personData">
						<option value="">-- Select Persons --</option>
						<option data-ng-repeat="personData in personDatas" value="{{personData.personId}}">{{personData.personName}}</option>
					</select><br>
				</div>					    
-->
					<select name="inExpType" id="inExpType" class="form-control" ng-model="inExpType">
						<option value="">Select</option>
						<option data-ng-repeat="KeyValuePairJson in cashFlowTypes" value="{{KeyValuePairJson.id}}">{{KeyValuePairJson.value}}</option>
					</select>
	                
<!-- 	 				<select name="inExpType" id="inExpType" class="form-control" 
	                    data-role="listview" ng-options="type as type.text for type in types.cast " ng-model="inExpType">
					 </select> -->
					    
					    
	                </div>
	                <div class="form-group">
	                    <label for="inExpAmount">Amount</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="inExpAmount" name="inExpAmount" 
	                        onkeypress="return numericValidation(this);" placeholder="Amount" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">		                
	                    <label for="inExpFreq">Frequency</label>
	                    <select name="inExpFreq" id="inExpFreq" class="form-control">
					        <option class="form-control">Select</option>
							<c:forEach items="${frequencyList}" var="frequency" varStatus="status">
								<option value="${frequency.id}">${frequency.display}</option>
							</c:forEach>
					    </select>
	                </div>
	                <div class="form-group">
	                    <label for="inExpFromMonth">From Date</label>
						<div class="form-inline">       
							<div class="form-group">
	
							  <!-- <input type="text" id="inExpFromMonth" name="inExpFromMonth" class="form-control input-sm" 
							  		style="width:100px"  placeholder="MM" required maxlength="2" data-validation-required-message="Month is required" > -->
			                    <select name="inExpFromMonth" id="inExpFromMonth" class="form-control input-sm" style="width:100px">
							        <option value="-1" class="form-control">Select</option>
							        <option value="1">Jan</option>
							        <option value="2">Feb</option>
							        <option value="3">Mar</option>
							        <option value="4">Apr</option>
							        <option value="5">May</option>
							        <option value="6">Jun</option>
							        <option value="7">Jul</option>
							        <option value="8">Aug</option>
							        <option value="9">Sept</option>
							        <option value="10">Oct</option>
							        <option value="11">Nov</option>
							        <option value="12">Dec</option>
							    </select>
							</div>
							<div class="form-group">									
							    <input type="text" id="inExpFromYear" name="inExpFromYear" class="form-control input-sm" onkeypress="return validateYear(this);" 
							    	style="width:100px"  placeholder="YYYY" required maxlength="4" data-validation-required-message="Year is required" >
							</div>
						</div>
	                </div>
	                
	                <div class="form-group">
	                    <label for="inExpToMonth">To Date</label>
						<div class="form-inline">       
							<div class="form-group">
<!-- 								  <input type="text" id="inExpToMonth" name="inExpToMonth" class="form-control input-sm" 
								  		style="width:100px"  placeholder="MM" required maxlength="2" data-validation-required-message="Month is required" > -->
			                    <select name="inExpToMonth" id="inExpToMonth" class="form-control input-sm" style="width:100px">
							        <option value="-1" class="form-control">Select</option>
							        <option value="1">Jan</option>
							        <option value="2">Feb</option>
							        <option value="3">Mar</option>
							        <option value="4">Apr</option>
							        <option value="5">May</option>
							        <option value="6">Jun</option>
							        <option value="7">Jul</option>
							        <option value="8">Aug</option>
							        <option value="9">Sept</option>
							        <option value="10">Oct</option>
							        <option value="11">Nov</option>
							        <option value="12">Dec</option>
							    </select>
							</div>
							<div class="form-group">									
							    <input type="text" id="inExpToYear" name="inExpToYear" class="form-control input-sm" onkeypress="return validateYear(this);"
							    	style="width:100px"  placeholder="YYYY" required maxlength="4" data-validation-required-message="Year is required" >
							</div>
						</div>
	                </div>
	                <div class="form-group">
	                    <label for="inExpInflation">Inflation Rate</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="inExpInflation" name="inExpInflation" maxlength="3" 
	                        onkeypress="return numericValidation(this);" placeholder="Inflation Rate" >
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                	<input type="button" name="inExpSubmit" id="inExpSubmit" value="Submit" class="btn btn-info pull-right" onClick="addCashFlowRow();">
	                </div>	
	            </div>
		        <div class="col-lg-8 col-md-push-1">
		            <div class="col-md-12">
			        <form method="post" action='/<%=StaticConstants.Context_ROOT %>/cashflowCalculator/calculateCashflow.html' id="cashFlowForm">
		                <div>
		                <br><br><br>
			                <div class="alert alert-success">
			                    <strong>Income Table</strong>
			                </div>
		                	<table id="incomeTable" class="table table-striped">
						        <thead>
						            <tr>
						                <th>Income Type</th>
						                <th>Amount</th>
						                <th>Frequency</th>
						                <th>From Date</th>
						                <th>To Date</th>
						                <th>Inflation</th>
						                <th>Delete</th>
						            </tr>
						        </thead>
						        <tbody>
									<c:forEach items="${inflowList}" var="incomeCashFlow" varStatus="status">
										<tr>
											<td><div style='display:none' id='income_type_${status.count}'>${incomeCashFlow.cashflowType}</div><div>${incomeCashFlow.cashflowType}</div></td>
											<td><div id='income_amount_${status.count}'>${incomeCashFlow.amount}</div></td>
											<td><div style='display:none' id='income_freq_${status.count}'>${incomeCashFlow.freq}</div><div>${incomeCashFlow.freq}</div></td>
											<td><div id='income_fromDate_${status.count}'>${incomeCashFlow.fromMonth}/${incomeCashFlow.fromYear}</div></td>
											<td><div id='income_toDate_${status.count}'>${incomeCashFlow.toMonth}/${incomeCashFlow.toYear}</div></td>
											<td><div id='income_inflation_${status.count}'>${incomeCashFlow.inflation}</div></td>
											<td><a href='#'><span class='glyphicon glyphicon-remove'></span></a></td>
											<%-- <td><input name="contacts[${status.index}].firstname" value="${contact.firstname}"/></td> --%>
										</tr>
									</c:forEach>
						        </tbody>
						    </table>                    
		                </div>
		                <div>
		                	<div class="alert alert-danger">
			                    <strong>Expense Table</strong>
			                </div>
		                	<table id="expenseTable" class="table table-striped">
						        <thead>
						            <tr>
						                <th>Income Type</th>
						                <th>Amount</th>
						                <th>Frequency</th>
						                <th>From Date</th>
						                <th>To Date</th>
						                <th>Inflation</th>
						                <th>Delete</th>
						            </tr>
						        </thead>
						        <tbody>
									<c:forEach items="${outflowList}" var="expenseCashFlow" varStatus="status">
										<tr>
											<td><div style='display:none' id='expense_type_${status.count}'>${expenseCashFlow.cashflowType}</div></td>
											<td><div id='expense_amount_${status.count}'>${expenseCashFlow.amount}</div></td>
											<td><div style='display:none' id='expense_freq_${status.count}'>${expenseCashFlow.freq}</div></td>
											<td><div id='expense_fromDate_${status.count}'>${expenseCashFlow.fromMonth}/${expenseCashFlow.fromYear}</div></td>
											<td><div id='expense_toDate_${status.count}'>${expenseCashFlow.toMonth}/${expenseCashFlow.toYear}</div></td>
											<td><div id='expense_inflation_${status.count}'>${expenseCashFlow.inflation}</div></td>
											<td><a href='#'><span class='glyphicon glyphicon-remove'></span></a></td>
											<%-- <td><input name="contacts[${status.index}].firstname" value="${contact.firstname}"/></td> --%>
										</tr>
									</c:forEach>
						        </tbody>
						    </table>                    
		                </div>
		              </form>
		              <div>
		                	<input type="submit" name="CalculateCashFlow" id="CalculateCashFlow" 
		                	value="Calculate Cash Flow" class="btn btn-info pull-right" onclick="CalculateCashFlow();">
		                	<!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data_target="#cashflow_cal_result_div">Calculate Cash Flow</button> -->
		              </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- Registration form - END -->
	</div>
	<br/><br/><br/>
	<div id='cashflow_cal_result_div'>
	
	</div>
</div>	
</body>
</html>