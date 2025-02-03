package com.ddd.attendance.core.domain.di

import com.ddd.attendance.core.domain.repository.QrRepository
import com.ddd.attendance.core.domain.usecase.QrDecodeUseCase
import com.ddd.attendance.core.domain.usecase.QrEncodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    @Singleton
    fun provideQrEncodeUseCase(
        repository: QrRepository
    ): QrEncodeUseCase = QrEncodeUseCase(repository)

    @Provides
    @Singleton
    fun provideQrDecodeUseCase(
        repository: QrRepository
    ): QrDecodeUseCase = QrDecodeUseCase(repository)
}