package uy.klutter.core.parsing

import org.junit.Test
import kotlin.test.assertEquals

fun get_customer_by_userName_data() {

}
class TestCamelCaseParsing {
    @Test fun testCommonCamelCaseSplitCases() {
        assertEquals(listOf("this", "is", "a", "test", "of", "splitting"), "thisIsATestOfSplitting".splitOnCamelCase())
        assertEquals(listOf("and", "what", "about", "this"), "AndWhatAboutThis".splitOnCamelCase())
        assertEquals(listOf("a", "uri", "is", "present"), "aURIIsPresent".splitOnCamelCase())
        assertEquals(listOf("something", "by", "something"), "SomethingBySomething".splitOnCamelCase())
        assertEquals(listOf("something20", "by", "something30"), "something20BySomething30".splitOnCamelCase())
        assertEquals(listOf("20this", "and", "that"), "20thisAndThat".splitOnCamelCase())
        assertEquals(listOf("20", "this", "and", "that"), "20ThisAndThat".splitOnCamelCase())
        assertEquals(listOf("what", "about", "underscores"), "What_about_underscores".splitOnCamelCase())
        assertEquals(listOf("what", "about", "under", "scores"), "_What_about_underScores".splitOnCamelCase())
        assertEquals(listOf("what", "about", "under", "scores"), "What_about_underScores_".splitOnCamelCase())
        assertEquals(listOf("what", "about", "underscores"), "What_about_underscores__".splitOnCamelCase())
        assertEquals(listOf("what", "a", "bo", "ut", "underscores"), "What___aBoUt________underscores".splitOnCamelCase())
        assertEquals(listOf("what", "under", "scores"), "what_underScores".splitOnCamelCase())
        assertEquals(listOf("20", "this", "and", "that", "and", "what"), "20_ThisAndThat_and_What".splitOnCamelCase())
        assertEquals(listOf("20", "this", "and", "that", "what"), "20_______thisAndThat__What".splitOnCamelCase())
        assertEquals(listOf("this", "and", "that", "what", "did", "you", "do"), "_thisAndThat_whatDid_YOU_do".splitOnCamelCase())
    }
}