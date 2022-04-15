package ru.TagAll.tenderHackBack.application.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.TagAll.tenderHackBack.application.auth.domain.Token;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.notification.domain.Notification;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

/**
 * Пользователь.
 *
 * @author Iurii Babalin.
 */
@Data
@Entity
@Table(name = "customer")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "notification_time")
    private Time notificationTime;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Token> tokenList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BotSettings> botSettingsList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Notification> notificationsList;
}