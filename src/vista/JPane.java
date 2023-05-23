package vista;
/**
 * 
 * @author KEVIN
 * @author ANGEL
 * @author SANTIAGO
 * @author SHARICK
 * 
 * Aquí lo que hemos hecho es crear unos jpain que nos permitiran mostrar unos pequeños mensajes 
 * al usuario
 *
 */

public class JPane extends javax.swing.JOptionPane {

	public void mostrarInformacion(String mensaje) {
		JPane.showMessageDialog(null, mensaje, "Ventana de Informacion", JPane.INFORMATION_MESSAGE);
	}

//	public void mostrarError(String mensaje) {
//		JPane.showMessageDialog(null, mensaje, "Ventana de Advertencia", JPane.ERROR_MESSAGE);
//	}

	public void mostrarError(String mensaje) {
		JPane.showMessageDialog(null, mensaje, "Ventana de Error", JPane.ERROR_MESSAGE);
	}

	public void mostrarOk(String mensaje) {
		JPane.showMessageDialog(null, mensaje, "Oyeeeee", JPane.PLAIN_MESSAGE);
	}

	public int mostrarYoN(String mensaje) {
		return JPane.showConfirmDialog(null, mensaje, "Wait...", JPane.YES_NO_OPTION,
				JPane.QUESTION_MESSAGE);
	}

	public void mostrarYes_Option(String mensaje) {
		JPane.showMessageDialog(null, mensaje, "", JPane.YES_OPTION);

	}
	
}
