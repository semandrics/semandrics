plugins {
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    java
}

dependencies {
    implementation(project(":purecore-annotations"))
    implementation(project(":purecore-standards"))
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation(project(":purecore-testing"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
