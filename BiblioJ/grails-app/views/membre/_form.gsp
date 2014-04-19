<%@ page import="biblioj.Membre" %>



<div class="fieldcontain ${hasErrors(bean: membreInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="membre.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${membreInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: membreInstance, field: 'mdp', 'error')} required">
	<label for="mdp">
		<g:message code="membre.mdp.label" default="Mdp" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="mdp" required="" value="${membreInstance?.mdp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: membreInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="membre.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${membreInstance.constraints.status.inList}" value="${membreInstance?.status}" valueMessagePrefix="membre.status" noSelection="['': '']"/>
</div>

