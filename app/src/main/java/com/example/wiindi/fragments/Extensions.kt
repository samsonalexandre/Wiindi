package com.example.wiindi.fragments

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

//Dies ist eine Datei mit zwei Funktionen, die von Fragmenten verwendet werden, um zu überprüfen, ob bestimmte Berechtigungen gewährt wurden.
fun Fragment.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        activity as AppCompatActivity, permission) == PackageManager.PERMISSION_GRANTED
}