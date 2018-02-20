package com.norrey.puzzle.io.serialization;

import com.norrey.puzzle.util.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @param <T>
 * @since Feb 19, 2018, 10:17:35 AM
 */
public abstract class ObjectSerializationHandler<T> {

    public void writeObject(final Path path, final T obj) throws IOException {
        requireNonNull(path, "The path must not be null");
        requireNonNull(obj, "The object must not be null");
        if (null != path.getParent()) {
            createDirectoryIfNotExists(path.getParent());
        }
        createFileIfNotExists(path);

        try (final FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
             final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(obj);
        }
    }

    public Optional<T> readObject(final Path filepath) throws IOException, ClassNotFoundException {
        requireNonNull(filepath, "The filename must not be null");

        if (!Files.exists(filepath)) {
            return Optional.empty();
        }

        try (final FileInputStream fileInputStream = new FileInputStream(filepath.toFile());
             final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            @SuppressWarnings("unchecked")
            T t = (T) objectInputStream.readObject();
            return Optional.of(t);
        }
    }

    public Optional<T> readObject(final String filePath) throws IOException, ClassNotFoundException {
        final ClassLoader classLoader = getClass().getClassLoader();

        try (final InputStream stream = classLoader.getResourceAsStream(filePath);
             final ObjectInputStream objectInputStream = new ObjectInputStream(stream);) {
            @SuppressWarnings("unchecked")
            T t = (T) objectInputStream.readObject();
            return Optional.of(t);
        }
    }

    private void createFileIfNotExists(final Path path) {
        requireNonNull(path, "The path must not be null");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Fatal::Could not create file: " + path.toString()), e);
            }
        }

    }

    private void createDirectoryIfNotExists(final Path path) {
        requireNonNull(path, "The path must not be null");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Fatal::Could not create directory: " + path.toString()), e);
            }
        }

    }

    public boolean deleteIfExists(final Path path) {
        requireNonNull(path, "The path cannot be null");
        try {
            return Files.deleteIfExists(path);
        } catch (final IOException ex) {
            throw new RuntimeException("Could not complete deletion", ex);
        }
    }

}
