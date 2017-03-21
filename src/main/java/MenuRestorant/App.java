package MenuRestorant;

import javax.persistence.EntityManager;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        try {
            EntityManager em = ConnectToDB.createDBconnection();
            Dish dish = new Dish();

            try {
                while(true) {
                    System.out.println("1. Add | 2. Price from-to | 3. Weight  | 4.Sale");
                    int ch=sc.nextInt();
                    switch(ch) {
                        case 1: dish.addNewDish(sc, em); break;
                        case 2: dish.selectFromPricetoPrice(sc, em); break;
                        case 3: dish.selectOnlyWithSale(sc, em); break;
                        case 4: dish.specialOrder(sc, em); break;
                        default: return;
                    }
                }
            } finally {
                sc.close();
                em.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
}
