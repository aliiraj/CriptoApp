package com.example.criptoapp.application

import kotlinx.coroutines.Dispatchers


class DefaultDispatchers : CoroutineDispatchers {
  override val background = Dispatchers.IO
}
