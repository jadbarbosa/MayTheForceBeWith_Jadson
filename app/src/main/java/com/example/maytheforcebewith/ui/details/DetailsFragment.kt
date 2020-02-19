package com.example.maytheforcebewith.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.maytheforcebewith.R
import com.example.maytheforcebewith.databinding.FragmentDetailsBinding
import com.example.maytheforcebewith.util.PERSON_URL
import com.example.maytheforcebewith.util.PREFERENCE_FAVORITE_LIST
import com.example.maytheforcebewith.util.helpers.getPreferencesSet
import com.example.maytheforcebewith.util.helpers.setPreferencesSet
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(DetailsViewModel::class.java) }
    lateinit var favoriteListPreference: MutableSet<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this

        binding.detailsViewModel = viewModel

        favoriteListPreference =
            if (getPreferencesSet(PREFERENCE_FAVORITE_LIST, context!!) != null) {
                getPreferencesSet(PREFERENCE_FAVORITE_LIST, context!!)!!
            } else {
                mutableSetOf()
            }

        val person = arguments?.getString(PERSON_URL)

        person?.let { viewModel.getPerson(it) }

        viewModel.personData.observe(this, Observer {
            if (it != null && isFavorite(it.name))
                cbFavorite.isChecked = true
        })


        binding.cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.postFavorite()
            } else {
                viewModel.personData.value?.name?.let { character ->
                    favoriteListPreference.remove(
                        character
                    )
                }
                setPreferencesSet(
                    favoriteListPreference.toList(),
                    PREFERENCE_FAVORITE_LIST,
                    true,
                    context!!
                )
            }
        }
        viewModel.successRequest.observe(this, Observer {
            when (it) {
                1 -> {
                    binding.cbFavorite.isChecked = true
                    viewModel.personData.value?.name?.let { character ->
                        favoriteListPreference.add(character)
                        setPreferencesSet(
                            favoriteListPreference.toList(),
                            PREFERENCE_FAVORITE_LIST,
                            true,
                            context!!
                        )
                    }
                }
                -1 -> binding.cbFavorite.isChecked = false
            }
        })
        return binding.root
    }

    private fun isFavorite(name: String): Boolean {
        favoriteListPreference.forEach {
            if (it.contains(name))
                return true
        }
        return false
    }

}
