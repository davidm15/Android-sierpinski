package com.example.android.android_sierpinski

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.{Button, TextView}


class MainActivity extends AppCompatActivity {

  def getTriangleView(): TriangleView = findViewById(R.id.TriangleView).asInstanceOf[TriangleView]

  def getLevelDownBtn(): Button = findViewById(R.id.levelDownBtn).asInstanceOf[Button]

  def getLevelUpBtn(): Button = findViewById(R.id.levelUpBtn).asInstanceOf[Button]

  def getShowLevel(): TextView = findViewById(R.id.showLevel).asInstanceOf[TextView]

  def updateShowLevel(): Unit = getShowLevel.setText("Level: " + getTriangleView.getLevel.toString)

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    updateShowLevel

    getLevelDownBtn().setOnClickListener(new View.OnClickListener() {
      def onClick(v : View): Unit = {
        getTriangleView.levelDown.invalidate
        updateShowLevel
      }
    })

    getLevelUpBtn().setOnClickListener(new View.OnClickListener() {
      def onClick(v : View): Unit = {
        getTriangleView.levelUp.invalidate
        updateShowLevel
      }
    })
  }

}
