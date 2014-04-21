<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="index" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'grid_12.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'reset.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'slider.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}"
	type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<g:layoutHead />
<r:layoutResources />

<style type="text/css">
#largeur {
	margin: 0 auto;
	width: 980px;
	background-color: #DDDDDD
}
</style>
</head>
<body>



	<!--==============================header=================================-->
	<div id="header" class="bg">
		<div class="bg-2">
			<h1>
				<a class="home" href="${createLink(uri: '/')}"><img
					src="${resource(dir: 'images', file: 'logo.png')}" alt="Grails"
					width="150" height="150" /></a>
			</h1>
		</div>
	</div>
	<!--========<g:layoutBody />======================content================================-->
	<g:set var="panierValeur" value="${0}" />

	<g:if test="${session?.user}">
		<div>
			Bonjour
			${session.user}<br />
			<g:link controller="membre" action="deconnexion">
				<input type="button" value="Déconnexion" class="button" />
			</g:link>
		</div>
		<div class="panier">
			<t2>Mon panier : </t2>
			<ul>
				<g:if test="${session?.user}">
					<g:each in="${session.panier}" var="livreInstance">

						<li><g:set var="panierValeur" value="${1}" /> <g:link
								controller="livre" action="removePanier"
								id="${livreInstance.id}" params="[currentController: 'index']">
								${fieldValue(bean: livreInstance, field: "titre")}
								<img
									src="${resource(dir: 'images', file: '../images/icon_croix.png')}"
									width="10" height="10" />
							</g:link></li>
					</g:each>


					<g:if test="${panierValeur == 1}">

						<g:link controller="reservation" action="validerPanier">
							<input type="button" value="Valider le panier" class="button" />
						</g:link>
					</g:if>

				</g:if>
				<g:else>
					<li>Vide</li>
				</g:else>

			</ul>
		</div>

		<!--========<g:layoutBody />======================finPanier================================-->


		<div class="bg">

			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<div class="bg-2">
				<div id="content">
					<div class="ic">More Website Templates @ TemplateMonster.com.
						May 14, 2012!</div>
					<div class="wrap page1-row1">
						<div class="box-1 border-right">

							<g:link controller="auteur" action="list">
								<strong class="number number-1">01.</strong>
								<span class="text-1">Nos auteurs</span>
								<span class="link-1">Click Here</span>
							</g:link>

						</div>
						<div class="box-1 border-right">

							<g:link controller="livre" action="list">
								<strong class="number number-2">02.</strong>
								<span class="text-1">Nos livres</span>
								<span class="link-1">Click Here</span>
							</g:link>
						</div>
						<div class="box-1 border-right">

							<g:link controller="reservation" action="list">
								<strong class="number number-3">03.</strong>
								<span class="text-1">Vos réservations</span>
								<span class="link-1">Click Here</span>
							</g:link>
						</div>
						<div class="box-1 last">

							<g:link controller="livre" action="indexRecherche">
								<strong class="number number-4">04.</strong>
								<span class="text-1">Rechercher un livre</span>
								<span class="link-1">Click Here</span>
							</g:link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</g:if>
	<g:else>




		<div id="connection" class="content scaffold-create" role="main">
			<h1>
				<g:message code="Connection" args="Connection" />
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${livreInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${livreInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form action="connection" controller="membre">
				<div
					class="fieldcontain ${hasErrors(bean: membreInstance, field: 'login', 'error')} required">
					<label for="login"> <g:message code="membre.login.label"
							default="Login" /> <span class="required-indicator">*</span>
					</label>
					<g:textField name="login" required=""
						value="${membreInstance?.login}" />
				</div>

				<div
					class="fieldcontain ${hasErrors(bean: membreInstance, field: 'mdp', 'error')} required">
					<label for="mdp"> <g:message code="membre.mdp.label"
							default="Mdp" /> <span class="required-indicator">*</span>
					</label>
					<g:textField name="mdp" required="" value="${membreInstance?.mdp}" />

					<fieldset class="buttons">
						<g:submitButton name="connection" class="connection"
							value="Connection" />

						<g:link controller="membre" action="indexInscription">
							<input type="button" value="S'inscrire" class="button" />
						</g:link>

					</fieldset>
				</div>
			</g:form>



		</div>

	</g:else>
	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />



	<!--  -->
</body>
</html>
