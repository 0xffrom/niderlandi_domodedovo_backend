package com.niderlandi.domodedovo.domain.data

data class ServiceFormElement(
    val id: Long,
    val title: String,
    val subtitle: String = "",
    val serviceElements: List<ServiceElement> = arrayListOf(),
    val footerText: String? = ""
)