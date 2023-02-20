package ru.viatsuk.sockswarehouse.services;

import java.io.File;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    boolean saveToOperationFile(String json);

    String readFromOperationFile();

    boolean cleanOperationFile();

    File getDataFile();

    File getOperationFile();
}
