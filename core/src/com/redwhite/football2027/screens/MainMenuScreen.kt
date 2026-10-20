
package com.redwhite.football2027.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.redwhite.football2027.GameMain
import com.redwhite.football2027.model.TeamsRepo

class MainMenuScreen(private val game: GameMain): Screen {
    private val shape = ShapeRenderer()
    private val playBtn = Rectangle(300f, 900f, 480f, 140f)
    private val quitBtn = Rectangle(300f, 700f, 480f, 120f)

    override fun show() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f,1f,1f,1f); Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.justTouched()) {
            val x = Gdx.input.x.toFloat()
            val y = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (playBtn.contains(x,y)) {
                game.setScreen(MatchScreen(game))
                return
            }
            if (quitBtn.contains(x,y)) Gdx.app.exit()
        }

        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.color = Color.valueOf("D10A1F")
        shape.rect(playBtn.x, playBtn.y, playBtn.width, playBtn.height)
        shape.color = Color.DARK_GRAY
        shape.rect(quitBtn.x, quitBtn.y, quitBtn.width, quitBtn.height)
        shape.end()

        game.batch.begin()
        game.font.color = Color.valueOf("D10A1F")
        game.font.draw(game.batch, "RedWhiteFootball 2027", 250f, 1700f)
        game.font.color = Color.BLACK
        game.font.draw(game.batch, "Al Nadi Al Ifriqi - Bab Jedid", 200f, 1630f)
        game.font.color = Color.WHITE
        game.font.draw(game.batch, "PLAY - Ebdaa Mobara", 400f, 980f)
        game.font.draw(game.batch, "QUIT", 500f, 770f)
        game.font.color = Color.BLACK
        game.font.draw(game.batch, TeamsRepo.teams.joinToString(" | ") { it.nameAr }.take(80), 20f, 400f)
        game.batch.end()
    }
    override fun resize(w:Int,h:Int){}; override fun pause(){}; override fun resume(){}; override fun hide(){}; override fun dispose(){ shape.dispose() }
}
