package Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_nummer")
    private int productNr;

    @Column(name = "naam")
    private String naam ;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private double prijs;

    @ManyToMany(mappedBy = "producten")

    List<OVChipkaart> kaarten = new ArrayList<>();

    public Product(){}

    public Product(int productNr, String naam, String beschrijving, double prijs) {
        this.productNr = productNr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }


    public int getProductNr() {
        return productNr;
    }
    public void setProductNr(int productNr) {
        this.productNr = productNr;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }
    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }


    public boolean equals(Object object){
        boolean gelijkeProduct = false ;

        if(object instanceof Product){
            Product andereProduct = (Product) object;

            if(this.productNr == andereProduct.productNr &&
                    this.naam.equals(andereProduct.naam) &&
                    this.beschrijving.equals(andereProduct.beschrijving)&&
                    this.prijs == andereProduct.prijs){
                gelijkeProduct = true;
            }
        }
        return gelijkeProduct;
    }

    public List<OVChipkaart> getOv() {

        return kaarten;
    }

    public void addKaart(OVChipkaart ov){
        for(OVChipkaart ov1: kaarten){
            if(ov.getKaartnummer() == ov1.getKaartnummer()){
                throw new DuplicateException("Ov-kaart", ov.getKaartnummer(),"Kaart nummer.");
            }

        }
        kaarten.add(ov);


    }
    public void deleteKaart(OVChipkaart ov){
        OVChipkaart ovChipkaart = kaarten.stream().filter(e->e.getKaartnummer()==ov.getKaartnummer()).findFirst().orElse(null);
        if(ovChipkaart != null){
            kaarten.remove(ov);
        }
    }

    public String toStrings(){
//        String s = productNr + ".. " + naam + ".. " + beschrijving +".. "+ prijs + kaarten ;
//
//        return s;
        return String.format("Product %s#: %s, %s, %s", productNr, naam, beschrijving, prijs);


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("OVChipkaarten: %s kaart(en)",
                kaarten.size()));
        for (OVChipkaart ovchip : kaarten)
            stringBuilder.append("\n        ").append(ovchip.toStringss());

        return String.format("%s\n    %s",
                toStrings(),
                kaarten.size() > 0 ? stringBuilder.toString() : "<Geen OVChipkaarten>"
        );
    }





}

