package org.paradigm.content.annotation

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class Import(vararg val paths: String)
