package com.vortex.billreminder.storage.json

import android.content.Context
import com.google.gson.GsonBuilder
import java.io.IOException
import kotlin.reflect.KClass

class JsonParserImpl(context: Context) : JsonParser {

    private val assets = context.assets

    override fun <M : Any> parseLocalJson(fileName: String, clazz: KClass<M>): M {

        val json: String? = try {

            val localJson = assets.open(fileName)
            val size = localJson.available()
            val buffer = ByteArray(size)
            localJson.read(buffer)
            localJson.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

        return GsonBuilder().create().fromJson<M>(json, clazz.java)
    }

    override fun <O : Any> parseObjectToJson(objects: O): String {
        return try {
            GsonBuilder().serializeNulls().create().toJson(objects)
        } catch (ex: Exception) {
            ""
        }
    }

    override fun <O : Any> parseJsonToObject(json: String, clazz: KClass<O>): O {
        return GsonBuilder().serializeNulls().create().fromJson<O>(json, clazz.java)
    }
}