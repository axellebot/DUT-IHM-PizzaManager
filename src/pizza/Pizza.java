package pizza;

/**
 * @author axell
 */
public class Pizza {

    private String nom, ingrédients;
    private double tarif;

    public Pizza() {
        this.nom = "sans nom";
        this.ingrédients = "sans ingrédients";
        this.tarif = 0;
    }

    public Pizza(Pizza p) {
        this.nom = p.nom;
        this.ingrédients = p.ingrédients;
        this.tarif = p.tarif;
    }

    public Pizza(String nom, String ingrédients, double tarif) {
        this.nom = nom;
        this.ingrédients = ingrédients;
        this.tarif = tarif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIngrédients() {
        return ingrédients;
    }

    public void setIngrédients(String ingrédients) {
        this.ingrédients = ingrédients;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return nom + "\t" + ingrédients + "\t" + tarif + "€";
    }

}
