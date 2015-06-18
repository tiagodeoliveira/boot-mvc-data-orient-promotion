package io.tiagodeoliveira

import com.orientechnologies.orient.object.db.OObjectDatabaseTx
import io.tiagodeoliveira.model.data.ClientDO
import io.tiagodeoliveira.model.data.PromotionDO
import io.tiagodeoliveira.model.data.StoreDO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.orient.commons.repository.config.EnableOrientRepositories
import org.springframework.data.orient.object.OrientObjectDatabaseFactory
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Created by tiago on 16/06/15.
 */

@SpringBootApplication
@EnableOrientRepositories('io.tiagodeoliveira.respository')
@EnableTransactionManagement
class Application implements CommandLineRunner {

    @Autowired
    OrientObjectDatabaseFactory repository

    @Autowired
    OrientObjectDatabaseFactory objectFactory

    static main(args) {
        SpringApplication.run Application, args
    }

    @Override
    void run(String... args) throws Exception {
        OObjectDatabaseTx db = objectFactory.openDatabase()
        db.getEntityManager().registerEntityClass(ClientDO.class);
        db.getEntityManager().registerEntityClass(PromotionDO.class);
        db.getEntityManager().registerEntityClass(StoreDO.class);
    }
}
