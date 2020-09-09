package cn.loberty.xgallery.utils

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.loberty.xgallery.R
import cn.loberty.xgallery.model.PhotoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.gallery_item.view.*

/**
 * Create by WangChen on 2020/9/8
 *
 */
class GalleryAdapter:ListAdapter<PhotoItem,MyViewHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_item,parent,false))
        holder.itemView.setOnClickListener{}
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.shimmerLayout.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        Glide.with(holder.itemView)
            .load(getItem(position).previewUrl)
            .placeholder(R.drawable.ic_placeholder_photo_green)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false.also {
                        holder.itemView.shimmerLayout?.stopShimmerAnimation()
                    }
                }
            })
            .into(holder.itemView.imageView)
    }

    object DIFF:DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)