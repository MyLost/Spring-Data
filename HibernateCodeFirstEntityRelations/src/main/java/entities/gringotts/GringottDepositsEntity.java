package entities.gringotts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="gringott_deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GringottDepositsEntity {

//    public GringottDepositsEntity() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="deposit_group", length = 20)
    private String depositGroup;

    @Column(name="deposit_start_date")
    private LocalDate depositStartDate;

    @Column(name="deposit_amount")
    private Double depositAmount;

    @Column(name="deposit_interest")
    private Double depositInterest;

    @Column(name="deposit_charge")
    private Double depositCharge;

    @Column(name="deposit_expiration_date")
    private LocalDate depositExpirationDate;

    @Column(name="is_deposit_expired")
    private Boolean isDepositExpired;

    @ManyToOne
    private GringottsEntity gringott;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getDepositGroup() {
//        return depositGroup;
//    }

//    public void setDepositGroup(String depositGroup) {
//        this.depositGroup = depositGroup;
//    }
//
//    public LocalDate getDepositStartDate() {
//        return depositStartDate;
//    }
//
//    public void setDepositStartDate(LocalDate depositStartDate) {
//        this.depositStartDate = depositStartDate;
//    }
//
//    public Double getDepositAmount() {
//        return depositAmount;
//    }
//
//    public void setDepositAmount(Double depositAmount) {
//        this.depositAmount = depositAmount;
//    }
//
//    public Double getDepositInterest() {
//        return depositInterest;
//    }
//
//    public void setDepositInterest(Double depositInterest) {
//        this.depositInterest = depositInterest;
//    }
//
//    public Double getDepositCharge() {
//        return depositCharge;
//    }
//
//    public void setDepositCharge(Double depositCharge) {
//        this.depositCharge = depositCharge;
//    }
//
//    public LocalDate getDepositExpirationDate() {
//        return depositExpirationDate;
//    }
//
//    public void setDepositExpirationDate(LocalDate depositExpirationDate) {
//        this.depositExpirationDate = depositExpirationDate;
//    }
//
//    public Boolean getDepositExpired() {
//        return isDepositExpired;
//    }
//
//    public void setDepositExpired(Boolean depositExpired) {
//        isDepositExpired = depositExpired;
//    }
//
//    public GringottsEntity getGringott() {
//        return gringott;
//    }
//
//    public void setGringott(GringottsEntity gringott) {
//        this.gringott = gringott;
//    }
}
