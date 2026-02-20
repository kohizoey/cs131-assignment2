plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "edu.elac.cs131.group5"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

javafx {
    version = "23.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("edu.elac.cs131.group5.Main")
}

tasks.named<JavaExec>("run") {
    doFirst {
        jvmArgs = listOf(
            "--module-path", classpath.asPath,
            "--add-modules", "javafx.controls,javafx.fxml"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}