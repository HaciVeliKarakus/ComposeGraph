package com.hvk.composegraph

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform