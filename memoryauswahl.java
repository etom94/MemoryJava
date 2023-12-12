// Importieren der benötigten Klassen
package Java.MemoryJava;

import java.awt.Color;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Deklaration der Klasse memoryauswahl
public class memoryauswahl 
{
    public static JPanel 
    memoryauswahl = new JPanel(new GridBagLayout());                // JPanel mit GridBagLayout

    // Methode zum Hinzufügen eines ActionListeners zu einem Button
    private static void addActionListener(Button button, int schwirigkeit, JFrame programmfenster) 
    {
        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent schwirigkeitauswahl) 
            {
                // Aufrufen der Methode resetMemory() und memory() aus der Klasse Memory
                Memory.resetMemory();
                Memory.memory(programmfenster, schwirigkeit);
                
                // Entfernen des memoryauswahl-Panels aus dem programmfenster
                programmfenster.remove(memoryauswahl);
            }
        });
    }

    // Methode zur Erstellung des Memory-Auswahlfensters
    public static void memoryauswahlfenster(JFrame programmfenster) 
    {
        // Definition der Klassenvariablen
        int 
        anzahlbuttons = 4,                                              // Anzahl der Buttons
        panelwidth = 0,                                                 // Breite des Panels
        panelheight = 0;                                                // Höhe des Panels

        Button[] 
        buttons = new Button[anzahlbuttons];                            // Array mit Buttons

        String[] 
        benennung = {"Leicht", "Mittel", "Schwer", "Rangliste"};   // Array mit Bezeichnungen für die Buttons

        GridBagConstraints 
        constraints = new GridBagConstraints();                         // Objekt zur Steuerung der Layouts

        // Festlegen der Größe und Farbe des memoryauswahl-Panels
        memoryauswahl.setBounds(0, 0, programmfenster.getWidth(), programmfenster.getHeight());
        memoryauswahl.setBackground(new Color(200, 240, 220));
        panelwidth = memoryauswahl.getWidth();
        panelheight = memoryauswahl.getHeight();

        // Erstellung der Buttons und Hinzufügen zum memoryauswahl-Panel
        for(int i=0;i<anzahlbuttons;i++) 
        {
            buttons[i] = new Button(benennung[i]);
            constraints.gridy = i;
            constraints.ipadx = (int) (panelwidth*.15);
            constraints.ipady = (int) (panelheight*.025);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(0,0,30,0);
            buttons[i].setBackground(new Color(200, 170, 100).brighter().brighter());
            memoryauswahl.add(buttons[i],constraints);
        }

        // Hinzufügen eines ActionListeners zu jedem Button
        addActionListener(buttons[0], 10, programmfenster);
        addActionListener(buttons[1], 15, programmfenster);
        addActionListener(buttons[2], 25, programmfenster);

        buttons[3].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent viergewinnt) 
            {
                programmfenster.remove(memoryauswahl);
                Memory.resetMemory();
                endblidschirm.auflistung(programmfenster);
            }
        });

        // Setzen des memoryauswahl-Panels als Inhalt des programmfensters
        programmfenster.setContentPane(memoryauswahl);
    }

    public static void memoryauswahlreset() 
    {
        memoryauswahl = new JPanel(new GridBagLayout()); 
    }
}