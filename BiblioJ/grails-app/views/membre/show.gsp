
<%@ page import="biblioj.Membre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'membre.label', default: 'Membre')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-membre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-membre" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list membre">
			
				<g:if test="${membreInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="membre.login.label" default="Login" /></span>
					
						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${membreInstance}" field="login"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${membreInstance?.mdp}">
				<li class="fieldcontain">
					<span id="mdp-label" class="property-label"><g:message code="membre.mdp.label" default="Mdp" /></span>
					
						<span class="property-value" aria-labelledby="mdp-label"><g:fieldValue bean="${membreInstance}" field="mdp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${membreInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="membre.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${membreInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${membreInstance?.id}" />
					<g:link class="edit" action="edit" id="${membreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
