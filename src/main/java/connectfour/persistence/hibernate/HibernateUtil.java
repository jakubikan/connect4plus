package connectfour.persistence.hibernate;

import connectfour.model.Computer;
import connectfour.model.Human;
import connectfour.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.service.ServiceRegistry;

//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        /*Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
        final AnnotationConfiguration cfg = new
                AnnotationConfiguration();
        cfg.configure("/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }

    public static PlayerHibernate convertToPlayerHibernate(Player player) {
        return new PlayerHibernate(player.getName(), player instanceof Computer);
    }

    public static Player convertToStandardPlayer(PlayerHibernate playerHibernate) {
        Player player;
        if (playerHibernate.isComputer) {
            player = new Computer(null);
            player.setName(playerHibernate.name);
        } else {
            player = new Human();
            player.setName(playerHibernate.name);
        }
        return player;
    }
}