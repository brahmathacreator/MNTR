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
<script type="text/javascript">
	function loadData(id, ajaxURL) {
		$('#' + id).DataTable({
			"responsive" : true,
			"autoFill" : true,
			"processing" : true,
			"serverSide" : true,
			"stateSave" : false,
			"displayLength" : 10,
			"displayStart" : 0,
			"ajax" : {
				"url" : ajaxURL,
				"type" : "POST"
			},
			"columns" : [ {
				"mData" : "roleId"
			}, {
				"mData" : "roleName"
			} ],
			"order" : [ [ 2, "asc" ] ],
			"pagingType" : "full_numbers",
			"deferLoading" : 15
		});
	}
</script>

</head>
<body onload="loadData('table1','getRoleList');">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="table1"
				class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>${roleId}</th>
						<th>${roleName}</th>
						<th><spring:message code="page.general.txt29" /></th>
						<th><spring:message code="page.general.txt37" /></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>