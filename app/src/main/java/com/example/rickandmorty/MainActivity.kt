package com.example.rickandmorty

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickandmorty.util.adapters.EpoxyCharController
import com.example.rickandmorty.util.adapters.EpoxyChipController
import com.example.rickandmorty.util.paging.LCharactersDataSource
import com.example.rickandmorty.util.paging.ViewModel

class MainActivity : AppCompatActivity(), CallBack {

    private var context: Context? = null
    private val viewModel: ViewModel by viewModels()
    private val epoxyCharController = EpoxyCharController()
    private lateinit var verticalRecyclerView: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val prefs = getSharedPreferences("mPrefs", MODE_PRIVATE)
        val hasOpenedBefore = prefs.getBoolean("hasOpenedBefore", false)
        if (!hasOpenedBefore) {
            prefs.edit().putBoolean("hasOpenedBefore", true).apply()
            setTheme(R.style.SplashThemeFirst)
        }

        val callBack: CallBack = this
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        val linearLayoutManager = LinearLayoutManager(context)

        val epoxyChipController = EpoxyChipController(callBack)


        viewModel.locationsPagedListLiveData.observe(this@MainActivity) { pagedList ->
            epoxyChipController.submitList(pagedList)
        }
        viewModel.charactersPagedListLiveData.observe(this@MainActivity) { pagedList2 ->
            epoxyCharController.submitList(pagedList2)
        }

        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                setContentView(R.layout.activity_main)

                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                val horizontalRecyclerView = findViewById<EpoxyRecyclerView>(R.id.horizontalRecyclerView)
                verticalRecyclerView = findViewById(R.id.verticalRecyclerView)

                setSupportActionBar(toolbar)

                horizontalRecyclerView.layoutManager = staggeredGridLayoutManager
                verticalRecyclerView.layoutManager = linearLayoutManager

                horizontalRecyclerView.setController(epoxyChipController)
                verticalRecyclerView.setController(epoxyCharController)
            }
        }.start()
    }

    override fun onCallback() {
        epoxyCharController.cancelPendingModelBuild()
        viewModel.lCharactersPagedListLiveData.removeObservers(this@MainActivity)
        if(LCharactersDataSource.location!!.residents != null){
            viewModel.renew()
            viewModel.lCharactersPagedListLiveData.observe(this@MainActivity) { pagedList2 ->
                if(pagedList2.size > 0){
                    epoxyCharController.submitList(pagedList2)
                    if(verticalRecyclerView.visibility == View.GONE){
                        verticalRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
        else {
            verticalRecyclerView.visibility = View.GONE
        }

    }
}

interface CallBack {
    fun onCallback()
}
