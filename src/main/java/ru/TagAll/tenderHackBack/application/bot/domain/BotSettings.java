package ru.TagAll.tenderHackBack.application.bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Time;

/**
 * Модель настроек бота.
 *
 * @author Iurii Babalin.
 */
@Data
@Entity
@Table(name = "bot_settings")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BotSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bot_settings_seq")
    @SequenceGenerator(name = "bot_settings_seq", sequenceName = "bot_settings_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "priority")
    private Long priority;

    @Column(name = "time_delay")
    private Time timeDelay;

    @Column(name = "step")
    private Double step;

    @Column(name = "min_payment")
    private Double minPayment;

    @Column(name = "status_work")
    private Boolean statusWork;
}
