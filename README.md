# spring-boot-25
Spring boot 학습용

# 개발환경
IntelliJ IDEA 2022.2.1 (Ultimate Edition)


# 교재
자바 웹 개발 워크북

# 부트 설정파일
=========== application.properties ============

    // DB (220-21 MariaDB)
    
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    
    spring.datasource.url=jdbc:mariadb://192.168.0.171:33062/bootex
    
    spring.datasource.username=bootuser
    
    spring.datasource.password=bootuser
    
    
    
    // spring JPA (Java Persistence A ) ORM
    
    spring.jpa.hibernate.ddl-auto=update
    
    // update : 기존과 다르면 drop 후 create
    
    // none : 테이블을 구조 변경하지않는다
    
    // create : 실행 할때마다 테이블 새로 생성
    
    // create-drop : 실행할 때 테이블 새로 생성하고 종료할때 드롭
    
    // validate : 변경된부분 콘솔에 알림 (구조 변경 안함)
    
    
    
    // sql 쿼리문을 콘솔에 표시
    
    spring.jpa.properties.hibernate.format_sql=true
    
    
    
    // jpa 프로세스 콘솔에 표시
    
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
