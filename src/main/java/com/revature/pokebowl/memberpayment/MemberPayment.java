package com.revature.pokebowl.memberpayment;

import javax.persistence.*;

@Entity
@Table(name="member_payment")
public class MemberPayment {

    @Id
    @Column(name="payment_id")
    private String paymentId;
}
