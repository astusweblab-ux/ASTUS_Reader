package com.astus.reader.feature_reader;

import androidx.lifecycle.SavedStateHandle;
import com.astus.reader.core.datastore.SettingsDataStore;
import com.astus.reader.core.tts.TtsController;
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
public final class ReaderViewModel_Factory implements Factory<ReaderViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<BookRepository> repositoryProvider;

  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  private final Provider<TtsController> ttsControllerProvider;

  public ReaderViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<BookRepository> repositoryProvider,
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<TtsController> ttsControllerProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.settingsDataStoreProvider = settingsDataStoreProvider;
    this.ttsControllerProvider = ttsControllerProvider;
  }

  @Override
  public ReaderViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), settingsDataStoreProvider.get(), ttsControllerProvider.get());
  }

  public static ReaderViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<BookRepository> repositoryProvider,
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<TtsController> ttsControllerProvider) {
    return new ReaderViewModel_Factory(savedStateHandleProvider, repositoryProvider, settingsDataStoreProvider, ttsControllerProvider);
  }

  public static ReaderViewModel newInstance(SavedStateHandle savedStateHandle,
      BookRepository repository, SettingsDataStore settingsDataStore, TtsController ttsController) {
    return new ReaderViewModel(savedStateHandle, repository, settingsDataStore, ttsController);
  }
}
