plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

rootProject.name = "paradigm"

/*
 * ===== CLIENT MODULES =====
 */
include(":client")

/*
 * ===== SERVER MODULES =====
 */
include(":server")
include(":server:launcher")
include(":server:common")
include(":server:util")
include(":server:logger")
include(":server:engine")
include(":server:config")