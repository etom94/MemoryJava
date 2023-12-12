package Java.MemoryJava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class endblidschirm 
{
    private static String
    dateiname, 
    inhaltname;

    public static void memory(JFrame programmfenster, int punkte, String zeit)
    {
        Dimension
        framegrösse = programmfenster.getSize(),
        buttongrösse;

        int
        buttonwidth = (int) (framegrösse.getWidth()*.075),
        buttonheight = 20;
    
        JButton
        bestätigen = new JButton("Bestätigen"),
        rangliste = new JButton("Rangliste");
    
        JTextField
        name = new JTextField();
    
        GridBagConstraints 
        gbc = new GridBagConstraints();
    
        JPanel 
        endbildschirm = new JPanel(new GridBagLayout()),
        buttonpanel = new JPanel(new FlowLayout());
    
        JLabel
        Gratulation = new JLabel("Congratulations!!!"),
        punktelabel = new JLabel("Ihre Punktzahl: "),
        zeitLabel,
        eingabe = new JLabel("Bitte geben Sie Ihren Namen an");

        endbildschirm.setBounds(0, 0, programmfenster.getWidth(), programmfenster.getHeight());
        endbildschirm.setBackground(new Color(200, 240, 220));

        Gratulation.setForeground(Color.black);
        Gratulation.setFont(new Font("MV Boli",Font.PLAIN,60));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        endbildschirm.add(Gratulation,gbc);

        punktelabel.setForeground(Color.black);
        punktelabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        gbc.gridy = 1;
        endbildschirm.add(punktelabel,gbc);

        zeitLabel = new JLabel("Ihre Zeit: "+ zeit);
        zeitLabel.setForeground(Color.black);
        zeitLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        gbc.gridy = 2;
        endbildschirm.add(zeitLabel,gbc);

        eingabe.setForeground(Color.black);
        eingabe.setFont(new Font("MV Boli",Font.PLAIN,20));
        gbc.gridy = 3;
        endbildschirm.add(eingabe,gbc);

        name.setHorizontalAlignment(JTextField.CENTER);
        name.setFont(new Font("MV Boli",Font.PLAIN,20));
        name.setBackground(new Color(200, 170, 100).brighter().brighter());
        gbc.gridy = 4;
        gbc.ipadx = (int) (programmfenster.getWidth()*.15);
        endbildschirm.add(name,gbc);

        gbc.gridy = 5;
        buttonpanel.setBackground(new Color(200, 240, 220));
        endbildschirm.add(buttonpanel,gbc);

        buttongrösse = new Dimension(buttonwidth, buttonheight);
        rangliste.setPreferredSize(buttongrösse);
        buttonpanel.add(rangliste);
        bestätigen.setPreferredSize(buttongrösse);
        buttonpanel.add(bestätigen);

        bestätigen.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent zurück) 
            {
                inhaltname = name.getText();

                dateiname = "Java/ProjektSpiele/rangliste.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiname, true))) 
                {
                    writer.write(inhaltname  +"," + punkte + ", " + zeit);
                    writer.newLine();
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(null, "Fehler beim Speichern: " + e.getMessage());
                }

                programmfenster.remove(endbildschirm);
                Startseite.startseite(programmfenster);
            }
        });

        rangliste.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent zurück) 
            {
                auflistung(programmfenster);
            }
        });

        programmfenster.setContentPane(endbildschirm);
        programmfenster.repaint();
        programmfenster.revalidate();
    }

    public static void auflistung(JFrame programmfenster) 
    {
        int
        punkte;

        JPanel 
        rangliste = new JPanel(new GridBagLayout());
        rangliste.setBounds(0, 0, programmfenster.getWidth(), programmfenster.getHeight());
        rangliste.setBackground(new Color(200, 240, 220));

        GridBagConstraints 
        constraintsrangliste = new GridBagConstraints();
        constraintsrangliste.anchor = GridBagConstraints.CENTER;

        JLabel
        rangLabel = new JLabel("Rangliste");
        rangLabel.setForeground(Color.black);
        rangLabel.setFont(new Font("MV Boli",Font.PLAIN,60));
        constraintsrangliste.gridy = 0;
        rangliste.add(rangLabel,constraintsrangliste);
    
        ArrayList<String[]> 
        data = new ArrayList<>();

        String
        zeit,
        name;
    
        String[] 
        werte,
        spaltennamen = {"Name", "Punktzahl", "Zeit"};
    
        DefaultTableModel 
        model = new DefaultTableModel(data.toArray(new String[0][0]), spaltennamen);
    
        JTable
        table = new JTable(model);
        table.setFont(new Font("MV Boli",Font.PLAIN,20));
        table.setBackground(new Color(200, 170, 100).brighter().brighter());
        table.getTableHeader().setBackground(new Color(200, 170, 100).brighter().brighter());
        
    
        JScrollPane 
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(200, 170, 100).brighter().brighter());
        constraintsrangliste.gridy = 1;
        rangliste.add(scrollPane,constraintsrangliste);

        JButton
        zurück = new JButton("Zurück");
        constraintsrangliste.gridy = 2;
        rangliste.add(zurück,constraintsrangliste);
        zurück.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent zurück) 
            {
                programmfenster.remove(rangliste);
                Startseite.startseite(programmfenster);
            }
        });
    
        File 
        file = new File("Java/ProjektSpiele/rangliste.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) 
            {
                werte = line.split(",");
                name = werte[0].trim(); 
                punkte = Integer.parseInt(werte[1].trim());
                zeit = werte[2].trim();

                String[] 
                reihe = {name, Integer.toString(punkte), zeit};
                data.add(reihe);
            }

            Collections.sort(data, new Comparator<String[]>()
            {
                @Override
                public int compare(String[] wert1, String[] wert2) 
                {
                    int punkte1 = Integer.parseInt(wert1[1]);
                    int punkte2 = Integer.parseInt(wert2[1]);
                    if (punkte1 != punkte2) 
                    {
                        return punkte2 - punkte1;
                    } 
                    else 
                    {
                        String zeit1 = wert1[2];
                        String zeit2 = wert2[2];
                        return zeit1.compareTo(zeit2);
                    }
                }
            });
            
            for (int i = 0; i < data.size(); i++) 
            {
                String[] row = data.get(i);
                row = new String[]{row[0], row[1], row[2]};
                model.addRow(row);
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Die Datei wurde nicht gefunden: " + file.getAbsolutePath());
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }

        programmfenster.setContentPane(rangliste);
        programmfenster.repaint();
        programmfenster.revalidate();
    }
    
    
}
