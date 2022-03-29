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
include(":server:engine")