package ru.TagAll.tenderHackBack.application.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Работа с тоблицей сообщений
 *
 */
public interface MessageRepository extends JpaRepository<Message,Long> {
}
