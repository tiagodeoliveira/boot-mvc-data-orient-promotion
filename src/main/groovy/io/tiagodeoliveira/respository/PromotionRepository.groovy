package io.tiagodeoliveira.respository

import io.tiagodeoliveira.model.Client
import io.tiagodeoliveira.model.Promotion
import org.springframework.data.orient.object.repository.OrientObjectRepository

/**
 * @author Tiago de Oliveira
 * @since 6/17/15
 */
interface PromotionRepository extends OrientObjectRepository<Promotion> {
}
