package com.aiinterviewtrainer.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
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
public final class SessionRepository_Factory implements Factory<SessionRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public SessionRepository_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public SessionRepository get() {
    return newInstance(firestoreProvider.get());
  }

  public static SessionRepository_Factory create(
      javax.inject.Provider<FirebaseFirestore> firestoreProvider) {
    return new SessionRepository_Factory(Providers.asDaggerProvider(firestoreProvider));
  }

  public static SessionRepository_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new SessionRepository_Factory(firestoreProvider);
  }

  public static SessionRepository newInstance(FirebaseFirestore firestore) {
    return new SessionRepository(firestore);
  }
}
