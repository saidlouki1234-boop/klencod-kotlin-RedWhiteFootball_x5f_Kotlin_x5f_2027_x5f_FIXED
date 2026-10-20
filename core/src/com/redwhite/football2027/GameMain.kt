
package com.redwhite.football2027

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.redwhite.football2027.screens.MainMenuScreen

class GameMain : Game() {
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    var selectedTeamA = "CA" // Club Africain
    var selectedTeamB = "BLU"

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont() // استبدل بخط عربي Cairo لدعم RTL
        setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
