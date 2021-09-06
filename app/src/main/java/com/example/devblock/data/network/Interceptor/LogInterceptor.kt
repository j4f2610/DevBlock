package com.example.devblock.data.network.Interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.util.*

/**
 * Created by Viktor on 31,May,2020
 */
class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder()
        val t1 = System.nanoTime()
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        val content = response.body!!.string()
        Log.v(
            "RESPONSE",
            String.format(
                Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request.url, (t2 - t1) / 1e6, response.headers
            ) + "\n" + content
        )
        return response.newBuilder()
            .body(content.toResponseBody(response.body!!.contentType()))
            .build()
    }
}