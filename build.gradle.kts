import io.quarkus.gradle.tasks.QuarkusBuild
import io.quarkus.gradle.tasks.QuarkusNative
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-jackson")
    implementation("io.quarkus:quarkus-amazon-lambda")
    implementation("io.quarkus:quarkus-amazon-dynamodb")
    implementation("software.amazon.awssdk:url-connection-client")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testCompile("io.quarkus:quarkus-junit5")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            javaParameters = true
        }
    }
    withType<QuarkusBuild> {
        isUberJar = true
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<QuarkusNative> {
        isEnableHttpUrlHandler = true
        isEnableHttpsUrlHandler = true
        dockerBuild = "true"
    }

    register<Zip>("distribution") {
        dependsOn("buildNative")

        from("$buildDir")

        include("${rootProject.name}-${rootProject.version}-runner")
        rename { "bootstrap" }

        archiveFileName.set("function.zip")
        destinationDirectory.set(file("$buildDir"))
    }
}

allOpen {
    annotations("javax.enterprise.context.ApplicationScoped")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}