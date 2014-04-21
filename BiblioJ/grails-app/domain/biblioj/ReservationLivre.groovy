package biblioj

import java.sql.Timestamp

class ReservationLivre {

	Reservation	reservation
	Livre		livre
	Timestamp 	version
	
	static mapping = {
		reservation fetch : 'join'
		livre fetch : 'join'
	}
	
    static constraints = {
		reservation nullable : false
		livre nullable : false
    }
}
