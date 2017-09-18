<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
         <div class="alert alert-success">
         	<strong>Result</strong>
         </div>
        <table id="incomeTable" class="table table-striped">
			<thead>
           		<tr>
	               <th>Year</th>
	               <th>Start Balance</th>
	               <th>Income Inflation</th>
	               <th>Income Inflation Amount</th>
	               <th>Current year Income</th>
	               <th>Expense Inflation</th>
	               <th>Expense Inflation Amount</th>
	               <th>Current year Expense</th>
	               <th>End Balance</th>
           		</tr>
       		</thead>
			<tbody>
       			<c:forEach var="cashStatement" items="${cashStatements}">
	       			<tr>
	       				<td>${cashStatement.year}</td>
	       				<td>${cashStatement.startBalance}</td>
	       				<td>${cashStatement.incomeInflation}</td>
	       				<td>${cashStatement.incomeInflationAmt}</td>
	       				<td>${cashStatement.yearlyInflowAmt}</td>
	       				<td>${cashStatement.expenseInflation}</td>
	       				<td>${cashStatement.expenseInflationAmt}</td>
	       				<td>${cashStatement.yearlyOutflowAmt}</td>
	       				<td>${cashStatement.endBalance}</td>
	       			</tr>	
       			</c:forEach>
    	    </tbody>
	   </table>                    
	</body>
</html>