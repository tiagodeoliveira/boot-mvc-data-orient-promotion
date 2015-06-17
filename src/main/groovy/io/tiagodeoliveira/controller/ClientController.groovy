package io.tiagodeoliveira.controller

import io.tiagodeoliveira.respository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by tiago on 16/06/15.
 */
@Controller
@RequestMapping('/client')
class ClientController {
    @Autowired
    ClientRepository clientRepository

    @RequestMapping('/list.json')
    def list() {
        return clientRepository.findAll()
    }
}
