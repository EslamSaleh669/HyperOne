package com.HyperOne.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent

import android.net.ConnectivityManager

import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.HyperOne.R


fun Context.makeToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.launchLoadingDialog(): Dialog {
    return Dialog(this, R.style.FullScreenDialog).apply {
        setContentView(R.layout.dialog_loading)
        show()
    }
}

fun Context.launchActivity(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
}

@SuppressLint("MissingPermission")
fun Context.checkInternetConnectivity(): Boolean {
    val manager: ConnectivityManager? =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return manager?.activeNetworkInfo?.isConnected ?: false
}




//fun Context.getArrayAdapter(items:ArrayList<Any>) todo


/*
*
*  */