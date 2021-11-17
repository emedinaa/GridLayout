package com.emedinaa.samples.gridlayout

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.emedinaa.samples.gridlayout.databinding.ActivityMainBinding

/**
 * @author Eduardo Medina
 */
private const val ROW = 3
private const val COLUMN = 3

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private val items = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9) //3x3
    private var array = Array(ROW) { IntArray(COLUMN) } //3x3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        renderGrid()
    }

    private fun setup() {
        //populate
        var count = 0
        for (i in 0 until ROW) {
            for (j in 0 until COLUMN) {
                array[i][j] = (count + 1)
                count++
            }
        }
        //render
        for (i in 0 until ROW) {
            for (j in 0 until COLUMN) {
                Log.v("CONSOLE", "($i,$j) ${array[i][j]}")
            }
        }
        /*
          (0,0) 1 (0,1) 2 (0,2) 3
          (1,0) 4 (1,1) 5 (1,2) 6
          (2,0) 7 (2,1) 8 (2,2) 9
         */
    }

    private fun renderGrid() {
        with(binding.gridLayout) {
            columnCount = COLUMN
            rowCount = ROW
            alignmentMode = GridLayout.ALIGN_BOUNDS
            useDefaultMargins = true
        }

        for (i in 0 until ROW) {
            for (j in 0 until COLUMN) {
                val button = buildButton(array[i][j])
                binding.gridLayout.addView(button)
                button.setOnClickListener {
                    showButtonInfo(array[i][j].toString())
                }
            }
        }
    }

    private fun buildButton(value: Int): Button {
        val button = Button(this)
        button.text = value.toString()
        //button.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        button.layoutParams = GridLayout.LayoutParams(
            GridLayout.spec(GridLayout.UNDEFINED, 1f),
            GridLayout.spec(GridLayout.UNDEFINED, 1f)
        ).apply {
            width = 0
            height = 0
        }
        return button
    }

    private fun showButtonInfo(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }
}