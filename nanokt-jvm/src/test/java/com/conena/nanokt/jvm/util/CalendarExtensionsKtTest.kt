package com.conena.nanokt.jvm.util

import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class CalendarExtensionsKtTest {

    // Sunday, December 17, 2023 5:29:20 PM GMT
    private val timeStamp = 1702834160170

    private val calendar = Calendar.getInstance().apply {
        timeInMillis = timeStamp
    }

    @Test
    fun isAM() {
        assertFalse(calendar.isAM)
        calendar.isAM = true
        assertTrue(calendar.isAM)
        assertNotEquals(timeStamp, calendar.timeInMillis)
        calendar.isAM = false
        assertFalse(calendar.isAM)
        assertEquals(timeStamp, calendar.timeInMillis)
    }

    @Test
    fun isPM() {
        assertTrue(calendar.isPM)
        calendar.isPM = false
        assertFalse(calendar.isPM)
        assertNotEquals(timeStamp, calendar.timeInMillis)
        calendar.isPM = true
        assertTrue(calendar.isPM)
        assertEquals(timeStamp, calendar.timeInMillis)
    }

    @Test
    fun year() {
        assertEquals(2023, calendar.year)
        assertEquals(2022, --calendar.year)
        assertNotEquals(timeStamp, calendar.timeInMillis)
        assertEquals(2023, ++calendar.year)
        assertEquals(timeStamp, calendar.timeInMillis)
    }

}