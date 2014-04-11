package biblioj

import org.springframework.dao.DataIntegrityViolationException

class AuteurLivreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [auteurLivreInstanceList: AuteurLivre.list(params), auteurLivreInstanceTotal: AuteurLivre.count()]
    }

    def create() {
        [auteurLivreInstance: new AuteurLivre(params)]
    }

    def save() {
        def auteurLivreInstance = new AuteurLivre(params)
        if (!auteurLivreInstance.save(flush: true)) {
            render(view: "create", model: [auteurLivreInstance: auteurLivreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), auteurLivreInstance.id])
        redirect(action: "show", id: auteurLivreInstance.id)
    }

    def show(Long id) {
        def auteurLivreInstance = AuteurLivre.get(id)
        if (!auteurLivreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "list")
            return
        }

        [auteurLivreInstance: auteurLivreInstance]
    }

    def edit(Long id) {
        def auteurLivreInstance = AuteurLivre.get(id)
        if (!auteurLivreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "list")
            return
        }

        [auteurLivreInstance: auteurLivreInstance]
    }

    def update(Long id, Long version) {
        def auteurLivreInstance = AuteurLivre.get(id)
        if (!auteurLivreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (auteurLivreInstance.version > version) {
                auteurLivreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'auteurLivre.label', default: 'AuteurLivre')] as Object[],
                          "Another user has updated this AuteurLivre while you were editing")
                render(view: "edit", model: [auteurLivreInstance: auteurLivreInstance])
                return
            }
        }

        auteurLivreInstance.properties = params

        if (!auteurLivreInstance.save(flush: true)) {
            render(view: "edit", model: [auteurLivreInstance: auteurLivreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), auteurLivreInstance.id])
        redirect(action: "show", id: auteurLivreInstance.id)
    }

    def delete(Long id) {
        def auteurLivreInstance = AuteurLivre.get(id)
        if (!auteurLivreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "list")
            return
        }

        try {
            auteurLivreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'auteurLivre.label', default: 'AuteurLivre'), id])
            redirect(action: "show", id: id)
        }
    }
}
