package com.dmadunts.samples.mvpsample.view.creature

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dmadunts.samples.mvpsample.R
import com.dmadunts.samples.mvpsample.databinding.ActivityCreatureBinding
import com.dmadunts.samples.mvpsample.model.AttributeStore
import com.dmadunts.samples.mvpsample.model.AttributeType
import com.dmadunts.samples.mvpsample.model.AttributeValue
import com.dmadunts.samples.mvpsample.model.Avatar
import com.dmadunts.samples.mvpsample.presenter.CreatureContract
import com.dmadunts.samples.mvpsample.presenter.CreaturePresenter
import com.dmadunts.samples.mvpsample.view.avatars.AvatarAdapter
import com.dmadunts.samples.mvpsample.view.avatars.AvatarBottomDialogFragment
import com.dmadunts.samples.mvpsample.view.base.BaseActivity
import kotlinx.coroutines.launch

class CreatureActivity : BaseActivity(), CreatureContract.View, AvatarAdapter.AvatarListener {
    private lateinit var binding: ActivityCreatureBinding
    private val presenter = CreaturePresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.setView(this)
        configureUI()
        configureSpinnerAdapters()
        configureSpinnerListeners()
        configureEditText()
        configureClickListeners()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.add_creature)
        if (presenter.isDrawableSelected()) hideTapLabel()
    }

    private fun configureSpinnerAdapters() {
        binding.intelligence.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE
        )
        binding.strength.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH
        )
        binding.endurance.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE
        )
    }

    private fun configureSpinnerListeners() {
        binding.intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.attributeSelected(AttributeType.INTELLIGENCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.attributeSelected(AttributeType.STRENGTH, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.attributeSelected(AttributeType.ENDURANCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun configureEditText() {
        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateName(s.toString())
            }
        })
    }

    private fun configureClickListeners() {
        binding.avatarImageView.setOnClickListener {
            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                presenter.saveCreature()
            }
        }
    }

    override fun avatarClicked(avatar: Avatar) {
        presenter.drawableSelected(avatar.drawable)
        hideTapLabel()
    }

    private fun hideTapLabel() {
        binding.tapLabel.visibility = View.INVISIBLE
    }

    override fun showHitPoints(hitPoints: String) {
        binding.hitPoints.text = hitPoints
    }

    override fun showAvatarDrawable(resourceId: Int) {
        binding.avatarImageView.setImageResource(resourceId)
    }

    override fun showCreatureSaved() {
        Toast.makeText(this, R.string.creature_saved, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showCreatureSavedError() {
        Toast.makeText(this, R.string.error_saving_creature, Toast.LENGTH_SHORT).show()
    }
}