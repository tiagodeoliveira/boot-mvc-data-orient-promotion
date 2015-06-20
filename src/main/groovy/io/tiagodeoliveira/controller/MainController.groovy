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
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by tiago on 16/06/15.
 */
@Controller
class MainController {

    @Autowired PromotionRepository promotionRepository
    @Autowired SaleRepository saleRepository

    @RequestMapping('/')
    public String index(Model model) {
        def promotions = promotionRepository.findAll()
        model.addAttribute("promotions", promotions)
        return 'index'
    }

    @RequestMapping('/admin')
    public String admin() {
        return 'admin'
    }

    @RequestMapping('/list.json')
    def @ResponseBody list(@RequestParam(value="promotion") String promoId) {

        def graph = [nodes: [], edges: []]

        if (promoId) {
            def promotion = this.promotionRepository.findOne(promoId)
            def sales = saleRepository.findByPromotion(promotion)

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
        }
        return graph
    }

}
