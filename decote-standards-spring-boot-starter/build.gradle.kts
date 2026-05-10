plugins {
    `java-library`
}

dependencies {
    api(project(":decote-standards"))
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:4.0.6")
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor:4.0.6")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:4.0.6")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
