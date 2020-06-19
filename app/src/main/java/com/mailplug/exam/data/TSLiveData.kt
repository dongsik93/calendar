package com.mailplug.exam.data

import androidx.lifecycle.MutableLiveData


class TSLiveData<T> : MutableLiveData<T> {
    constructor() {}
    constructor(value: T) {
        setValue(value)
    }
}