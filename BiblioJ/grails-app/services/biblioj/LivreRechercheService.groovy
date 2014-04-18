package biblioj

class LivreRechercheService {

	def rechercheLivre(def params, def livreInstance) {
		def findLivre = true
		def findType = true
		def findAuteur = false
		def typeLivre = null
		def map = []

		try{
			if(params.typeDocument != null)
				typeLivre = TypeDocument.get(params.typeDocument.get('id'))
		}catch(e) {
			typeLivre = null
		}

		livreInstance.each { curLivre ->
			if(!params.titre.equals("")){
				if(!(curLivre.titre.toLowerCase() ==~ ".*"+params.titre.toLowerCase()+".*"))
					findLivre = false
			}
			if(typeLivre != null){
				if(!curLivre.typeDocument.equals(typeLivre))
					findType = false
			}
			if(!params.auteur.equals("")){
				curLivre.auteurLivres.each{  curLivreAuteur ->
					if(curLivreAuteur.auteur.nom.toLowerCase() ==~ ".*"+params.auteur.toLowerCase()+".*"){
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
