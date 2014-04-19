
<%@ page import="biblioj.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservation">
			
				<g:if test="${reservationInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="reservation.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${reservationInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.reservation}">
				<li class="fieldcontain">
					<span id="reservation-label" class="property-label"><g:message code="reservation.reservation.label" default="Reservation" /></span>
					
						<span class="property-value" aria-labelledby="reservation-label"><g:formatDate date="${reservationInstance?.reservation}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.membre}">
				<li class="fieldcontain">
					<span id="membre-label" class="property-label"><g:message code="reservation.membre.label" default="Membre" /></span>
					
						<span class="property-value" aria-labelledby="membre-label"><g:link controller="membre" action="show" id="${reservationInstance?.membre?.id}">${reservationInstance?.membre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.reservationLivres}">
				<li class="fieldcontain">
					<span id="reservationLivres-label" class="property-label"><g:message code="reservation.reservationLivres.label" default="Reservation Livres" /></span>
					
						<g:each in="${reservationInstance.reservationLivres}" var="r">
						<span class="property-value" aria-labelledby="reservationLivres-label"><g:link controller="reservationLivre" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
