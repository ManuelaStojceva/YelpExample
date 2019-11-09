package com.yelp.yelpapp.model.response

data class BusinessDetailResponse(
    val alias: String?,
    val categories: List<Category>?,
    val coordinates: Coordinates?,
    val display_phone: String?,
    val hours: List<Hour>?,
    val id: String?,
    val image_url: String?,
    val is_claimed: Boolean?,
    val is_closed: Boolean?,
    val location: Location?,
    val name: String?,
    val phone: String?,
    val photos: List<String>?,
    val price: String?,
    val rating: Double?,
    val review_count: Int?,
    val special_hours: List<SpecialHour>?,
    val transactions: List<Any>?,
    val url: String?
)

data class Hour(
    val hours_type: String?,
    val is_open_now: Boolean?,
    val `open`: List<Open>?
)

data class Open(
    val day: Int?,
    val end: String?,
    val is_overnight: Boolean?,
    val start: String?
)


data class SpecialHour(
    val date: String?,
    val end: String?,
    val is_closed: Boolean?,
    val is_overnight: Boolean?,
    val start: String?
)