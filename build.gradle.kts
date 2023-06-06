import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.7"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

/**
 * is the auth supposed to be a different project
 * does it just handle redirecting and validation?
 * can I get info from it in the frontend? ie email,name?
 * Do I need to just reroute the user to the keycloak url in the frontend?
 * What dependencies do I need for the config?
 */

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.keycloak:keycloak-spring-boot-starter:11.0.3")
	testImplementation ("io.mockk:mockk:1.12.0")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine")
    //implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	//implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    //implementation("org.springframework.boot:spring-boot-starter-security:2.4.0")
//
//
//
//	implementation("org.springframework.security:spring-security-oauth2-jose")
//	implementation("org.springframework.security:spring-security-oauth2-resource-server")



	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//dependencyManagement {
//	imports {
//		mavenBom("org.keycloak.bom:keycloak-adapter-bom:12.0.4")
//	}
//}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
