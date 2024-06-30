package com.nkcfoodsapps.mvvmsample.di

import com.nkcfoodsapps.mvvmsample.dummyapi.DummyUserApiImplTest
import com.nkcfoodsapps.mvvmsample.dummyapi.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DIModule::class]
)
object DIModuleTest {

//    @Named("name1")
    @Provides
    fun userAPI(): UserAPI {
        return DummyUserApiImplTest()
    }


}
