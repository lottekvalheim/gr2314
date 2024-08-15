package persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Dresses;
import core.Users;
import persistence.internal.CoutureModule;
import core.Dress;
import core.User;

/**
 * A class responsible for storing and loading dress and user data in JSON
 * format.
 */
public class Storage {

    /**
     * A Dresses object representing the dress data.
     */
    private Dresses dresses = new Dresses();

    /**
     * The name of the JSON file used to store dress data.
     */
    private static final String FILE_NAME_DRESS = "DressStorage.json";

    /**
     * The name of the JSON file used to store user data.
     */
    private static final String FILE_NAME_USER = "UserStorage.json";

    /**
     * An ObjectMapper used for JSON serialization and deserialization.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new Storage instance.
     * The default file path is set to the user's home directory.
     * The ObjectMapper is configured to enable indentation in JSON output.
     */
    public Storage() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.registerModule(new CoutureModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Builds the full file path for a given filename.
     *
     * @param filename The name of the file.
     * @return The full file path.
     */
    String buildPath(final String filename) {

        return System.getProperty("user.home") + "/" + filename;
    }

    /**
     * Loads dress data from a JSON file.
     *
     * @return A Dresses object representing the loaded data.
     * @throws IOException If there is an issue reading the JSON file.
     */
    public Dresses loadDresses() {
        initializeFile(FILE_NAME_DRESS);
        Dresses dresses = new Dresses();
        try (Reader reader = new FileReader(buildPath(FILE_NAME_DRESS), StandardCharsets.UTF_8)) {
            dresses = objectMapper.readValue(reader, Dresses.class);
        } catch (IOException e) {
            System.err.println("Error reading from JSON file: " + e.getMessage());
            // e.printStackTrace(); // Uncomment if you want the stack trace to be printed
        }
        return dresses;
    }

    /**
     * Loads user data from a JSON file.
     *
     * @return A Users object representing the loaded data.
     * @throws IOException If there is an issue reading the JSON file.
     */
    public Users loadUsers() {
        System.out.println("vi er i loadUsers");
        initializeFile(FILE_NAME_USER);
        Users users = null;
        try (Reader reader = new FileReader(buildPath(FILE_NAME_USER), StandardCharsets.UTF_8)) {
            users = objectMapper.readValue(reader, Users.class);
            System.out.println(users.toString());
        } catch (IOException e) {
            System.err.println("Error reading from JSON file: " + e.getMessage());
        }
        return users;
    }


    /**
     * Writes dress data to a JSON file.
     *
     * @param dresses The Dresses object to be written.
     * @throws IOException If there is an issue writing the JSON file.
     */
    public void writeDresses(final Dresses dresses) {
        initializeFile(FILE_NAME_DRESS);
        try (Writer writer = new FileWriter(buildPath(FILE_NAME_DRESS), StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, dresses);

        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + e.getMessage());
    }
    }

    /**
     * Writes user data to a JSON file.
     *
     * @param users The Users object to be written.
     * @throws IOException If there is an issue writing the JSON file.
     */
    public void writeUsers(final Users users) {
        initializeFile(FILE_NAME_USER);
        try (Writer writer = new FileWriter(buildPath(FILE_NAME_USER), StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users);
        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + e.getMessage());
        }
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @param file
     * @return true if the file was created, false if not
     */
    public Boolean makefileUser(final File file) {

        try {
            Files.createDirectories(file.getParentFile().toPath());

            User user = new User("Lotte", "1234");
            Users users = new Users();
            users.addIndividual(user);

            Writer writer = new FileWriter(buildPath(FILE_NAME_USER), StandardCharsets.UTF_8);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users);

            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @param file
     * @return true if the file was created, false if not
     */
    public Boolean makefileDress(final File file) {

        try {
            Files.createDirectories(file.getParentFile().toPath());


            Dress dress1 = new Dress("dress1", true);
            Dress dress2 = new Dress("dress2", true);
            Dress dress3 = new Dress("dress3", true);
            Dress dress4 = new Dress("dress4", true);
            Dress dress5 = new Dress("dress5", true);
            Dress dress6 = new Dress("dress6", true);
            Dresses dresses = new Dresses();
            dresses.addDress(dress1);
            dresses.addDress(dress2);
            dresses.addDress(dress3);
            dresses.addDress(dress4);
            dresses.addDress(dress5);
            dresses.addDress(dress6);

            Writer writer = new FileWriter(buildPath(FILE_NAME_DRESS), StandardCharsets.UTF_8);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, dresses);

          return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if file exists.
     *
     * @param filename
     */
    public void initializeFile(final String filename) {
        System.out.println("vi er i initializeFile"
        );
        File file = new File(buildPath(filename));

        if (!file.exists()) {
            System.out.println("finner ikke filen");
            if (filename.equals(FILE_NAME_DRESS)) {
                makefileDress(file);
            } else {
                makefileUser(file);
            }
        }
    }

    /**
     * Creates jackson module using CoutureModule.
     * @return SimpleModule.
     */
    public static SimpleModule createJacksonModule() {
        return new CoutureModule();
    }

    /**
     * Creates object mapper.
     * @return ObjectMapper.
     */
    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper().registerModule(createJacksonModule());
    }

    /**
     * Adds a new user.
     * @param user
     */
    public void createUser(final User user) {
        System.out.println("vi er i storage!!");
        Users users = loadUsers();
        users.addIndividual(user);
        writeUsers(users);
    }

    /**
     * Changes the availability of a dress.
     * @param id
     */
    public void changeAvailability(final String id) {
        Dresses dresses = loadDresses();
        Dress dress = dresses.getIndividual(id);
        dresses.removeDress(id);
        dress.changeAvailability();
        dresses.addDress(dress);
        writeDresses(dresses);
    }

    /**
     * Gets the availability of a dress.
     * @param id
     * @return Boolean isAvailable
     */
    public Boolean getAvailability(final String id) {
        Dresses dresses = loadDresses();
        Dress dress = dresses.getIndividual(id);
        return dress.getIsAvailable();
    }

    /**
     * Gets the filename for dress.
     * @return FILE_NAME_DRESS
     */
    public static String getFileNameDress() {
        return FILE_NAME_DRESS;
    }

    /**
     * Gets the filename for user.
     * @return FILE_NAME_USER.
     */
    public static String getFileNameUser() {
        return FILE_NAME_USER;
    }
}