package io.tiagodeoliveira.controller

import io.tiagodeoliveira.model.data.ClientDO
import io.tiagodeoliveira.model.data.PromotionDO
import io.tiagodeoliveira.model.data.SaleDO
import io.tiagodeoliveira.model.data.StoreDO
import io.tiagodeoliveira.respository.ClientRepository
import io.tiagodeoliveira.respository.PromotionRepository
import io.tiagodeoliveira.respository.SaleRepository
import io.tiagodeoliveira.respository.StoreRepository
import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

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

    @Autowired ClientRepository clientRepository
    @Autowired PromotionRepository promotionRepository
    @Autowired StoreRepository storeRepository
    @Autowired SaleRepository saleRepository

    @RequestMapping('/seed')
    def seed() {
        ClientDO clientDO = new ClientDO()
        clientDO.name = RandomStringUtils.randomAlphabetic(30)
        clientDO.address = 'Somewhere in some city'
        clientDO = clientRepository.save(clientDO)

        PromotionDO promotionDO = promotionRepository.findAll().get(0)

        StoreDO storeDO = new StoreDO()
        storeDO.name = RandomStringUtils.randomAlphabetic(20)
        storeDO.address = 'Another place'
        storeDO.contact = 'Me myself'
        storeDO.promotions = [promotionDO]
        storeDO = storeRepository.save(storeDO)

        SaleDO saleDO = new SaleDO()
        saleDO.client = clientDO
        saleDO.promotion = promotionDO
        saleDO.store = storeDO
        saleDO.price = new Random().nextLong()
        saleRepository.save(saleDO)

        return 'index'
    }

    @RequestMapping('/list.json')
    def @ResponseBody list() {
        def sales = saleRepository.findAll()
        def graph = [ nodes: [], edges: []]
        sales.each { SaleDO sale ->
            def node = []
            node += [id: sale.client.id, caption: sale.client.name, nodeType: "client"]
            node += [id: sale.promotion.id, caption: sale.promotion.name, nodeType: "promotion"]
            node += [id: sale.id, caption: sale.price, nodeType: "sale"]
            node += [id: sale.store.id, caption: sale.store.name, nodeType: "store"]
            graph.nodes += node

            def edge = []
            edge += [source: sale.client.id, "target": sale.id, "edgeType": "BOUGHT"]
            edge += [source: sale.id, "target": sale.promotion.id, "edgeType": "BELONGS_TO"]
            edge += [source: sale.store.id, "target": sale.promotion.id, "edgeType": "HAS"]
            edge += [source: sale.id, "target": sale.store.id, "edgeType": "DONE_BY"]
            graph.edges += edge
        }
        return graph
    }

}
