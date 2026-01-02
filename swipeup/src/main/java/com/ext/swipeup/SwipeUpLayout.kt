package com.ext.swipeup

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import kotlin.math.abs

class SwipeUpLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var startY = 0f
    private var lastTranslationY = 0f

    private var expandedY = 0f
    private var collapsedY = 0f

    private var swipeThreshold = 120

    private var callback: SwipeUpCallback? = null
    private var contentView: View? = null
    private var currentState = SwipeUpState.COLLAPSED
    private var startExpanded = false



    init {
        isClickable = true
        isFocusable = true
        context.obtainStyledAttributes(attrs, R.styleable.SwipeUpLayout).apply {
            startExpanded = getBoolean(
                R.styleable.SwipeUpLayout_startExpanded,
                false
            )
            recycle()
        }
    }

    fun setSwipeUpCallback(callback: SwipeUpCallback) {
        this.callback = callback
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        post {
            contentView?.let {
                expandedY = 0f
                collapsedY = it.height.toFloat()

                if (startExpanded) {
                    it.translationY = expandedY
                    currentState = SwipeUpState.EXPANDED
                } else {
                    it.translationY = collapsedY
                    currentState = SwipeUpState.COLLAPSED
                }
            }
        }


    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                startY = ev.rawY
                lastTranslationY = translationY
            }
            MotionEvent.ACTION_MOVE -> {
                val diff = abs(startY - ev.rawY)
                return diff > 10
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                val diff = event.rawY - startY
                val target = lastTranslationY + diff

                contentView?.translationY = target.coerceIn(expandedY, collapsedY)

            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                if (translationY < collapsedY / 2) {
                    expand()
                } else {
                    collapse()
                }
            }
        }
        return true
    }
    override fun onFinishInflate() {
        super.onFinishInflate()
        contentView = getChildAt(0)
    }


    fun expand() {
        currentState = SwipeUpState.EXPANDED
        contentView?.animate()
            ?.translationY(expandedY)
            ?.setDuration(250)
            ?.start()

        callback?.onSwipeUp()
    }

    fun collapse() {
        currentState = SwipeUpState.COLLAPSED
        contentView?.animate()
            ?.translationY(collapsedY)
            ?.setDuration(250)
            ?.start()
    }


}
