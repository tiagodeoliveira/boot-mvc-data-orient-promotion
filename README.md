# boot-mvc-data-orient-promotion

This is a very simple application showing how to use: springboot, groovy, gradle, spring mvc, spring data, thymeleaf, twitter bootstrap, alchemy.js and OrientDB.

The spring-data-orientdb are still being developed, so some problems exists:

* There is no artifact on maven yet, we need to compile the project;
* The PR [#25](https://github.com/orientechnologies/spring-data-orientdb/pull/25) fix a problem with document database implementation (that is not supported yet), but it's not merged yet, so, need to do that manually.
* A compiled version working can be founded here: https://bintray.com/tiagodeoliveira/maven/spring-data-orientdb

The application aims to allow the CRUD of clients, stores, promotions and sales.

One store has many promotions, one client "buys a sale", one sale belongs to a promotion and is done by a store.

Since the graph database is not supported yet on spring-data-orientdb, the edges on alchemy.js visualization are created manually.

A live demo can be seen [here](http://boot-mvc-data-orient-promotion-220c1a1c.tiagodeoliveira.svc.tutum.io:8008/)

This project is a proof of concept and I have just a couple of free nights to work. I intend to evolute this with spring-security and work on the spring-data orientdb-graph-database module to make easy to deal with graph inside spring-data repositories.
