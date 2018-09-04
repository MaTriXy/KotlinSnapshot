package com.karumi.kotlinsnapshot.core

import org.junit.Test

class TestClassNameAsDirectoryTest {

    private val snapWithExtractorSupport = getCamera(true)
    private val snapWithoutExtractorSupport =
        getCamera(true, TestCaseExtractorNotSupported)

    private fun getCamera(
        testClassAsDirectory: Boolean,
        testCaseNameExtractor: TestCaseExtractor = TestCaseExtractor()
    ) = Camera(KotlinSerialization(), testCaseNameExtractor, testClassAsDirectory)

    @Test
    fun `should create test class name folder with the method name snap file`() {
        val json = """{"name":"gabriel","id":5}"""
        snapWithExtractorSupport.matchWithSnapshot(json)
    }

    @Test
    fun `should create test class name folder with a custom name snap file`() {
        val json = """{"name":"gabriel","id":5}"""
        snapWithExtractorSupport.matchWithSnapshot(json,
            "this file should be inside test class name folder")
    }

    @Test
    fun `should not create the test class name folder if doesn't find test class name`() {
        val json = """{"name":"gabriel","id":5}"""
        snapWithoutExtractorSupport.matchWithSnapshot(json,
            "this file should be without test class name folder")
    }

    private object TestCaseExtractorNotSupported : TestCaseExtractor() {
        override fun getTestStackElement(): StackTraceElement? = null
    }
}
