package rest;

import java.io.IOException;
import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Dresses;
import core.Users;
import core.User;
import persistence.Storage;

@RestController
@RequestMapping()
public class CoutureRentalController {
  /**
   * The serivce class.
   */
  private CoutureRentalService coutureRentalService;

  /**
   * Initializes the storage class.
   * @throws IOException if there is an error during the initializing.
   */
  public CoutureRentalController() throws IOException {
    this.coutureRentalService = new CoutureRentalService();
  }

  /**
   * Returns an instance of the storage class.
   * @return an instance of the storage class.
   */
  public Storage getStorage() {
    return this.coutureRentalService.getStorage();
  }

  /**
   * Sets a new active instance of the storage class.
   * @param storage
   * @throws IOException if there is an error during the initializing
   */
  public void setStorage(final Storage storage) throws IOException {
    coutureRentalService.setStorage(Objects.requireNonNull(storage));
  }

  /**
   * Performs a GET request to retrieve a list of all the dresses.
   * @return list of all the dresses.
   * @throws IOException if there is an error during the initializing.
   */
  @GetMapping("/dresses")
  public Dresses getDresses() throws IOException {
    return getStorage().loadDresses();
  }

  /**
   * Performs a GET request to retrieve a list of all the users.
   * @return list of all the users.
   * @throws IOException if there is an error during the initializing.
   */
  @GetMapping("/users")
  public Users getUsers() throws IOException {
    System.out.println("jeg er i getusers i controller");
    return getStorage().loadUsers();
  }

  /**
   * Performes a POST request to make an instance of a new user.
   * @param user
   * @return the new user.
   * @throws IOException if there is an error during the initializing.
   */
  @PostMapping("/users/add")
  public User addUser(@RequestBody final User user) throws IOException {
    getStorage().createUser(user);

    return user;
  }

  /**
   * Performs a GET request to retrieve a list of all the dresses with their availability.
   * @param id
   * @return list of all the dresses.
   * @throws IOException if there is an error during the initializing.
   */
  @GetMapping("/dress/avaliable/{id}")
  public Boolean getAvailability(@PathVariable final String id) throws IOException {
    System.out.println(getStorage().getAvailability(id) + "---------------------");
    return getStorage().getAvailability(id);
  }

  /**
   * Performs a PUT request to change the availability of a dress.
   * @param id
   * @throws IOException if there is an error during the initializing.
   */
  @PutMapping("/dress/newStatus/{id}")
  public void changeAvailability(@PathVariable final String id) throws IOException {
    System.out.println("endrer kjole" + id);
    getStorage().changeAvailability(id);
  }
}