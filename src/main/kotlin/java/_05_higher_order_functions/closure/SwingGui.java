package java._05_higher_order_functions.closure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGui {
    public static void main(String[] args) {
        var window = new JFrame("CSD215 Lab 4");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 300);

        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        // Add label
        var label = new JLabel("Hello, World!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        window.getContentPane().add(label);

        var button = makeButton(label);
        window.getContentPane().add(button);


        window.setVisible(true);
    }

    private static JButton makeButton(JLabel label) {
        var button = new JButton("Click me");

        // var n = 0;  // See note in addActionListener below
        final var n = new int[] {0};  // This is a workaround to the problem of n not being effectively final


        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Before Java 8:
        // button.addActionListener(new MyButtonActionListener());

        // After Java 8:
        // button.addActionListener(e -> System.out.println("You clicked the button!"));

        button.addActionListener((e) -> {
            // n += 1;  // This doesn't work because n is not effectively final
            n[0] += 1;  // This works because n is a reference to an array, and the array is effectively final
                        // (i.e., the reference to the array cannot be changed, but the contents of the array can)

            label.setText("You clicked " + n[0] + " times!");

        });
        return button;
    }

    // Before Java 8:
    private static class MyButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You clicked the button!");
        }
    }

}
