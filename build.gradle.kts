plugins {
  id("org.jetbrains.kotlin.jvm") version "1.6.0"

  `java-library`
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("com.google.guava:guava:30.0-jre")

  testImplementation("org.jetbrains.kotlin:kotlin-test")

  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

  api("org.apache.commons:commons-math3:3.6.1")

  implementation(kotlin("reflect"))
  implementation(kotlin("script-runtime"))
  implementation(kotlin("compiler-embeddable"))
  implementation(kotlin("script-util"))
  implementation(kotlin("scripting-compiler-embeddable"))
}
