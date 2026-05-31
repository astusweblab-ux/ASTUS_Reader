package com.astus.reader.data;

import android.content.Context;
import com.astus.reader.core.database.dao.BookDao;
import com.astus.reader.core.datastore.SettingsDataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "deprecation"
})
public final class BookRepositoryImpl_Factory implements Factory<BookRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<BookDao> daoProvider;

  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  public BookRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<BookDao> daoProvider, Provider<SettingsDataStore> settingsDataStoreProvider) {
    this.contextProvider = contextProvider;
    this.daoProvider = daoProvider;
    this.settingsDataStoreProvider = settingsDataStoreProvider;
  }

  @Override
  public BookRepositoryImpl get() {
    return newInstance(contextProvider.get(), daoProvider.get(), settingsDataStoreProvider.get());
  }

  public static BookRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<BookDao> daoProvider, Provider<SettingsDataStore> settingsDataStoreProvider) {
    return new BookRepositoryImpl_Factory(contextProvider, daoProvider, settingsDataStoreProvider);
  }

  public static BookRepositoryImpl newInstance(Context context, BookDao dao,
      SettingsDataStore settingsDataStore) {
    return new BookRepositoryImpl(context, dao, settingsDataStore);
  }
}
