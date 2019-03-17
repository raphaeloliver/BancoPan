package br.com.raphaeloliveira.testebancopan.shared

import android.content.Context
import android.net.ConnectivityManager

class Utils{
    enum class Keys {
        URL_LOGO,
        URL_BOX,
        TITLE,
        CHANNEL,
        VIEWER
    }

    companion object {
        fun getBoxUrl(url: String, density: Float): String {
            val width = (152 * density).toInt().toString()
            val height = (218 * density).toInt().toString()
            return url.replace("{width}", width).replace("{height}", height)
        }

        fun getDensity(ctx: Context): Float {
            return ctx.resources.displayMetrics.density
        }

        fun isInternetAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

        fun getLogoUrl(url: String, density: Float): String {
            val width = (64 * density).toInt().toString()
            val height = (38 * density).toInt().toString()
            return url.replace("{width}", width).replace("{height}", height)
        }
    }
}