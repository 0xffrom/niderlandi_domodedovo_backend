package com.niderlandi.domodedovo.data

data class ServiceFormElement(
    val id : Long,
    val title : String,
    val subtitle : String?,
    val serviceElements : List<ServiceElement>,
    val footerText: String?
)