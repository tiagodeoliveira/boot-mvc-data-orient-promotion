package io.tiagodeoliveira.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by tiago on 16/06/15.
 */
@Controller
class MainController {

    @RequestMapping('/')
    public String index() {
        return 'index'
    }

    @RequestMapping('/admin')
    public String admin() {
        return 'admin'
    }

}
