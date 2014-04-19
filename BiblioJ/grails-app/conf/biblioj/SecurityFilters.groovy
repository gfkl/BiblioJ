package biblioj

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				if (!session["user"] && (actionName != null && !actioName.equals('connection'))) {
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
