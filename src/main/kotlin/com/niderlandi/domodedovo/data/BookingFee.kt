package com.niderlandi.domodedovo.data

data class BookingFee(
    val preTaxFee : Int,
    val taxRate : Float,
    val taxInvoiceText : List<String?>,
    val customInfoText : List<String>? = null,
    val taxId : String
)