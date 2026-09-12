package com.aiinterviewtrainer.ui.session;

import androidx.lifecycle.SavedStateHandle;
import com.aiinterviewtrainer.data.repository.SessionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SessionDetailViewModel_Factory implements Factory<SessionDetailViewModel> {
  private final Provider<SessionRepository> sessionRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public SessionDetailViewModel_Factory(Provider<SessionRepository> sessionRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public SessionDetailViewModel get() {
    return newInstance(sessionRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static SessionDetailViewModel_Factory create(
      javax.inject.Provider<SessionRepository> sessionRepositoryProvider,
      javax.inject.Provider<SavedStateHandle> savedStateHandleProvider) {
    return new SessionDetailViewModel_Factory(Providers.asDaggerProvider(sessionRepositoryProvider), Providers.asDaggerProvider(savedStateHandleProvider));
  }

  public static SessionDetailViewModel_Factory create(
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new SessionDetailViewModel_Factory(sessionRepositoryProvider, savedStateHandleProvider);
  }

  public static SessionDetailViewModel newInstance(SessionRepository sessionRepository,
      SavedStateHandle savedStateHandle) {
    return new SessionDetailViewModel(sessionRepository, savedStateHandle);
  }
}
