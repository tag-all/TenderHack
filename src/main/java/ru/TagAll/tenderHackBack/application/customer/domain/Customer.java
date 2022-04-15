package ru.TagAll.tenderHackBack.application.customer.domain;

import lombok.*;
import ru.TagAll.tenderHackBack.application.auth.domain.Token;
import ru.TagAll.tenderHackBack.application.common.ApplicationRoles;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "email_confirmed")
    private Boolean emailConfirmed;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone")
    private String phone;

    @Column(name = "code_region")
    private String codeRegion;

    @Column(name = "role")
    private ApplicationRoles role;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
    List<Token> tokenList;
}