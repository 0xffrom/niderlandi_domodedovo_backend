package com.niderlandi.domodedovo.data

data class ServiceFormPage(
    val id : Long,
    val title: String,
    val serviceFormElements : List<ServiceFormElement>,
    val wizardStep: Int,
)