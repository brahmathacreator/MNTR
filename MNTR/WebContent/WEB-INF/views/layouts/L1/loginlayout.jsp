<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>

<head>
<title><spring:message code="page.loginlayout.txt1" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="<spring:theme code='page.style1.path'/>" rel="stylesheet" />
<!-- Custom CSS -->
<link href="<spring:theme code='page.style2.path'/>" rel="stylesheet" />

<!-- Bootstrap Core CSS - RTL -->
<link href="<spring:theme code='page.style3.path'/>" rel="stylesheet" />
<!-- Custom CSS - RTL -->
<link href="<spring:theme code='page.style4.path'/>" rel="stylesheet" />

<!-- Custom Fonts -->
<link href="<spring:theme code='page.font1.path'/>" rel="stylesheet"
	type="text/css" />

<!-- Custom JS -->
<script src="<spring:theme code='page.script0.path'/>"></script>
<!-- jQuery -->
<script src="<spring:theme code='page.script1.path'/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<spring:theme code='page.script2.path'/>"></script>
<!-- parsley min JavaScript -->
<script src="<spring:theme code='page.script3.path'/>"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-3">

				<c:if test="${succmsg != null and not empty succmsg}">
					<div class="alert alert-success">
						<strong><spring:message code="page.general.txt12" />&nbsp;</strong>${succmsg}
					</div>
				</c:if>
				<c:if test="${errmsg != null and not empty errmsg}">
					<div class="alert alert-danger">
						<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${errmsg}
					</div>
				</c:if>
				<c:if
					test="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'] != null and not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'] }">
					<div class="alert alert-danger">
						<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}
					</div>
				</c:if>
				<c:if
					test="${sessionScope[AUTHENTICATION_EXCEPTION] != null and not empty sessionScope[AUTHENTICATION_EXCEPTION] }">
					<div class="alert alert-danger">
						<strong><spring:message code="page.general.txt13" />&nbsp;</strong>${sessionScope[AUTHENTICATION_EXCEPTION]}
					</div>
				</c:if>
				<c:if test="${infomsg != null and not empty infomsg}">
					<div class="alert alert-info">
						<strong><spring:message code="page.general.txt14" />&nbsp;</strong>${infomsg}
					</div>
				</c:if>
				<c:if test="${warnmsg != null and not empty warnmsg}">
					<div class="alert alert-warning">
						<strong><spring:message code="page.general.txt15" />&nbsp;</strong>${warnmsg}
					</div>
				</c:if>
				<section id="login-site-content"> <tiles:insertAttribute
					name="body" /> </section>
			</div>
		</div>
	</div>
	<footer class="footer">
	<div class="container">
		<form>
			<div class="row">
				<div class="col-lg-2">
					<a href="?lang=en&theme=L1T1"> <spring:message
							code="page.loginlayout.txt2" /></a> &nbsp; <label
						class="text-primary"> <spring:message
							code="page.general.txt1" />
					</label> &nbsp; <a href="?lang=ar&theme=L1T2"><spring:message
							code="page.loginlayout.txt3" /></a>
				</div>
				<div class="col-lg-10">
					<div class="footerrights">
						<p class="text-primary">
							<spring:message code="page.loginlayout.txt4" />
						</p>
					</div>
				</div>
			</div>
		</form>
	</div>
	</footer>
</body>
</html>