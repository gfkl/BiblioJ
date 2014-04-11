
<%@ page import="biblioj.AuteurLivre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auteurLivre.label', default: 'AuteurLivre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-auteurLivre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-auteurLivre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="auteurLivre.auteur.label" default="Auteur" /></th>
					
						<th><g:message code="auteurLivre.livre.label" default="Livre" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${auteurLivreInstanceList}" status="i" var="auteurLivreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${auteurLivreInstance.id}">${fieldValue(bean: auteurLivreInstance, field: "auteur")}</g:link></td>
					
						<td>${fieldValue(bean: auteurLivreInstance, field: "livre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${auteurLivreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
