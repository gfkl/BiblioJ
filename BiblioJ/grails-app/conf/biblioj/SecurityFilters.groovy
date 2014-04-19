package biblioj

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				if (!(session["user"] || (!session["user"] && 
						(actionName == null || actionName.equals('connection' )
							|| actionName.equals('indexInscription') || actionName.equals('inscription')
							|| actionName.equals('inscriptionForm'))))) {
					redirect(uri:'/')
					return false
				}
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
