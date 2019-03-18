package com.teste.thiago

import br.com.raphaeloliveira.testebancopan.model.Links
import junit.framework.Assert.assertEquals
import org.junit.Test

class LinksTest{

    @Test
    fun testLinkGetters() {
        val (self, next) = Links("self", "next")

        assertEquals(next, "next")
        assertEquals(self, "self")
    }
}