package com.example.flashcardlearneng.model

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.flashcardlearneng.R

class ViewCategory : View {

    var data:Category = Category("",1,1)
    get() = field
    set(value) {
        field = value
    }



    val defaultFontSize: Float = 13f

    val offsetTextCircle: Float = 10f
    val offsetDetailBottom: Float = 8f

    val boundsMaxText = Rect()

    var locationTextPercent: Point = Point()

    var locationLabel: Point = Point()
    var locationDetail: Point = Point()

    val colorContent = Color.parseColor("#9BFF00")
    val paintContent = Paint()

    val colorErase = Color.parseColor("#FFFFFF")
    val paintCircleErase = Paint()

    val colorActive = Color.parseColor("#FFF000")
    val paintRoundLearned = Paint()

    val colorContentText = Color.parseColor("#000000")
    val paintLabelText = Paint()

    val paintPercentText = Paint()

    val paintRound = Paint()

    val strokeWidth = 14f
    val offsetCirclePercent = 6f

    var radiusCirclePercent = 0f

    var paintDetailText = Paint()

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
        paintRound.strokeWidth = defaultFontSize
        paintRound.isAntiAlias = true

        paintRoundLearned.color = colorActive
        paintRoundLearned.style = Paint.Style.STROKE
        paintRoundLearned.strokeWidth = 14f
        paintRoundLearned.isAntiAlias = true

        paintLabelText.color = colorContentText
        paintLabelText.style = Paint.Style.FILL
        paintLabelText.isAntiAlias = true
        paintLabelText.strokeWidth = defaultFontSize
        paintLabelText.textSize = defaultFontSize

        paintPercentText.color = colorContent
        paintPercentText.style = Paint.Style.FILL
        paintPercentText.isAntiAlias = true
        paintPercentText.strokeWidth = defaultFontSize
        paintPercentText.textSize = defaultFontSize

        paintDetailText.color = colorContentText
        paintDetailText.style = Paint.Style.FILL
        paintDetailText.isAntiAlias = true
        paintDetailText.strokeWidth = defaultFontSize
        paintDetailText.textSize = defaultFontSize

    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
       /* val a = context.obtainStyledAttributes(attrs, R.styleable.ViewCategoryStyle)
        if (a.hasValue(R.styleable.ViewCategoryStyle_percentActive)) {
            this.percentLearned = a.getInteger(R.styleable.ViewCategoryStyle_percentActive, 0)
        }*/
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("recyclerView","onDraw")
        Log.d("text size",paintLabelText.textSize.toString() + "_"+paintDetailText.textSize.toString() + "_" + paintPercentText.textSize.toString() + "_")
        calculateLocationTextPercent()
        drawContent(canvas)
        drawCirclePercent(canvas)
        drawPercent(canvas)
        drawTextContent(canvas)
        drawTextDetail(canvas)
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

    fun drawCirclePercent(canvas: Canvas?) {
        canvas?.drawCircle(
            this.width.toFloat() - this.height / 2.toFloat(),
            this.height / 2.toFloat(),
            radiusCirclePercent,
            paintRound
        )

        canvas?.drawArc(
            RectF(
                this.width - (radiusCirclePercent * 2 + strokeWidth / 2 + offsetCirclePercent),
                offsetCirclePercent + strokeWidth / 2,
                this.width - (strokeWidth / 2 + offsetCirclePercent),
                this.height - (offsetCirclePercent + strokeWidth / 2)
            ),
            -90f, (this.data.percentLearned.toFloat() / 100) * 360, false, paintRoundLearned
        )
    }

    fun drawPercent(canvas: Canvas?) {
        canvas?.drawText(
            this.data.percentLearned.toString() + "%",
            this.locationTextPercent.x.toFloat(),
            this.locationTextPercent.y.toFloat(),
            paintPercentText
        )
    }

    fun drawTextContent(canvas: Canvas?) {
        canvas?.drawText(
            this.data.nameCategory,
            this.locationLabel.x.toFloat(),
            this.locationLabel.y.toFloat(),
            paintLabelText
        )
    }

    fun drawTextDetail(canvas: Canvas?) {
        canvas?.drawText(
            this.data.wordTotal.toString(),
            this.locationDetail.x.toFloat(),
            this.locationDetail.y.toFloat(),
            paintDetailText
        )
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        Log.d("recyclerView","onLayout")
        calculateLocation()
        calculateSizeTextPercent()
    }

    fun calculateSizeTextPercent() {

        this.paintPercentText.textSize = this.defaultFontSize

        this.radiusCirclePercent = this.height / 2 - (offsetCirclePercent + strokeWidth / 2)

        var scaleFlexText =
            (this.radiusCirclePercent * 2 - this.strokeWidth - this.offsetTextCircle) / this.paintPercentText.measureText(
                "100%"
            )

        this.paintPercentText.textSize = this.defaultFontSize * scaleFlexText
    }

    fun calculateLocation() {
        var calculatedBoundText = Rect()
        val boundTextDefault = Rect()
        this.paintLabelText.textSize = this.defaultFontSize
        paintLabelText.getTextBounds(
            this.data.nameCategory,
            0,
            this.data.nameCategory.length,
            boundTextDefault
        )

        var scaleFlexLabel = ((2f / 6f) * (this.height) / boundTextDefault.height())

        this.paintLabelText.textSize = this.defaultFontSize * scaleFlexLabel

        paintLabelText.getTextBounds(this.data.nameCategory, 0, this.data.nameCategory.length, calculatedBoundText)
        locationLabel.x = height / 2
        locationLabel.y = height / 2 + calculatedBoundText.height() / 2

        ////////////////////

        this.paintDetailText.textSize = 1f / 2f * this.paintLabelText.textSize

        paintDetailText.getTextBounds(
            this.data.wordTotal.toString(),
            0,
            this.data.wordTotal.toString().length,
            calculatedBoundText
        )
        locationDetail.x =
            this.width - this.height - paintDetailText.measureText(this.data.wordTotal.toString())
                .toInt()
        locationDetail.y = height - offsetDetailBottom.toInt()
    }

    fun calculateLocationTextPercent() {

        val calculatedBoundText = Rect()

        paintPercentText.getTextBounds(
            this.data.percentLearned.toString() + "%",
            0,
            this.data.percentLearned.toString().length + 1,
            calculatedBoundText
        )

        val xCenterCirclePercent = this.width.toFloat() - this.height / 2.toFloat()
        val yCenterCirclePercent = this.height / 2.toFloat()

        this.locationTextPercent.x =
            (xCenterCirclePercent - paintPercentText.measureText(this.data.percentLearned.toString() + "%") / 2).toInt()
        this.locationTextPercent.y =
            (yCenterCirclePercent + calculatedBoundText.height() / 2).toInt()
    }
}