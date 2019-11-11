package com.yelp.yelpapp

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.yelp.yelpapp.interfaces.LocationServicesListener

fun Context.displayMessage()  {
    try {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(R.string.location_permissions_message)
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
                (this as LocationServicesListener).onLocationDenided()
            }
            .setNegativeButton(getString(R.string.dont_allow_text)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.displayExplanationMessage()  {
    try {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(getString(R.string.location_permissions_denied))
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
        alertDialog.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.displayGpsMessage()  {
    try {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(getString(R.string.location_message))
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
                (this as LocationServicesListener).onLocationServices()
            }
            .setCancelable(false)
        alertDialog.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.displayErrorMessage(message : String)  {
    try {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(message)
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
        alertDialog.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.hideKeyboard(view : View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}