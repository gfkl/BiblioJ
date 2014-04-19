<%@ page import="biblioj.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')}">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
	</label>
	<g:textField name="titre" value="${livreInstance?.titre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDocument', 'error')} ">
	<label for="typeDocument">
		<g:message code="livre.typeDocument.label" default="Type Document" />
		
	</label>
	<g:select id="typeDocument" name="typeDocumentId" from="${biblioj.TypeDocument.list()}" optionKey="id" value="${livreInstance?.typeDocument?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'nom', 'error')} ">
	<label for="Auteur">
		<g:message code="auteur.nom.label" default="Auteur" />
	</label>
	<g:textField name="auteur" value="${auteurInstance?.nom}"/>
</div>

