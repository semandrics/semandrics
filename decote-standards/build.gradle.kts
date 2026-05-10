plugins {
    `java-library`
}

dependencies {
    api(project(":decote-annotations"))

    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
