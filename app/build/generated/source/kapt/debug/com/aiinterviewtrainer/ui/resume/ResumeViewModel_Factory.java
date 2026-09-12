package com.aiinterviewtrainer.ui.resume;

import com.aiinterviewtrainer.data.local.FileStorageManager;
import com.aiinterviewtrainer.data.repository.AuthRepository;
import com.aiinterviewtrainer.data.repository.GeminiRepository;
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
public final class ResumeViewModel_Factory implements Factory<ResumeViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<FileStorageManager> fileStorageManagerProvider;

  private final Provider<GeminiRepository> geminiRepositoryProvider;

  public ResumeViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<FileStorageManager> fileStorageManagerProvider,
      Provider<GeminiRepository> geminiRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.fileStorageManagerProvider = fileStorageManagerProvider;
    this.geminiRepositoryProvider = geminiRepositoryProvider;
  }

  @Override
  public ResumeViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get(), fileStorageManagerProvider.get(), geminiRepositoryProvider.get());
  }

  public static ResumeViewModel_Factory create(
      javax.inject.Provider<AuthRepository> authRepositoryProvider,
      javax.inject.Provider<UserRepository> userRepositoryProvider,
      javax.inject.Provider<FileStorageManager> fileStorageManagerProvider,
      javax.inject.Provider<GeminiRepository> geminiRepositoryProvider) {
    return new ResumeViewModel_Factory(Providers.asDaggerProvider(authRepositoryProvider), Providers.asDaggerProvider(userRepositoryProvider), Providers.asDaggerProvider(fileStorageManagerProvider), Providers.asDaggerProvider(geminiRepositoryProvider));
  }

  public static ResumeViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<FileStorageManager> fileStorageManagerProvider,
      Provider<GeminiRepository> geminiRepositoryProvider) {
    return new ResumeViewModel_Factory(authRepositoryProvider, userRepositoryProvider, fileStorageManagerProvider, geminiRepositoryProvider);
  }

  public static ResumeViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository, FileStorageManager fileStorageManager,
      GeminiRepository geminiRepository) {
    return new ResumeViewModel(authRepository, userRepository, fileStorageManager, geminiRepository);
  }
}
