package com.project;

import com.project.config.HibernateUtil;
import com.project.dao.entity.*;
import com.project.domain.*;
import com.project.service.FilmService;
import com.project.service.RentalService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

        public static void main(String[] args) {

            // Fuerza la creación del SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("SessionFactory creado correctamente.");

            // Instancias de cada DAO
            ActorDao actorDao = new ActorDao();
            AddressDao addressDao = new AddressDao();
            CategoryDao categoryDao = new CategoryDao();
            CityDao cityDao = new CityDao();
            CountryDao countryDao = new CountryDao();
            CustomerDao customerDao = new CustomerDao();
            FilmDao filmDao = new FilmDao();
            FilmTextDao filmTextDao = new FilmTextDao();
            InventoryDao inventoryDao = new InventoryDao();
            LanguageDao languageDao = new LanguageDao();
            PaymentDao paymentDao = new PaymentDao();
            RentalDao rentalDao = new RentalDao();
            StaffDao staffDao = new StaffDao();
            StoreDao storeDao = new StoreDao();

            System.out.println("DAOs instanciados correctamente.");

            // Ejemplo simple: crear una sesión y verificar que funciona
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Sesión abierta correctamente.");
            }

            // Opcional: instancias vacías de cada entidad (si lo pediste por eso)
            Actor actor = new Actor();
            Address address = new Address();
            Category category = new Category();
            City city = new City();
            Country country = new Country();
            Customer customer = new Customer();
            //FilmActor filmActor = new FilmActor();
            Film film = new Film();
            FilmText filmText = new FilmText();
            Inventory inventory = new Inventory();
            Language language = new Language();
            Payment payment = new Payment();
            Rental rental = new Rental();
            Staff staff = new Staff();
            Store store = new Store();

            System.out.println("Instancias de entidades creadas correctamente.");

            RentalService rentalService = new RentalService();
            rentalService.rentMovie(1L, 1L, 1L);

            // ---- 1. Выпускаем новый фильм ----
            FilmService filmService = new FilmService();
            filmService.releaseNewFilm();

            // ---- 2. Пример аренды фильма ----
            rentalService = new RentalService();

            // Предположим, что customerId=1, storeId=1, staffId=1
            try {
                rentalService.rentMovie(1L, 1L, 1L);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Программа завершена.");

            // Cerrar SessionFactory al final
            sessionFactory.close();
            System.out.println("SessionFactory cerrado.");
        }
}
