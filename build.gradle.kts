buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    }


}

plugins {
    `java-library`
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    kotlin("kapt") version "1.4.31"
    maven
    `maven-publish`
}


group = "com.github.latunsk4"
version = "1.2-kotlin-upgrade-V4"



dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.31")

    val ktorVersion = "1.5.1"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")

    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    // Annotation processor that generates Java builders for data classes
    val ktBuilderVersion = "1.2.1"
    implementation("com.thinkinglogic.builder:kotlin-builder-annotation:$ktBuilderVersion")
    kapt("com.thinkinglogic.builder:kotlin-builder-processor:$ktBuilderVersion")

    testImplementation("junit:junit:4.12")
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    val sourcesJar by creating(Jar::class) {
        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        from(sourceSets["main"].allSource)
    }

    sourcesJar.archiveClassifier.convention("sources")
    sourcesJar.archiveClassifier.set("sources")

    artifacts {
        add("archives", sourcesJar)
        add("archives", jar)
    }
}
