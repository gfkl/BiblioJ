package biblioj

class AuteurLivre {

	Auteur	auteur
	Livre	livre
	
	static mapping = {
		auteur fetch : 'join'
		livre fetch : 'join'
	}
	
    static constraints = {
		'*'(nullable : false)
    }
}