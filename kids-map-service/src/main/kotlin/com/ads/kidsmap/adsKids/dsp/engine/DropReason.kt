package com.ads.kidsmap.adsKids.dsp.engine

enum class DropReason {
    NON_VALID_REQUEST,
    NO_CANDIDATE, // lack of candidate from ranker
    AUTO_BID_FAILED, // failed to get bid price
    AUCTION_DROPPED,
    UNSPECIFIED,
}
