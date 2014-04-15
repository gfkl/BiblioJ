package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {

	void testSomething() {
		Reservation r = new Reservation(code:"Code", reservation: new Date())

		assertEquals(r.code, "Code")
	}
}
