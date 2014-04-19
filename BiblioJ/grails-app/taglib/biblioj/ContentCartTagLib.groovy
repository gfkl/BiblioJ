package biblioj

class ContentCartTagLib {
	def panierUser = {attrs, body ->
		out << "<ul>"
		session.panier.each{ livre ->
			out << "<li>"
			out << livre.getTitre().toString() + 
//			"<g:link action=\"updatePlusNote\" id=\"${ligneInstance.id}\">" +
			"  <img src=\"${resource(dir: 'images', file: '../images/icon_croix.png')}\" width=\"10\" height=\"10\"/>"
//			"</g:link>"
			out << "</li>"
		}
		out << "</ul>"
	}	
}

//http://localhost:8080/BiblioJ/livre/removePanier/4?currentController=livre&currentAction=list
//def targetUri = params.targetUri ?: "/"
//redirect(uri: targetUri)