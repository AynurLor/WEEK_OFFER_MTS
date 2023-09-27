package ru.mts.tariff.services;

import java.io.IOException;
import java.util.List;

public interface Parsing<T> {

  List<String> parsingFile(T resource) throws IOException;
}
