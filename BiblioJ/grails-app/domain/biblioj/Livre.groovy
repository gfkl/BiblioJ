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
		'*'(nullable : false)
		typeDocument nullable : true
		titre blank : false
    }
}
