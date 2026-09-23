import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

tasks{
    test{
        useJUnitPlatform()
    }

    jar{
        enabled = false
    }

    withType<KotlinCompile>{
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict", "-Xannotation-default-target=param-property"))
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    java{
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot","spring-boot-starter-tomcat")
        because("We are replacing tomcat with undertow as embedded server.")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.apache.logging.log4j:log4j-core")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")


    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}