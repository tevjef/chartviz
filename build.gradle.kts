plugins {
    kotlin("jvm") version "1.3.70"
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
}

group = "com.github.tevjef"
