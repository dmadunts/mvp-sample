package com.dmadunts.samples.mvpsample.view.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dmadunts.samples.mvpsample.model.Result
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    fun <T : Any> handleResult(
        resource: Result<T>,
        isLoading: (Boolean) -> Unit,
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ) {
        val contentView = this.findViewById<View>(android.R.id.content)
        when (resource) {
            is Result.Loading -> {
                isLoading.invoke(true)
            }
            is Result.Success -> {
                isLoading.invoke(false)
                onSuccess.invoke(resource.data)
            }
            is Result.Error -> {
                isLoading.invoke(false)
                if (onError != null) {
                    onError.invoke(resource.exception)
                } else {
                    showSnack(
                        message = resource.exception.message ?: "Error",
                        contentView = contentView
                    )
                }
            }
        }
    }

    private fun showSnack(message: String?, contentView: View) =
        Snackbar.make(
            contentView,
            message ?: "Error",
            Snackbar.LENGTH_SHORT
        ).show()
}