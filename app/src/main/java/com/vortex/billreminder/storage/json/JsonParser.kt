package com.vortex.billreminder.storage.json

import kotlin.reflect.KClass

interface JsonParser {

    fun <M : Any> parseLocalJson(fileName: String, clazz: KClass<M>): M

    fun <O : Any> parseObjectToJson(objects: O): String

    fun <O : Any> parseJsonToObject(json: String, clazz: KClass<O>): O
}