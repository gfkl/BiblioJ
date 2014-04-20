package biblioj

class ReservationService {

    def verrifierDiponnibilite(def varSession) {
		def listLivreNonDisponible = []
		varSession["panier"].each { livre ->
			if (livre.getNombreExemplairesDisponibles() == 0) {
				listLivreNonDisponible.add(livre.getTitre())
			}
		}
		return listLivreNonDisponible
	}
	
	def validerReservation(def varSession) {
		/* suppression des livres non dispo du panier */
		def listLivreNonDisponible = verrifierDiponnibilite(varSession)
		listLivreNonDisponible.each { titreLivre ->
			println "-> " + titreLivre
			varSession["panier"].remove(Livre.findByTitre(titreLivre))
		}
		/* validation du panier */
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date));
		def dateResa = new Date().format("yyyy-MM-dd HH:mm:ss")
		println varSession["user"]
		def membre = Membre.findByLogin(varSession["user"])
		println "#" + membre.login
		def code = dateResa.toString() + membre.login
		println "##" + code
		def resa = new Reservation(code: code, reservation: dateResa, membre: membre).save(flush : true)
		println "###" + resa
		def resa2 = Reservation.findByCode(code)
		println "####" + resa2
		varSession["panier"].each { livre ->
//			def livre = Livre.findByTitre(titreLivre)
			println "---->" + livre.titre
			livre.nombreExemplairesDisponibles--
			def aaa = new ReservationLivre(reservation: resa, livre: livre).save(flush: true)
			println aaa
		}
		listLivreNonDisponible.each {
			println "-->" + it
		}
		println varSession["panier"]
		println Reservation.list()
		
	}
	
	def supprimerReservation(def code) {
		def resa = Reservation.findByCode(code);
		resa.reservationLivres.each { resaLivre ->
			resaLivre.delete()
		}
		resa.delete()
	}
	
	def rendreLivre(def codeResa, def titreLivre) {
		def resa = Reservation.findByCode(codeResa);
		resa.reservationLivres { resaLivre ->
			if (resaLivre.livre.titre.equals(titreLivre)) {
				resaLivre.delete()
			}
		}
		if (resa.reservationLivres.list().size == 0)
			resa.delete()
	}
}
