<!DOCTYPE html>
<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

	<head>	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title><spring:message code="project.title"/></title>
		<%-- <tiles:insertAttribute name="title" ignore="true" /> --%>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
    <%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
         <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script> --%>
    <%--
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script> 
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script> --%>


    
    <script type="text/javascript" src="<c:url value="/resources/js/finworld.js" />"></script>
	    <!-- Bootstrap Core CSS -->
	    <%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" /> --%>
	    <%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />"/> --%>	    
	    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/fonts/font-awesome/css/font-awesome.min.css" />" />
	    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/heroic-features.css" />" />
	
	</head>


	<body>
	
	    <tiles:insertAttribute name="header" />
	    
	    <div class="container" id="maincontainer">	    
		    <tiles:insertAttribute name="body" />
		</div>		
		
		<div id="footer">
	       <tiles:insertAttribute name="footer" />
		</div>
	</body>

</html>