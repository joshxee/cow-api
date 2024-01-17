plugins {
  id("org.jetbrains.kotlin.jvm") version "1.9.21"
  id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
  id("com.google.devtools.ksp") version "1.9.21-1.0.16"
  id("com.github.johnrengelman.shadow") version "8.1.1"
  id("io.micronaut.application") version "4.2.1"
  id("io.micronaut.test-resources") version "4.2.1"
  id("io.micronaut.aot") version "4.2.1"
}

version = "0.1"
group = "com.halter"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
  mavenCentral()
}

dependencies {
  annotationProcessor("io.micronaut.data:micronaut-data-processor")
  annotationProcessor("io.micronaut.openapi:micronaut-openapi:6.3.0!!")
  testAnnotationProcessor("io.micronaut:micronaut-inject-java")
  ksp("io.micronaut:micronaut-http-validation")
  // Persistence
  implementation("io.micronaut.flyway:micronaut-flyway")
  implementation("io.micronaut.sql:micronaut-jdbc-hikari")
  implementation("io.micronaut.data:micronaut-data-jdbc")
  implementation("org.apache.commons:commons-lang3:3.14.0")
  implementation("io.micronaut.cache:micronaut-cache-core")

  // We are pinning openapi to 6.3.0 because of https://github.com/micronaut-projects/micronaut-openapi/issues/1154
  ksp("io.micronaut.openapi:micronaut-openapi:6.3.0!!")
  ksp("io.micronaut.serde:micronaut-serde-processor")
  implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
  implementation("io.micronaut.redis:micronaut-redis-lettuce")
  implementation("io.micronaut.serde:micronaut-serde-jackson")
  implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
  compileOnly("io.micronaut:micronaut-http-client")
  compileOnly("io.micronaut.openapi:micronaut-openapi-annotations:6.3.0!!")
  runtimeOnly("org.postgresql:postgresql")
  runtimeOnly("ch.qos.logback:logback-classic")
  runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
  runtimeOnly("org.mockito:mockito-junit-jupiter")
  testImplementation("io.micronaut:micronaut-http-client")
  testImplementation("io.micronaut.testresources:micronaut-test-resources-extensions-junit-platform")

  // Testing
  testCompileOnly("org.mockito:mockito-junit-jupiter")
  testCompileOnly("io.micronaut.test:micronaut-test-junit5")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}


application {
  mainClass.set("com.halter.ApplicationKt")
}
java {
  sourceCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection.set(false)
micronaut {
  runtime("netty")
  testRuntime("junit5")
  processing {
    incremental(true)
    annotations("com.halter.*")
  }
  testResources {
    additionalModules.add("jdbc-postgresql")
  }
  aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
    optimizeServiceLoading.set(false)
    convertYamlToJava.set(false)
    precomputeOperations.set(true)
    cacheEnvironment.set(true)
    optimizeClassLoading.set(true)
    deduceEnvironment.set(true)
    optimizeNetty.set(true)
  }
}



