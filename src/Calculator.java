import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class Calculator implements ActionListener {

    JFrame frame = new JFrame();
    JTextField currentInput = new JTextField();
    JTextField lastInput = new JTextField();
    JPanel buttonsPanel = new JPanel(); //all buttons except for C and <
    JPanel centerPanel = new JPanel();  //buttom panel and current input
    JPanel bottomPanel = new JPanel();  //C and < buttons
    JPanel topPanel = new JPanel(); //lastInput
    JButton[] numButtons = new JButton[10];
    JButton[] fnButtons = new JButton[8];
    JButton addButton = new JButton();
    JButton subButton = new JButton();
    JButton mulButton = new JButton();
    JButton divButton = new JButton();
    JButton decButton = new JButton();
    JButton eqlsButton = new JButton();
    JButton delButton = new JButton();
    JButton clrButton = new JButton();
    Font digitalFont = new Font("Unispace", Font.PLAIN,60);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        ImageIcon icon = new ImageIcon("Images/Calculator_512.png");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));
        frame.setTitle("Calculator");
        frame.setSize(400,600);
        frame.setMinimumSize(new Dimension(400,600));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(new Color(40,40,40));

        buttonsPanel.setBackground(new Color(40,40,40));
        buttonsPanel.setLayout(new GridLayout(4,4,10,10));

        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 0, 10, new Color(40,40,40)));

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createMatteBorder(-10, 10, 0, 10, new Color(40,40,40)));

        bottomPanel.setBackground(new Color(40,40,40));
        bottomPanel.setLayout(new GridLayout(1,2,10,10));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, new Color(40,40,40)));

        currentInput.setEditable(false);
        currentInput.setBorder(null);
        currentInput.setText("0");
        currentInput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        currentInput.setPreferredSize(new Dimension(380,80));
        currentInput.setBackground(new Color(40,40,40));
        currentInput.setFont(digitalFont);
        currentInput.setForeground(Color.WHITE);
        currentInput.setCaretColor(Color.WHITE);

        lastInput.setEditable(false);
        lastInput.setBorder(null);
        lastInput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        lastInput.setPreferredSize(new Dimension(380,30));
        lastInput.setBackground(new Color(40,40,40));
        lastInput.setFont(new Font("Unispace", Font.PLAIN,20));
        lastInput.setForeground(new Color(200,200,200));
        lastInput.setCaretColor(new Color(200,200,200));
        
        fnButtons[0] = addButton;
        fnButtons[1] = subButton;
        fnButtons[2] = mulButton;
        fnButtons[3] = divButton;
        fnButtons[4] = decButton;
        fnButtons[5] = eqlsButton;
        fnButtons[6] = delButton;
        fnButtons[7] = clrButton;

        ImageIcon delIcon = new ImageIcon("Images/delete.png"); // load the image to a imageIcon
        Image image2 = delIcon.getImage(); // transform it 
        Image newimg2 = image2.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        delIcon = new ImageIcon(newimg2);  // transform it back

        addButton.setText("+");
        addButton.setBackground(new Color(80,80,80));
        addButton.setForeground(new Color(255,170,0));

        subButton.setText("-");
        subButton.setBackground(new Color(80,80,80));
        subButton.setForeground(new Color(255,170,0));

        mulButton.setText("x");
        mulButton.setBackground(new Color(80,80,80));
        mulButton.setForeground(new Color(255,170,0));

        divButton.setText("/");
        divButton.setBackground(new Color(80,80,80));
        divButton.setForeground(new Color(255,170,0));

        decButton.setText(".");
        decButton.setBackground(new Color(80,80,80));
        decButton.setForeground(Color.WHITE);

        eqlsButton.setText("=");
        eqlsButton.setBackground(new Color(255,170,0));
        eqlsButton.setForeground(Color.WHITE);

        delButton.setIcon(delIcon);
        delButton.setBackground(new Color(80,80,80));
        delButton.setForeground(Color.WHITE);

        clrButton.setText("C");
        clrButton.setBackground(new Color(80,80,80));
        clrButton.setForeground(Color.WHITE);

        for(int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(digitalFont);
            numButtons[i].setFocusable(false);
            numButtons[i].setBackground(new Color(80,80,80));
            numButtons[i].setForeground(Color.WHITE);
            numButtons[i].setBorder(BorderFactory.createEmptyBorder());
        }

        for(int i = 0; i < 8; i++) {
            fnButtons[i].addActionListener(this);
            fnButtons[i].setFont(digitalFont);
            fnButtons[i].setFocusable(false);
            fnButtons[i].setBorder(BorderFactory.createEmptyBorder());
        }

        buttonsPanel.add(numButtons[7]);
        buttonsPanel.add(numButtons[8]);
        buttonsPanel.add(numButtons[9]);
        buttonsPanel.add(addButton);
        buttonsPanel.add(numButtons[4]);
        buttonsPanel.add(numButtons[5]);
        buttonsPanel.add(numButtons[6]);
        buttonsPanel.add(subButton);
        buttonsPanel.add(numButtons[1]);
        buttonsPanel.add(numButtons[2]);
        buttonsPanel.add(numButtons[3]);
        buttonsPanel.add(mulButton);
        buttonsPanel.add(decButton);
        buttonsPanel.add(numButtons[0]);
        buttonsPanel.add(eqlsButton);
        buttonsPanel.add(divButton);
        bottomPanel.add(clrButton);
        bottomPanel.add(delButton);
        centerPanel.add(buttonsPanel, BorderLayout.CENTER);
        centerPanel.add(currentInput, BorderLayout.NORTH);
        topPanel.add(lastInput);
        frame.add(topPanel, BorderLayout.NORTH);        
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == numButtons[i]) {
                if(currentInput.getText().equals("0")) {
                    currentInput.setText("");
                }
                currentInput.setText(currentInput.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == decButton) {
            currentInput.setText(currentInput.getText().concat("."));
        }

        try {            
            if(e.getSource() == addButton) {
                operator = '+';
                num1 = Double.parseDouble(currentInput.getText());
                lastInput.setText(operator + " " + currentInput.getText());
                currentInput.setText("");
            }
    
            if(e.getSource() == subButton) {
                operator = '-';
                num1 = Double.parseDouble(currentInput.getText());
                lastInput.setText(operator + " " +  currentInput.getText());
                currentInput.setText("");
            }
    
            if(e.getSource() == mulButton) {
                operator = '*';
                num1 = Double.parseDouble(currentInput.getText());
                lastInput.setText(operator + " " +  currentInput.getText());
                currentInput.setText("");
            }
    
            if(e.getSource() == divButton) {
                operator = '/';
                num1 = Double.parseDouble(currentInput.getText());
                lastInput.setText(operator + " " +  currentInput.getText());
                currentInput.setText("");
            }
    
            if(e.getSource() == eqlsButton) {
                num2 = Double.parseDouble(currentInput.getText());
                switch(operator) {
                    case'+':
                        result = num1 + num2;
                        lastInput.setText("=" + " " + String.valueOf(num2) + " " + operator + " " + String.valueOf(num1));
                        break;
    
                    case'-':
                        result = num1 - num2;
                        lastInput.setText("=" + " " + String.valueOf(num2) + " " + operator + " " + String.valueOf(num1));
                        break;
    
                    case'*':
                        result = num1 * num2;
                        lastInput.setText("=" + " " + String.valueOf(num2) + " " + operator + " " + String.valueOf(num1));
                        break;
    
                    case'/':
                        result = num1 / num2;
                        lastInput.setText("=" + " " + String.valueOf(num2) + " " + operator + " " + String.valueOf(num1));
                        break;
                    
                    default:
                        result = Integer.valueOf(currentInput.getText());
                        lastInput.setText("=" + " " + result);
                        break;
                }
                if(result == (int)result) {
                    DecimalFormat format = new DecimalFormat("0.#");
                    currentInput.setText(String.valueOf(format.format(result)));
                } else {
                    currentInput.setText(String.valueOf(result));
                }                
                num1 = result;
            }

        } catch (Exception a) {
            
        }

        if(e.getSource() == clrButton) {
            currentInput.setText("0");
            lastInput.setText("");
        }

        if(e.getSource() == delButton) {
            if (currentInput.getText().length() == 1) {
                currentInput.setText("0");
            } else {
                String newInput = currentInput.getText();
                currentInput.setText("");
                for(int i = 0; i < newInput.length() - 1; i++) {
                    currentInput.setText(currentInput.getText() + newInput.charAt(i));
                }
            }            
        }
    }
}