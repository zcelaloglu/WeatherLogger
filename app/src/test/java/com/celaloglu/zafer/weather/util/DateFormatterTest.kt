package com.celaloglu.zafer.weather.util

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DateFormatterTest {

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_should_return_formatted_date_in_string() {
        val expected = "Wed, Feb 13, 2019 05:20 PM"
        val actual = DateFormatter.format(1550067600)
        Assert.assertEquals(expected, actual)
    }
}