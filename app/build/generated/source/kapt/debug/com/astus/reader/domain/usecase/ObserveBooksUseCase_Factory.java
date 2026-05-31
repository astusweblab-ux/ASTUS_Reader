package com.astus.reader.domain.usecase;

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
public final class ObserveBooksUseCase_Factory implements Factory<ObserveBooksUseCase> {
  private final Provider<BookRepository> repositoryProvider;

  public ObserveBooksUseCase_Factory(Provider<BookRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveBooksUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveBooksUseCase_Factory create(Provider<BookRepository> repositoryProvider) {
    return new ObserveBooksUseCase_Factory(repositoryProvider);
  }

  public static ObserveBooksUseCase newInstance(BookRepository repository) {
    return new ObserveBooksUseCase(repository);
  }
}
