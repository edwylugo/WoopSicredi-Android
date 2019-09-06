package edwylugo.woopsicredi.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import edwylugo.woopsicredi.R

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val DURATION: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* ======== Start intro animation. ======== */
        startAnimation()
    }

    /* ########################################## */
    /* #                                        # */
    /* #          Load Start animation          # */
    /* #                                        # */
    /* ########################################## */
    private fun startAnimation() {
        /* ======== Intro animation configuration. ======== */
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            iv_logo.scaleX = value
            iv_logo.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = DURATION

        /* ======== Set animator listener. ======== */
        val intent = Intent(this, MainActivity::class.java)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                /* ======== Navigate to main activity on navigation end. ======== */
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })

        /* ======== Start animation. ======== */
        valueAnimator.start()
    }

}
