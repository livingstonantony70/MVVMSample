package com.nkcfoodsapps.mvvmsample.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nkcfoodsapps.mvvmsample.dummyapi.DummyUserApiImpl
import com.nkcfoodsapps.mvvmsample.dummyapi.UserAPI
import com.nkcfoodsapps.mvvmsample.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
object DIModule {


    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())

    }

//    @Named("name1")
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): UserAPI {
        return retrofit
            .build()
            .create(UserAPI::class.java)
    }
//   @Named("name2")
  /*  @Provides
    fun userAPI(): UserAPI {
        return DummyUserApiImpl()
    }
*/
}