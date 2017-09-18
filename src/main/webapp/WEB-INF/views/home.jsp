
<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script>
	
	</script>
    	
	</head>
<body>
<div id='home_page_div'>
        <header class="jumbotron margin-top-70">
            <h2> 
            	<spring:message code="project.welcome.message"/>
            </h2>
            <p><spring:message code="project.fin.calc.message"/></p>
            <p>
            	<%-- <a class="btn btn-primary btn-large" href="#" onClick="loadPageInMainDiv('/<%=StaticConstants.Context_ROOT%>/jsp/cashflow_calculator.jsp');">Cash-Flow Calculator</a> --%>
            	<a class="btn btn-primary btn-large" href="/<%=StaticConstants.Context_ROOT %>/navigate/loadBasicCashFlow.html">
            		<spring:message code="project.btn.cashflow.calc"/>
            	</a>
            </p>
        </header>

        <hr>

        <div class="row">
            <div class="col-lg-12">
                <h3><spring:message code="project.label.latest.feature"/></h3>
            </div>
        </div>

        <!-- Page Features -->
        <div class="row text-center">

            <div class="col-md-3 col-sm-6">
                <div class="thumbnail">
                    <img src="<c:url value="/resources/images/moneytree.jpg"/>" alt="">
                    <div class="caption">
                        <h3>Premium Cash-Flow Calculator</h3>
                        <p>Get your Cash Statement here with detailed Cashflow Calculator.</p>
                        <p>
                            <a href="/<%=StaticConstants.Context_ROOT %>/navigate/premium/loadCashFlow.html" class="btn btn-primary">Premium Cash-Flow Calculator</a> 
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 ">
                <div class="thumbnail">
                    <img src="<c:url value="/resources/images/moneytree.jpg"/>" alt="">
                    <div class="caption">
                        <h3>Basic Cash-Flow Calculator</h3>
                        <p>Get your initial level Cash Statement here with Basic Cashflow Calculator.</p>
                        <p>
                            <a href="/<%=StaticConstants.Context_ROOT %>/navigate/loadBasicCashFlow.html" class="btn btn-primary">Basic Cash-Flow Calculator</a> 
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h3><spring:message code="project.label.upcoming.feature"/></h3>
            </div>
        </div>

        <!-- Page Features -->
        <div class="row text-center">

            <div class="col-md-3 col-sm-6 ">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>Retirement Calculator</h3>
                        <p>How much you would have at the time of your Retirement.</p>
                        <p>
                            <a href="#" class="btn btn-primary">Retirement Calculator</a> 
                        </p>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.row -->
</div>
</body>


</html>