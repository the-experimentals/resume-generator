plugins{
    application
    kotlin("jvm") version "2.2.0" apply false
    id("io.spring.dependency-management") version "1.1.7"
    id("org.springframework.boot") version "4.1.1" apply false
    id("com.github.node-gradle.node") version "7.1.0" apply false
    kotlin("plugin.spring") version "2.2.0" apply false
}

allprojects {
    group = "com.experimental.resume.generator"
}

subprojects {
    afterEvaluate {
        apply {
            plugin("application")
            plugin("io.spring.dependency-management")
        }

        dependencyManagement {
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:4.1.1")
            }

            dependencies {
                dependencySet("org.jetbrains.kotlin:2.2.0"){
                    entry("kotlin-reflect")
                    entry("kotlin-stdlib")
                }
                dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
            }
        }
    }
}