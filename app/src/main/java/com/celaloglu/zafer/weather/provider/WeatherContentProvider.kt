package com.celaloglu.zafer.weather.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.celaloglu.zafer.weather.database.WeatherDatabase
import com.celaloglu.zafer.weather.util.Constants

class WeatherContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.celaloglu.zafer.weather.weathercontentprovider.provider"

        private const val CODE_WEATHER_DIR = 1

        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        MATCHER.addURI(AUTHORITY, Constants.WEATHER_TABLE_NAME, CODE_WEATHER_DIR)
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val code = MATCHER.match(uri)
        if (code == CODE_WEATHER_DIR) {
            val context = context ?: return null
            val weatherDao = WeatherDatabase.getDatabase(context).weatherDao()
            val cursor = weatherDao.selectAll()
            cursor.setNotificationUri(context.contentResolver, uri)
            return cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/" + AUTHORITY + "." + Constants.WEATHER_TABLE_NAME
    }

    /**
     * Not implemented
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return Uri.EMPTY
    }

    /**
     * Not implemented
     */
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs:
    Array<String>?): Int {
        return -1
    }

    /**
     * Not implemented
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return -1
    }

}