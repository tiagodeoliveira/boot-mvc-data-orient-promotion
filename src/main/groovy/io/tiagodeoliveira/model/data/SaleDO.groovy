package io.tiagodeoliveira.model.data

import com.orientechnologies.orient.core.annotation.OId
import com.orientechnologies.orient.core.annotation.OVersion

/**
 * Created by tiago on 16/06/15.
 */
class SaleDO {
    @OId
    String id
    @OVersion
    Long version
    ClientDO client
    StoreDO store
    PromotionDO promotion
    Long price
    Date date
    def detachAll
}
