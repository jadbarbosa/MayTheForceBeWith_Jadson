package com.example.maytheforcebewith.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maytheforcebewith.R
import com.example.maytheforcebewith.databinding.FragmentMainBinding
import com.example.maytheforcebewith.ui.main.adapter.PeopleAdapter

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by lazy {  ViewModelProviders.of(this).get(MainViewModel::class.java)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.mainViewModel = viewModel

        configuraRecyclerView()
        binding.btLoadMore.setOnClickListener {
            viewModel.get()
        }
        return binding.root
    }

    private fun navigateToDetails(personDetailsUrl : String){
        val directions = MainFragmentDirections.actionMainFragmentToDetailsFragment()
        directions.personUrl = personDetailsUrl
        binding.root.findNavController().navigate(directions)
    }

    private fun configuraRecyclerView() {
        binding.rvPeople.adapter = PeopleAdapter(
            mutableListOf()) { person : String  -> navigateToDetails(person)}
        with(binding.rvPeople) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}
