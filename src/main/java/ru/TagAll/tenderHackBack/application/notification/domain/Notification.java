package ru.TagAll.tenderHackBack.application.notification.domain;

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

/**
 * Уведомление.
 *
 * @author Iurii Babalin.
 */
@Data
@Entity
@Table(name = "notification")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    @SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "read_status")
    private Boolean readStatus;
}
