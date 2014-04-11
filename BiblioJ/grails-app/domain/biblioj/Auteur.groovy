package biblioj

class Auteur {
	
	String	nom
	String	prenom
	
	static hasmany = [auteurLivres : AuteurLivre]
	
    static constraints = {
		'*'(nullable : false, blank : false)
    }
}
