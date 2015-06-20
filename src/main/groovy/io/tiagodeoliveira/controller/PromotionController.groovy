package io.tiagodeoliveira.controller

import groovy.util.logging.Log
import io.tiagodeoliveira.model.data.PromotionDO
import io.tiagodeoliveira.model.visual.PromotionVO
import io.tiagodeoliveira.respository.PromotionRepository
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
@RequestMapping('/promotion')
class PromotionController {

    @Autowired
    PromotionRepository promotionRepository

    @RequestMapping(['', '/', '/list'])
    def list(Model model) {
        try {
            def promotions = this.promotionRepository.findAll()
            def promos = []
            promotions.each { promo ->
                promos += [new PromotionVO(id: promo.id, name: promo.name, description: promo.description)]
            }
            model.addAttribute("promotions", promos)
            return 'promotion/list'
        } catch (Exception e) {
            log.throwing('PromotionController', 'list', e)
            model.addAttribute("promotions", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'promotion/list'
        }
    }

    @RequestMapping('/edit/{id}')
    def edit(Model model, @PathVariable('id') String id) {
        log.info("Searching for promotion ${id}")
        try {
            def promotion = this.promotionRepository.findOne(id)
            model.addAttribute("promotion", promotion)
            return 'promotion/form'
        } catch (Exception e) {
            log.throwing('PromotionController', 'edit', e)
            model.addAttribute("promotion", null)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'promotion/form'
        }
    }

    @RequestMapping('/create')
    def create(Model model) {
        model.addAttribute("promotion", new PromotionVO())
        return 'promotion/form'
    }

    @RequestMapping('/save')
    def save(Model model, RedirectAttributes redirectAttributes, PromotionVO promotion) {
        log.info("Saving promotion [${promotion.dump()}]")
        try {
            def oPromo = new PromotionDO()
            oPromo.description = promotion.description
            oPromo.name = promotion.name
            oPromo.id = promotion.id
            def saved = this.promotionRepository.save(oPromo)
            log.info("Saved... ${saved.id}")
            redirectAttributes.addFlashAttribute('message', 'PromotionDO saved!! Id: ' + saved.id)
            return 'redirect:/promotion/list'
        } catch (Exception e) {
            log.throwing('PromotionController', 'save', e)
            model.addAttribute("error", e.getLocalizedMessage())
            model.addAttribute("promotion", promotion)
            return 'promotion/form'
        }
    }

    @RequestMapping('/delete/{id}')
    def delete(Model model, RedirectAttributes redirectAttributes, @PathVariable('id') String id) {
        log.info("Deleting promotion [${id}]")

        try {
            this.promotionRepository.delete(id)
            redirectAttributes.addFlashAttribute('message', 'Promotion deleted: ' + id)
            return 'redirect:/promotion/list'
        } catch (Exception e) {
            log.throwing('PromotionController', 'delete', e)
            model.addAttribute("error", e.getLocalizedMessage())
            return 'promotion/list'
        }
    }
}
