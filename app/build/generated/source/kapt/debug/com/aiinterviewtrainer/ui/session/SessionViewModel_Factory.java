package com.aiinterviewtrainer.ui.session;

import com.aiinterviewtrainer.data.repository.AuthRepository;
import com.aiinterviewtrainer.data.repository.GeminiRepository;
import com.aiinterviewtrainer.data.repository.SessionRepository;
import com.aiinterviewtrainer.data.repository.UserRepository;
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
public final class SessionViewModel_Factory implements Factory<SessionViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<SessionRepository> sessionRepositoryProvider;

  private final Provider<GeminiRepository> geminiRepositoryProvider;

  public SessionViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<GeminiRepository> geminiRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.geminiRepositoryProvider = geminiRepositoryProvider;
  }

  @Override
  public SessionViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get(), sessionRepositoryProvider.get(), geminiRepositoryProvider.get());
  }

  public static SessionViewModel_Factory create(
      javax.inject.Provider<AuthRepository> authRepositoryProvider,
      javax.inject.Provider<UserRepository> userRepositoryProvider,
      javax.inject.Provider<SessionRepository> sessionRepositoryProvider,
      javax.inject.Provider<GeminiRepository> geminiRepositoryProvider) {
    return new SessionViewModel_Factory(Providers.asDaggerProvider(authRepositoryProvider), Providers.asDaggerProvider(userRepositoryProvider), Providers.asDaggerProvider(sessionRepositoryProvider), Providers.asDaggerProvider(geminiRepositoryProvider));
  }

  public static SessionViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<GeminiRepository> geminiRepositoryProvider) {
    return new SessionViewModel_Factory(authRepositoryProvider, userRepositoryProvider, sessionRepositoryProvider, geminiRepositoryProvider);
  }

  public static SessionViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository, SessionRepository sessionRepository,
      GeminiRepository geminiRepository) {
    return new SessionViewModel(authRepository, userRepository, sessionRepository, geminiRepository);
  }
}
