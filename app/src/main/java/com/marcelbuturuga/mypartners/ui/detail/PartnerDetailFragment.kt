package com.marcelbuturuga.mypartners.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marcelbuturuga.mypartners.data.model.Partner
import com.marcelbuturuga.mypartners.databinding.FragmentPartnerDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnerDetailFragment : Fragment() {

    private var _binding: FragmentPartnerDetailBinding? = null
    private val binding get() = _binding!!

    private var partner: Partner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartnerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val partner = arguments?.getParcelable("partner", Partner::class.java)

        partner?.let {
            binding.name.text = it.name
            binding.description.text = it.description
            Glide.with(this).load(it.image_url).into(binding.image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}