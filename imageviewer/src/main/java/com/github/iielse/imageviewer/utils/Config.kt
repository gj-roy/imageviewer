package com.github.iielse.imageviewer.utils

import android.graphics.Color
import androidx.viewpager2.widget.ViewPager2
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

object Config {
    var DEBUG: Boolean = true
    var OFFSCREEN_PAGE_LIMIT: Int = 1
    var VIEWER_ORIENTATION: Int = ViewPager2.ORIENTATION_HORIZONTAL
    var VIEWER_BACKGROUND_COLOR: Int = Color.BLACK
    var DURATION_TRANSITION: Long = 400L
    var DURATION_BG: Long = 300L
    var SUBSAMPLING_SCALE_TYPE = SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE
    var SWIPE_DISMISS: Boolean = true
    var DISMISS_FRACTION: Float = 0.15f
}