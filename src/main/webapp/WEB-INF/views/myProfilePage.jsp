<%@page import="com.achhabra.finworld.util.StaticConstants"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="/finworld/navigate/updateProfile.html" method="post" id="loginForm" modelAttribute="entityBean">
	<div class="col-lg-6">
		<div class="form-group">
			<label for="userId" class="col-sm-3 control-label required"><spring:message code="project.profile.userid"/></label>
			<div class="col-sm-9" style="margin-bottom:5px;">
				<form:input type="hidden" path="id" id="id" />
				<form:input type="text" readonly="true" class="form-control" path="userId" id="userId" placeholder="User Id" data-validation-required-message="User Id is required"/>
			</div>
		</div>
		<div class="form-group">
			<label for="firstName" class="col-sm-3 control-label required"><spring:message code="project.profile.first.name"/></label>
			<div class="col-sm-9" style="margin-bottom:5px;">
				<form:input type="text" class="form-control" path="firstName" id="firstName" placeholder="First Name" data-validation-required-message="First Name is required"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="lastName" class="col-sm-3 control-label"><spring:message code="project.profile.last.name"/></label>
			<div class="col-sm-9" style="margin-bottom:5px;">
				<form:input type="text" class="form-control" path="lastName" id="lastName" placeholder="Last Name" data-validation-required-message="Last Name is required"/>
			</div>
		</div>
 		<div class="form-group">
			<label for="primaryEmailId" class="col-sm-3 control-label required"><spring:message code="project.profile.emailid"/></label>
			<div class="col-sm-9" style="margin-bottom:5px;">
				<form:input type="text" class="form-control" path="primaryEmailId" id="primaryEmailId" placeholder="Email Id" data-validation-required-message="Email Id is required"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="primaryPhoneNo" class="col-sm-3 control-label"><spring:message code="project.profile.contact.no"/></label>
			<div class="col-sm-9" style="margin-bottom:5px;">
				<form:input type="text" class="form-control" path="primaryPhoneNo" id="primaryPhoneNo" placeholder="Contact No" data-validation-required-message="Contact No is required"/>
			</div>
		</div>
		
		<input type="submit" name="submit" id="submit" value="Update" class="btn btn-info pull-right" style="margin:10px;">
	</div>
     
</form:form>
