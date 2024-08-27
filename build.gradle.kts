plugins {
    id("java")
    id("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

dependencies {
    implementation("org.hibernate.orm:hibernate-core:6.6.0.Final")
    implementation("com.h2database:h2:2.3.232")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    testImplementation("io.quarkus:quarkus-junit5:3.14.0")
}

tasks.withType<War> {
    archiveFileName.set("${project.name}.war")
}

tasks.test {
    useJUnitPlatform()
}