package com.aiinterviewtrainer.ui.profile;

import com.aiinterviewtrainer.data.local.FileStorageManager;
import com.aiinterviewtrainer.data.repository.AuthRepository;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<FileStorageManager> fileStorageManagerProvider;

  public ProfileViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<FileStorageManager> fileStorageManagerProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.fileStorageManagerProvider = fileStorageManagerProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get(), fileStorageManagerProvider.get());
  }

  public static ProfileViewModel_Factory create(
      javax.inject.Provider<AuthRepository> authRepositoryProvider,
      javax.inject.Provider<UserRepository> userRepositoryProvider,
      javax.inject.Provider<FileStorageManager> fileStorageManagerProvider) {
    return new ProfileViewModel_Factory(Providers.asDaggerProvider(authRepositoryProvider), Providers.asDaggerProvider(userRepositoryProvider), Providers.asDaggerProvider(fileStorageManagerProvider));
  }

  public static ProfileViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<FileStorageManager> fileStorageManagerProvider) {
    return new ProfileViewModel_Factory(authRepositoryProvider, userRepositoryProvider, fileStorageManagerProvider);
  }

  public static ProfileViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository, FileStorageManager fileStorageManager) {
    return new ProfileViewModel(authRepository, userRepository, fileStorageManager);
  }
}
