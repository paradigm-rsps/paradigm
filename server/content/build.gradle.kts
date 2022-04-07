dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(kotlin("scripting-common"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-dependencies"))
    implementation(kotlin("script-runtime"))
    implementation("io.github.classgraph:classgraph:_")
}

subprojects {
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
}