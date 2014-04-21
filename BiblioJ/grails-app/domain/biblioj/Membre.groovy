package biblioj

import java.sql.Timestamp

class Membre {

	String	login
	String	mdp
	String	status
	Timestamp version
	
    static constraints = {
		login blank : false, nullable : false, unique : true
		mdp blank : false, nullable : false
		status blank : false, nullable : false
		status inList : ["client", "admin"]
    }
}
