package com.nwq.code.liferecord.ui.anchor

import androidx.lifecycle.LiveData
import com.nwq.code.liferecord.data_base.bean.AnchorPoint
import com.nwq.code.liferecord.data_base.bean.ContentType

/**
create by: 86136
create time: 2020/10/30 14:31
Function description:
 */

class AnchorLiveData(private val anchorPoint: AnchorPoint = AnchorPoint()) :
    LiveData<AnchorPoint>() {

    fun setContent(content: String) {
        anchorPoint.comment = content
        postValue(anchorPoint)
    }

    fun setContentType(contentType: ContentType) {
        anchorPoint.contentType = contentType
        postValue(anchorPoint)
    }

    fun hasInputContent(): Boolean {
        return !anchorPoint.comment.isNullOrEmpty()
    }


}