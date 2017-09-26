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
<!-- Client Side Validation Config -->
<spring:message code="textGeneralMin" var="csgTxtmin" />
<spring:message code="textGeneralMax" var="csgTxtmax" />
<spring:message code="textGeneralPattern" var="csgTxtPat" />
<spring:message code="textGeneralUserIdPatteren" var="csgTxtUPat" />
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

<!-- Datatable Display Config -->
<spring:message code="SortAscending" var="SortAscending" />
<spring:message code="SortDescending" var="SortDescending" />
<spring:message code="First" var="First" />
<spring:message code="Last" var="Last" />
<spring:message code="Next" var="Next" />
<spring:message code="Previous" var="Previous" />
<spring:message code="EmptyTable" var="EmptyTable" />
<spring:message code="Info" var="Info" />
<spring:message code="InfoEmpty" var="InfoEmpty" />
<spring:message code="LengthMenu" var="LengthMenu" />
<spring:message code="LoadingRecords" var="LoadingRecords" />
<spring:message code="Processing" var="Processing" />
<spring:message code="SearchPlaceholder" var="SearchPlaceholder" />
<spring:message code="ZeroRecords" var="ZeroRecords" />
<!-- Datatable Display Config -->
<!-- Generic Display Text Config -->
<spring:message code="page.general.txt20" var="btnSubmitTxt" />
<spring:message code="page.general.txt21" var="btnCancelTxt" />
<spring:message code="page.general.txt37" var="options" />
<spring:message code="page.general.txt29" var="status" />
<spring:message code="page.general.txt30" var="createdBy" />
<spring:message code="page.general.txt31" var="createdOn" />
<spring:message code="page.general.txt45" var="modifiedBy" />
<spring:message code="page.general.txt32" var="modifiedOn" />
<spring:message code="page.general.txt40" var="active" />
<spring:message code="page.general.txt41" var="inActive" />
<spring:message code="page.general.txt51" var="headCap" />
<spring:message code="page.general.txt52" var="history" />
<!-- Generic Display Text Config -->

<!-- Page Display Text Config -->
<spring:message code="page.roles.txt1" var="roleId" />
<spring:message code="page.roles.txt2" var="roleName" />
<spring:message code="page.roles.txt3" var="desc" />
<!-- Page Display Text Config -->

<sec:authentication property="principal.currentUrlDetails.opsType"
	var="ops" />
<sec:authentication property="principal.currentUrlDetails.url"
	var="menuURL" />
<c:if test="${ops eq 3 }">
	<script type="text/javascript">
function loadData() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var table = $('#table1')
				.DataTable(
						{
							'pagingType' : 'full_numbers',
							'processing' : true,
							'ajax' : {
								'contentType' : 'application/json',
								'url' : 'getRoleList',
								'type' : 'POST',
								'data' : function(d) {
									return JSON.stringify(d);
								},
								'beforeSend' : function(request) {
									request.setRequestHeader(header, token);
								},
								'error' : function(xhr) {
									alert('<spring:message code="page.general.txt18" />');
								}
							},
							'oLanguage': {
							    'oAria': {
							        'sSortAscending': '${SortAscending}',
							        'sSortDescending': '${SortDescending}'
							    },
							    'oPaginate': {
							        'sFirst': '${First}',
							        'sLast': '${Last}',
							        'sNext': '${Next}',
							        'sPrevious': '${Previous}'
							    },
							    'sEmptyTable': '${EmptyTable}',
							    'sInfo': '${Info}',
							    'sInfoEmpty': '${InfoEmpty}',
							    'sInfoFiltered':'',
							    'sInfoPostFix': '',
							    'sDecimal': '',
							    'sThousands': ',',
							    'sLengthMenu': '${LengthMenu}',
							    'sLoadingRecords': '${LoadingRecords}',
							    'sProcessing': '${Processing}',
							    'sSearch': '',
							    'sSearchPlaceholder': '${SearchPlaceholder}',
							    'sUrl': '',
							    'sZeroRecords': '${ZeroRecords}'
							 },
							'serverSide' : true,
							'order': [[ 5, 'desc' ]],
							columns : [
									{
										data : 'roleId'
									},
									{
										data : 'roleName'
									},
									{
										data : 'description',
										orderable : false,
										searchable : false
									},
									{
										data : '${status}',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											if (row.status == '1')
												return "<spring:message code='page.general.txt40' />";
											else
												return "<spring:message code='page.general.txt41' />";
										}
									},
									{
										data : '${options}',
										orderable : false,
										searchable : false,
										render : function(data, type, row) {
											return ("<a  href='#' onclick='return listFormSubmit(&quot;${menuURL}SELECT&quot;,&quot;7&quot;,&quot;"
													+ row.roleId
													+ "&quot;);'><spring:message code='page.general.txt25' /></a> / <a  href='#' onclick='return listFormSubmit(&quot;${menuURL}SELECT&quot;,&quot;2&quot;,&quot;"
													+ row.roleId
													+ "&quot;);'><spring:message code='page.general.txt24' /></a> / <a  href='#' onclick='return listFormSubmit(&quot;${menuURL}SELECT&quot;,&quot;4&quot;,&quot;"
													+ row.roleId + "&quot;);'><spring:message code='page.general.txt26' /></a>");
										}
									},{
										data : 'createdDt',
										orderable : false,
										searchable : false,
										visible: false
									} ]
						});
	}
