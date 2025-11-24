package com.project.service;

import com.project.config.HibernateUtil;
import com.project.domain.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalService {

    /**
     * Событие: покупатель пришёл в магазин, арендовал фильм и оплатил.
     */
    public void rentMovie(Long customerId, Long storeId, Long staffId) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // ---- 1. Получаем необходимые сущности ----
            Customer customer = session.get(Customer.class, customerId);
            Store store = session.get(Store.class, storeId);
            Staff staff = session.get(Staff.class, staffId);

            if (customer == null || store == null || staff == null) {
                throw new RuntimeException("Customer, Store или Staff не найдены");
            }

            // ---- 2. Находим доступный инвентарь (Inventory) ----
            Inventory availableInventory = session.createQuery("""
                            SELECT i FROM Inventory i
                            WHERE i.store.storeId = :storeId
                            """, Inventory.class)
                    .setParameter("storeId", storeId)
                    .getResultList()
                    .stream()
                    .filter(i -> {
                        Rental lastRental = i.getRentals().stream()
                                .max((r1, r2) -> r1.getRentalDate().compareTo(r2.getRentalDate()))
                                .orElse(null);
                        return lastRental == null || lastRental.getReturnDate() != null;
                    })
                    .findFirst()
                    .orElse(null);

            if (availableInventory == null) {
                throw new RuntimeException("Нет доступного инвентаря для аренды.");
            }

            Film film = availableInventory.getFilm();
            System.out.println("Выбран фильм: " + film.getTitle());

            // ---- 3. Создаём Rental ----
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(availableInventory);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());
            rental.setLastUpdate(LocalDateTime.now());

            session.persist(rental);

            // ---- 4. Создаём Payment ----
            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setAmount(BigDecimal.valueOf(4.99)); // цена аренды
            payment.setPaymentDate(LocalDateTime.now());
            payment.setLastUpdate(LocalDateTime.now());

            session.persist(payment);

            tx.commit();
            System.out.println("Аренда успешно оформлена!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

