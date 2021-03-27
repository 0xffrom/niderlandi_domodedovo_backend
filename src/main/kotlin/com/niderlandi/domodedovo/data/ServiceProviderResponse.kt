package com.niderlandi.domodedovo.data

data class ServiceProviderResponse(
    val serviceProviderHeader: ServiceProviderHeader,
    val serviceFormPage: ServiceFormPage,
    val validationError : String? = null,
    val bookingFee : BookingFee? = null,
    val termConditionalURL : String? = null
)