package com.example.affirmations.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.ListFragment
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * Adapter for the [RecyclerView] in [ListFragment]. Displays [Affirmation] data object.
 */
class ItemAdapter(
    private val context: ListFragment,
    private val dataset: List<Affirmation>,
    private val onItemClickListener: ListFragment
):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

     //Provide a reference to the views for each data item
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)

        // sets OnClickListener on a view item within a recycler view
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }
}





