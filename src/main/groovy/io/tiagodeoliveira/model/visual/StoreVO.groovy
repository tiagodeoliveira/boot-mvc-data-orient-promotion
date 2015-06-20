package io.tiagodeoliveira.model.visual

import com.orientechnologies.orient.core.annotation.OId
import com.orientechnologies.orient.core.annotation.OVersion
import io.tiagodeoliveira.model.data.PromotionDO

/**
 * Created by tiago on 16/06/15.
 */
class StoreVO {
    String id
    String name
    String address
    String contact
    def promotions
}
