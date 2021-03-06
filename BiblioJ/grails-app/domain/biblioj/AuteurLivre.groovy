package biblioj

import java.sql.Timestamp

class AuteurLivre {

	Auteur	auteur
	Livre	livre
	Timestamp version
	
	static mapping = {
		auteur fetch : 'join'
		livre fetch : 'join'
	}
	
    static constraints = {
		auteur nullable : false
		livre nullable : false
    }
	
	@Override
	public String toString() {
			return " auteur=" + auteur + ", livre=" + livre+"";

	}

}
