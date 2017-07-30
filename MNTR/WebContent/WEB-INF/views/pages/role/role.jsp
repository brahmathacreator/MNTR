<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>

<!-- Display Text Config -->
<spring:message code="page.roles.txt1" var="roleId" />
<spring:message code="page.roles.txt2" var="roleName" />
<!-- Display Text Config -->
<sec:csrfMetaTags />
<script type="text/javascript">
	$(document).ready(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$('#table1').DataTable({
			"processing" : true,
			"serverSide" : true,
			"order" : [ [ 1, "desc" ] ],
			"pagingType" : "full_numbers",
			"ajax" : {
				"contentType" : "application/json; charset=utf-8",
				"url" : "getRoleList",
				"type" : "GET",
				"data" : function(d) {
					return encodeURIComponent(JSON.stringify(d));
				},
				"beforeSend" : function(request) {
					request.setRequestHeader(header, token);
				}
			},
			columns : [ {
				data : 'roleId'
			}, {
				data : 'roleName'
			}, {
				data : 'status'
			}, {
				data : 'createdBy'
			}, {
				data : 'createdDT'
			}, {
				data : 'modifiedBy'
			}, {
				data : 'modifiedDT'
			} ]
		});
	});
</script>

</head>
<body>
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="table1"
				class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>roleId</th>
						<th>roleName</th>
						<th>status</th>
						<th>createdBy</th>
						<th>createdDT</th>
						<th>modifiedBy</th>
						<th>modifiedDT</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>