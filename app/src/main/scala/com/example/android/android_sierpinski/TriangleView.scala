package com.example.android.android_sierpinski

import android.content.Context
import android.graphics.Path.FillType
import android.graphics.{Canvas, Paint, Path}
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

class Point(val x: Float, val y: Float)

class Triangle(pos: Point, len: Float, level: Int, canvas: Canvas, paint: Paint) {
  if (level != 0) {
    //Calculating points A,B and C of triangle
    val a = new Point(pos.x, pos.y)
    val b = new Point(pos.x + len, pos.y)
    val height = Math.sqrt((len * len) - (len / 2) * (len / 2)).toFloat //Pythagoras for height
    val c = new Point((b.x - a.x) / 2 + pos.x, pos.y - height)

    //"cursor"
    val path = new Path()
    path.setFillType(FillType.EVEN_ODD)
    path.moveTo(a.x, b.y) //left top -> right top
    path.lineTo(b.x, b.y)
    path.lineTo(c.x, c.y)
    path.lineTo(a.x, a.y)
    path.close()

    //draw
    canvas.drawPath(path, paint)

    new Triangle(new Point(a.x, a.y), len / 2, level - 1, canvas, paint)
    new Triangle(new Point(c.x, a.y), len / 2, level - 1, canvas, paint)
    new Triangle(new Point((c.x - a.x) / 2 + a.x, (c.y - a.y) / 2 + a.y), len / 2, level - 1, canvas, paint)
  }
}

class TriangleView(val context: Context, val attrs: AttributeSet) extends View(context, attrs) {

  private var level: Int = 3

  def showToast(msg: String): Unit = Toast.makeText(getContext, msg, Toast.LENGTH_SHORT).show()

  def getLevel(): Int = level

  def setLevel(level: Int): Unit = {
    if (level == 0)
      showToast("Minimum level reached")
    else if (level > 7)
      showToast("Maximum level reached")

    this.level = if (level > 0 && level <= 7) level else this.level
  }

  def levelDown(): TriangleView = {
    setLevel(level - 1)
    this
  }

  def levelUp(): TriangleView = {
    setLevel(level + 1)
    this
  }

  override protected def onDraw(canvas: Canvas):Unit = {
    super.onDraw(canvas)

    val paint = new Paint()
    val screenWidth = getResources().getDisplayMetrics().widthPixels
    val screenHeight = getResources().getDisplayMetrics().heightPixels
    val startPosition = new Point((screenWidth*0.1).toFloat, (screenHeight*0.6).toFloat)
    val length = (screenWidth*0.8).toFloat

    paint.setStrokeWidth(2)
    paint.setColor(android.graphics.Color.BLUE)
    paint.setStyle(Paint.Style.STROKE)
    paint.setAntiAlias(true)

    new Triangle(startPosition, length, level, canvas, paint)
  }

}

