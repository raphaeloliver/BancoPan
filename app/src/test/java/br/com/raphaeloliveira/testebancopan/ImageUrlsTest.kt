package com.teste.thiago

import br.com.raphaeloliveira.testebancopan.model.ImagesUrls
import junit.framework.Assert.assertEquals
import org.junit.Test

class ImageUrlsTest{

    @Test
    fun testImageUrlsGetters() {
        val (large, medium, small, template) = ImagesUrls("large", "medium", "small", "template")

        assertEquals(large, "large")
        assertEquals(medium, "medium")
        assertEquals(small, "small")
        assertEquals(template, "template")
    }
}