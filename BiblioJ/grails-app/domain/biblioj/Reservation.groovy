package biblioj

class Reservation {

	String	code
	Date	reservation
	
	static hashMany = [reservationLivres : ReservationLivre]
		
    static constraints = {
		'*'(nullable : false)
    }
}
