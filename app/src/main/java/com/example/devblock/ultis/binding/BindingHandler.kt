package com.example.devblock.ultis.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.diskCacheStrategyOf


/**
 * Created by Viktor on 02,June,2020
 */
object BindingHandler {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl"], requireAll = false)
    fun imageUrl(
        view: ImageView, url: String?
    ) {
        Glide.with(view)
            .load(url)
            .apply(diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(view)
    }
}