package com.conena.nanokt

import org.junit.Assert.*
import org.junit.Test

class EnumExtensionsKtTest {

    private enum class TestClass {
        Enum1
    }

    @Test
    fun `enumValueOfOrNull - Enum exists`() {
        assertEquals(TestClass.Enum1, enumValueOfOrNull<TestClass>("Enum1"))
    }

    @Test
    fun `enumValueOfOrNull - Enum does not exist`() {
        assertNull(enumValueOfOrNull<TestClass>("Enum2"))
    }

}