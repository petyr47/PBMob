package com.aneke.peter.pbmob

import com.aneke.peter.pbmob.util.resolveTags
import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilUnitTest {

    @Test
    fun testResolveTags() {
        val tags = "spring bird, bird, tit"
        val tagList = listOf("Spring bird", "Bird" ,"Tit")
        assertEquals(tagList, resolveTags(tags))
    }

}