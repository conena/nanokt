package com.conena.nanokt

import org.junit.Assert.*

import org.junit.Test

class IterableExtensionsKtTest {

    private val testList = listOf(
        "String1",
        "String2",
        "String3",
        "String4",
        "String1",
        "String2",
        "String3",
        "String4"
    )

    @Test
    fun indexOfOrNull() {
        assertEquals(1, testList.indexOfOrNull(testList[1]))
        assertEquals(null, testList.indexOfOrNull("String0"))
    }

    @Test
    fun indexOfFirstOrNull() {
        assertEquals(3, testList.indexOfFirstOrNull { it.endsWith("4") })
        assertEquals(null, testList.indexOfFirstOrNull { it.endsWith("5") })
    }

    @Test
    fun lastIndexOfOrNull() {
        assertEquals(5, testList.lastIndexOfOrNull(testList[5]))
        assertEquals(null, testList.lastIndexOfOrNull("String0"))
    }

    @Test
    fun indexOfLastOrNull() {
        assertEquals(7, testList.indexOfLastOrNull { it.endsWith("4") })
        assertEquals(null, testList.indexOfLastOrNull { it.endsWith("5") })
    }

}