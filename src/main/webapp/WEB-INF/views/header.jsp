<!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@page import="com.achhabra.finworld.util.StaticConstants"%>
    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> -->
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%-- <a class="navbar-brand" href="#" onClick="loadPageInMainDiv('/<%=StaticConstants.Context_ROOT%>/jsp/home.jsp');">Home</a> --%>
                <a class="navbar-brand" href="/<%=StaticConstants.Context_ROOT %>/navigate/loadHomePage.html">Fin-World</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="navbar-collapse collapse" id="navbar-menu-collapse">
                <ul class="nav navbar-nav navbar-right">
	                <li>
	                	<a href="/<%=StaticConstants.Context_ROOT %>/navigate/loadHomePage.html">Home</a>
	                </li>
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadServices.html">Services</a>
                    </li>
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadPromotionalLinks.html">Promotional Links</a>
                    </li>
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadContactUs.html">Contact Us</a>
                    </li>
                    <%-- <%if(request.getSession().getAttribute("userName")==null){ %> --%>
                    <sec:authorize access="isAnonymous()">
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadLoginPage.html">Login</a>
                    </li>
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadRegisteration.html">Register</a>
                    </li>
                    </sec:authorize>
					<%-- <%}else{ %> --%>
					<sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadMyProfile.html">My Profile</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Portfolio<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        	<li class="dropdown-header">saved profile</li>
                        	<c:forEach items="${userPortfolios}" var="userPortfolio" varStatus="status">
                        		<li><a href="/<%=StaticConstants.Context_ROOT%>/navigate/premium/loadCashFlow.html?portfolioId=${userPortfolio.id}">${userPortfolio.value}</a></li>
							</c:forEach>
                        </ul>
                    </li>
                    
                    <li>
                        <%-- <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadLoginPage.html?logout=true">Logout</a> --%>
                        <a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
                    </li>
                    <li>
                        <%-- <a href="/<%=StaticConstants.Context_ROOT%>/navigate/loadLoginPage.html?logout=true">Logout</a> --%>
                        <a href="#">Welcome ${userDetails.username}</a>
                    </li>
                    </sec:authorize>
                    <%-- <%}  %> --%>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<c:if test="${not empty message}">
	<div class="alert alert-success">
		<strong>${message}</strong>
	</div>    
</c:if>
<c:if test="${not empty errMessage}">
	<div class="alert alert-danger">
		<strong>${errMessage}</strong>
	</div>    
</c:if>
