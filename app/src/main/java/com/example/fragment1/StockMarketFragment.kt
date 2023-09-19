package com.example.fragment1

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.fragment1.databinding.StockMarketFragmentBinding
import kotlin.random.Random

class StockMarketFragment : Fragment() {
    private lateinit var binding: StockMarketFragmentBinding
    var state = true

        set(value) {
            if(!field && value){
                field = value
                StockMarketThread().execute(null)
            }
            if (!value){
                field = value
            }
        }
    private var bseIndexedValue = 66000
        set(value) {
            field = value
            binding.txtBseIndexValue.text = "$field"
        }

    private var nseIndexedValue = 20000
        set(value) {
            field = value
            binding.txtNseIndexValue.text = "$field"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = StockMarketFragmentBinding.bind(
            inflater.inflate(R.layout.stock_market_fragment,null)
        )

        binding.btnStop.setOnClickListener {
            state = false
        }
        StockMarketThread().execute(null)

        return binding.root
    }

    private inner class StockMarketThread : AsyncTask<Any,Int,Any>(){
        override fun doInBackground(vararg params: Any?): Any? {
            while (state){
                Thread.sleep(1000)
                publishProgress(null)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            bseIndexedValue += Random.nextInt().mod(10)
            nseIndexedValue += Random.nextInt().mod(10)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}