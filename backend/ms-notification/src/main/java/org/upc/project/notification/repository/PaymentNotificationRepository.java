package org.upc.project.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.notification.entity.PaymentNotification;

@Repository
public interface PaymentNotificationRepository extends CrudRepository<PaymentNotification, Long> {
}
