<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<spring:message code="page.changepassword.txt3" var="pwd" />
<spring:message code="page.saregister.txt2" var="userId" />
<!-- Display Text Config -->
</head>
<body>
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="fa fa-chevron-circle-right"></i>
				<spring:message code="page.login.txt0" />
			</h3>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${suaCount eq 1 }">
					<c:url var="actionURL" value="/login" />
					<form action="${actionURL}" role="form" autocomplete="false"
						id="form1" name="form1" method="post"
						data-parsley-validate="data-parsley-validate">
						<fieldset>
							<div class="form-group">
								<label>${userId}</label> <input
									class="form-control convertInputUpperCase" name="userId"
									id="userId" type="text" autocomplete="off" required="required"
									data-parsley-required-message="${ttReqYes}"
									data-toggle="tooltip" data-html="true"
									title="${ttField}${userId}${ttDetails}<br>${ttReqYes}" />
							</div>
							<div class="form-group">
								<label>${pwd}</label> <input
									class="form-control convertInputUpperCase" name="password"
									id="password" type="password" autocomplete="off"
									required="required" data-parsley-required-message="${ttReqYes}"
									data-toggle="tooltip" data-html="true"
									title="${ttField}${pwd}${ttDetails}<br>${ttReqYes}" />
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-lg btn-success btn-block"
									value="<spring:message code='page.login.txt4' />" /> <input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</div>
						</fieldset>
					</form>
				</c:when>
				<c:otherwise>
					<form action="${actionURL}" method="post">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="<spring:message code='page.login.txt5' />"></input> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>