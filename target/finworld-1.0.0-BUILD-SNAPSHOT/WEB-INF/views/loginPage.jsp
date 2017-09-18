<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <form action="<c:url value='/j_spring_security_check'/>" method="post" id="loginForm"> --%>
<sec:authorize access="isAnonymous()">
<form method="post" action="<c:url value='/j_spring_security_check'/>" >
	    <div class="col-lg-6">
			<div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
			<c:if test="${error != null}">
			<div class="alert alert-danger">
         		<strong>${error}</strong>
         	</div>
         	</c:if>
         	<c:if test="${msg != null}">
			<div class="alert alert-success">
         		<strong>${msg}</strong>
         	</div>
			</c:if>
			<div class="form-group">
                   <label for="firstName">Enter User Id</label>
                   <div class="input-group">
                       <input type="text" class="form-control" name="j_username" id="j_username" placeholder="Enter User Id" required>
                       <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                   </div>
               </div>
               <div class="form-group">
                   <label for="userPassword">Password</label>
                   <div class="input-group">
                       <input type="password" class="form-control" id="j_password" name="j_password" placeholder="Enter Password" required>
                       <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                   </div>
               </div>
               <input type="submit" name="submit" id="submit" value="Login" class="btn btn-info pull-right">
		</div>
     
</form>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
</sec:authorize>
<%-- 
<form action="/<%=StaticConstants.Context_ROOT %>/authenticate.html" method="post" id="loginForm">

	    <div class="col-lg-6">
			<div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
			<div class="form-group">
                   <label for="firstName">Enter User Id</label>
                   <div class="input-group">
                       <input type="text" class="form-control" name="userName" id="userName" placeholder="Enter User Id" required>
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
               <input type="submit" name="submit" id="submit" value="Login" class="btn btn-info pull-right">
		</div>
     
</form> 
 --%>