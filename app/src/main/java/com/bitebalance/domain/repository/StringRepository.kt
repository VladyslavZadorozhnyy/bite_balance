package com.bitebalance.domain.repository

interface StringRepository {
    fun getStr(id: Int): String

    fun getStr(id: Int, vararg formatArgs: String): String
}