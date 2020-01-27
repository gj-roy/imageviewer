package com.github.iielse.imageviewer.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.iielse.imageviewer.R
import com.github.iielse.imageviewer.adapter.ItemType
import com.github.iielse.imageviewer.core.Components.requireImageLoader
import com.github.iielse.imageviewer.core.Components.requireVHCustomizer
import com.github.iielse.imageviewer.core.Photo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_imageviewer_subsampling.*

class SubsamplingViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    init {
        requireVHCustomizer().initialize(ItemType.SUBSAMPLING, this)
    }

    fun bind(item: Photo) {
        subsamplingView.setTag(R.id.viewer_adapter_item_key, item.id())
        requireImageLoader().load(subsamplingView, item)
        requireVHCustomizer().bind(ItemType.SUBSAMPLING, item, this)
    }
}


