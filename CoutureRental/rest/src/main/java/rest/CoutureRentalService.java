package rest;

import java.util.Objects;
import org.springframework.stereotype.Service;

import persistence.Storage;

@Service
public class CoutureRentalService {

  /**
   * The local storage that is used to implement our storage class.
   */
  private Storage storage;

  /**
   * Constructor that initializes the application storage.
   */
  public CoutureRentalService() {
    this.storage = new Storage();
  }

  /**
   * Returns an instance of the storage class.
   * @return an instance of the storage class.
   */
  public Storage getStorage() {
    return storage;
  }

  /**
   * Sets an instane of the storage.
   * @param storage represents an instance of the storage class.
   */
  public void setStorage(final Storage storage) {
  this.storage = Objects.requireNonNull(storage);
  }
}
