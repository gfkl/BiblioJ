
<%@ page import="biblioj.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-livre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="titre" title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					
						<g:sortableColumn property="nombreExemplaires" title="${message(code: 'livre.nombreExemplaires.label', default: 'Nombre Exemplaires')}" />
					
						<g:sortableColumn property="nombreExemplairesDisponibles" title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Nombre Exemplaires Disponibles')}" />
					
						<th><g:message code="livre.typeDocument.label" default="Type Document" /></th>
					
						<th><g:message code="livre.typeDocument.label" default="Emprunter" /></th>
					
					</tr>
				</thead>
				<tbody>
				
				
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<g:set var="find" value="${0}"/>
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td><g:link action="show" id="${livreInstance.id}">${fieldValue(bean: livreInstance, field: "titre")}</g:link></td>
					
						<td>${fieldValue(bean: livreInstance, field: "nombreExemplaires")}</td>
					
						<td>${fieldValue(bean: livreInstance, field: "nombreExemplairesDisponibles")}</td>
					
						<td>${fieldValue(bean: livreInstance, field: "typeDocument")}</td>
						
						<g:if test="${session?.user}">
							<g:each in="${session.panier}" var="count" >
								<g:if test="${(fieldValue(bean: livreInstance, field:'titre').toString()).equals(count.titre.toString())}">
									<td><g:link action="removePanier" id="${livreInstance.id}" params="[currentController: params.controller, currentAction: 'list', offset: params.offset]"> 
										<input type="button" value="Retirer" class="button"/></g:link></td>
								
									<g:set var="find" value="${1}"/>
								</g:if>
							</g:each>
							<g:if test="${find == 0}">
								<g:if test="${livreInstance?.nombreExemplairesDisponibles}">
								<td><g:link action="emprunter" id="${livreInstance.id}" params="[currentController: params.controller, currentAction: 'list', offset: params.offset]"> 
										<input type="button" value="Emprunter" class="button"/></g:link></td>
								</g:if>
								<g:else>
									<td><p>Non disponible</p></td>
								</g:else>
							</g:if>
							
						</g:if>
						<g:else>
							<g:if test="${livreInstance?.nombreExemplairesDisponibles}">
							<td><g:link action="emprunter" id="${livreInstance.id}" params="[currentController: params.controller, currentAction: 'list', offset: params.offset]"> 
									<input type="button" value="Emprunter" class="button"/></g:link></td>
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
				<g:paginate total="${livreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
