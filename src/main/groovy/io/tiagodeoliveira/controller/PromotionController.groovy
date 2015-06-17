package io.tiagodeoliveira.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by tiago on 16/06/15.
 */
@Controller
@RequestMapping('/promotion')
class PromotionController {

    @RequestMapping('/list')
    def list() {
        return 'promotion/list'
    }

    @RequestMapping('/edit')
    def edit() {
        return 'promotion/edit'
    }

}
