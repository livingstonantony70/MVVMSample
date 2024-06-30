package com.nkcfoodsapps.mvvmsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkcfoodsapps.mvvmsample.dummyapi.DummyUserApiImpl
import com.nkcfoodsapps.mvvmsample.dummyapi.UserAPI
import com.nkcfoodsapps.mvvmsample.model.data.Employees
import com.nkcfoodsapps.mvvmsample.repository.UserRepository
import com.nkcfoodsapps.mvvmsample.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val repository: UserRepository) : ViewModel() {

    private val _employees: MutableLiveData<Resource<Employees>> = MutableLiveData()

    val employees: LiveData<Resource<Employees>>
        get() = _employees


    fun getEmployees() {
        viewModelScope.launch {
            repository.getEmployees().onEach { employees ->
                _employees.value = employees
            }.launchIn(viewModelScope)
        }
    }

    fun getName() = repository.getName()
}