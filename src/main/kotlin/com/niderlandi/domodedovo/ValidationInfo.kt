package com.niderlandi.domodedovo

data class ValidationInfo(
    val validationState: ValidationState,

)

enum class ValidationState{
    ERROR,
    OK
}