package com.example.flashcardlearneng.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.flashcardlearneng.R

class ViewCategory : View {

    var percentLearned :Float = 0f

    val colorContent = Color.parseColor("#9BFF00")
    val paintContent = Paint()

    val colorErase = Color.parseColor("#FFFFFF")
    val paintCircleErase = Paint()

    val colorActive = Color.parseColor("#FFF000")
    val paintRoundLearned = Paint()

    val paintRound = Paint()

    val strokeWidth = 14f
    val offsetCirclePercent = 6f

    var radiusCirclePercent = 0f

    init {
        paintContent.color = colorContent
        paintContent.style = Paint.Style.FILL
        paintContent.isAntiAlias = true

        paintCircleErase.color = colorErase
        paintCircleErase.style = Paint.Style.FILL
        paintCircleErase.isAntiAlias = true

        paintRound.color = colorContent
        paintRound.style = Paint.Style.STROKE
        paintRound.strokeWidth = 14f
        paintRound.isAntiAlias = true

        paintRoundLearned.color = colorActive
        paintRoundLearned.style = Paint.Style.STROKE
        paintRoundLearned.strokeWidth = 14f
        paintRoundLearned.isAntiAlias = true
    }



    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ViewCategoryStyle)
            if (a.hasValue(R.styleable.ViewCategoryStyle_percentActive)) {
                this.percentLearned = a.getFloat(R.styleable.ViewCategoryStyle_percentActive,0f)
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawContent(canvas)
        drawCirclePercent(canvas)
    }

    fun drawContent(canvas: Canvas?) {
        canvas?.drawCircle(
            this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            paintContent
        )

        canvas?.drawRect(
            this.height / 2.toFloat(),
            0f,
            this.width.toFloat() - this.height / 2.toFloat(),
            this.height.toFloat(),
            paintContent
        )

        canvas?.drawCircle(
            this.width.toFloat() - this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            paintCircleErase
        )
    }

    fun drawCirclePercent(canvas: Canvas?){
        canvas?.drawCircle(
            this.width.toFloat() - this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            radiusCirclePercent,
            paintRound
        )

        canvas?.drawArc(
            RectF(
                this.width - (radiusCirclePercent * 2 + strokeWidth/2 + offsetCirclePercent),
                offsetCirclePercent + strokeWidth / 2,
                this.width - (strokeWidth / 2 + offsetCirclePercent),
                this.height - (offsetCirclePercent + strokeWidth / 2)
            ),
            -90f,  (this.percentLearned/100)*360, false, paintRoundLearned
        )
    }

    /*fun drawPercent(canvas: Canvas?){
        canvas?.drawText(this.percentLearned.toString()+"%",0,this.percentLearned.toString().length+1,)
    }*/

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //setMeasuredDimension(40,40)
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        if (specMode == MeasureSpec.AT_MOST)
            Log.d("MeasureSpec", "at most")
        if (specMode == MeasureSpec.EXACTLY)
            Log.d("MeasureSpec", "exactly")
        if (specMode == MeasureSpec.UNSPECIFIED)
            Log.d("MeasureSpec", "unspecified")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        this.radiusCirclePercent = this.height / 2 - ( offsetCirclePercent + strokeWidth / 2)
    }
}