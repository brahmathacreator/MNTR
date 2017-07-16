<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<script type="text/javascript">
	$(function() {
		$('#suRegForm').parsley().on('field:validated', function() {
			var ok = $('.parsley-error').length === 0;
			if (!ok)
				return false;
		}).on('form:submit', function() {
			return true;
		});
	});
</script>
<!-- Client Side Validation Config -->
<spring:message code="textGeneralMin" var="csgTxtmin" />
<spring:message code="textGeneralMax" var="csgTxtmax" />
<spring:message code="textGeneralPattern" var="csgTxtPat" />
<spring:message code="textGeneralUserIdPatteren" var="csgTxtUPat" />
<spring:message code="passGeneralMin" var="csgPassMin" />
<spring:message code="passGeneralMin" var="csgPassMax" />
<spring:message code="passGeneralPattern" var="csgPassPat" />
<spring:message code="phnoGeneralPattern" var="csgPhonePat" />
<spring:message code="phnoGeneralMin" var="csgPhoneMin" />
<spring:message code="phnoGeneralMax" var="csgPhoneMax" />
<!-- Client Side Validation Config -->

<!-- Tool Tip Config -->
<spring:message code="page.general.txt2" var="ttField" />
<spring:message code="page.general.txt3" var="ttDetails" />
<spring:message code="page.general.txt6" var="ttReqYes" />
<spring:message code="page.general.txt7" var="ttReqNo" />
<spring:message code="page.general.txt4" var="ttMin" />
<spring:message code="page.general.txt5" var="ttMax" />
<spring:message code="page.general.txt8" var="ttAllow1" />
<spring:message code="page.general.txt9" var="ttAllow2" />
<spring:message code="page.general.txt10" var="ttAllow3" />
<spring:message code="page.general.txt11" var="ttAllow4" />
<!-- Tool Tip  Config -->

<!-- Display Text Config -->
<spring:message code="page.saregister.txt1" var="userName" />
<spring:message code="page.saregister.txt2" var="userId" />
<spring:message code="page.saregister.txt3" var="email" />
<spring:message code="page.saregister.txt5" var="phno" />
<!-- Display Text Config -->
</head>
<body>
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="glyphicon glyphicon-plus-sign"></i>
				<spring:message code="page.saregister.txt0" />
			</h3>
		</div>
		<div class="panel-body">
			<sf:form action="${actionURL}" commandName="user" role="form"
				autocomplete="false" id="suRegForm" name="suRegForm" method="post"
				data-parsley-validate="data-parsley-validate">

				<fieldset>
					<div class="form-group">
						<label>${userId}</label>
						<sf:input class="form-control convertInputUpperCase" path="userId"
							id="userId" type="text" autocomplete="off" required="required"
							data-parsley-required-message="${ttReqYes}"
							data-parsley-minlength="${csgTxtmin}"
							data-parsley-minlength-message="${ttMin}${csgTxtmin}"
							data-parsley-maxlength="${csgTxtmax}"
							data-parsley-maxlength-message="${ttMax}${csgTxtmax}"
							data-parsley-pattern="${csgTxtUPat}"
							data-parsley-pattern-message="${ttAllow3}" data-toggle="tooltip"
							data-html="true"
							title="${ttField}${userId}${ttDetails}<br>${ttReqYes}<br>${ttMin}${csgTxtmin}<br>${ttMax}${csgTxtmax}<br>${ttAllow3}" />
						<sf:errors path="userId" class="text-danger" />
					</div>
					<div class="form-group">
						<label>${userName}</label>
						<sf:input class="form-control convertInputUpperCase"
							path="userName" id="userName" type="text" autocomplete="off"
							required="required" data-parsley-required-message="${ttReqYes}"
							data-parsley-minlength="${csgTxtmin}"
							data-parsley-minlength-message="${ttMin}${csgTxtmin}"
							data-parsley-maxlength="${csgTxtmax}"
							data-parsley-maxlength-message="${ttMax}${csgTxtmax}"
							data-parsley-pattern="${csgTxtPat}"
							data-parsley-pattern-message="${ttAllow1}" data-toggle="tooltip"
							data-html="true"
							title="${ttField}${userName}${ttDetails}<br>${ttReqYes}<br>${ttMin}${csgTxtmin}<br>${ttMax}${csgTxtmax}<br>${ttAllow1}" />
						<sf:errors path="userName" class="text-danger" />
					</div>
					<div class="form-group">
						<label>${email}</label>
						<sf:input class="form-control" path="email" id="email"
							type="email" data-parsley-trigger="change" autocomplete="off"
							required="required" data-parsley-required-message="${ttReqYes}"
							data-toggle="tooltip" data-html="true"
							title="${ttField}${email}${ttDetails}<br>${ttReqYes}" />
						<sf:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<label>${phno}</label>
						<sf:input class="form-control" path="phno" id="phno" type="text"
							autocomplete="off" required="required"
							data-parsley-required-message="${ttReqYes}"
							data-parsley-pattern="${csgPhonePat}"
							data-parsley-pattern-message="${ttAllow4}" data-toggle="tooltip"
							data-html="true"
							title="${ttField}${phno}${ttDetails}<br>${ttReqYes}<br>${ttAllow4}" />
						<sf:errors path="phno" class="text-danger" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="<spring:message code='page.saregister.txt4' />" /> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="hidden" name="CURDOpt"
							value="1" />
					</div>
				</fieldset>
			</sf:form>
		</div>
	</div>
</body>
</html>