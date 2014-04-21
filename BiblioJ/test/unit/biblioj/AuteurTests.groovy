package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

    void testSomething() {
       Auteur a = new Auteur(nom:"Nom_Auteur_1", prenom: "Prenom_Auteur_1")
	   assertEquals(a.nom, "Nom_Auteur_1")
	   assertEquals(a.toString(), "Nom_Auteur_1 Prenom_Auteur_1")
    }
}
