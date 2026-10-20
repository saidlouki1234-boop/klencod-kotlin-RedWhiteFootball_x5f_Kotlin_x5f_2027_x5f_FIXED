
package com.redwhite.football2027.model

class Ball(var x: Float, var y: Float, var vx: Float=0f, var vy: Float=0f) {
    fun update(dt: Float) {
        x += vx*dt; y += vy*dt
        vx *= 0.98f; vy *= 0.98f // friction
        // bounce walls
        if (x<20) { x=20f; vx*=-0.7f }
        if (x>1080-20) { x=1080-20f; vx*=-0.7f }
        if (y<20) { y=20f; vy*=-0.7f }
        if (y>1920-20) { y=1920-20f; vy*=-0.7f }
    }
}

class Player(var x: Float, var y: Float, val isUser: Boolean, val teamId: String) {
    var vx=0f; var vy=0f
    fun update(dt: Float) {
        x+=vx*dt; y+=vy*dt
    }
}

class MatchModel {
    var scoreA=0; var scoreB=0
    var timeSec=90f
    var isPlaying=true
    val ball = Ball(540f, 960f)
    val teamA = mutableListOf<Player>()
    val teamB = mutableListOf<Player>()
    init {
        // spawn players
        for (i in 0..4) teamA.add(Player(300f + i*40, 400f + i*120, i==0, "CA"))
        for (i in 0..4) teamB.add(Player(700f + i*30, 1200f + i*100, false, "BLU"))
    }

    fun checkGoal(): Int? {
        // goals at top and bottom center
        if (ball.y < 80 && ball.x in 400f..680f) { scoreB++; reset(); return 2 }
        if (ball.y > 1840 && ball.x in 400f..680f) { scoreA++; reset(); return 1 }
        return null
    }
    fun reset() { ball.x=540f; ball.y=960f; ball.vx=0f; ball.vy=0f }
    fun update(dt: Float) {
        if (!isPlaying) return
        timeSec-=dt
        if (timeSec<=0) { timeSec=0f; isPlaying=false }
        ball.update(dt)
        teamA.forEach{ it.update(dt) }
        teamB.forEach{ it.update(dt) }
        // simple AI: closest B chases ball
        val chaser = teamB.minByOrNull { dist(it.x,it.y,ball.x,ball.y) }
        chaser?.let{
            val dx = ball.x-it.x; val dy=ball.y-it.y
            val d = kotlin.math.sqrt(dx*dx+dy*dy)
            if (d>5f){ it.vx=dx/d*180f; it.vy=dy/d*180f } else { // kick
                ball.vx = (540f-ball.x)*0.01f + (-1f)*500f
                ball.vy = -400f
            }
        }
    }
    private fun dist(ax:Float,ay:Float,bx:Float,by:Float)= kotlin.math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by))
}
