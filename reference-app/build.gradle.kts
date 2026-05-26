plugins {
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    java
}

dependencies {
    implementation(project(":semandrics-annotations"))
    implementation("org.springframework.boot:spring-boot-starter-web:4.0.6")

    testImplementation(project(":semandrics-testing"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.6")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
