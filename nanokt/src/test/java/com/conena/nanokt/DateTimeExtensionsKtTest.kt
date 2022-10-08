package com.conena.nanokt


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class DateTimeExtensionsKtTest {

    @Test
    fun formatOrNull() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        assertEquals("2022-10-08", formatter.formatOrNull(Date(1665239501000L)))
        assertNull(formatter.formatOrNull(null))
    }

}