package yegor.cheprasov.kmmapp.extentions

import io.ktor.http.*

fun String.getUrlParam(key: String): String? {
    val uru = Url(this).parameters
    return uru[key]
}