dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(project(":server:config"))
    implementation(project(":server:cache"))
    implementation(project(":server:content"))
    implementation("com.google.guava:guava:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
    implementation("io.netty:netty-all:_")
    implementation("io.github.classgraph:classgraph:_")
    implementation("com.github.paradigm-rsps:pathfinder:_")
    implementation("org.json:json:_")
}