package io.tiagodeoliveira.respository

import io.tiagodeoliveira.model.data.ClientDO
import io.tiagodeoliveira.model.data.PromotionDO
import io.tiagodeoliveira.model.data.SaleDO
import io.tiagodeoliveira.model.data.StoreDO
import org.springframework.data.orient.object.repository.OrientObjectRepository

/**
 * @author Tiago de Oliveira
 * @since 6/17/15
 */
interface SaleRepository extends OrientObjectRepository<SaleDO> {
    List<SaleDO> findByClient(ClientDO clientDO)
    List<SaleDO> findByPromotion(PromotionDO promotionDO)
    List<SaleDO> findByStore(StoreDO storeDO)
}
