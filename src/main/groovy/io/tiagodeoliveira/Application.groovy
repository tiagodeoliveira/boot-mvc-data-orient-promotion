package io.tiagodeoliveira

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.orient.commons.repository.config.EnableOrientRepositories
import org.springframework.data.orient.document.OrientDocumentDatabaseFactory
import org.springframework.data.orient.object.OrientObjectDatabaseFactory
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Created by tiago on 16/06/15.
 */

@SpringBootApplication
@EnableOrientRepositories
@EnableTransactionManagement
class Application {

    @Autowired
    OrientObjectDatabaseFactory repository

    @Autowired
    OrientObjectDatabaseFactory objectFactory

    static main(args) {
        SpringApplication.run Application, args
    }
}
