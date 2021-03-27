package com.niderlandi.domodedovo.data

data class ServiceElement(
    val id : Long,
    val elementType : ElementType,
    val valueDefinition : String,
    val value : String,
    val editable : Boolean,
    val contentCategory : ContentCategory,
    val contentKey: String,
    val contentValue : String,
    val mandatory : Boolean,
    val errorText : String
)