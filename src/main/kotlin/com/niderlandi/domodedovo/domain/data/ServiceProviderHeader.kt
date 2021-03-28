package com.niderlandi.domodedovo.domain.data

data class ServiceProviderHeader(
    val serviceID : String,
    val description : String? = null,
    var bookingId : Long,
    val extBookingId : Long? = null,
    var bookingStatus : Int? = null,
    val currentWizardStep : Int,
    val prefLanguage : String? = null,
    val paymentsStatus : Int? = null
)