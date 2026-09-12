package com.aiinterviewtrainer.ui.progress;

import com.aiinterviewtrainer.data.repository.AuthRepository;
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
public final class ProgressViewModel_Factory implements Factory<ProgressViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SessionRepository> sessionRepositoryProvider;

  public ProgressViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
  }

  @Override
  public ProgressViewModel get() {
    return newInstance(authRepositoryProvider.get(), sessionRepositoryProvider.get());
  }

  public static ProgressViewModel_Factory create(
      javax.inject.Provider<AuthRepository> authRepositoryProvider,
      javax.inject.Provider<SessionRepository> sessionRepositoryProvider) {
    return new ProgressViewModel_Factory(Providers.asDaggerProvider(authRepositoryProvider), Providers.asDaggerProvider(sessionRepositoryProvider));
  }

  public static ProgressViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider) {
    return new ProgressViewModel_Factory(authRepositoryProvider, sessionRepositoryProvider);
  }

  public static ProgressViewModel newInstance(AuthRepository authRepository,
      SessionRepository sessionRepository) {
    return new ProgressViewModel(authRepository, sessionRepository);
  }
}
