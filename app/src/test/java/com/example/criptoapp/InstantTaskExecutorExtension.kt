package com.example.criptoapp

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantTaskExecutorExtension : BeforeAllCallback, AfterAllCallback {

  override fun beforeAll(context: ExtensionContext?) {
    Dispatchers.setMain(Dispatchers.Unconfined)
    ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
      override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
      }

      override fun postToMainThread(runnable: Runnable) {
        runnable.run()
      }

      override fun isMainThread(): Boolean {
        return true
      }
    })
  }

  override fun afterAll(context: ExtensionContext?) {
    Dispatchers.resetMain()
    ArchTaskExecutor.getInstance().setDelegate(null)
  }
}
