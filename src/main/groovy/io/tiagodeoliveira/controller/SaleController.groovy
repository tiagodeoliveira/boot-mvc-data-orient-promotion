package io.tiagodeoliveira.controller

import io.tiagodeoliveira.model.data.ClientDO
import io.tiagodeoliveira.model.data.SaleDO
import io.tiagodeoliveira.respository.ClientRepository
import io.tiagodeoliveira.respository.PromotionRepository
import io.tiagodeoliveira.respository.SaleRepository
import io.tiagodeoliveira.respository.StoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by tiago on 16/06/15.
 */
@Controller
@RequestMapping('/sale')
class SaleController {

    @Autowired ClientRepository clientRepository
    @Autowired PromotionRepository promotionRepository
    @Autowired StoreRepository storeRepository
    @Autowired SaleRepository saleRepository

    @RequestMapping('/generate/{amount}')
    def generate(Model model, @PathVariable('amount') Long amount) {
        def clients = clientRepository.findAll()
        def promotions = promotionRepository.findAll()
        def stores = storeRepository.findAll()
        def random = new Random()
        amount.times {
            SaleDO saleDO = new SaleDO()
            saleDO.client = clients.get(random.nextInt(clients.size()))
            saleDO.promotion = promotions.get(random.nextInt(promotions.size()))
            saleDO.store = stores.get(random.nextInt(stores.size()))
            saleDO.price = random.nextInt( Integer.MAX_VALUE ) + 1
            saleRepository.save(saleDO)
        }
        return 'redirect:/index'
    }
}
