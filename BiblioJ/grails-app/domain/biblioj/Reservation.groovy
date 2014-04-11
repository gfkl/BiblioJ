package biblioj

class Reservation {

	String	code
	Date	reservation
	
	static hasMany = [reservationLivres : ReservationLivre]
		
    static constraints = {
		code nullable : false
		reservation nullable : false
    }
}
