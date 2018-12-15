import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	kotlin("jvm") version "1.3.10"
	id("org.jetbrains.kotlin.kapt") version "1.3.11"
}

group = "com.alicesouthey"
version = "0.1.0"


repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	compile(kotlin("stdlib-jdk8"))
	// JDA: https://bintray.com/dv8fromtheworld/maven/JDA/
	compile("net.dv8tion:JDA:3.8.1_448")

	//Dagger2
	// Dagger: https://mvnrepository.com/artifact/com.google.dagger/dagger/
	compile("com.google.dagger:dagger:2.19")
	// Dagger-Compiler: https://mvnrepository.com/artifact/com.google.dagger/dagger-compiler/
	kapt("com.google.dagger:dagger-compiler:2.19")

	compile("ch.qos.logback:logback-classic:1.2.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

application {
	mainClassName = "com.alicesouthey.maelstrombot.app.MaelstromAppKt"
}

tasks {
	val stage by registering {
		dependsOn("clean", "build")
	}
}
