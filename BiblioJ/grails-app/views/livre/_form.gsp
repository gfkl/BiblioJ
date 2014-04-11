<%@ page import="biblioj.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} required">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titre" required="" value="${livreInstance?.titre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplaires', 'error')} required">
	<label for="nombreExemplaires">
		<g:message code="livre.nombreExemplaires.label" default="Nombre Exemplaires" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplaires" type="number" value="${livreInstance.nombreExemplaires}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesDisponibles', 'error')} required">
	<label for="nombreExemplairesDisponibles">
		<g:message code="livre.nombreExemplairesDisponibles.label" default="Nombre Exemplaires Disponibles" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesDisponibles" type="number" value="${livreInstance.nombreExemplairesDisponibles}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDocument', 'error')} ">
	<label for="typeDocument">
		<g:message code="livre.typeDocument.label" default="Type Document" />
		
	</label>
	<g:select id="typeDocument" name="typeDocument.id" from="${biblioj.TypeDocument.list()}" optionKey="id" value="${livreInstance?.typeDocument?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteurLivres', 'error')} ">
	<label for="auteurLivres">
		<g:message code="livre.auteurLivres.label" default="Auteur Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${livreInstance?.auteurLivres?}" var="a">
    <li><g:link controller="auteurLivre" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="auteurLivre" action="create" params="['livre.id': livreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'reservationLivres', 'error')} ">
	<label for="reservationLivres">
		<g:message code="livre.reservationLivres.label" default="Reservation Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${livreInstance?.reservationLivres?}" var="r">
    <li><g:link controller="reservationLivre" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservationLivre" action="create" params="['livre.id': livreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre')])}</g:link>
</li>
</ul>

</div>

