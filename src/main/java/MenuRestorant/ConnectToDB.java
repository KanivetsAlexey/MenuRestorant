package MenuRestorant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Alexey on 19.03.2017
 */
public class ConnectToDB {
    public static EntityManager createDBconnection(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurant");
        return emf.createEntityManager();
    }
}
