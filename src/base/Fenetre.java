package base;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author Axel
 */
public class Fenetre extends JFrame implements ActionListener, FocusListener {

    JTextField txt;
    JButton bouton;
    JLabel label1, label2, label3;
    JComboBox combobox;

    public Fenetre() {
        this.setTitle("Changement de base");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("mainicon.png")).getImage());

        initialisation();

        //ajout des ecouteurs
        combobox.addActionListener(this);
        bouton.addActionListener(this);
        txt.addFocusListener(this);

    }

    private void initialisation() {
        //Création des composants

        txt = new JTextField("");
        txt.setColumns(10);

        bouton = new JButton("Convert");
        bouton.setBackground(Color.GREEN);

        label1 = new JLabel("Nombre (en base 10) :");
        label1.setBorder(new BevelBorder(1));

        label2 = new JLabel("Base :");
        label2.setBorder(new BevelBorder(1));

        label3 = new JLabel("empty");
        label3.setBorder(new BevelBorder(1));

        String es[] = {"2", "3", "8", "16"}; // new implicite
        combobox = new JComboBox(es);

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
        pano.add(label1, cont);

        cont.gridx = 1;
        pano.add(label2, cont);

        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(txt, cont);

        cont.gridx = 1;
        pano.add(combobox, cont);

        cont.gridx = 0;
        cont.gridy = 2;
        cont.gridwidth = 2;
        pano.add(bouton, cont);

        cont.gridx = 0;
        cont.gridy = 3;
        cont.gridwidth = 2;
        pano.add(label3, cont);

        //changer la couleur
        pano.setBackground(Color.lightGray);
        //placer le panneau dans la JFrame

        this.setContentPane(pano);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bouton) {

            int _tmp = Integer.parseInt(txt.getText());
            int _base = Integer.parseInt((String) combobox.getSelectedItem());
            String _convert = Integer.toString(_tmp, _base);
            label3.setText(_convert);

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txt) {
            txt.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

}
