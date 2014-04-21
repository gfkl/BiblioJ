package biblioj



import org.junit.*

import grails.test.mixin.*

@TestFor(AuteurLivreController)
@Mock(AuteurLivre)
class AuteurLivreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params['auteur'] = new Auteur(nom:"Nom_Auteur_1", prenom: "Prenom_Auteur_1")
		params['livre'] = new Livre(titre:"Livre_1",nombreExemplaires:5,nombreExemplairesDisponibles:3)
    }

    void testIndex() {
        controller.index()
        assert "/auteurLivre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.auteurLivreInstanceList.size() == 0
        assert model.auteurLivreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.auteurLivreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.auteurLivreInstance != null
        assert view == '/auteurLivre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/auteurLivre/show/1'
        assert controller.flash.message != null
        assert AuteurLivre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/auteurLivre/list'

        populateValidParams(params)
        def auteurLivre = new AuteurLivre(params)

        assert auteurLivre.save() != null

        params.id = auteurLivre.id

        def model = controller.show()

        assert model.auteurLivreInstance == auteurLivre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/auteurLivre/list'

        populateValidParams(params)
        def auteurLivre = new AuteurLivre(params)

        assert auteurLivre.save() != null

        params.id = auteurLivre.id

        def model = controller.edit()

        assert model.auteurLivreInstance == auteurLivre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/auteurLivre/list'

        response.reset()

        populateValidParams(params)
        def auteurLivre = new AuteurLivre(params)

        assert auteurLivre.save() != null

        // test invalid parameters in update
        params.id = auteurLivre.id
        //TODO: add invalid values to params object
		params.livre = 20

        controller.update()

        assert view == "/auteurLivre/edit"
        assert model.auteurLivreInstance != null

        auteurLivre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/auteurLivre/show/$auteurLivre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        auteurLivre.clearErrors()

        populateValidParams(params)
        params.id = auteurLivre.id
        controller.update()

        assert view == "/auteurLivre/edit"
        assert model.auteurLivreInstance != null
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/auteurLivre/list'

        response.reset()

        populateValidParams(params)
        def auteurLivre = new AuteurLivre(params)

        assert auteurLivre.save() != null
        assert AuteurLivre.count() == 1

        params.id = auteurLivre.id

        controller.delete()

        assert AuteurLivre.count() == 0
        assert AuteurLivre.get(auteurLivre.id) == null
        assert response.redirectedUrl == '/auteurLivre/list'
    }
}
