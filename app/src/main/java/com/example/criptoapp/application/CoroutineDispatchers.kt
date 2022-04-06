package com.example.criptoapp.application

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
  val background: CoroutineDispatcher
}