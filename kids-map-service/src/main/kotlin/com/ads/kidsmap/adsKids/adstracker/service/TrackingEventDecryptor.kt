package com.ads.kidsmap.adsKids.adstracker.service

import org.springframework.stereotype.Component

interface TrackingEventDecryptor {
    fun decrypt(data: String): String
}

@Component
class AesTrackingEvntDecryptor : TrackingEventDecryptor {
    override fun decrypt(data: String): String {
        // TODO AesUtil.decrypt(data)
        return data
    }
}
