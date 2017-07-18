<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
</head>
<body>
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<spring:message code="page.login.txt0" />
			</h3>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${suaCount eq 1 }">
					<form role="form">
						<fieldset>
							<div class="form-group">
								<input class="form-control"
									placeholder="<spring:message code="page.login.txt1" />"
									name="UserId" type="text" autofocus>
							</div>
							<div class="form-group">
								<input class="form-control"
									placeholder="<spring:message code="page.login.txt2" />"
									name="password" type="password" value="">
							</div>
							<div class="checkbox">
								<label> <input name="remember" type="checkbox"
									value="<spring:message code="page.login.txt3" />"> <spring:message
										code="page.login.txt3" />
								</label>
							</div>
							<a href="index.html" class="btn btn-lg btn-success btn-block"><spring:message
									code="page.login.txt4" /></a>
						</fieldset>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
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