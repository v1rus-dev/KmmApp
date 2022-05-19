package yegor.cheprasov.kmmapp.extentions

import io.ktor.http.*

fun String.getUrlParam(key: String): String? {
    val uru = Url(this).parameters
    return uru[key]
}

fun<T> List<T>.setByWithList(value: (List<T>, T) -> Boolean): List<T> {
    val result = arrayListOf<T>()
    this.forEach {
        if (value(result, it)) {
            result.add(it)
        }
    }
    return result
}

fun<T> List<T>.containsBy(value: (T) -> Boolean): Boolean {
    var result = false
    this.forEach {
        if (!result && value(it)) {
            result = true
        }
    }
    return result
}