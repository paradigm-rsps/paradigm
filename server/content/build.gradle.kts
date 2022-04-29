dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(kotlin("scripting-common"))
    implementation(kotlin("script-runtime"))
    implementation("io.github.classgraph:classgraph:_")
}

subprojects {
    val subproject = this
    dependencies {
        implementation(project(":server:common"))
        implementation(project(":server:util"))
        implementation(project(":server:logger"))
        implementation(project(":server:cache"))
        implementation(project(":server:engine"))
        implementation(project(":server:config"))
        implementation(project(":server:api"))
        implementation(project(":server:content"))
        implementation(kotlin("script-runtime"))
        project(":server:content").dependencyProject.subprojects.forEach { subproject ->
            if (subproject.buildFile.exists() && subproject.name != project.name) {
                api(subproject)
            }
        }
    }

    tasks {
        named<Jar>("jar") {
            archiveBaseName.set(subproject.name)
            archiveVersion.set("")
            archiveClassifier.set("")
        }

        register<Copy>("assembleModule") {
            group = "paradigm"
            doFirst {
                rootProject.projectDir.resolve("data/modules/${subproject.name}.jar").also {
                    if (it.exists()) it.deleteRecursively()
                }
            }
            from(named("jar"))
            into(rootProject.projectDir.resolve("data/modules/"))
        }

        compileKotlin {
            finalizedBy(named("assembleModule"))
        }
    }
}