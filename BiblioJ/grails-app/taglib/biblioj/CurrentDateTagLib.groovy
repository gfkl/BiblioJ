package biblioj

class CurrentDateTagLib {
	def	currentDate = { attrs, body ->
		out << body() << new Date()
	}
}
