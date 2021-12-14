package com.dmadunts.samples.mvpsample.view.allcreatures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmadunts.samples.mvpsample.R
import com.dmadunts.samples.mvpsample.databinding.ActivityAllCreaturesBinding
import com.dmadunts.samples.mvpsample.view.base.BaseActivity
import com.dmadunts.samples.mvpsample.view.creature.CreatureActivity

class AllCreaturesActivity : BaseActivity() {
    private lateinit var binding: ActivityAllCreaturesBinding
    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCreaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.creaturesRecyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreatureActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}