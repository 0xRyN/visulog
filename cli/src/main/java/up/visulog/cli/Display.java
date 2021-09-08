package up.visulog.cli;

import javax.swing.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Display extends JFrame {

    private JTextField textEdit;
    private JButton btn;
    private JPanel main;
    private JPanel text;
    private JPanel radioButtons;
    private JPanel errorMsg;
    private ButtonGroup radioButtonsGroup;
    private JLabel textEditLabel;
    private JLabel msgError = new JLabel("Veuillez verifer votre lien git.");
    private JLabel gL = new JLabel("Le lien est bon. Veuillez attendre que le telecharment se termine.");
    private JRadioButton circle;
    private JRadioButton bar;
    private JRadioButton list;

    public JTextField getTextEdit() {
        return textEdit;
    }

    public JButton getBtn() {
        return btn;
    }

    public JPanel getMain() {
        return main;
    }

    public Display() {
        this.setTitle("Visulog Premium");
        this.setSize(600,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.main = new JPanel();
        this.main.setLayout(new GridLayout(3,1));
        this.setContentPane(main);
        this.textEditLabel = new JLabel("Votre URL :");
        this.btn = new JButton("Analyser");
        this.textEdit = new JTextField("", 20);
        this.circle = new JRadioButton("Cercle", true);
        this.bar = new JRadioButton("Barres");
        this.list = new JRadioButton("Liste");
        this.errorMsg = new JPanel();
        /*this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(circle);
        this.radioButtonsGroup.add(bar);
        this.radioButtonsGroup.add(list);
        this.radioButtons = new JPanel();
        this.radioButtons.add(circle);
        this.radioButtons.add(bar);
        this.radioButtons.add(list);*/
        this.text = new JPanel();
        this.text.add(textEditLabel);
        this.main.add(text);
        this.text.add(textEdit);
        //this.main.add(radioButtons);
        this.main.add(errorMsg);
        this.textEdit.setHorizontalAlignment(SwingConstants.CENTER);
        this.textEdit.setMaximumSize(new Dimension(200,10));
        this.main.add(btn);
    }

    public void linkError(){
        this.errorMsg.add(msgError);
        this.validate();
    }

    public void goodLink(){
        this.errorMsg.add(gL);
        this.validate();
    }

}