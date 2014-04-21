package biblioj



import org.junit.*

import grails.test.mixin.*

@TestFor(ReservationLivreController)
@Mock(ReservationLivre)
class ReservationLivreControllerTests {
	
    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params['reservation'] = new Reservation(code:"Code",reservation: new Date())
		params['livre'] = new Livre(titre:"Livre_1",nombreExemplaires:5,nombreExemplairesDisponibles:3)
    }

    void testIndex() {
        controller.index()
        assert "/reservationLivre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reservationLivreInstanceList.size() == 0
        assert model.reservationLivreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reservationLivreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reservationLivreInstance != null
        assert view == '/reservationLivre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reservationLivre/show/1'
        assert controller.flash.message != null
        assert ReservationLivre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationLivre/list'

        populateValidParams(params)
        def reservationLivre = new ReservationLivre(params)

        assert reservationLivre.save() != null

        params.id = reservationLivre.id

        def model = controller.show()

        assert model.reservationLivreInstance == reservationLivre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationLivre/list'

        populateValidParams(params)
        def reservationLivre = new ReservationLivre(params)

        assert reservationLivre.save() != null

        params.id = reservationLivre.id

        def model = controller.edit()

        assert model.reservationLivreInstance == reservationLivre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationLivre/list'

        response.reset()

        populateValidParams(params)
        def reservationLivre = new ReservationLivre(params)

        assert reservationLivre.save() != null

        // test invalid parameters in update
        params.id = reservationLivre.id
        //TODO: add invalid values to params object
		params.livre = 20
		
        controller.update()

        assert view == "/reservationLivre/edit"
        assert model.reservationLivreInstance != null

        reservationLivre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reservationLivre/show/$reservationLivre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reservationLivre.clearErrors()

        populateValidParams(params)
        params.id = reservationLivre.id
        controller.update()

        assert view == "/reservationLivre/edit"
        assert model.reservationLivreInstance != null
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reservationLivre/list'

        response.reset()

        populateValidParams(params)
        def reservationLivre = new ReservationLivre(params)

        assert reservationLivre.save() != null
        assert ReservationLivre.count() == 1

        params.id = reservationLivre.id

        controller.delete()

        assert ReservationLivre.count() == 0
        assert ReservationLivre.get(reservationLivre.id) == null
        assert response.redirectedUrl == '/reservationLivre/list'
    }
}
