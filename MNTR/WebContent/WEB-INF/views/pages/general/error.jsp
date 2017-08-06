<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
</head>
<body>
	<div class="panel panel-yellow">
		<div class="panel-heading">
			<h3 class="panel-header">
				<i class="fa fa-exclamation-circle"></i>
				<spring:message code='page.accessdenied.txt1' />
			</h3>
		</div>
		<div class="panel-body">
			<h4 class="panel-header">
				<spring:message code='page.accessdenied.txt2' />
			</h4>
			<h6>
				<c:choose>
					<c:when test="${errmsg != null and not empty errmsg }">
						<div class="alert alert-danger">
							<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${errmsg}
						</div>
					</c:when>
					<c:when
						test="${session[SPRING_SECURITY_LAST_EXCEPTION] != null and not empty session[SPRING_SECURITY_LAST_EXCEPTION] }">
						<div class="alert alert-danger">
							<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${session[SPRING_SECURITY_LAST_EXCEPTION]}
						</div>
					</c:when>
					<c:when
						test="${session[AUTHENTICATION_EXCEPTION] != null and not empty session[AUTHENTICATION_EXCEPTION] }">
						<div class="alert alert-danger">
							<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${session[AUTHENTICATION_EXCEPTION]}
						</div>
					</c:when>
					<c:otherwise>
						<spring:message code='page.accessdenied.txt3' />
					</c:otherwise>
				</c:choose>
			</h6>
			<a href="login" class="btn btn-primary"><spring:message
					code='page.login.txt4' /></a>
		</div>
	</div>
</body>
</html>