<%@ page import="biblioj.ReservationLivre" %>



<div class="fieldcontain ${hasErrors(bean: reservationLivreInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="reservationLivre.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${biblioj.Reservation.list()}" optionKey="id" required="" value="${reservationLivreInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationLivreInstance, field: 'livre', 'error')} required">
	<label for="livre">
		<g:message code="reservationLivre.livre.label" default="Livre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="livre" name="livre.id" from="${biblioj.Livre.list()}" optionKey="id" required="" value="${reservationLivreInstance?.livre?.id}" class="many-to-one"/>
</div>

