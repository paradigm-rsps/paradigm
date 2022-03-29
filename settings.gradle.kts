plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

rootProject.name = "paradigm"

/*
 * ===== CLIENT MODULES =====
 */
include(":client")
include(":client:launcher")
include(":client:core")

/*
 * ===== SERVER MODULES =====
 */
include(":server")
include(":server:launcher")
include(":server:common")
include(":server:util")
include(":server:engine")