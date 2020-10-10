package Domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartnummer;

    @Column(name = "geldig_tot")
    private Date geldigheid ;

    @Column(name = "klasse")
    private int klasse ;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "product_nummer")})
    private List<Product> producten = new ArrayList<>();

    private static StringBuilder stringBuilder;

    public OVChipkaart(){}

    public OVChipkaart(int kaartnummer, Date geldigheid, int klasse, double saldo){
        this.kaartnummer = kaartnummer;
        this.geldigheid = geldigheid;
        this.klasse = klasse;
        this.saldo = saldo;

    }

    public OVChipkaart(int kaartnummer, Date geldigheid, int klasse, double saldo, Reiziger reiziger){
        this.kaartnummer = kaartnummer;
        this.geldigheid = geldigheid;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldigheid() {
        return geldigheid;
    }

    public void setGeldigheid(Date geldigheid) {
        this.geldigheid = geldigheid;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public boolean equlas(Object obj){
        boolean gelijkeOv = false;

        if(obj instanceof OVChipkaart){
            OVChipkaart andereOv = (OVChipkaart) obj;
            if(this.kaartnummer == andereOv.kaartnummer &&
                    this.geldigheid.equals(andereOv.geldigheid)&&
                    this.klasse == andereOv.klasse &&
                    this.saldo == andereOv.saldo){
                gelijkeOv = true;
            }
        }
        return gelijkeOv;
    }

    public List<Product> getProducten(){
        return producten;
    }
    public void setList(List<Product> producten){
        this.producten = producten;
    }

    public void addProduct(Product product){
        for(Product product1: producten){
            if(product.getProductNr() == product.getProductNr()){
//                throw new DuplicateException("product ", product1.getProductNr() , "Product nummer");

            }
            producten.add(product);
        }


    }

    public void deleteProduct(Product product){

        Product product1 = producten.stream().filter(e->e.getProductNr()==product.getProductNr()).findFirst().orElse(null);
        if(product != null){
            producten.remove(product1);
        }



    }
    public String toStringss(){
        String s = " Ov-nummer: " + kaartnummer + ".\n Geldigheid: " + geldigheid +".\n Klasse: " + klasse +
                ".\n Saldo: " + saldo + ". \n Reiziger: " + reiziger.toStringg() + "\n`" ;
        return s;
    }


public String String() {
    String s = " Ov-nummer: " + kaartnummer + ".\n Geldigheid: " + geldigheid +".\n Klasse: " + klasse +
            ".\n Saldo: " + saldo + "\n`" ;
    return s;
}

    @Override
    public String toString() {
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Producten: %s product(en)", producten.size()));
        for (Product product : producten)
            stringBuilder.append("\n   ").append(product.toStrings());

        return String.format("%s\n    %s\n    %s",
                String(),
                reiziger != null ? reiziger.toStringg() : "<Geen Reiziger>",
                producten.size() > 0 ? stringBuilder.toString() : "<Geen Producten>"
        );
    }



}

