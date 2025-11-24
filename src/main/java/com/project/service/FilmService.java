package com.project.service;

import com.project.config.HibernateUtil;
import com.project.domain.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FilmService {

    /**
     * Событие: снят новый фильм, он добавлен в базу и доступен для аренды.
     */
    public void releaseNewFilm() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // ---- 1. Язык ----
            Language english = session.get(Language.class, (byte) 1); // English
            if (english == null) {
                english = new Language();
                english.setName("English");
                english.setLastUpdate(LocalDateTime.now());
                session.persist(english);
            }

            // ---- 2. Создаем фильм ----
            Film film = new Film();
            film.setTitle("The New Adventure");
            film.setDescription("Exciting new movie for all ages");
            film.setReleaseYear((short) 2025);
            film.setLanguage(english);
            film.setRentalDuration((byte) 5);
            film.setRentalRate(BigDecimal.valueOf(4.99));
            film.setReplacementCost(BigDecimal.valueOf(19.99));
            film.setLastUpdate(LocalDateTime.now());

            // ---- 3. Добавляем актеров ----
            Actor actor1 = session.get(Actor.class, (short) 1);
            Actor actor2 = session.get(Actor.class, (short) 2);
            Set<Actor> actors = new HashSet<>();
            if (actor1 != null) actors.add(actor1);
            if (actor2 != null) actors.add(actor2);
            film.setActors(actors);

            // ---- 4. Добавляем категории ----
            Category category = session.get(Category.class, (short) 1); // Action
            Set<Category> categories = new HashSet<>();
            if (category != null) categories.add(category);
            film.setCategories(categories);

            session.persist(film); // Сохраняем фильм

            // ---- 5. Создаем инвентарь ----
            Store store = session.get(Store.class, (byte) 1); // Первый магазин
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventory.setLastUpdate(LocalDateTime.now());

            session.persist(inventory);

            tx.commit();
            System.out.println("Новый фильм добавлен и доступен для аренды!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
