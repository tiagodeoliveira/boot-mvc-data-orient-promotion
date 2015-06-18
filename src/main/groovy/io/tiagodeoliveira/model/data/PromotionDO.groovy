package io.tiagodeoliveira.model.data

import com.orientechnologies.orient.core.annotation.OId
import com.orientechnologies.orient.core.annotation.OVersion

class PromotionDO {
    @OId
    String id
    @OVersion
    Long version
    String name
    String description
    def detachAll
}