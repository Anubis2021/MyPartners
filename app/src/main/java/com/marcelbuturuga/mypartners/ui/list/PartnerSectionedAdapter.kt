package com.marcelbuturuga.mypartners.ui.list

import android.view.LayoutInflater
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelbuturuga.mypartners.data.model.Partner
import com.marcelbuturuga.mypartners.databinding.ItemPartnerBinding
import com.marcelbuturuga.mypartners.databinding.ItemSectionHeaderBinding

class PartnerSectionedAdapter(
    private val onClick: (Partner) -> Unit,
    private val onLongClickListener: (Partner) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ListItem>()

    fun submitData(grouped: Map<Int, List<Partner>>) {
        items.clear()
        grouped.entries.sortedByDescending { it.key }.forEach { (rating, partners) ->
            items.add(ListItem.Header(rating))
            items.addAll(partners.map { ListItem.Item(it) })
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ListItem.Header -> VIEW_TYPE_HEADER
        is ListItem.Item -> VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                ItemSectionHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ItemViewHolder(
                ItemPartnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ListItem.Header -> (holder as HeaderViewHolder).bind(item.rating)
            is ListItem.Item -> (holder as ItemViewHolder).bind(item.partner)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class HeaderViewHolder(private val binding: ItemSectionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rating: Int) {
            binding.headerTitle.text = "Rating $rating"
        }
    }

    inner class ItemViewHolder(private val binding: ItemPartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partner: Partner) {
            binding.name.text = partner.name
            binding.description.text = partner.description
            binding.root.setOnClickListener {
                onClick(partner)
            }
            binding.partnerCard.setOnLongClickListener {
               onLongClickListener(partner)
                true
            }
        }


    }

    sealed class ListItem {
        data class Header(val rating: Int) : ListItem()
        data class Item(val partner: Partner) : ListItem()
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }


}