package org.paradigm.content

import org.koin.dsl.module

val CONTENT_MODULE = module {
    single { ContentScriptManager() }
}