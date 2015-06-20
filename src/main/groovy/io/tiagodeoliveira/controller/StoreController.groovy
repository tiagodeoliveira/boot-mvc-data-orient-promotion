package io.tiagodeoliveira.controller

import groovy.util.logging.Log
import io.tiagodeoliveira.model.data.PromotionDO
import io.tiagodeoliveira.model.data.StoreDO
import io.tiagodeoliveira.model.visual.StoreVO
import io.tiagodeoliveira.respository.PromotionRepository
import io.tiagodeoliveira.respository.StoreRepository
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
@RequestMapping('/store')
class StoreController {

    @Autowired StoreRepository storeRepository
    @Autowired PromotionRepository promotionRepository

    @RequestMapping(['', '/', '/list'])
    def list(Model model) {
        try {
            def stores = this.storeRepository.findAll()
            model.addAttribute("stores", stores)
            return 'store/list'
        } catch (Exception e) {
            log.throwing('StoreController', 'list', e)
            model.addAttribute("stores", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'store/list'
        }
    }

    def loadPromotions(StoreDO store) {
        def promotions = []
        promotionRepository.findAll().each { promo ->
            def has = false
            if (store) {
                has = store.promotions.findAll { it.id.equals(promo.id) }.size() > 0
            }
            promotions += [active: has, id: promo.id, name: promo.name]
        }
        return promotions
    }

    @RequestMapping('/edit/{id}')
    def edit(Model model, @PathVariable('id') String id) {
        log.info("Searching for store ${id}")
        try {
            def store = this.storeRepository.findOne(id)
            def storeVO = new StoreVO(id: store.id, name: store.name, address: store.address, contact: store.contact)
            storeVO.promotions = this.loadPromotions(store)
            model.addAttribute("store", storeVO)
            return 'store/form'
        } catch (Exception e) {
            log.throwing('StoreController', 'edit', e)
            model.addAttribute("store", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'store/form'
        }
    }

    @RequestMapping('/create')
    def create(Model model) {
        def storeVO = new StoreVO()
        storeVO.promotions = this.loadPromotions(null)
        model.addAttribute("store", storeVO)
        return 'store/form'
    }

    @RequestMapping('/save')
    def save(Model model, RedirectAttributes redirectAttributes, StoreVO storeVO) {
        log.info("Saving promotion [${storeVO.dump()}]")
        try {
            def store = new StoreDO()
            store.name = storeVO.name
            store.address = storeVO.address
            store.contact = storeVO.contact
            store.id = storeVO.id
            def promotions = []
            storeVO.promotions?.each { promo ->
                promotions += promotionRepository.findOne(promo)
            }
            store.promotions = promotions
            log.info("Saving store ${store.dump()}")
            def saved = this.storeRepository.save(store)
            log.info("Saved... ${saved.id}")
            redirectAttributes.addFlashAttribute('message', 'Store saved!! Id: ' + saved.id)
            return 'redirect:/store/list'
        } catch (Exception e) {
            log.throwing('StoreController', 'save', e)
            model.addAttribute("error", e.getLocalizedMessage())
            model.addAttribute("promotion", storeVO)
            return 'store/form'
        }
    }

    @RequestMapping('/delete/{id}')
    def delete(Model model, RedirectAttributes redirectAttributes, @PathVariable('id') String id) {
        log.info("Deleting store [${id}]")

        try {
            this.storeRepository.delete(id)
            redirectAttributes.addFlashAttribute('message', 'Store deleted: ' + id)
            return 'redirect:/store/list'
        } catch (Exception e) {
            log.throwing('StoreController', 'delete', e)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'store/list'
        }
    }
}
