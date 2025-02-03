package com.ddd.attendance.core.utils

// mapper, usecase or repository 구현체에서 사용하면 good ~

// Generic nullable 확장 함수
fun <T> T?.default(default: T?): T? {
    return this ?: default
}

fun String?.default(): String {
    return this ?: ""
}

fun Int?.default(): Int {
    return this ?: 0
}

fun Double?.default(): Double {
    return this ?: 0.0
}

fun Boolean?.default(): Boolean {
    return this ?: false
}

fun <T> List<T>?.default(): List<T> {
    return this ?: emptyList()
}

fun <K, V> Map<K, V>?.default(): Map<K, V> {
    return this ?: emptyMap()
}

fun <T> Set<T>?.default(): Set<T> {
    return this ?: emptySet()
}

// 사용자 정의 클래스 처리
inline fun <reified T> T?.default(default: () -> T): T {
    return this ?: default()
}
