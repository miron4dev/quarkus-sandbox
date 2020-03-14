import io.quarkus.gradle.tasks.QuarkusBuild
import io.quarkus.gradle.tasks.QuarkusNative

group = "com.miron4dev"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.70"
    kotlin("plugin.allopen") version "1.3.70"
    id("io.quarkus") version "1.3.0.Final"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(enforcedPlatform("io.quarkus:quarkus-bom:1.3.0.Final"))
//    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-amazon-lambda")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testCompile("io.quarkus:quarkus-junit5")
}


tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<QuarkusNative> {
        isEnableHttpUrlHandler = true
        isEnableHttpsUrlHandler = true
        dockerBuild = "true"
    }
    withType<QuarkusBuild> {
        isUberJar = true
    }
}

allOpen {
    annotations("javax.enterprise.context.ApplicationScoped")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}