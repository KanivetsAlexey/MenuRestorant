package MenuRestorant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexey on 19.03.2017
 */
@Entity
@Table(name = "dish")
public class Dish implements OperationsDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id")
    private long id;

    @Column(name = "dish_name")
    private String name;

    @Column(name = "dish_weight")
    private Double weight;

    @Column(name = "dish_price")
    private Integer price;

    @Column(name = "dish_sale")
    private Integer sale;

    public Dish() {
    }

    public Dish(String name, Double weight, Integer price, Integer sale) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.sale = sale;
    }

    @Override
    public void addNewDish(Scanner scanner, EntityManager entityManager) {
        System.out.println("Type dish name: ");
        String userDishName = scanner.next();
        System.out.println("Type dish weight: ");
        Double userDishWeight = scanner.nextDouble();
        System.out.println("Type dish price: ");
        Integer userDishPrice = (int)(scanner.nextDouble() * 100);
        System.out.println("Type dish sale: ");
        Integer userDishSale = scanner.nextInt();

        entityManager.getTransaction().begin();
        try {
            Dish userDish = new Dish(userDishName, userDishWeight, userDishPrice, userDishSale);
            entityManager.persist(userDish);
            entityManager.getTransaction().commit();
        } catch(Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void selectFromPricetoPrice(Scanner scanner, EntityManager entityManager) {
        System.out.println("Please input parameter from:");
        Integer userFromParameter = (int) (scanner.nextDouble() * 100);
        System.out.println("Please input parameter to:");
        Integer userToParameter = (int) (scanner.nextDouble() * 100);

        Dish d = null;
        Query query = entityManager.createQuery("SELECT d from Dish d where d.price > :userFromParameter AND d.price < :userToParameter", Dish.class);
        query.setParameter("userFromParameter", userFromParameter);
        query.setParameter("userToParameter", userToParameter);

        List<Dish> menuList = (List<Dish>) query.getResultList();
        for (Dish iterator:menuList) {
            System.out.println(iterator.toString());
        }
    }

    @Override
    public void selectOnlyWithSale(Scanner scanner, EntityManager entityManager) {
        Query query = entityManager.createQuery("SELECT d from Dish d where d.sale > 0", Dish.class);

        List<Dish> menuList = (List<Dish>) query.getResultList();
        for (Dish iterator:menuList) {
            System.out.println(iterator.toString());
        }
    }

    @Override
    public void specialOrder(Scanner scanner, EntityManager entityManager) {
        System.out.println("Please input max dish weight:");
        Double maxWeight = scanner.nextDouble();
        double interactiveWeight = 0.0;
        boolean flag = true;

        List<Dish> wishList = new ArrayList<>();
        do {
            System.out.println("Please choose some food: ");
            printAllDishes(entityManager);
            String userDishName = scanner.nextLine();
            Dish userChoiceDish = addDishToWishList(userDishName, entityManager);
            wishList.add(userChoiceDish);
            interactiveWeight += userChoiceDish.weight;
            if(interactiveWeight < 0) {
                flag = false;
            }
        } while(interactiveWeight>=maxWeight && flag);
    }

    private List<Dish> getDishesList(EntityManager entityManager){
        Query query = entityManager.createQuery("select d from Dish d", Dish.class);
        return (List<Dish>) query.getResultList();
    }

    private void printAllDishes(EntityManager entityManager){
        for(Dish iterator: getDishesList(entityManager)) {
            System.out.println(iterator.toString());
        }
    }

    private Dish addDishToWishList(String userDishName, EntityManager entityManager){
        Query query = entityManager.createQuery("SELECT d from Dish d where d.name= :userDishName");
        query.setParameter("userDishName", userDishName);
        return (Dish) query.getSingleResult();
    }

    @Override
    public String toString() {
        return "\n" + "Dish" + "\n" +
                "name= " + name + "\n" +
                "weight= " + weight + "\n" +
                "price= " + price + "\n" +
                "sale= " + sale + "\n";
    }
}