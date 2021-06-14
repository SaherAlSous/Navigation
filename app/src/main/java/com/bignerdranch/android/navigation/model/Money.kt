package com.bignerdranch.android.navigation.model

import android.icu.math.BigDecimal
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Money(
    val amount : BigDecimal
) : Parcelable { }