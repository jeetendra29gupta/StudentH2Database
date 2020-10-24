# Student H2 Database

In this tutorial, we'll explore using H2 with Spring Boot. Just like other databases, there's full intrinsic support for it in the Spring Boot ecosystem.

## Dependencies
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Database Configuration and other configuration.
```
spring.main.banner-mode=off
spring.application.name=StudentH2Database

spring.datasource.url=jdbc:h2:file:./data/student;DB_CLOSE_ON_EXIT=FALSE

spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

-[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

-[http://localhost:8080/](http://localhost:8080/)

