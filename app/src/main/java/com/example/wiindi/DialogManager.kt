package com.example.wiindi

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

//Eine Hilfsklasse, die Dialoge für die Anwendung erstellt.
object DialogManager {

    //Erstelle einen Dialog, um die Standortdienste zu aktivieren.
    fun locationSettingsDialog(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Standort aktivieren?")
        dialog.setMessage("Deaktiviertes Standort. Möchten Sie den Standort aktivieren?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {_,_ ->
            listener.onClick(null)
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Stornieren") {_,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    //Erstelle einen Dialog, um nach einer Stadt per Name zu suchen.
    fun searchByNameDialog(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        val edName = EditText(context)
        builder.setView(edName)
        val dialog = builder.create()
        dialog.setTitle("Stadtname:")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {_,_ ->
            listener.onClick(edName.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Stornieren") {_,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }
    interface Listener {
        fun onClick(name: String?)
    }
}