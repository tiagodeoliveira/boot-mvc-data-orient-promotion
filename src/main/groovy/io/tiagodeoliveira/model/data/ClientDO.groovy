package io.tiagodeoliveira.model.data

import com.orientechnologies.orient.core.annotation.OId
import com.orientechnologies.orient.core.annotation.OVersion

/**
 * Created by tiago on 16/06/15.
 */
class ClientDO {
    @OId
    String id
    @OVersion
    Long version
    String name
    String address
    def detachAll

}
