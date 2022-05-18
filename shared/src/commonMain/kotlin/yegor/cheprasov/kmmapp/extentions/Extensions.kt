package yegor.cheprasov.kmmapp.android

import android.net.Uri

fun String.getUrlParam(key: String): String? {
    val uri: Uri = Uri.parse(this)
    return uri.getQueryParameter(key)
}