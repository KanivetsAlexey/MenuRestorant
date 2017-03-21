package MenuRestorant;

import javax.persistence.EntityManager;
import java.util.Scanner;

/**
 * Created by Alexey on 19.03.2017
 */
public interface OperationsDAO {

    void addNewDish(Scanner scanner, EntityManager entityManager);
    void selectFromPricetoPrice(Scanner scanner, EntityManager entityManager);
    void selectOnlyWithSale(Scanner scanner, EntityManager entityManager);
    void specialOrder(Scanner scanner, EntityManager entityManager);
}
