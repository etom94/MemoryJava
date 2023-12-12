// Importieren der benötigten Klassen und Pakete
package Java.ProjektSpiele;

import java.awt.Color;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Definieren der Klasse "Startseite"
public class Startseite
{
    //Variablen für Farben erstellen
    private static Color
    farbefenster = new Color(200, 240, 220),
    farbebutton = new Color(200, 170, 100).brighter().brighter();

    // Hauptmethode des Programms
    public static void main(String[] args)
    {

        // Erstelle das Hauptfenster
        JFrame programmfenster = new JFrame("Spiele by Natascha and Thomas");

        // Lade ein Bild zur Verwendung als Fenster-Symbol
        ImageIcon image = new ImageIcon("Java/PrjektSpiele/Bilder/benedict.png");
        programmfenster.setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Setze das Fenster auf Vollbild-Modus
        programmfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                     // Beende das Programm, wenn das Fenster geschlossen wird
        programmfenster.getContentPane().setBackground(farbefenster);                       // Setze die Hintergrundfarbe des Fensters
        programmfenster.setIconImage(image.getImage());                                     // Setze das Fenster-Symbol
        programmfenster.setLayout(null);                                            // Setze das Layout des Fensters auf "null"
        programmfenster.setResizable(false);
        programmfenster.setVisible(true);                                                 // Mache das Fenster sichtbar

        // Rufe die Methode auf, die die Startseite erstellt
        Startseite.startseite(programmfenster);                                             
    }

    // Methode zur Erstellung der Startseite
    public static void startseite(JFrame programmfenster) 
    { 
        programmfenster.setVisible(true);
        int 
        anzahlbuttons = 5,                                                                  // Anzahl der Buttons auf der Startseite
        panelwidth = 0,                                                                         // Breite Höhe des Startseiten-Panels
        panelheight = 0;                                                                        // Breite Höhe des Startseiten-Panels
    
        Button[] 
        buttons = new Button[anzahlbuttons];                                                // Array für die Buttons auf der Startseite
    
        String[] 
        benennung = {"Memory", "Snake","Zahlenraten","4Gewinnt","Beenden"};                 // Array für die Benennung der Buttons
    
        GridBagConstraints 
        constraints = new GridBagConstraints();                                             // Constraints für das GridBagLayout
    
        JPanel startpanel = new JPanel(new GridBagLayout());                         // Panel für die Startseite

        startpanel.setBounds(0, 0, programmfenster.getWidth(), programmfenster.getHeight());    // Setze die Größe und Position des Startseiten-Panels
        startpanel.setBackground(farbefenster);                                                     // Setze die Hintergrundfarbe des Startseiten-Panels
        panelwidth = startpanel.getWidth();                                                         // Speichere die Breite des Startseiten-Panels
        panelheight = startpanel.getHeight();                                                       // Speichere die Höhe des Startseiten-Panels

        // Erstelle die Buttons für die Startseite
        for(int i=0;i<anzahlbuttons;i++)
        {
            buttons[i] = new Button(benennung[i]);
            constraints.gridy = i;
            constraints.ipadx = (int) (panelwidth*.15);
            constraints.ipady = (int) (panelheight*.025);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(0,0,30,0);
            // Füge die Buttons zum Startseiten-Panel hinzu
            buttons[i].setBackground(farbebutton);
            startpanel.add(buttons[i],constraints);
        }

        // Füge ActionListener zu Button 0 (Memory) hinzu
        buttons[0].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent memory) 
            {
                memoryauswahl.memoryauswahlreset();
                memoryauswahl.memoryauswahlfenster(programmfenster);
            }
        });

        // Füge ActionListener zu Button 1 (Snake) hinzu
        buttons[1].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent snake) 
            {
                
            }
        });
        
        // Füge ActionListener zu Button 2 (Zahlenraten) hinzu
        buttons[2].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent zahlenraten) 
            {
                
            }
        });
        
        // Füge ActionListener zu Button 3 (4Gewinnt) hinzu
        buttons[3].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent viergewinnt) 
            {
                programmfenster.dispose();
                VierGewinnt.FunktionVierGewinnt();
            }
        });
        
        // Füge ActionListener zu Button 4 (Beenden) hinzu
        buttons[4].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent beenden) 
            {
                System.exit(0);
            }
        });
        
        // Setze das Startseiten-Panel als Inhalt des Hauptfensters
        programmfenster.setContentPane(startpanel);
    }
}
