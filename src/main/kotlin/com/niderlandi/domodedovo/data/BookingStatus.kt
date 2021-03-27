package com.niderlandi.domodedovo.data

enum class BookingStatus(val key: Int){
    UNDEFINED(100),
    NON_RESERVED(101),
    RESERVED(102),
    WIZARD_STEPS_COM_PLETED(200),
    REGISTERED(999),
    CANCELED(899)
}