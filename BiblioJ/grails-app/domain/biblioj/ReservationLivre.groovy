package biblioj

class ReservationLivre {

	Reservation	reservation
	Livre		livre
	
	static mapping = {
		reservation fetch : 'join'
		livre fetch : 'join'
	}
	
    static constraints = {
		'*'(nullable : false)
    }
}
