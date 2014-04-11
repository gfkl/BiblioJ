<%@ page import="biblioj.TypeDocument" %>



<div class="fieldcontain ${hasErrors(bean: typeDocumentInstance, field: 'intitule', 'error')} required">
	<label for="intitule">
		<g:message code="typeDocument.intitule.label" default="Intitule" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="intitule" required="" value="${typeDocumentInstance?.intitule}"/>
</div>

