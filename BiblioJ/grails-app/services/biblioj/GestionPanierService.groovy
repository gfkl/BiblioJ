package biblioj

class GestionPanierService {

    def emprunter(def params, def session) {
		def livreInstance = Livre.get(params.id)
		def contains = false
		def panier = session["panier"]
		def list = []

		if (!panier){
			list.add(livreInstance)
			session["panier"] = list

		}else{
			list = session["panier"]
			if(!(livreInstance in list))		//redefinir methode equals (sécurité)
				list.add(livreInstance)
			session["panier"] = list
		}
    }
	
	def removePanier(def params, def session){
		def livreInstance = Livre.get(params.id)
		def list = session["panier"]
		def memLivreInPanier
		list.each { curPanier ->
			if(curPanier.titre.equals(livreInstance.titre)){
				memLivreInPanier = curPanier
				return
			}
		}
		
		list.remove(memLivreInPanier)
	}
}
