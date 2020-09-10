package cn.loberty.xgallery.utils

import android.graphics.drawable.Drawable
import android.text.Layout
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
import kotlinx.android.synthetic.main.layout_page_photo_view.view.*

/**
 * Create by WangChen on 2020/9/10
 *
 */
class PagerPhotoAdapter : ListAdapter<PhotoItem,PagerPhotoViewHolder>(DiffCallBack) {

    object DiffCallBack:DiffUtil.ItemCallback<PhotoItem>(){
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
           return oldItem.photoId == newItem.photoId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.layout_page_photo_view,parent,false).apply {
            return PagerPhotoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        holder.itemView.shimmerLayout1.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        Glide.with(holder.itemView)
            .load(getItem(position).fullUrl)
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
                    holder.itemView.shimmerLayout1?.stopShimmerAnimation()
                }
            }
        })
            .into(holder.itemView.imageView2)
    }
}

class PagerPhotoViewHolder(item: View): RecyclerView.ViewHolder(item)