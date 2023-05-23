
package vista;

import vista.Ventanaprincipal;
/**
 * Panel destinado a que desde una sola ventana podamos llamar a todas
 * @author Kevin
 * @author Gabriel 
 */

public class Ventana_general {

	private Ventanaprincipal v_principal;
	private JPane jp;

	public Ventana_general() {

		v_principal = new Ventanaprincipal();
		jp = new JPane();

	}


	public JPane getJp() {
		return jp;
	}


	public void setJp(JPane jp) {
		this.jp = jp;
	}


	public Ventanaprincipal getV_principal() {
		return v_principal;
	}

	public void setV_principal(Ventanaprincipal v_principal) {
		this.v_principal = v_principal;
	}

	

}
