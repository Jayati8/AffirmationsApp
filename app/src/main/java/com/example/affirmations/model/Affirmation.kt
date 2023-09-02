package com.example.affirmations.model

import android.content.DialogInterface.OnClickListener
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.view.menu.MenuView.ItemView

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int)

