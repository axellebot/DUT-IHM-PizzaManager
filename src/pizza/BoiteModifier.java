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
import java.util.ArrayList;
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
public class BoiteModifier extends JDialog implements ActionListener {

    //attribut graphique
    private JButton btAnnuler, btValider, btLeft, btRight;
    private JTextField txtNomPizza, txtIngredientPizza, txtPrixPizza;
    private JLabel lblNomPizza, lblIngredientPizza, lblPrixPizza;

    private ArrayList<Pizza> listPizza;
    private int x;

    // 1 attribut de type pizza
    private Pizza pizza = null;

    public BoiteModifier(Fenetre owner) {
        super(owner, true); //constructeur de la classe Mère : owner = propriétaire de la fenêtre (son parent), le second paramètre est true pour la rendre modale

        this.setTitle("Modifier Pizza");
        this.setIconImage(new ImageIcon(this.getClass().getResource("editicon.png")).getImage());

        this.x = 0;
        this.listPizza=owner.getListPizza();
        this.btValider = new JButton("Valider");
        btValider.setBackground(Color.GREEN);

        this.btAnnuler = new JButton("Annuler");
        btAnnuler.setBackground(Color.RED);

        this.btLeft = new JButton("<<");
        this.btRight = new JButton(">>");

        this.lblNomPizza = new JLabel("Nom : ");
        this.lblIngredientPizza = new JLabel("Ingrédients : ");
        this.lblPrixPizza = new JLabel("Prix (en €) : ");

        initialisation();

        //ajout des ecouteurs
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
         btLeft.addActionListener(this);
        btRight.addActionListener(this);

    }

    public void initialisation() {
        
        this.pizza=listPizza.get(x);
        this.txtNomPizza = new JTextField(pizza.getNom());
        this.txtIngredientPizza = new JTextField(pizza.getIngrédients());
        Double _tmp = pizza.getTarif();
        this.txtPrixPizza = new JTextField(_tmp.toString());

        
        
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
        
        cont.gridx = 0;
        cont.gridy = 4;
        pano.add(btLeft, cont);

        cont.gridx = 1;
        pano.add(btRight, cont);
        
        //changer la couleur
        pano.setBackground(Color.WHITE);
        //placer le panneau dans la JFrame
        this.setContentPane(pano);
        this.pack();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btAnnuler) {

            this.setVisible(false);//on ferme la fenêtre
        }
        if (ae.getSource() == btValider) {
            this.pizza.setNom(txtNomPizza.getText());
            this.pizza.setIngrédients(txtIngredientPizza.getText());
            this.pizza.setTarif(Double.parseDouble(txtPrixPizza.getText()));

            this.setVisible(false);//on ferme la fenêtre

        }
        if (ae.getSource() == btLeft) {
            if (this.x > 0) {
                this.x--;
                initialisation();
            }
        }
        if (ae.getSource() == btRight) {
            if (this.x < this.listPizza.size()-1) {
                this.x++;
                initialisation();
            }
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
