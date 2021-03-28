package com.niderlandi.domodedovo.domain.data

data class ServiceProviderResponse(
    val serviceProviderHeader: ServiceProviderHeader? = null,
    val serviceFormPage: ServiceFormPage? = null,
    val validationError : String? = null,
    val bookingFee : BookingFee? = null,
    val termConditionalURL : String? = null
)