package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReservationLivre)
class ReservationLivreTests {

	void testSomething() {
		Reservation r = new Reservation(code:"Code",reservation: new Date())
		Livre l = new Livre(titre:"Livre_1",nombreExemplaires:5,nombreExemplairesDisponibles:3)
		ReservationLivre rl = new ReservationLivre(livre:l, reservation:r)
		
		assertEquals(l, rl.livre)
		assertEquals(r, rl.reservation)
	}
}
