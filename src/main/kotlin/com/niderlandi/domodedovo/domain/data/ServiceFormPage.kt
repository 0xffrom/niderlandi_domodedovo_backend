package com.niderlandi.domodedovo.domain.data

import java.io.Serializable

data class ServiceFormPage(
    val id : Long,
    val title: String,
    val serviceFormElements : List<ServiceFormElement>,
    val wizardStep: Int,
) : Serializable