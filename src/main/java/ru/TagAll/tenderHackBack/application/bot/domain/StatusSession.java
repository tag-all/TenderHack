package ru.TagAll.tenderHackBack.application.bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Статус сессии - информацию о сессиях
 * мы получаем из внешних систем
 *
 * @author Iurii Babalin.
 */
@Data
@Entity
@Table(name = "status_session")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class StatusSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_session_seq")
    @SequenceGenerator(name = "status_session_seq", sequenceName = "status_session_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "status")
    private String status;

    @Column(name = "operating_mode")
    private Boolean operatingMode;

    @OneToOne(mappedBy = "statusSession", cascade = CascadeType.ALL)
    private BotSettings botSettings;
}
