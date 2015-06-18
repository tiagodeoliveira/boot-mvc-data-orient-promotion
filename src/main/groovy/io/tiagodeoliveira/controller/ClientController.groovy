package io.tiagodeoliveira.controller

import io.tiagodeoliveira.respository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by tiago on 16/06/15.
 */
@Controller
@RequestMapping('/client')
class ClientController {

    @Autowired
    ClientRepository clientRepository

    @RequestMapping('/list.json')
    def @ResponseBody list() {
        return clientRepository.findAll()
    }
}
