package com.bitebalance.unit.junit4

import org.junit.experimental.categories.Categories
import org.junit.experimental.categories.Categories.IncludeCategory
import org.junit.runner.RunWith
import org.junit.runners.Suite.SuiteClasses


interface MetricTests
interface SettingsTests

@RunWith(Categories::class)
@IncludeCategory(MetricTests::class, SettingsTests::class)
@SuiteClasses(
    MetricUnitTest::class,
    SettingsUnitTest::class
)
class CategoryUnitTest