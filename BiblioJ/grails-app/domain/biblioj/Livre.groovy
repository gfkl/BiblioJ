package biblioj

import java.sql.Timestamp

class Livre {
	String			titre
	int				nombreExemplaires
	int				nombreExemplairesDisponibles
	TypeDocument	typeDocument
	Timestamp		 version

	static hasMany = [auteurLivres : AuteurLivre,
		reservationLivres : ReservationLivre]


	static constraints = {
		titre nullable : false, blank : false
		nombreExemplaires nullable : false
		nombreExemplairesDisponibles nullable : false
		typeDocument nullable : true
	}

	public String toString() {
		titre +" - "+typeDocument
	}
}
