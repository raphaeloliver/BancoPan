package com.teste.thiago

import br.com.raphaeloliveira.testebancopan.model.Game
import br.com.raphaeloliveira.testebancopan.model.ImagesUrls
import junit.framework.Assert.assertEquals
import org.junit.Test



class GameTest{

    @Test
    fun testGameGetters() {
        val imagesUrls = ImagesUrls("large", "medium", "small", "template")
        val (name, box, logo, id, giantBombId) = Game("gameName", imagesUrls, imagesUrls, "id", "giantBomb")

        assertEquals(name, "gameName")
        assertEquals(id, "id")
        assertEquals(giantBombId, "giantBomb")
        assertEquals(logo, imagesUrls)
        assertEquals(box, imagesUrls)
    }
}