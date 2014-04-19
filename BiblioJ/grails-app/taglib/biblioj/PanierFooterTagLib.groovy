package biblioj

class PanierFooterTagLib {

	def panierUser = {attrs, body ->
		out << body() << printSession()
	}
	
	def printSession(){
		def list = session.panier
		def strList = ""
		list.each{ livre ->
			strList = strList + "<br\\>" + livre.toString()
		}
		strList
	}
	
}
