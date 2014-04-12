package biblioj

class Livre {
	String			titre
	int				nombreExemplaires
	int				nombreExemplairesDisponibles
	TypeDocument	typeDocument
	
	static hasMany = [auteurLivres : AuteurLivre, 
		reservationLivres : ReservationLivre]
	
	static mapping = {
		typeDocument lazy : false
	}
	
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
