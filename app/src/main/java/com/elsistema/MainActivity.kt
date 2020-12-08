package com.elsistema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ml : MotionLayout = findViewById(R.id.motion_layout)

        val viewModel = ViewModelProviders
                .of(this)
                .get(SwipeRightViewModel::class.java)

        viewModel
                .modelStream
                .observe(this, Observer {
                    bindCard(it)
                })

        ml.setTransitionListener(object: TransitionAdapter(){
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                    }
                }
            }
        })

    }

    private fun bindCard(it: Any) {

    }
}