package com.ext.swipeup

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

class SwipeUpLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var startY = 0f
    private var swipeThreshold =
        resources.getDimensionPixelSize(R.dimen.default_swipe_threshold)
    private var callback: SwipeUpCallback? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SwipeUpLayout).apply {
            swipeThreshold = getDimensionPixelSize(
                R.styleable.SwipeUpLayout_swipeThreshold,
                swipeThreshold
            )
            recycle()
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> startY = ev.y
            MotionEvent.ACTION_MOVE -> {
                val diff = startY - ev.y
                return diff > swipeThreshold
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
    fun setSwipeUpCallback(callback: SwipeUpCallback) {
        this.callback = callback
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                val diff = startY - event.y
                if (diff > swipeThreshold) {
                    callback?.onSwipeUp()
                }
            }
        }
        return true
    }

}
