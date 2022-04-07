plugins {
    java
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:1.52")
    implementation("org.json:json:_")
    implementation("com.formdev:flatlaf:_")
    implementation("com.formdev:flatlaf-intellij-themes:_")
}

tasks {
    register<JavaExec>("run client") {
        group = "paradigm"
        workingDir = rootProject.projectDir
        mainClass.set("Launcher")
        classpath = sourceSets["main"].runtimeClasspath
    }

    named<Jar>("jar") {
        archiveBaseName.set("paradigm-client")
        archiveClassifier.set("")
        archiveVersion.set("")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes["Main-Class"] = "Launcher"
        }
        from(configurations.runtimeClasspath.get().map {
            if(it.isDirectory) it
            else zipTree(it)
        })
    }
}