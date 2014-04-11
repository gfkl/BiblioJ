
<%@ page import="biblioj.ReservationLivre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservationLivre.label', default: 'ReservationLivre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reservationLivre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reservationLivre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="reservationLivre.reservation.label" default="Reservation" /></th>
					
						<th><g:message code="reservationLivre.livre.label" default="Livre" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationLivreInstanceList}" status="i" var="reservationLivreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservationLivreInstance.id}">${fieldValue(bean: reservationLivreInstance, field: "reservation")}</g:link></td>
					
						<td>${fieldValue(bean: reservationLivreInstance, field: "livre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationLivreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
