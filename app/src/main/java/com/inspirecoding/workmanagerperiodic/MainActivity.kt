package com.inspirecoding.workmanagerperiodic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inspirecoding.workmanagerperiodic.viewmodel.PeriodicWorkViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private lateinit var periodicWorkViewModel: PeriodicWorkViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        periodicWorkViewModel = ViewModelProvider(this)
            .get(PeriodicWorkViewModel::class.java)

        periodicWorkViewModel.getInfo()?.let {
            textView.text = it
        }

        btn_start.setOnClickListener {
            periodicWorkViewModel.startPeriodicWork()
        }
        btn_stop.setOnClickListener {
            periodicWorkViewModel.stopPeriodicWork()
        }
        btn_clear.setOnClickListener {
            periodicWorkViewModel.clearInfo()
        }
    }
}