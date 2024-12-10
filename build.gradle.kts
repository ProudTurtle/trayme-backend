plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "pl.infirsoft"
version = "0.0.3"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

kapt {
    javacOptions {
        option("querydsl.entityAccessors", true)
    }
    arguments {
        arg("plugin", "com.querydsl.apt.jpa.JPAAnnotationProcessor")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.2.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.+")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.3.5")


    runtimeOnly("com.mysql:mysql-connector-j")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
