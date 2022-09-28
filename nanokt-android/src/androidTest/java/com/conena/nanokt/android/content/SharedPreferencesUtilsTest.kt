package com.conena.nanokt.android.content

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class SharedPreferencesUtilsTest {

    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp() {
        sharedPreferences = InstrumentationRegistry
            .getInstrumentation()
            .context
            .getSharedPreferences("Test", Context.MODE_PRIVATE)
    }

    @Test
    fun put_boolean() {
        sharedPreferences.put("put_boolean", true)
        assertTrue(sharedPreferences.getBoolean("put_boolean", false))
    }

    @Test
    fun put_float() {
        val value = 15.22f
        sharedPreferences.put("put_float", value)
        assertEquals(value, sharedPreferences.getFloat("put_float", 0.1f))
    }

    @Test
    fun put_int() {
        val value = 15
        sharedPreferences.put("put_int", value)
        assertEquals(value, sharedPreferences.getInt("put_int", 1))
    }

    @Test
    fun put_long() {
        val value = 15L
        sharedPreferences.put("put_long", value)
        assertEquals(value, sharedPreferences.getLong("put_long", 1L))
    }

    @Test
    fun put_string() {
        val value = "TestString"
        sharedPreferences.put("put_string", value)
        assertEquals(value, sharedPreferences.getString("put_string", "No"))
    }

    @Test
    fun put_string_set() {
        val value = setOf("Test1", "Test2", "Test3")
        sharedPreferences.put("put_string_set", value)
        assertEquals(value, sharedPreferences.getStringSet("put_string_set", setOf("Test1", "Test2")))
    }

    @Test
    fun remove() {
        val value = "TestRemove"
        sharedPreferences.put("remove", value)
        sharedPreferences.put("do_not_remove", value)
        assertEquals(value, sharedPreferences.getString("remove", "No"))
        assertEquals(value, sharedPreferences.getString("do_not_remove", "No"))
        sharedPreferences.remove("remove")
        assertEquals("No", sharedPreferences.getString("remove", "No"))
        assertEquals(value, sharedPreferences.getString("do_not_remove", "No"))
    }

    @Test
    fun clear() {
        sharedPreferences.clear()
        sharedPreferences.put("clear1", "Value1")
        sharedPreferences.put("clear2", "Value2")
        assertEquals(2, sharedPreferences.all.size)
        sharedPreferences.clear()
        assertEquals(0, sharedPreferences.all.size)
    }

    @Test
    fun size() {
        sharedPreferences.clear()
        sharedPreferences.put("size1", "Value1")
        sharedPreferences.put("size2", "Value2")
        assertEquals(2, sharedPreferences.size)
        sharedPreferences.clear()
        assertEquals(0, sharedPreferences.size)
    }

    @Test
    fun getBooleanOrNull() {
        val correctKey = "getBooleanOrNull"
        val otherTypeKey = "getBooleanOrNull2"
        val noValueKey = "getBooleanOrNull3"
        sharedPreferences.put(correctKey, true)
        sharedPreferences.put(otherTypeKey, "Test")
        assertEquals(true, sharedPreferences.getBooleanOrNull(correctKey))
        assertNull(sharedPreferences.getBooleanOrNull(otherTypeKey))
        assertNull(sharedPreferences.getBooleanOrNull(noValueKey))
    }

    @Test
    fun getFloatOrNull() {
        val value = 15.2f
        val correctKey = "getFloatOrNull"
        val otherTypeKey = "getFloatOrNull2"
        val noValueKey = "getFloatOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, "Test")
        assertEquals(value, sharedPreferences.getFloatOrNull(correctKey))
        assertNull(sharedPreferences.getFloatOrNull(otherTypeKey))
        assertNull(sharedPreferences.getFloatOrNull(noValueKey))
    }

    @Test
    fun getIntOrNull() {
        val value = 15
        val correctKey = "getIntOrNull"
        val otherTypeKey = "getIntOrNull2"
        val noValueKey = "getIntOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, "Test")
        assertEquals(value, sharedPreferences.getIntOrNull(correctKey))
        assertNull(sharedPreferences.getIntOrNull(otherTypeKey))
        assertNull(sharedPreferences.getIntOrNull(noValueKey))
    }

    @Test
    fun getLongOrNull() {
        val value = 15L
        val correctKey = "getLongOrNull"
        val otherTypeKey = "getLongOrNull2"
        val noValueKey = "getLongOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, "Test")
        assertEquals(value, sharedPreferences.getLongOrNull(correctKey))
        assertNull(sharedPreferences.getLongOrNull(otherTypeKey))
        assertNull(sharedPreferences.getLongOrNull(noValueKey))
    }

    @Test
    fun getStringOrNull() {
        val value = "TestValue"
        val correctKey = "getStringOrNull"
        val otherTypeKey = "getStringOrNull2"
        val noValueKey = "getStringOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, true)
        assertEquals(value, sharedPreferences.getStringOrNull(correctKey))
        assertNull(sharedPreferences.getStringOrNull(otherTypeKey))
        assertNull(sharedPreferences.getStringOrNull(noValueKey))
    }

    @Test
    fun getImmutableStringSetOrNull() {
        val value = setOf("AB", "CD", "EF")
        val correctKey = "getImmutableStringSetOrNull"
        val otherTypeKey = "getImmutableStringSetOrNull2"
        val noValueKey = "getImmutableStringSetOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, "Test")
        assertEquals(value, sharedPreferences.getImmutableStringSetOrNull(correctKey))
        assertNull(sharedPreferences.getImmutableStringSetOrNull(otherTypeKey))
        assertNull(sharedPreferences.getImmutableStringSetOrNull(noValueKey))
    }

    @Test
    fun getMutableStringSetOrNull() {
        val value = setOf("AB", "CD", "EF")
        val correctKey = "getMutableStringSetOrNull"
        val otherTypeKey = "getMutableStringSetOrNull2"
        val noValueKey = "getMutableStringSetOrNull3"
        sharedPreferences.put(correctKey, value)
        sharedPreferences.put(otherTypeKey, "Test")
        val retrievedValue = sharedPreferences.getMutableStringSetOrNull(correctKey)
        assertEquals(value, retrievedValue)
        retrievedValue!!.clear()
        assertNotEquals(value, retrievedValue)
        assertEquals(value, sharedPreferences.getMutableStringSetOrNull(correctKey))
        assertNull(sharedPreferences.getMutableStringSetOrNull(otherTypeKey))
        assertNull(sharedPreferences.getMutableStringSetOrNull(noValueKey))
    }

    @Test
    fun invertBoolean() {
        sharedPreferences.put("invertBoolean", false)
        Truth.assertThat(sharedPreferences.getBooleanOrNull("invertBoolean")).isFalse()
        Truth.assertThat(sharedPreferences.invertBoolean("invertBoolean")).isTrue()
        Truth.assertThat(sharedPreferences.invertBoolean("missingBoolean")).isNull()

        Truth.assertThat(sharedPreferences.getBooleanOrNull("invertBoolean")).isTrue()
        Truth.assertThat(sharedPreferences.getBooleanOrNull("missingBoolean")).isNull()
    }

    @Test
    fun invertBoolean_or_default() {
        sharedPreferences.put("invertBoolean_or_default", false)
        Truth.assertThat(sharedPreferences.getBooleanOrNull("invertBoolean_or_default")).isFalse()
        Truth.assertThat(sharedPreferences.invertBoolean("invertBoolean_or_default", false)).isTrue()
        Truth.assertThat(sharedPreferences.invertBoolean("missingBoolean2", true)).isTrue()

        Truth.assertThat(sharedPreferences.getBooleanOrNull("invertBoolean_or_default")).isTrue()
        Truth.assertThat(sharedPreferences.getBooleanOrNull("missingBoolean2")).isTrue()
    }

}