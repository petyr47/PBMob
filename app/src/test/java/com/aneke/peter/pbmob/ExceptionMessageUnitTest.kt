package com.aneke.peter.pbmob

import com.aneke.peter.pbmob.util.resolveMessage
import junit.framework.Assert.assertEquals
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class ExceptionMessageUnitTest {

    @Test
    fun test500HTTPError() {
        val error = HttpException(Response.error<Any?>(500, ResponseBody.Companion.create(null, "") ))
        assertEquals("Oops! Something went wrong on our servers" ,error.resolveMessage())
    }

    @Test
    fun testIllegalStateException() {
        val error = IllegalStateException("An Illegal State Error")
        assertEquals("An Illegal State Error" ,error.resolveMessage())
    }

    @Test
    fun testUnknownHostException() {
        val error = UnknownHostException()
        assertEquals("Could not connect to network, please check your internet connection", error.resolveMessage())
    }

}