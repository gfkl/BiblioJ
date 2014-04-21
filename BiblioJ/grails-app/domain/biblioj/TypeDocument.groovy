package biblioj

import java.sql.Timestamp

class TypeDocument {

	String	intitule
	Timestamp version
	
    static constraints = {
		intitule nullable : false, blank : false
    }

	public String toString() {
		intitule
	}
}

