package com.bitebalance.unit.junit4

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite
import java.util.*

@RunWith(Suite::class)
@Suite.SuiteClasses(
    BlockJUnit4ClassRunnerUnitTest::class,
    ParameterizedUnitTest::class,
)
class SuiteUnitTest() {}