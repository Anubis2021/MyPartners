package com.marcelbuturuga.mypartners.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.marcelbuturuga.mypartners.R
import com.marcelbuturuga.mypartners.databinding.FragmentPartnerListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PartnerListFragment : Fragment() {

    private var _binding: FragmentPartnerListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PartnerListViewModel by viewModels()

    private lateinit var adapter: PartnerSectionedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartnerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PartnerSectionedAdapter { partner ->
            val bundle = Bundle().apply {
                putParcelable("partner", partner)
            }
            findNavController().navigate(
                R.id.partnerDetailFragment,
                bundle
            )
        }

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.groupedPartners.collect { grouped ->
                adapter.submitData(grouped)
            }
        }

        viewModel.loadPartners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}