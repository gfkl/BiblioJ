
<%@ page import="biblioj.Livre"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'livre.label', default: 'Livre')}" />
<title><g:message code="default.listRecherche.label"
		args="[entityName]" /></title>
</head>
<body>
	<a href="#list-livre" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>



		</ul>
	</div>
	<div id="list-listRecherche" class="content scaffold-listRecherche"
		role="main">
		<h1>
			<g:message code="default.listRecherche.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>

					<g:sortableColumn property="titre"
						title="${message(code: 'livre.titre.label', default: 'Titre')}" />

					<th><g:message code="livre.typeDocument.label"
							default="Type Document" /></th>

					<th><g:message code="livre.typeDocument.label"
							default="Retirer" /></th>

				</tr>
			</thead>
			<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<g:set var="find" value="${0}" />
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>
							${(livreInstance.titre)}
						</td>

						<td>
							${(livreInstance.typeDocument)}
						</td>

						<td><g:link controller="Livre" action="removePanier"
								id="${(livreInstance.id)}"
								params="[currentController: 'reservation', currentAction: 'validerPanier', offset: params.offset]">
								<input type="button" value="Remove" class="button" />
							</g:link></td>

					</tr>

				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${livreInstanceTotal}" params="${params}" />
		</div>

		<fieldset class="buttons">
			<g:link controller="reservationLivre" action="validerReservation" params="[test:params.test]">
				<input name="valider reservation" class="button" type="button"
					value="valider reservation" />
			</g:link>
	</div>
</body>
</html>
