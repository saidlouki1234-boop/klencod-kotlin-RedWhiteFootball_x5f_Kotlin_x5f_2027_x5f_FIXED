
package com.redwhite.football2027.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.redwhite.football2027.GameMain
import com.redwhite.football2027.model.MatchModel

class MatchScreen(private val game: GameMain): Screen {
    private val model = MatchModel()
    private val shape = ShapeRenderer()
    private var joystick = Vector2()
    private var shootCharge = 0f
    private var isShooting = false

    override fun show() {
        Gdx.input.inputProcessor = null
    }

    override fun render(delta: Float) {
        // Handle input manually
        if (Gdx.input.isTouched) {
            val x = Gdx.input.x.toFloat()
            val y = Gdx.input.y.toFloat()
            val gy = Gdx.graphics.height - y
            if (x < Gdx.graphics.width/2f) {
                // joystick left
                joystick.x = (x - 200f)/150f
                joystick.y = (gy - 400f)/150f
                joystick.clamp(-1f,1f)
            } else {
                isShooting = true; shootCharge += delta
            }
        } else {
            if (isShooting) {
                val power = 500f + shootCharge.coerceAtMost(1.2f)*1200f
                val user = model.teamA.firstOrNull { it.isUser }
                model.ball.vy = if ((user?.y ?: 0f) < 960f) power else -power
                model.ball.vx = joystick.x*200f
                isShooting=false; shootCharge=0f
            }
            joystick.setZero()
        }

        model.update(delta)
        val user = model.teamA.firstOrNull { it.isUser }
        user?.let{
            it.vx = joystick.x*320f
            it.vy = joystick.y*320f
            if (dist(it.x,it.y,model.ball.x,model.ball.y)<65f){
                model.ball.x += it.vx*delta*0.7f
                model.ball.y += it.vy*delta*0.7f
            }
        }
        val goal = model.checkGoal()

        Gdx.gl.glClearColor(0.18f,0.55f,0.18f,1f); Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.color = Color.WHITE
        shape.rectLine(20f,20f,1060f,20f,3f); shape.rectLine(20f,1900f,1060f,1900f,3f)
        shape.rectLine(20f,20f,20f,1900f,3f); shape.rectLine(1060f,20f,1060f,1900f,3f)
        shape.color = Color.valueOf("D10A1F"); shape.rect(400f,10f,280f,28f); shape.rect(400f,1870f,280f,28f)
        shape.color = Color.valueOf("D10A1F")
        model.teamA.forEach{ shape.circle(it.x,it.y,24f) }
        shape.color = Color.valueOf("0A2A9A")
        model.teamB.forEach{ shape.circle(it.x,it.y,24f) }
        shape.color = Color.WHITE; shape.circle(model.ball.x, model.ball.y, 15f)
        // joystick visual
        shape.color = Color(1f,1f,1f,0.3f)
        shape.circle(200f,400f,90f)
        shape.color = Color.WHITE
        shape.circle(200f+joystick.x*50f,400f+joystick.y*50f,30f)
        shape.end()

        game.batch.begin()
        game.font.color = Color.WHITE
        game.font.draw(game.batch, "${model.scoreA} - ${model.scoreB}  ${model.timeSec.toInt()/60}:${(model.timeSec.toInt()%60).toString().padStart(2,'0')}", 430f, 1830f)
        game.font.draw(game.batch, if(isShooting) "Tasdid: ${(shootCharge*100).toInt()}%" else "Yasar: Haraka | Yamin: Tasdid", 80f, 120f)
        if (goal!=null) { game.font.color = Color.YELLOW; game.font.draw(game.batch, "GOAL! Hadaf!", 450f, 1050f) }
        if (!model.isPlaying) { game.font.color = Color.WHITE; game.font.draw(game.batch, "Entahet - ${model.scoreA}-${model.scoreB} - Touch to Menu", 200f, 1000f)
            if (Gdx.input.justTouched()) game.setScreen(MainMenuScreen(game)) }
        game.batch.end()
    }
    private fun dist(ax:Float,ay:Float,bx:Float,by:Float)= kotlin.math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by))
    override fun resize(w:Int,h:Int){}; override fun pause(){}; override fun resume(){}; override fun hide(){}; override fun dispose(){ shape.dispose() }
}
