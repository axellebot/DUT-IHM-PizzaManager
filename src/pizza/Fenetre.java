package pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

/**
 * @author Axel
 */
public class Fenetre extends JFrame implements ActionListener, FocusListener {

    private ArrayList<Pizza> listPizza;
    private ArrayList<JLabel> listLabel;
    private ArrayList<JComboBox> listCbb;
    private JButton RAZ, Calculer;
    private JLabel resultat;

    JMenuBar barMenu;
    JMenu menuPizza;
    JMenuItem Ajouter, Supprimer, Modifier;

    public Fenetre() {
        this.setTitle("Pizza Hut");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("mainicon.png")).getImage());

        this.listPizza = new ArrayList();

        listPizza.add(new Pizza("4 Fromage", "Chevre, Mozarella", 14.2));
        listPizza.add(new Pizza("Savoyarde", "Reblochon", 1.2));

        this.RAZ = new JButton("RAZ");
        RAZ.setBackground(Color.RED);

        this.Calculer = new JButton("Calculer");
        this.Calculer.setBackground(Color.GREEN);

        this.resultat = new JLabel("0€");

        this.barMenu = new JMenuBar();
        this.menuPizza = new JMenu("Pizza");
        this.Ajouter = new JMenuItem("Ajouter");
        this.Supprimer = new JMenuItem("Supprimer");
        this.Modifier = new JMenuItem("Modifier");

        initialisation();

        //ajout des ecouteurs
        RAZ.addActionListener(this);
        Calculer.addActionListener(this);
        menuPizza.addActionListener(this);
        Ajouter.addActionListener(this);
        Supprimer.addActionListener(this);
        Modifier.addActionListener(this);

    }

    public ArrayList<Pizza> getListPizza() {
        return listPizza;
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

        String es[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // new implicite

//Reaffectation des nouvelles listes de Label et de Cbb
        this.listLabel = new ArrayList();
        this.listCbb = new ArrayList();

        for (Pizza p : listPizza) {
            listLabel.add(new JLabel(p.toString()));
            listCbb.add(new JComboBox(es));
        }

        for (int i = 0; i < listPizza.size(); i++) {
            pano.add(listLabel.get(i), cont);
            cont.gridy++;
        }
        cont.gridx = 1;
        cont.gridy = 0;
        for (int i = 0; i < listPizza.size(); i++) {
            pano.add(listCbb.get(i), cont);
            cont.gridy++;
        }
        cont.gridx = 3;
        cont.gridy = 0;

        cont.fill = GridBagConstraints.HORIZONTAL;

        cont.gridx = 3;
        cont.gridy = 0;
        cont.anchor = GridBagConstraints.PAGE_START;
        pano.add(RAZ, cont);

        cont.gridy = 1;
        cont.anchor = GridBagConstraints.PAGE_END;
        pano.add(Calculer, cont);

        cont.gridy = 2;
        cont.anchor = GridBagConstraints.PAGE_END;
        pano.add(resultat, cont);

        cont.fill = GridBagConstraints.BOTH;

        //ajouter les différents option du menuPizza
        menuPizza.add(Ajouter);
        menuPizza.add(Modifier);
        menuPizza.add(Supprimer);

        //ajouter nemuPizza à la bar de menu
        barMenu.add(menuPizza);
        this.setJMenuBar(barMenu);

        //changer la couleur
        pano.setBackground(Color.lightGray);
        //placer le 
        this.setContentPane(pano);
        this.pack();

    }

    public void addPizza(Pizza p) {
        listPizza.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RAZ) {
            for (JComboBox cbb : listCbb) {
                cbb.setSelectedIndex(0);

            }
            resultat.setText("0€");
        }
        if (e.getSource() == Calculer) {
            Double _tmp = 0.0;
            for (int i = 0; i < listCbb.size(); i++) {
                _tmp += Double.parseDouble((String) listCbb.get(i).getSelectedItem()) * listPizza.get(i).getTarif();
            }
            resultat.setText(_tmp.toString() + "\t€");
        }
        if (e.getSource() == Ajouter) {
            BoiteAjouter boite = new BoiteAjouter(this);
            Pizza p = boite.showDialog();
            if (p != null) {
                addPizza(p);
                initialisation();
            }

        }
        if (e.getSource() == Modifier) {
            BoiteModifier boite = new BoiteModifier(this);
            boite.showDialog();
            initialisation();
        }
        if (e.getSource() == Supprimer) {
            BoiteSupprimer boite = new BoiteSupprimer(this);
            this.listPizza = boite.showDialog();
            initialisation();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

}
