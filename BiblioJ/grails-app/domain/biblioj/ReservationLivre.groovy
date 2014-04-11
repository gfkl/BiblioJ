package biblioj

class ReservationLivre {

	Reservation	reservation
	Livre		livre
	
	static mapping = {
		reservation fetch : 'join'
		livre fetch : 'join'
	}
	
    static constraints = {
		reservation nullable : false
		livre nullable : false
    }
}
