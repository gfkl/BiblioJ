package biblioj

import java.sql.Timestamp

class Reservation {

	String	code
	Date	reservation
	Membre	membre
	Boolean receptionnee
	Timestamp version
	
	static mapping = {
		membre lazy : false
	}
	
	static hasMany = [reservationLivres : ReservationLivre]
		
    static constraints = {
		code nullable : false
		reservation nullable : false
		membre nullable : false
		receptionnee nullable : false
    }
}
