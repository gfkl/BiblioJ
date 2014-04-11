<%@ page import="biblioj.AuteurLivre" %>



<div class="fieldcontain ${hasErrors(bean: auteurLivreInstance, field: 'auteur', 'error')} required">
	<label for="auteur">
		<g:message code="auteurLivre.auteur.label" default="Auteur" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="auteur" name="auteur.id" from="${biblioj.Auteur.list()}" optionKey="id" required="" value="${auteurLivreInstance?.auteur?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurLivreInstance, field: 'livre', 'error')} required">
	<label for="livre">
		<g:message code="auteurLivre.livre.label" default="Livre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="livre" name="livre.id" from="${biblioj.Livre.list()}" optionKey="id" required="" value="${auteurLivreInstance?.livre?.id}" class="many-to-one"/>
</div>

