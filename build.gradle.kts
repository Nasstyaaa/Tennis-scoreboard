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
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1")
    compileOnly("org.projectlombok:lombok:1.18.34")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    testImplementation("io.quarkus:quarkus-junit5:3.14.0")
}

tasks.withType<War> {
    archiveFileName.set("${project.name}.war")
}

tasks.test {
    useJUnitPlatform()
}