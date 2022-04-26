dependencies {
    implementation(project(":server:common"))
    implementation(project(":server:util"))
    implementation(project(":server:config"))
    implementation(project(":server:logger"))
    api("io.guthix:jagex-bytebuf-extensions:_")
    api("io.guthix:jagex-bytebuf-wrapper:_")
    api("net.runelite:cache:_")
}