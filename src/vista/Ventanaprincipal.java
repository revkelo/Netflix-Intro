package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventanaprincipal extends JFrame {

	JButton b_iniciar;
	JButton b_parar;
	JButton b_salir;
	JPanel fondo, color,bloqueador;
	JLabel minutos, listo, logo;

	public Ventanaprincipal() {
		setSize(500, 500);
//		setLocation(2000, 100);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Img/icon.png"));

		setLocationRelativeTo(null);

		Font font = new Font("Agency FB", Font.BOLD, 14);

		logo = new JLabel();
		logo.setSize(300, 160);
		logo.setLocation(85, -30);
		ImageIcon img = new ImageIcon("Img/logo.png");
		Icon icono = new ImageIcon(
				img.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
		logo.setIcon(icono);

		

		b_iniciar = new JButton("Iniciar");
		b_iniciar.setBorderPainted(false);
		b_iniciar.setBackground(Color.black);
		b_iniciar.setForeground(Color.white);
		b_iniciar.setFont(font);
		b_iniciar.setFocusable(false);
		b_iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		b_iniciar.setBounds(110, 100, 250, 50);
		b_iniciar.setActionCommand("INICIAR");
		b_iniciar.setVisible(true);

		b_parar = new JButton("Parar");
		b_parar.setBorderPainted(false);
		b_parar.setBackground(Color.black);
		b_parar.setForeground(Color.white);
		b_parar.setFont(font);
		b_parar.setFocusable(false);
		b_parar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		b_parar.setBounds(110, 200, 250, 50);
		b_parar.setActionCommand("PARAR");
		b_parar.setVisible(true);

		b_salir = new JButton("Salir");
		b_salir.setBorderPainted(false);
		b_salir.setBackground(Color.black);
		b_salir.setForeground(Color.white);
		b_salir.setFont(font);
		b_salir.setFocusable(false);
		b_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		b_salir.setBounds(110, 300, 250, 50);
		b_salir.setActionCommand("SALIR");
		b_salir.setVisible(true);

		fondo = new JPanel();
		fondo.setBounds(0, 0, 500, 500);
		fondo.setBackground(Color.black);

		color = new JPanel();
		color.setBounds(180, 430, 20, 20);
		color.setBackground(Color.white);
		color.setVisible(false);
		
		minutos = new JLabel("20");
		minutos.setBounds(400, 430, 20, 20);
		minutos.setForeground(Color.white);
		minutos.setVisible(false);

		listo = new JLabel("Capturando intro");
		listo.setBounds(50, 430, 120, 20);
		listo.setForeground(Color.white);
		listo.setVisible(false);
		
		add(listo);
		add(color);
		add(minutos);
		add(logo);
		add(b_parar);
		add(b_iniciar);
		add(b_salir);
		add(fondo);

	}

	public JButton getB_iniciar() {
		return b_iniciar;
	}

	public void setB_iniciar(JButton b_iniciar) {
		this.b_iniciar = b_iniciar;
	}

	public JButton getB_parar() {
		return b_parar;
	}

	public void setB_parar(JButton b_parar) {
		this.b_parar = b_parar;
	}

	public JButton getB_salir() {
		return b_salir;
	}

	public void setB_salir(JButton b_salir) {
		this.b_salir = b_salir;
	}

	public JPanel getFondo() {
		return fondo;
	}

	public void setFondo(JPanel fondo) {
		this.fondo = fondo;
	}

	public JPanel getColor() {
		return color;
	}

	public void setColor(JPanel color) {
		this.color = color;
	}

	public JLabel getMinutos() {
		return minutos;
	}

	public void setMinutos(JLabel minutos) {
		this.minutos = minutos;
	}

	public JLabel getListo() {
		return listo;
	}

	public void setListo(JLabel listo) {
		this.listo = listo;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}
	


}
