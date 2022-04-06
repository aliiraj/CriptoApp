package com.criptoapp.example

import com.example.criptoapp.application.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers

class TestDispatchers : CoroutineDispatchers {
  override val background = Dispatchers.Unconfined
}