</script>
</c:if>
<c:if test="${(ops eq 1) or (ops eq 2) }">
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
</c:if>
</head>
<body <c:if test="${ops eq 3 }"> onload="return loadData();"</c:if>>
	<div class="col-lg-12">
		<c:choose>
			<c:when test="${ops eq 3 }">
				<table id="table1"
					class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>${roleId}</th>
							<th>${roleName}</th>
							<th>${desc}</th>
							<th>${status}</th>
							<th>${options}</th>
						</tr>
					</thead>
				</table>
			</c:when>
			<c:when
				test="${(ops eq 1) or (ops eq 2) or (ops eq 4) or (ops eq 7)}">
				<sf:form action="${actionURL}" commandName="mObject" role="form"
					autocomplete="false" id="form1" name="form1" method="post"
					data-parsley-validate="data-parsley-validate">
					<c:if test="${ops ne 1}">
						<fieldset class="title">
							<legend class="grpTitleTextColor">${headCap}
								${mObject.roleId}</legend>
						</fieldset>
					</c:if>
					<sf:hidden path="roleId" />
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label>${roleName}</label>
								<c:choose>
									<c:when test="${(ops eq 1) or (ops eq 2)}">
										<sf:input class="form-control convertInputUpperCase"
											path="roleName" id="roleName" type="text" autocomplete="off"
											required="required"
											data-parsley-required-message="${ttReqYes}"
											data-parsley-minlength="${csgTxtmin}"
											data-parsley-minlength-message="${ttMin}${csgTxtmin}"
											data-parsley-maxlength="${csgTxtmax}"
											data-parsley-maxlength-message="${ttMax}${csgTxtmax}"
											data-parsley-pattern="${csgTxtUPat}"
											data-parsley-pattern-message="${ttAllow3}"
											data-toggle="tooltip" data-html="true"
											title="${ttField}${roleName}${ttDetails}<br>${ttReqYes}<br>${ttMin}${csgTxtmin}<br>${ttMax}${csgTxtmax}<br>${ttAllow3}" />
										<sf:errors path="roleName" class="text-danger" />
									</c:when>
									<c:otherwise>
										<p>${mObject.roleName}</p>
										<sf:hidden path="roleName" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label>${status}</label>
								<c:choose>
									<c:when test="${(ops eq 1) or (ops eq 2)}">
										<sf:select class="form-control" id="status" path="status"
											required="required"
											data-parsley-required-message="${ttReqYes}"
											data-toggle="tooltip" data-html="true"
											title="${ttField}${status}${ttDetails}<br>${ttReqYes}">
											<sf:option value="">
												<spring:message code="page.general.txt47" />
											</sf:option>
											<sf:option value="1">${active}</sf:option>
											<sf:option value="0">${inActive}</sf:option>
										</sf:select>
										<sf:errors path="status" class="text-danger" />
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${mObject.status eq 1 }">
												<p>${active}</p>
											</c:when>
											<c:otherwise>
												<p>${inActive}</p>
											</c:otherwise>
										</c:choose>
										<sf:hidden path="status" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label>${desc}</label>
								<c:choose>
									<c:when test="${(ops eq 1) or (ops eq 2)}">
										<sf:input class="form-control" path="description"
											id="description" type="text" autocomplete="off"
											data-parsley-minlength="${csgTxtmin}"
											data-parsley-minlength-message="${ttMin}${csgTxtmin}"
											data-parsley-maxlength="${csgTxtmax}"
											data-parsley-maxlength-message="${ttMax}${csgTxtmax}"
											data-parsley-pattern="${csgTxtPat}"
											data-parsley-pattern-message="${ttAllow1}"
											data-toggle="tooltip" data-html="true"
											title="${ttField}${desc}${ttDetails}<br>${ttMin}${csgTxtmin}<br>${ttMax}${csgTxtmax}<br>${ttAllow1}" />
										<sf:errors path="description" class="text-danger" />
									</c:when>
									<c:otherwise>
										<p>${mObject.description}</p>
										<sf:hidden path="description" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<c:if test="${ops ne 1}">
						<fieldset class="title">
							<legend class="grpTitleTextColor">${history}</legend>
						</fieldset>
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label>${createdOn}</label>
									<p>${mObject.createdDt}</p>
									<sf:hidden path="createdDt" />
								</div>
								<div class="form-group">
									<label>${modifiedOn}</label>
									<p>${mObject.modifiedDt}</p>
									<sf:hidden path="modifiedDt" />
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>${createdBy}</label>
									<p>${mObject.createdBy}</p>
									<sf:hidden path="createdBy" />
								</div>
								<div class="form-group">
									<label>${modifiedBy}</label>
									<p>${mObject.modifiedBy}</p>
									<sf:hidden path="modifiedBy" />
								</div>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-lg-12">
							<hr />
							<c:if test="${(ops eq 1) or (ops eq 2) or (ops eq 4)}">
								<button type="submit" class="btn btn-success">
									<span class="glyphicon glyphicon-ok-sign pull-left"></span>&nbsp;
									<spring:message code='page.general.txt43' />
								</button>
							</c:if>
							<button type="button" class="btn btn-default"
								onclick="return navFormSubmit('navigate','<sec:authentication
									property='principal.currentUrlDetails.url' />','<sec:authentication
									property='principal.currentUrlDetails.menuId' />','1','3');">
								<span
									class="glyphicon glyphicon glyphicon-remove-sign pull-left"></span>&nbsp;
								<spring:message code='page.general.txt44' />
							</button>
							<input type="hidden" name="CURDOpt" id="CURDOpt" value="${ops}" />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</sf:form>
			</c:when>
			<c:otherwise>
				<spring:message code="page.general.txt42" />
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>