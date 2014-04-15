package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AuteurLivre)
class AuteurLivreTests {

	void testSomething() {
		Auteur a = new Auteur(nom:"Nom_Auteur_1", prenom: "Prenom_Auteur_1")
		Livre l = new Livre(titre:"Livre_1",nombreExemplaires:5,nombreExemplairesDisponibles:3)
		AuteurLivre al = new AuteurLivre(auteur:a, livre:l)
		
		assertEquals(a, al.auteur)
		assertEquals(l, al.livre)
	}
}
