package com.aiinterviewtrainer.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class FileStorageManager_Factory implements Factory<FileStorageManager> {
  private final Provider<Context> contextProvider;

  public FileStorageManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FileStorageManager get() {
    return newInstance(contextProvider.get());
  }

  public static FileStorageManager_Factory create(javax.inject.Provider<Context> contextProvider) {
    return new FileStorageManager_Factory(Providers.asDaggerProvider(contextProvider));
  }

  public static FileStorageManager_Factory create(Provider<Context> contextProvider) {
    return new FileStorageManager_Factory(contextProvider);
  }

  public static FileStorageManager newInstance(Context context) {
    return new FileStorageManager(context);
  }
}
