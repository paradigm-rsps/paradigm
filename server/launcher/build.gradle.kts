dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(project(":server:engine"))
    implementation(project(":server:config"))
    implementation(project(":server:cache"))
    implementation(project(":server:api"))
    implementation(project(":server:content"))
    project(":server:content").dependencyProject.subprojects.forEach { subproject ->
        if (subproject.buildFile.exists()) {
            implementation(subproject)
        }
    }
    implementation("ch.qos.logback:logback-classic:_")
}

tasks {
    register<JavaExec>("run server") {
        group = "paradigm"
        workingDir = rootProject.projectDir
        mainClass.set("org.paradigm.launcher.Launcher")
        classpath = sourceSets["main"].runtimeClasspath
    }

    register<JavaExec>("setup server") {
        group = "paradigm"
        finalizedBy(project(":nxt-client").tasks.getByName("patch"))
        workingDir = rootProject.projectDir
        mainClass.set("org.paradigm.launcher.Setup")
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