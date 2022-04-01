dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(project(":server:engine"))
}

tasks {
    register<JavaExec>("run server") {
        group = "application"
        workingDir = rootProject.projectDir
        mainClass.set("org.paradigm.launcher.Launcher")
        classpath = sourceSets["main"].runtimeClasspath
    }

    named<Jar>("jar") {
        archiveBaseName.set("paradigm-server")
        archiveClassifier.set("")
        archiveVersion.set("")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes["Main-Class"] = "org.paradigm.launcher.Launcher"
        }
        from(configurations.runtimeClasspath.get().map {
            if(it.isDirectory) it
            else zipTree(it)
        })
    }
}