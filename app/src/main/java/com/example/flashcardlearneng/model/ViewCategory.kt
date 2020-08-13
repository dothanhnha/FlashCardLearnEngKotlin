package com.example.flashcardlearneng.model

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.flashcardlearneng.R

class ViewCategory : View {

    val label:String = "People"

    val amountWord:Int = 0

    var percentLearned :Int = 0

    val defaultFontSize :Float = 13f

    val offsetTextCircle:Float = 10f

    val boundsMaxText = Rect()

    var locationTextPercent : Point = Point()

    var locationLabel : Point = Point()

    val colorContent = Color.parseColor("#9BFF00")
    val paintContent = Paint()

    val colorErase = Color.parseColor("#FFFFFF")
    val paintCircleErase = Paint()

    val colorActive = Color.parseColor("#FFF000")
    val paintRoundLearned = Paint()

    val colorContentText = Color.parseColor("#000000")
    val paintContentText = Paint()

    val paintRound = Paint()

    val strokeWidth = 14f
    val offsetCirclePercent = 6f

    var radiusCirclePercent = 0f

    init {
        paintContent.color = colorContent
        paintContent.style = Paint.Style.FILL
        paintContent.isAntiAlias = true
        paintContent.setTextSize(defaultFontSize)  //set text size

        paintContent.getTextBounds("100%", 0, 3, boundsMaxText)

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

        paintContentText.color = colorContentText
        paintContentText.style = Paint.Style.FILL
        paintContentText.isAntiAlias = true
        paintContentText.setTextSize(defaultFontSize)

    }



    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        val a = context.obtainStyledAttributes(attrs, R.styleable.ViewCategoryStyle)
        if (a.hasValue(R.styleable.ViewCategoryStyle_percentActive)) {
            this.percentLearned = a.getInteger(R.styleable.ViewCategoryStyle_percentActive,0)
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawContent(canvas)
        drawCirclePercent(canvas)
        drawPercent(canvas)
        drawTextContent(canvas)
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
            -90f,  (this.percentLearned.toFloat()/100)*360, false, paintRoundLearned
        )
    }

    fun drawPercent(canvas: Canvas?){
        canvas?.drawText(this.percentLearned.toString()+"%",this.locationTextPercent.x.toFloat(),this.locationTextPercent.y.toFloat(),paintContent)
    }

    fun drawTextContent(canvas: Canvas?){
        canvas?.drawText(this.label,this.locationLabel.x.toFloat(),this.locationLabel.y.toFloat(),paintContentText)
    }

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

        var scaleFlexText = (this.radiusCirclePercent*2 - this.strokeWidth - this.offsetTextCircle)/this.paintContent.measureText("100%")

        this.paintContent.textSize = this.defaultFontSize*scaleFlexText


        val calculatedBoundText = Rect()

        paintContent.getTextBounds(this.percentLearned.toString() + "%",0,this.percentLearned.toString().length+1, calculatedBoundText)

        val xCenterCirclePercent = this.width.toFloat() - this.height / 2.toFloat()
        val yCenterCirclePercent  = this.height / 2.toFloat()

        this.locationTextPercent.x = (xCenterCirclePercent - paintContent.measureText(this.percentLearned.toString() + "%")/2).toInt()
        this.locationTextPercent.y = (yCenterCirclePercent + calculatedBoundText.height()/2).toInt()

        /////////////////////////



        var scaleFlexLabel = (this.width - 3*this.height/2 - this.offsetTextCircle)/this.paintContentText.measureText(this.label)

        this.paintContentText.textSize = this.defaultFontSize*scaleFlexLabel

        paintContentText.getTextBounds(this.label,0,this.label.toString().length, calculatedBoundText)
        locationLabel.x = height/2
        locationLabel.y = height/2 + calculatedBoundText.height()/2
    }
}