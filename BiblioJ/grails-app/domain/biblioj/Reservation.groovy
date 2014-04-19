package biblioj

class Reservation {

	String	code
	Date	reservation
	Membre	membre
	
	static mapping = {
		membre lazy : false
	}
	
	static hasMany = [reservationLivres : ReservationLivre]
		
    static constraints = {
		code nullable : false
		reservation nullable : false
		membre nullable : false
    }
}
