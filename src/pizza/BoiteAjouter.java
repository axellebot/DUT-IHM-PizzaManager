package pizza;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author axell
 */
public class BoiteAjouter extends JDialog implements ActionListener {

    //attribut graphique
    private JButton btAnnuler, btValider;
    private JTextField txtNomPizza, txtIngredientPizza, txtPrixPizza;
    private JLabel lblNomPizza, lblIngredientPizza, lblPrixPizza;

    // 1 attribut de type pizza
    private Pizza pizza = null;

    public BoiteAjouter(Fenetre owner) {
        super(owner, true); //constructeur de la classe Mère : owner = propriétaire de la fenêtre (son parent), le second paramètre est true pour la rendre modale

        this.setTitle("Ajouter Pizza");
        this.setIconImage(new ImageIcon(this.getClass().getResource("addicon.png")).getImage());

        this.btValider = new JButton("Valider");
        btValider.setBackground(Color.GREEN);

        this.btAnnuler = new JButton("Annuler");
        btAnnuler.setBackground(Color.RED);

        this.lblNomPizza = new JLabel("Nom : ");
        this.lblIngredientPizza = new JLabel("Ingrédients : ");
        this.lblPrixPizza = new JLabel("Prix (en €) : ");

        this.txtNomPizza = new JTextField("");
        this.txtIngredientPizza = new JTextField("");
        this.txtPrixPizza = new JTextField("");

        initialisation();

        //ajout des ecouteurs
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
       // txtNomPizza.addFocusListener(this);
        //txtIngredientPizza.addFocusListener(this);
        //txtPrixPizza.addFocusListener(this);
    }

    public void initialisation() {
  //création d'un panneau
        JPanel pano = new JPanel();
        //ajout du gestionnaire de placement au panneau
        pano.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();

        /*placement des composants*/
        //remplir
        cont.fill = GridBagConstraints.BOTH;

        //redimensionne en fonction de la fenetre
        cont.weightx = 1;
        cont.weighty = 1;

        //padding
        cont.ipadx = 0;
        cont.ipadx = 0;

        cont.gridx = 0;
        cont.gridy = 0;
        pano.add(lblNomPizza, cont);

        cont.gridx = 1;
        pano.add(txtNomPizza, cont);

        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(lblIngredientPizza, cont);

        cont.gridx = 1;
        pano.add(txtIngredientPizza, cont);

        cont.gridx = 0;
        cont.gridy = 2;
        pano.add(lblPrixPizza, cont);

        cont.gridx = 1;
        pano.add(txtPrixPizza, cont);

        cont.gridx = 0;
        cont.gridy = 3;
        pano.add(btValider, cont);

        cont.gridx = 1;
        pano.add(btAnnuler, cont);

        //changer la couleur
        pano.setBackground(Color.WHITE);
        //placer le panneau dans la JFrame
        this.setContentPane(pano);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btAnnuler) {
            this.pizza = null;
            this.setVisible(false);//on ferme la fenêtre
        }
        if (ae.getSource() == btValider) {
            Double _tmp=0.0;
            _tmp=Double.parseDouble(this.txtPrixPizza.getText());
            this.pizza = new Pizza(this.txtNomPizza.getText(),this.txtIngredientPizza.getText(),_tmp);
            this.setVisible(false);//on ferme la fenêtre
        }
    }

    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == txtNomPizza) {
            txtNomPizza.setText("");
        }
        if (fe.getSource() == txtIngredientPizza) {
            txtIngredientPizza.setText("");
        }
        if (fe.getSource() == txtPrixPizza) {
            txtPrixPizza.setText("");
        }
    }

    public Pizza showDialog() {
        this.setVisible(true);//on ouvre la fenêtre
        return pizza;//on retourne Le résultat
    }

}
