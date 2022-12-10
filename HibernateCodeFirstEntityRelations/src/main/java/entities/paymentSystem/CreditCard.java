package entities.paymentSystem;

import jakarta.persistence.Column;

public class CreditCard extends BillingDetail {

    @Column(length = 30)
    private String cardType;

    @Column(name = "expiration_month")
    private byte expirationMonth;

    @Column(name = "expiration_year")
    private short expirationYear;
}
