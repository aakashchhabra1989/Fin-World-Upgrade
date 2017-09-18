<!DOCTYPE html>
<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Finance World</title>
	<jsp:include page="common.jsp"></jsp:include>

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
	
    <!-- Page Content -->
    <div class="container" id="maincontainer">
    		
    	<jsp:include page="home.jsp"></jsp:include>
    	
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>

</body>

</html>
