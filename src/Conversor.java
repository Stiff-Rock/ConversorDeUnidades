import java.awt.Color;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Conversor extends JFrame {

	private static final long serialVersionUID = 1L;

	private String pattern = "[0-9][0-9]?(\\.[0-9][0-9]?)?";

	private JPanel contentPane;
	private JTextField tfMeters;
	private JLabel lblYards;
	private JLabel lblMiles;
	private JLabel lblInches;
	private JLabel lblWarning;

	public Conversor() {
		setTitle("Conversor de unidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfMeters = new JTextField();
		tfMeters.setHorizontalAlignment(SwingConstants.CENTER);
		tfMeters.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String inputText = tfMeters.getText();

				if(inputText.isEmpty())
					return;
					
				
				Pattern inPattern = Pattern.compile(pattern);
				Matcher inMatcher = inPattern.matcher(inputText);

				if (!inMatcher.matches()) {
					lblWarning.setText("<html>Formato de unidad incorrecto.</html>");
				} else {
					float unit = Float.parseFloat(inputText);

					if (unit > 10) {
						lblWarning.setText("<html>La unidad no puede ser mayor que 10.</html>");
					} else if (unit <= 0) {
						lblWarning.setText("<html>La unidad debe ser mayor que 0.</html>");
					} else {
						lblWarning.setText("");
						makeConversion(unit);
					}
				}
			}
		});

		tfMeters.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Metros",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfMeters.setBounds(53, 112, 117, 36);
		contentPane.add(tfMeters);
		tfMeters.setColumns(10);

		JLabel lblArrow = new JLabel(">>");
		lblArrow.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArrow.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrow.setBounds(201, 123, 31, 14);
		contentPane.add(lblArrow);

		lblYards = new JLabel("");
		lblYards.setHorizontalAlignment(SwingConstants.CENTER);
		lblYards.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Yardas",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblYards.setBounds(260, 38, 117, 36);
		contentPane.add(lblYards);

		lblMiles = new JLabel("");
		lblMiles.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiles.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Millas",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblMiles.setBounds(260, 112, 117, 36);
		contentPane.add(lblMiles);

		lblInches = new JLabel("");
		lblInches.setHorizontalAlignment(SwingConstants.CENTER);
		lblInches.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pulgadas",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblInches.setBounds(260, 186, 117, 36);
		contentPane.add(lblInches);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(new Color(255, 0, 0));
		lblWarning.setBounds(53, 160, 117, 36);
		contentPane.add(lblWarning);
	}

	private void makeConversion(float unit) {

		lblYards.setText(String.format("%.2f", unit * 1.094));

		lblMiles.setText(String.format("%.2f", unit / 1609));

		lblInches.setText(String.format("%.2f", unit * 39.37));
	}
}
