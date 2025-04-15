package com.example.dicerollerapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dicerollerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initViews
        binding.apply {
            btnRoll.setOnClickListener{
                rollDice()
            }
        }

    }
    private fun getRandomDiceImageResource(): Int
    {
        return when((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun rollDice()
    {
        //initViews
        binding.apply {
            //disable the roll button to prevent multiple rolls
            btnRoll.isEnabled = false
            //roll the dice for 1 second with a delay of 100 millisecond
            val diceRoller = object : Runnable{
                var counter = 0
                override fun run() {
                    counter ++
                    if (counter>10)
                    {
                        //stop rolling after 1 second
                        diceImage.setImageResource(getRandomDiceImageResource())
                        btnRoll.isEnabled = true
                    }else
                    {
                       //continue rolling with a delay of 100 ms
                        diceImage.setImageResource(getRandomDiceImageResource())
                        Handler(Looper.getMainLooper()).postDelayed(this, 100)
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed(diceRoller,0)
        }
    }
}