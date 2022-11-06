package entities.gringotts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="gringotts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GringottsEntity {

//    public GringottsEntity() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private Integer age;

    @OneToOne
    @JoinColumn(name = "magic_wand_id", referencedColumnName = "id")
    private MagicWandEntity magicWand;

    @OneToMany(mappedBy = "gringott", targetEntity = GringottDepositsEntity.class)
    private List<GringottDepositsEntity> deposits;

//    public MagicWandEntity getMagicWand() {
//        return magicWand;
//    }
//
//    public void setMagicWand(MagicWandEntity magicWand) {
//        this.magicWand = magicWand;
//    }
//
//    public List<GringottDepositsEntity> getDeposits() {
//        return deposits;
//    }
//
//    public void setDeposits(List<GringottDepositsEntity> deposits) {
//        this.deposits = deposits;
//    }

    //    @Column(name="magic_wand_creator", length = 100)
//    private String magicWandCreator;
//
//    @Column(name="magic_wand_size")
//    private int magicWandSize;

//    @Column(name="deposit_group", length = 20)
//    private String depositGroup;
//
//    @Column(name="deposit_start_date")
//    private LocalDate depositStartDate;
//
//    @Column(name="deposit_amount")
//    private Double depositAmount;
//
//    @Column(name="deposit_interest")
//    private Double depositInterest;
//
//    @Column(name="deposit_charge")
//    private Double depositCharge;
//
//    @Column(name="deposit_expiration_date")
//    private LocalDate depositExpirationDate;
//
//    @Column(name="is_deposit_expired")
//    private Boolean isDepositExpired;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

//    public String getMagicWandCreator() {
//        return magicWandCreator;
//    }
//
//    public void setMagicWandCreator(String magicWandCreator) {
//        this.magicWandCreator = magicWandCreator;
//    }
//
//    public int getMagicWandSize() {
//        return magicWandSize;
//    }
//
//    public void setMagicWandSize(int magicWandSize) {
//        this.magicWandSize = magicWandSize;
//    }

//    public String getDepositGroup() {
//        return depositGroup;
//    }
//
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
}
