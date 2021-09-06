package com.example.devblock.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.devblock.R
import com.example.devblock.data.model.ContactInfo
import com.example.devblock.databinding.ContactItemLayoutBinding

class ContactsAdapter : PagingDataAdapter<ContactInfo, ContactsAdapter.ContactViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.contact_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ContactInfo>() {
            override fun areItemsTheSame(oldItem: ContactInfo, newItem: ContactInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContactInfo, newItem: ContactInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ContactViewHolder(private val binding: ContactItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContactInfo, position: Int) {
            binding.contactInfo = item
            binding.llContact.setOnClickListener {

            }
        }
    }
}
