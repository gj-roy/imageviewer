package com.github.iielse.imageviewer.demo.business

import androidx.fragment.app.FragmentActivity
import com.github.iielse.imageviewer.ImageViewerBuilder
import com.github.iielse.imageviewer.ImageViewerDialogFragment
import com.github.iielse.imageviewer.core.DataProvider
import com.github.iielse.imageviewer.core.Photo
import com.github.iielse.imageviewer.core.SimpleDataProvider
import com.github.iielse.imageviewer.demo.core.viewer.FullScreenImageViewerDialogFragment
import com.github.iielse.imageviewer.demo.core.viewer.SimpleImageLoader
import com.github.iielse.imageviewer.demo.core.viewer.SimpleTransformer
import com.github.iielse.imageviewer.demo.data.MyData
import com.github.iielse.imageviewer.demo.data.Service
import com.github.iielse.imageviewer.demo.utils.PAGE_SIZE
import com.github.iielse.imageviewer.widgets.video.ExoVideoView

/**
 * viewer的自定义初始化方案
 */
object ViewerHelper {
    var orientationH: Boolean = true
    var loadAllAtOnce: Boolean = false
    var fullScreen: Boolean = false
    var simplePlayVideo: Boolean = true
    var videoScaleType: Int = ExoVideoView.SCALE_TYPE_FIT_CENTER

    fun provideImageViewerBuilder(context: FragmentActivity, clickedData: MyData): ImageViewerBuilder {
        // 数据提供者 一次加载 or 分页
        fun myDataProvider(clickedData: MyData): DataProvider {
            return if (loadAllAtOnce) {
                SimpleDataProvider(clickedData, Service.houMen)
            } else {
                 object : DataProvider {
                    override fun loadInitial(): List<Photo> = listOf(clickedData)
                    override fun loadAfter(key: Long, callback: (List<Photo>) -> Unit)
                        = Service.api.asyncQueryAfter(key, PAGE_SIZE, callback)
                    override fun loadBefore(key: Long, callback: (List<Photo>) -> Unit)
                        = Service.api.asyncQueryBefore(key, PAGE_SIZE, callback)
                }
            }
        }

        // viewer 构造的基本元素
        val builder = ImageViewerBuilder(
                context = context,
                dataProvider = myDataProvider(clickedData), // 数据提供者. 和调用者业务强绑定
                imageLoader = SimpleImageLoader(),  // 自定义实现
                transformer = SimpleTransformer() // 固定写法. 实现 ViewerTransitionHelper 确定 进场退场动画
        )

        SimpleViewerCustomizer().process(context, builder) // 添加自定义业务逻辑和UI处理

        if (fullScreen) { //
            builder.setViewerFactory(object : ImageViewerDialogFragment.Factory() {
                override fun build() = FullScreenImageViewerDialogFragment()
            }) // 对弹窗增加自定义内容
        }
        return builder
    }

}

