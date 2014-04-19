<%@ page import="biblioj.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="reservation.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${reservationInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="reservation.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="reservation" precision="day"  value="${reservationInstance?.reservation}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'membre', 'error')} required">
	<label for="membre">
		<g:message code="reservation.membre.label" default="Membre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="membre" name="membre.id" from="${biblioj.Membre.list()}" optionKey="id" required="" value="${reservationInstance?.membre?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'reservationLivres', 'error')} ">
	<label for="reservationLivres">
		<g:message code="reservation.reservationLivres.label" default="Reservation Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${reservationInstance?.reservationLivres?}" var="r">
    <li><g:link controller="reservationLivre" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservationLivre" action="create" params="['reservation.id': reservationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre')])}</g:link>
</li>
</ul>

</div>

