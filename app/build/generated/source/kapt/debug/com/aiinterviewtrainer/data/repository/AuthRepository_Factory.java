package com.aiinterviewtrainer.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<FirebaseAuth> authProvider;

  public AuthRepository_Factory(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(authProvider.get());
  }

  public static AuthRepository_Factory create(javax.inject.Provider<FirebaseAuth> authProvider) {
    return new AuthRepository_Factory(Providers.asDaggerProvider(authProvider));
  }

  public static AuthRepository_Factory create(Provider<FirebaseAuth> authProvider) {
    return new AuthRepository_Factory(authProvider);
  }

  public static AuthRepository newInstance(FirebaseAuth auth) {
    return new AuthRepository(auth);
  }
}
