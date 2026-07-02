package com.ads.kidsmap.adsKids.common.client

interface GrpcClient<T, R> {
    suspend fun call(request: T): R
}
