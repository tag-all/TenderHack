package ru.TagAll.tenderHackBack.application.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для взаимодействия с таблицей токена.
 *
 * @author Iurii Babalin.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
    /**
     * Получение модели {@link Token}.
     *
     * @param token токен доступа.
     * @return Token
     */
    Token getTokenByToken(String token);
}
