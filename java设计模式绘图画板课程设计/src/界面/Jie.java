package 界面;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Jie extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jie frame = new Jie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 179, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("基本图形：");
		label.setBounds(10, 10, 73, 15);
		panel.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("三角形");
		comboBox.addItem("矩形");
		comboBox.addItem("圆形");
		comboBox.setBounds(68, 6, 81, 23);
		panel.add(comboBox);
	}
}
