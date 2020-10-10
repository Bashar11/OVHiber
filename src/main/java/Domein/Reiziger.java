package Domein;





import javax.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    private int id ;

    @Column(name = "voorletters")
    private String voorletters ;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;


    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private Date geboortedatum;

    @OneToOne(
            mappedBy = "reiziger",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )

    private Adres adres;

    @OneToMany(
            mappedBy = "reiziger",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OVChipkaart> kaarten = new ArrayList<>();

    public Reiziger(){}

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres, OVChipkaart ovKaart) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.adres = adres;




    }
    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;


    }



    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getVoorletters(){return voorletters;}
    public void setVoorletters(String voorletters){this.voorletters = voorletters;}

    public String getTussenvoegsel(){return tussenvoegsel;}
    public void setTussenvoegsel(String tussenvoegsel){this.tussenvoegsel = tussenvoegsel;}


    public String getNaam(){return achternaam;}
    public void setNaam(String achternaam){this.achternaam = achternaam;}

    public Date getGeboortedatum(){return geboortedatum;}
    public void setGeboortedatum(Date geboortedatum){this.geboortedatum = geboortedatum;}

    public boolean equals(Object obj){

        boolean gelijkeReiziger = false ;

        if(obj instanceof Reiziger){
            Reiziger reiziger = (Reiziger) obj;

            if(this.id == reiziger.id &&
                    this.achternaam.equals(reiziger.achternaam)&&
                    this.voorletters.equals(reiziger.voorletters)&&
                    this.tussenvoegsel.equals(reiziger.tussenvoegsel)&&
                    this.geboortedatum.equals(reiziger.geboortedatum)){
                gelijkeReiziger = true;
            }
        }
        return gelijkeReiziger;
    }

    public Adres getAdres(){return adres;}
    public void setAdres(Adres adres ){this.adres = adres ;}



    public List<OVChipkaart> getKaarten(){return kaarten;}
    public void setKaarten(List<OVChipkaart> kaarten){
        this.kaarten = kaarten;
    }
    public void addKaart(OVChipkaart newKaart){
        kaarten.add(newKaart);
    }
    public void deleteKaart(OVChipkaart oldKaart){
        kaarten.remove(oldKaart);
    }

    public String toStringg(){
        String s =  voorletters +(tussenvoegsel == null ? "" : " " + tussenvoegsel) +" " + achternaam+ ". id: " + id + ". Geboortedatum: " + geboortedatum;
        return s;

    }

    public String toString(){
        String s = "Reiziger: " + voorletters +(tussenvoegsel == null ? "" : " " + tussenvoegsel) +" " + achternaam+ ". id: " + id + ". Geboortedatum: " + geboortedatum ;
        if (adres ==null ){
            s += "";
        }else{
            s+=". Adres{ " + adres +" }" ;

        }
        s+= "\n " +kaarten;
        return s;
    }


}

