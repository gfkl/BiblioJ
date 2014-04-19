
<%@ page import="biblioj.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.listRecherche.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-listRecherche" class="content scaffold-listRecherche" role="main">
			<h1><g:message code="default.listRecherche.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="titre" title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					
						<g:sortableColumn property="nombreExemplairesDisponibles" title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Nombre Exemplaires Disponibles')}" />
					
						<th><g:message code="livre.typeDocument.label" default="Type Document" /></th>
						
						<th><g:message code="Auteur" default="Auteur"/></th>
						
						<th><g:message code="livre.typeDocument.label" default="Emprunter" /></th>
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<g:set var="find" value="${0}"/>
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${(livreInstance.get('livre')).id}">${(livreInstance.get('livre')).titre}</g:link></td>
					
						<td>${(livreInstance.get('livre')).nombreExemplairesDisponibles}</td>
					
						<td>${(livreInstance.get('livre')).typeDocument}</td>
						
						<g:set var="auteurLivreList" value="${(livreInstance.get('livre')).auteurLivres}" />
						
						<td>
							<ul>
							<g:each in="${auteurLivreList}" status="j" var="auteurLivreInstance">
								<li>
									${fieldValue(bean: auteurLivreInstance, field: "auteur")}
								</li>
							</g:each>
							</ul>
						</td>
						
						<g:if test="${session?.user}">
							<g:each in="${session.panier}" var="count" >
								<g:if test="${((livreInstance.get('livre').titre.toString()).equals(count.titre.toString()))}">
									<g:set var="find" value="${1}"/>
									<td><g:link action="removePanier" id="${(livreInstance.get('livre')).id}" params="[currentController: params.controller, currentAction: params.action, offset: params.offset]"> 
										<input type="button" value="Remove" class="button"/></g:link></td>
								</g:if>
							</g:each>
							<g:if test="${find == 0}">
								<g:if test="${(livreInstance.get('livre'))?.nombreExemplairesDisponibles}">
									<td><g:link action="emprunter" id="${(livreInstance.get('livre')).id}" params="[currentController: params.controller, currentAction: params.action, offset: params.offset]"> 
										<input type="button" value="emprunter" class="button"/></g:link></td>
								</g:if>
								<g:else>
									<td><p>Non disponible</p></td>
								</g:else>
							</g:if>
							
						</g:if>
						<g:else>
							<g:if test="${(livreInstance.get('livre'))?.nombreExemplairesDisponibles}">
							<td><g:link action="emprunter" id="${(livreInstance.get('livre'))?.nombreExemplairesDisponibles}" params="[currentController: params.controller, currentAction: params.action, offset: params.offset]"> 
									<input type="button" value="emprunter" class="button"/></g:link></td>
							</g:if>
							<g:else>
								<td><p>Non disponible</p></td>
							</g:else>
						</g:else>
					</tr>

				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${livreInstanceTotal}" action="listRecherche" params="${params}"/>
			</div>
		</div>
	</body>
</html>
