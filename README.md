# spring-boot-25
Spring boot 학습용

# 개발환경
IntelliJ IDEA 2022.2.1 (Ultimate Edition)


# 교재
자바 웹 개발 워크북

# 부트 설정파일
=========== application.properties ============
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://{ip}:6805/bootex
spring.datasource.username={name}
spring.datasource.password={password}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true


# Dependencies

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'

    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'

    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    // lombok
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
