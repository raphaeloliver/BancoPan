package com.teste.thiago

import br.com.raphaeloliveira.testebancopan.model.GameItem
import junit.framework.Assert.assertEquals
import org.junit.Test

class GameItemTest {

    @Test
    fun testGameItem() {

        val (game, viewers, channels) = GameItem(null, 100, 200)

        assertEquals(channels, 200)
        assertEquals(viewers, 100)
        assertEquals(game, null)
    }
}