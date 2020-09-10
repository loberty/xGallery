package cn.loberty.xgallery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import cn.loberty.xgallery.model.PhotoItem
import cn.loberty.xgallery.utils.PagerPhotoAdapter
import kotlinx.android.synthetic.main.fragment_page.*

/**
 * A simple [Fragment] subclass.
 */
class PageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerList = arguments?.getParcelableArrayList<PhotoItem>("PHOTO_LIST")
        PagerPhotoAdapter().apply {
            viewPager2.adapter = this
            submitList(pagerList)
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indexTag.text = "${position+1}/${pagerList?.size}"
            }
        })
        viewPager2.setCurrentItem(arguments?.getInt("PHOTO_POSITION")?:0,false)
    }

}
