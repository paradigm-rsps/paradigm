dependencies {
    implementation("org.tinylog:tinylog-api-kotlin:_")
    implementation("org.tinylog:tinylog-impl:_")
    implementation("net.java.dev.jna:jna:_")
    implementation("net.java.dev.jna:jna-platform:_")
    implementation("org.jire.arrowhead:arrowhead:_")
}

tasks {
    register<JavaExec>("run nxt-client") {
        group = "paradigm"
        workingDir = rootProject.projectDir
        mainClass.set("org.paradigm.nxtclient.Launcher")
        classpath = sourceSets["main"].runtimeClasspath
    }

    named<Jar>("jar") {
        archiveBaseName.set("paradigm-nxt-client")
        archiveClassifier.set("")
        archiveVersion.set("")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes["Main-Class"] = "org.paradigm.nxtclient.Launcher"
        }
        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it
            else zipTree(it)
        })
    }

    register<JavaExec>("patch nxt-clinet") {
        group = "paradigm"
        workingDir = rootProject.projectDir
        mainClass.set("org.paradigm.nxtclient.Patcher")
        classpath = sourceSets["main"].runtimeClasspath
    }
}