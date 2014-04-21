package biblioj



import java.util.Date;

import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {
	
	void testSomething() {
		Membre m = new Membre(login:"bla", mdp:"mdp", status:"status")
		Reservation r = new Reservation(code:"Code", reservation: new Date(), receptionnee:true, membre:m)

		assertEquals(r.code, "Code")
	}
}
