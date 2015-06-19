package io.tiagodeoliveira.controller

import groovy.util.logging.Log
import io.tiagodeoliveira.model.data.ClientDO
import io.tiagodeoliveira.model.visual.ClientVO
import io.tiagodeoliveira.respository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * Created by tiago on 16/06/15.
 */
@Log
@Controller
@RequestMapping('/client')
class ClientController {

    @Autowired
    ClientRepository clientRepository

    @RequestMapping(['', '/', '/list'])
    def list(Model model) {
        try {
            def clients = this.clientRepository.findAll()
            model.addAttribute("clients", clients)
            return 'client/list'
        } catch (Exception e) {
            log.throwing('ClientController', 'list', e)
            model.addAttribute("clients", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'client/list'
        }
    }

    @RequestMapping('/edit/{id}')
    def edit(Model model, @PathVariable('id') String id) {
        log.info("Searching for client ${id}")
        try {
            def client = this.clientRepository.findOne(id)
            model.addAttribute("client", client)
            return 'client/form'
        } catch (Exception e) {
            log.throwing('ClientController', 'edit', e)
            model.addAttribute("client", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'client/form'
        }
    }

    @RequestMapping('/create')
    def create(Model model) {
        model.addAttribute("client", new ClientVO())
        return 'client/form'
    }

    @RequestMapping('/save')
    def save(Model model, RedirectAttributes redirectAttributes, ClientVO client) {
        log.info("Saving client [${client.dump()}]")
        try {
            def oClient = new ClientDO()
            oClient.name = client.name
            oClient.id = client.id
            oClient.address = client.address
            def saved = this.clientRepository.save(oClient)
            log.info("Saved... ${saved.id}")
            redirectAttributes.addFlashAttribute('message', 'Client saved!! Id: ' + saved.id)
            return 'redirect:/client/list'
        } catch (Exception e) {
            log.throwing('ClientController', 'save', e)
            model.addAttribute("error", e.getLocalizedMessage())
            model.addAttribute("client", client)
            return 'client/form'
        }
    }

    @RequestMapping('/delete/{id}')
    def delete(Model model, RedirectAttributes redirectAttributes, @PathVariable('id') String id) {
        log.info("Deleting client [${id}]")

        try {
            this.clientRepository.delete(id)
            redirectAttributes.addFlashAttribute('message', 'Client deleted: ' + id)
            return 'redirect:/client/list'
        } catch (Exception e) {
            log.throwing('ClientController', 'delete', e)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'client/list'
        }
    }
}
