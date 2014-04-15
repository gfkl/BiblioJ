package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

    void testSomething() {
       		Livre l = new Livre(titre:"Livre_1",nombreExemplaires:5,nombreExemplairesDisponibles:3)
			assertEquals(l.titre, "Livre_1")

    }
}
