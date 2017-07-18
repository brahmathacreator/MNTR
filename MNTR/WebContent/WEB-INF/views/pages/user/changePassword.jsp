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
		$('#form1').parsley().on('field:validated', function() {
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
<spring:message code="passGeneralMax" var="csgPassMax" />
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
<spring:message code="page.general.txt20" var="btnSubmitTxt" />
<spring:message code="page.general.txt21" var="btnCancelTxt" />
<spring:message code="page.changepassword.txt5" var="headerTxt" />
<spring:message code="page.changepassword.txt3" var="pwd" />
<spring:message code="page.changepassword.txt4" var="cpwd" />
<spring:message code="page.changepassword.txt7" var="pwdMsg" />
<!-- Display Text Config -->
</head>
<body>
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="glyphicon glyphicon-plus-sign"></i> ${headerTxt}
			</h3>
		</div>
		<div class="panel-body">
			<sf:form action="${actionURL}" commandName="mObject" role="form"
				autocomplete="false" id="form1" name="form1" method="post"
				data-parsley-validate="data-parsley-validate">
				<fieldset>
					<div class="form-group">
						<label>${pwd}</label>
						<sf:input class="form-control convertInputUpperCase"
							path="password" id="password" type="password" autocomplete="off"
							required="required" data-parsley-required-message="${ttReqYes}"
							data-parsley-minlength="${csgPassMin}"
							data-parsley-minlength-message="${ttMin}${csgPassMin}"
							data-parsley-maxlength="${csgPassMax}"
							data-parsley-maxlength-message="${ttMax}${csgPassMax}"
							data-parsley-pattern="${csgPassPat}"
							data-parsley-pattern-message="${ttAllow2}" data-toggle="tooltip"
							data-html="true"
							title="${ttField}${pwd}${ttDetails}<br>${ttReqYes}<br>${ttMin}${csgPassMin}<br>${ttMax}${csgPassMax}<br>${ttAllow2}" />
						<sf:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<label>${cpwd}</label>
						<sf:input class="form-control convertInputUpperCase"
							path="cPassword" id="cPassword" type="password"
							autocomplete="off" required="required"
							data-parsley-required-message="${ttReqYes}"
							data-parsley-minlength="${csgPassMin}"
							data-parsley-minlength-message="${ttMin}${csgPassMin}"
							data-parsley-maxlength="${csgPassMax}"
							data-parsley-maxlength-message="${ttMax}${csgPassMax}"
							data-parsley-pattern="${csgPassPat}"
							data-parsley-pattern-message="${ttAllow2}" data-toggle="tooltip"
							data-html="true" data-parsley-equalto="#password"
							data-parsley-equalto-message="${pwdMsg}"
							title="${ttField}${pwd}${ttDetails}<br>${ttReqYes}<br>${ttMin}${csgPassMin}<br>${ttMax}${csgPassMax}<br>${ttAllow2}" />
						<sf:errors path="cPassword" class="text-danger" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="${btnSubmitTxt}" /> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
							type="hidden" name="CURDOpt" value="${CURDOpt}" />
						<sf:input type="hidden" path="passRefId" id="passRefId"
							value="${pwdRefId}" />
					</div>
				</fieldset>
			</sf:form>
		</div>
	</div>
</body>
</html>