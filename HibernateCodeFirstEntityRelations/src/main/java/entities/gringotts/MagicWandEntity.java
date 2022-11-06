package entities.gringotts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "magic_wand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagicWandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name="magic_wand_size")
    private int magicWandSize;

    @OneToOne(mappedBy = "magicWand", targetEntity = GringottsEntity.class)
    private GringottsEntity gringott;

//    public MagicWandEntity() {
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
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
//
//    public GringottsEntity getGringott() {
//        return gringott;
//    }
//
//    public void setGringott(GringottsEntity gringott) {
//        this.gringott = gringott;
//    }
}
