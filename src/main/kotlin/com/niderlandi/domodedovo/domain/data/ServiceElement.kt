package com.niderlandi.domodedovo.domain.data

import com.niderlandi.domodedovo.domain.data.enums.ContentCategory
import com.niderlandi.domodedovo.domain.data.enums.ElementType

data class ServiceElement(
    val id: Long,
    val elementType: ElementType,
    val valueDefinition: String = "",
    val value: String = "",
    val editable: Boolean = true,
    val contentCategory: ContentCategory?,
    val contentKey: String? = null,
    val contentValue: String = "",
    val mandatory: Boolean = true,
    val errorText: String = ""
)