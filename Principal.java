

package Principal;


import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Principal {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Moeda> cmb;
	private JLabel lbl;
	
	public enum Moeda{
		real_dolar,
		real_euro,
		real_libra,
		dolar_real,
		euro_real,
		libra_real
	}
	
	public double dolar = 4.95;
	public double euro = 5.34;
	public double libra = 6.23;
	
	public double valorInput = 0.00;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 11, 123, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moeda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moeda.values()));
		cmb.setBounds(10, 59, 123, 22);
		frame.getContentPane().add(cmb);
		
		// botao
		btn = new JButton("Converter");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Converter();
			}
		});
		btn.setBounds(161, 59, 89, 23);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(161, 11, 89, 20);
		frame.getContentPane().add(lbl);
	}
	
	public void Converter() {
		 //validacao de valores
		if(Validar(txt.getText())) {
			Moeda moeda = (Moeda) cmb.getSelectedItem();
			
			switch (moeda) {
			
			case real_dolar: 
				ReaisPara(dolar);
				break;
			case real_euro: 
				ReaisPara(euro);
				break;
			case real_libra: 
				ReaisPara(libra);
				break;
			case dolar_real: 
				MoedaParaReais(dolar);
				break;
			case euro_real: 
				MoedaParaReais(euro);
				break;
			case libra_real: 
				MoedaParaReais(libra);
				break;
				default:
				throw new IllegalArgumentException("Unexpected value: " + moeda);
		
		}		
		}
	}

	
	public void ReaisPara(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Arredondar(res));
	}
	
	public void MoedaParaReais(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Arredondar(res));
	}
	
	// arredondar decimais
	public String Arredondar(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	// validar input
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Inserir Um Valor Valido !!");
			return false;
		}
	}
}

