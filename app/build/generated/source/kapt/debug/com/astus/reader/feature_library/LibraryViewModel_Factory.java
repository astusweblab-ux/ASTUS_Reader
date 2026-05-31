package com.astus.reader.feature_library;

import com.astus.reader.core.datastore.SettingsDataStore;
import com.astus.reader.domain.repository.BookRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "deprecation"
})
public final class LibraryViewModel_Factory implements Factory<LibraryViewModel> {
  private final Provider<BookRepository> repositoryProvider;

  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  public LibraryViewModel_Factory(Provider<BookRepository> repositoryProvider,
      Provider<SettingsDataStore> settingsDataStoreProvider) {
    this.repositoryProvider = repositoryProvider;
    this.settingsDataStoreProvider = settingsDataStoreProvider;
  }

  @Override
  public LibraryViewModel get() {
    return newInstance(repositoryProvider.get(), settingsDataStoreProvider.get());
  }

  public static LibraryViewModel_Factory create(Provider<BookRepository> repositoryProvider,
      Provider<SettingsDataStore> settingsDataStoreProvider) {
    return new LibraryViewModel_Factory(repositoryProvider, settingsDataStoreProvider);
  }

  public static LibraryViewModel newInstance(BookRepository repository,
      SettingsDataStore settingsDataStore) {
    return new LibraryViewModel(repository, settingsDataStore);
  }
}
