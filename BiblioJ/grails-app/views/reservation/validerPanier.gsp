<%@ page import="biblioj.Reservation"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'reservation.label', default: 'Reservation')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<div id="create-reservation" class="content scaffold-create"
		role="main">
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${reservationInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${reservationInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

		<fieldset class="buttons">
			<g:link controller="reservationLivre" action="validerReservation">

				<input name="valider reservation" class="save"
					value="valider reservation"
					params="[confirmation:params.confirmation]" />
			</g:link>
		</fieldset>
	</div>
</body>
</html>
