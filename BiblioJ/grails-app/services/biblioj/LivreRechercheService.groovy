package biblioj

class LivreRechercheService extends Exception{

	def rechercheLivre(def varParams, def livreInstance) {
		def findLivre = true
		def findType = true
		def findAuteur = false
		def typeLivre = null
		def map = []
		println varParams
		
		try{
			if(varParams.typeDocument != null || varParams.typeDocument.get('id') != null)
				typeLivre = TypeDocument.get(varParams.typeDocument.get('id'))
		}catch(e) {
			typeLivre = null
		}
		println typeLivre
		livreInstance.each { curLivre ->
			if(!varParams.titre.equals("")){
				if(!(curLivre.titre.toLowerCase() ==~ ".*"+varParams.titre.toLowerCase()+".*"))
					findLivre = false
			}
			if(typeLivre != null){
				if(!curLivre.typeDocument.equals(typeLivre))
					findType = false
			}
			if(!varParams.auteur.equals("")){
				curLivre.auteurLivres.each{  curLivreAuteur ->
					if(curLivreAuteur.auteur.nom.toLowerCase() ==~ ".*"+varParams.auteur.toLowerCase()+".*"){
						findAuteur = true
					}
				}

			}else
				findAuteur = true

			if(findLivre == true && findType == true && findAuteur == true){
				map.add('livre':curLivre)
			}
			findLivre = true
			findType = true
			findAuteur = false
		}

		map
	}

	def mapByOffset(def map, def params, def myOffset){
		def mapRet = []
		def size = map.size()

		if (size == 0) {
			// retourner un truc (le top : un throw avec un try catch dans le controler)
			throw(Exception)
			
		}
		if(params?.offset){
			def place = params.offset.toInteger()
			if(place+myOffset > size-1)
				for(i in place..size-1)
					mapRet.add(map.get(i))
			else
				for(i in place..place+myOffset)
					mapRet.add(map.get(i))
		}else{
			if(myOffset > size-1)
				for(i in 0..size-1)
					mapRet.add(map.get(i))
			else
				for(i in 0..myOffset)
					mapRet.add(map.get(i))
		}
		mapRet
	}
}
