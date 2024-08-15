package ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Dress;
import core.Dresses;
import core.User;
import core.Users;
import persistence.Storage;

/**
 * A presentation layer REST accessor that acts as a link between the backend REST API and the frontend.
 */
public class RemoteCoutureRentalAccess {
  /**
   * The base URI of the REST API.
   */
  private String baseUri = "http://localhost:";
  /**
   * The content type of the request.
   */
  private static final String APPLICATION_JSON = "application/json";
  /**
   * The content type of the request.
   */
  private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
  /**
   * The accept header of the request.
   */
  private static final String ACCEPT_HEADER = "Accept";
  /**
   * The content type header of the request.
   */
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  /**
   * The default port.
   */
  private static final int DEFAULT_PORT = 8080;
  /**
   * The dress instance.
   */
  private Dress dressInstance;
  /**
   * The user instance.
   */
  private User userInstance;
  /**
   * The users instance.
   */
  private Users users;
  /**
   * The dresses instance.
   */
  private Dresses dresses;
  /**
   * The dress instance.
   */
  private Dress dress;
  /**
   * The object mapper.
   */
  private ObjectMapper objectMapper;
  /**
   * The id.
   */
  private String id;
  /**
   * The client.
   */
  private final HttpClient client = HttpClient.newHttpClient();

  /**
   * Initializes REST accessor on default port.
   */
  public RemoteCoutureRentalAccess() {
    this(DEFAULT_PORT);
  }

  /**
   * Initializes REST accessor on another port.
   * @param port is the localhost port to interat with.
   */
  public RemoteCoutureRentalAccess(final int port) {
    baseUri += String.valueOf(port);
    this.objectMapper = Storage.createObjectMapper();
  }

  /**
   * Method that performs a get request to retrieve a list of all the dresses.
   * @return a list of dresses.
   * @throws InterruptedException if there is no connection established.
   */
  public Dresses getDresses() {
    String endpoint = baseUri + "/dresses";

    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint))
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .GET()
        .build();
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      this.dresses = objectMapper.readValue(response.body(), Dresses.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return dresses;
  }

  /**
   * Method that performs a get request to retrieve a list of all the users.
   * @return a list of users.
   * @throws InterruptedException if there is no connection established.
   */
  public Users getUsers() {
    String endpoint = baseUri + "/users";
    users = new Users(); //la til nå
    try {
      System.out.println("jeg er i getusers i remote");
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint))
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .GET()
        .build();
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println(response.body());
      this.users = objectMapper.readValue(responseString, Users.class); //her fucker den opp
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  private String uriParam(final String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  /**
   * Method that performs a post request that adds a user.
   * @param userInstance instance of a new user.
   * @throws InterruptedException if there is no connection established.
   */
  public void addUser(final User userInstance) {
    System.out.println("jeg kom meg til access i adddress");
    Objects.requireNonNull(userInstance);
    String endpoint = baseUri + "/users/add";

    System.out.println(URI.create(endpoint));

    try {
      String json = objectMapper.writeValueAsString(userInstance);
      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(endpoint))
      .header(ACCEPT_HEADER, APPLICATION_JSON)
      .header("Content-Type", APPLICATION_JSON)
      .POST(HttpRequest.BodyPublishers.ofString(json))
      .build();
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println(responseString);
      this.userInstance = objectMapper.readValue(responseString, User.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method that performs a get request to retrieve the availability of the dress.
   * @param id
   * @return if the dress is available or not
   * @throws InterruptedException if there is no connection established.
   */
  public Boolean getAvailability(final String id) {
    String endpoint = baseUri + "/dress" + "/avaliable/" + id;

    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint))
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .GET()
        .build();
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      return objectMapper.readValue(response.body(), Boolean.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method that performs a put request to change the availability of the dress.
   * @param id
   * @throws InterruptedException if there is no connection established.
   */
  public void changeAvailability(final String id) {
    String endpoint = baseUri + "/dress" + "/newStatus/" + id;

    try {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .PUT(BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        // Do not attempt to deserialize the response if you're not expecting a specific result
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
    }
}
}