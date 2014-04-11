package biblioj

class Livre {
	String	titre
	int		nombreExemplaires
	int		nombreExemplairesDisponibles
	
    static constraints = {
		'*'(nullable : false)
		titre blank : false
    }
}
