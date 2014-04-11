<%@ page import="biblioj.Auteur" %>



<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="auteur.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${auteurInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="auteur.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" required="" value="${auteurInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'auteurLivres', 'error')} ">
	<label for="auteurLivres">
		<g:message code="auteur.auteurLivres.label" default="Auteur Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auteurInstance?.auteurLivres?}" var="a">
    <li><g:link controller="auteurLivre" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="auteurLivre" action="create" params="['auteur.id': auteurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre')])}</g:link>
</li>
</ul>

</div>

