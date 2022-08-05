package com.github.iielse.imageviewer.demo.business

import android.view.View
import android.widget.ImageView
import com.github.iielse.imageviewer.ImageViewerBuilder
import com.github.iielse.imageviewer.core.SimpleDataProvider
import com.github.iielse.imageviewer.core.Transformer
import com.github.iielse.imageviewer.demo.R
import com.github.iielse.imageviewer.demo.core.viewer.SimpleImageLoader
import com.github.iielse.imageviewer.demo.data.Service

// 自定义Transition startView 尺寸/位置/加载模式
object CustomTransitionHelper {
    fun show(view: View) {
        val dataList = Service.houMen.toMutableList()
        val clickedData = dataList[dataList.size - 1 - (System.currentTimeMillis() % 10).toInt()]
        val builder = ImageViewerBuilder(
                context = view.context,
                dataProvider = SimpleDataProvider(clickedData, dataList),
                imageLoader = SimpleImageLoader(),
                transformer = object : Transformer {
                    override fun getView(key: Long): ImageView {
                        return fakeStartView(view)
                    }
                }
        )
        builder.show()
    }

    // 提供原图尺寸/位置/加载模式
    private fun fakeStartView(view: View): ImageView {
        val customWidth = view.width
        val customHeight = view.height
        val customLocation = IntArray(2).also { view.getLocationOnScreen(it) }
        val customScaleType = ImageView.ScaleType.CENTER_CROP
        return ImageView(view.context).apply {
            left = 0
            right = customWidth
            top = 0
            bottom = customHeight
            scaleType = customScaleType
            setTag(R.id.viewer_start_view_location_0, customLocation[0])
            setTag(R.id.viewer_start_view_location_1, customLocation[1])
        }
    }
}