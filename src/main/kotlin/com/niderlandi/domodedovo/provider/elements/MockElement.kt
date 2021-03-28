package com.niderlandi.domodedovo.provider.elements

import com.niderlandi.domodedovo.domain.data.ServiceElement
import com.niderlandi.domodedovo.domain.data.ServiceFormElement
import com.niderlandi.domodedovo.domain.data.ServiceFormPage
import com.niderlandi.domodedovo.domain.data.enums.ContentCategory
import com.niderlandi.domodedovo.domain.data.enums.ElementType

class MockElement {
    val serviceFormPage: ServiceFormPage by lazy {
        ServiceFormPage(
            0,
            "Заказ сопровождения",
            listOf(
                getDateForm(),
                getDepartureForm(),
                getArrivalForm(),
                getUserDataForm(),
                getContactForm(),
            ),
            1
        )
    }


    private fun getDateForm() = ServiceFormElement(
        id = 0,
        title = "Направление и дата",
        serviceElements = listOf(
            ServiceElement(
                id = 0,
                elementType = ElementType.selectList,
                valueDefinition = "Прилёт;Вылет",
                value = "",
                contentValue = "Направление:",
                errorText = "Вы забыли выбрать направление",
                contentCategory = null,
                contentKey = "DIRECTION_SELECT_LIST"
            ),
            ServiceElement(
                id = 1,
                elementType = ElementType.date,
                value = "",
                contentValue = "Дата:",
                errorText = "Вы забыли выбрать дату",
                contentCategory = ContentCategory.date
            ),
        ),
    )

    private fun getDepartureForm() = ServiceFormElement(
        id = 1,
        title = "Рейс вылета",
        serviceElements = listOf(
            ServiceElement(
                id = 0,
                elementType = ElementType.selectList,
                valueDefinition = "DP186;DP240;SP432;SP450",
                contentValue = "Выберите номер рейса",
                errorText = "Вы забыли выбрать номер рейса",
                contentCategory = ContentCategory.flightNr,
                contentKey = "departure"
            ),
            ServiceElement(
                id = 1,
                elementType = ElementType.text,
                valueDefinition = "Терминал А;Терминал B;Терминал С",
                contentValue = "Выберите место встречи:",
                errorText = "Вы забыли выбрать место встречи",
                contentKey = "departure",
                contentCategory = null
            ),
            ServiceElement(
                id = 2,
                elementType = ElementType.time,
                value = "Время",
                contentValue = "Укажите время встречи:",
                errorText = "Вы забыли выбрать время встречи",
                contentKey = "departure",
                contentCategory = ContentCategory.time
            ),
        ),
    )

    private fun getArrivalForm() = ServiceFormElement(
        id = 2,
        title = "Рейс прилёта",
        serviceElements = listOf(
            ServiceElement(
                id = 0,
                elementType = ElementType.selectList,
                valueDefinition = "DP180;DP230;SP424;SP440",
                contentValue = "Выберите номер рейса",
                errorText = "Вы забыли выбрать номер рейса",
                contentCategory = ContentCategory.flightNr,
                contentKey = "arrival"
            )
        ),
    )

    private fun getUserDataForm() = ServiceFormElement(
        id = 3,
        title = "Личные данные",
        serviceElements = listOf(
            ServiceElement(
                id = 0,
                elementType = ElementType.text,
                contentValue = "Введите Ваше имя",
                errorText = "Вы забыли ввести имя",
                contentCategory = ContentCategory.firstName,
            ),
            ServiceElement(
                id = 1,
                elementType = ElementType.text,
                contentValue = "Введите Вашу фамилию",
                errorText = "Вы забыли ввести фамилию",
                contentCategory = ContentCategory.familyName,
            ),
            ServiceElement(
                id = 2,
                elementType = ElementType.selectList,
                valueDefinition = "Особенность1;Особенность2;Особенность3",
                contentValue = "Укажите особенности:",
                errorText = "Вы забыли указать Ваши особенности",
                contentCategory = null,
            ),
            ServiceElement(
                id = 3,
                elementType = ElementType.selectList,
                valueDefinition = "ТипТранспорта1;ТипТранспорта2;ТипТранспорта3",
                contentValue = "Укажите тип транспорта:",
                errorText = "Вы забыли указать Ваш тип транспорта",
                contentCategory = null,
            ),
        ),
    )

    private fun getContactForm() = ServiceFormElement(
        id = 4,
        title = "Контактные данные",
        serviceElements = listOf(
            ServiceElement(
                id = 0,
                elementType = ElementType.text,
                contentValue = "Введите Ваш телефон",
                errorText = "Вы забыли ввести телефон",
                contentCategory = ContentCategory.tel,
            ),
            ServiceElement(
                id = 0,
                elementType = ElementType.text,
                contentValue = "Введите Ваш Email",
                errorText = "Вы забыли ввести email",
                contentCategory = ContentCategory.email,
            ),
        ),
    )


}