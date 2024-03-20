package com.bitebalance.data.db_repository

import android.content.Context
import com.bitebalance.domain.repository.StringRepository

class StringRepositoryImpl(
    private val context: Context,
) : StringRepository {

    override fun getStr(id: Int): String {
        return context.getString(id)
    }

    override fun getStr(id: Int, vararg formatArgs: String): String {
        return context.getString(id, formatArgs)
    }
}