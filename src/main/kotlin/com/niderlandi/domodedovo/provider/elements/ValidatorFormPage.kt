package com.niderlandi.domodedovo.provider.elements

import com.niderlandi.domodedovo.domain.data.ServiceFormPage

class ValidatorFormPage : (ServiceFormPage) -> Boolean {
    override fun invoke(serviceFormPage: ServiceFormPage): Boolean {
        return true
    }
}