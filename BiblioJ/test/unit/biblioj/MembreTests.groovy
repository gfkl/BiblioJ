package biblioj



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Membre)
class MembreTests {

    void testSomething() {
		Membre a = new Membre(login:"login", mdp: "mdp", status:"status")
		
		assertEquals(a.login, "login")
		assertEquals(a.mdp, "mdp")
		assertEquals(a.status, "status")
    }
}
