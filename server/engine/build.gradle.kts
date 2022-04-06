dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:logger"))
    implementation(project(":server:config"))
    implementation(project(":server:cache"))
    implementation("com.google.guava:guava:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
    implementation("io.netty:netty-all:_")
    implementation("io.github.classgraph:classgraph:_")
}