package controlador;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import vista.Ventana_general;

public class Controlador implements ActionListener {

	Ventana_general vp;
	private int width;
	private int height;
	boolean ciclo1 = true, ciclo2 = true;

	public Controlador() {

		vp = new Ventana_general();
		asignarOyentes();

	}

	public void iniciar() {

		File file21 = new File("V2.txt");
		if (file21.exists()) {
			vp.getJp()
			.mostrarInformacion("Ya se esta ejecutando la aplicacion");
			System.exit(0);
		} else {
			 try {
				file21.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    Runtime.getRuntime().addShutdownHook(new Thread() {
			        public void run() {
			            file21.delete();
			        }
			    });

			vp.getV_principal().setVisible(true);
			vp.getJp()
					.mostrarInformacion("Bienvenido" + "\n" + "Esta aplicacion solo funciona para pantallas 1080" + "\n"
							+ "1)El netflix tiene que estar en pantalla completa" + "\n"
							+ "2)Verifique si captura su pantalla en la carpeta de la app netflix" + "\n"
							+ "3)Disfrute ver sus series sin las intro");
		}

	}

	public void asignarOyentes() {

		vp.getV_principal().getB_salir().addActionListener(this);
		vp.getV_principal().getB_iniciar().addActionListener(this);
		vp.getV_principal().getB_parar().addActionListener(this);
	}

	public void capturar() {
		Rectangle rectangleTam = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		try {
			int vueltas = 0;
			while (ciclo2 == true) {
				if (vueltas == 10) {
					vueltas = 0;
					borrar();
				}
				Robot robot = new Robot();
				// tomamos una captura de pantalla( screenshot )
				BufferedImage bufferedImage = robot.createScreenCapture(rectangleTam);

				String nombreFichero = "NETFLIX/caputura" + vueltas + ".jpg";

				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("YA  captura  " + vueltas);
				FileOutputStream out = new FileOutputStream(nombreFichero);

				// esbribe la imagen a fichero
				ImageIO.write(bufferedImage, "jpg", out);

				// Cargar la imagen original en un BufferedImage
				BufferedImage originalImage = ImageIO.read(new File("NETFLIX/caputura" + vueltas + ".jpg"));

				// Recortar la imagen
				int x = 1760;
				int y = 900;
				int width = 100;
				int height = 30;
				BufferedImage croppedImage = originalImage.getSubimage(x, y, width, height);

				// Guardar la imagen recortada en un archivo
				ImageIO.write(croppedImage, "jpg", new File("NETFLIX/Recortar/intro" + vueltas + ".jpg"));

				// Imprimir el resultado de la comparación
				if (comparador(vueltas) == true) {
//					System.out.println("Las imágenes son iguales");
					click();
					borrar();
					ciclo2 = true;
//					System.out.println("Termino ciclo");
					break;
				} else {
//					System.out.println("Las imágenes son diferentes");
				}

				vueltas++;
			}

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean comparador(int vueltas) throws IOException {
		boolean ciclo = false;
		BufferedImage img1 = ImageIO.read(new File("NETFLIX/Recortar/intro.jpg"));
		BufferedImage img2 = ImageIO.read(new File("NETFLIX/Recortar/intro" + vueltas + ".jpg"));

		int w1 = img1.getWidth();
		int w2 = img2.getWidth();
		int h1 = img1.getHeight();
		int h2 = img2.getHeight();
		if ((w1 != w2) || (h1 != h2)) {

		} else {
			long diff = 0;
			for (int j = 0; j < h1; j++) {
				for (int i = 0; i < w1; i++) {
					// Getting the RGB values of a pixel
					int pixel1 = img1.getRGB(i, j);
					Color color1 = new Color(pixel1, true);
					int r1 = color1.getRed();
					int g1 = color1.getGreen();
					int b1 = color1.getBlue();
					int pixel2 = img2.getRGB(i, j);
					Color color2 = new Color(pixel2, true);
					int r2 = color2.getRed();
					int g2 = color2.getGreen();
					int b2 = color2.getBlue();
					// sum of differences of RGB values of the two images
					long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
					diff = diff + data;
				}
			}
			double avg = diff / (w1 * h1 * 3);
			double percentage = (avg / 255) * 100;
//			System.out.println("Difference: " + percentage);
			if (percentage <= 6) {
				ciclo = true;
			} else {
//				System.out.println("Son diferentes");
				ciclo = false;
			}
		}
		return ciclo;
	}

	public void borrar() {
		try {
			// Especifica la ruta de la carpeta
			String capturas1 = "NETFLIX";
			String recorte1 = "NETFLIX/Recortar";

			File carpeta1 = new File(capturas1);
			File carpeta2 = new File(recorte1);

			// Obtén una lista de todos los archivos en la carpeta
			File[] archivos1 = carpeta1.listFiles();
			File[] archivos2 = carpeta2.listFiles();

			// Recorre la lista de archivos y borra solo las imágenes
			for (File file : archivos1) {
				if (file.isFile()) {
					String fileName = file.getName();
					if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
							|| fileName.endsWith(".gif")) {
						file.delete();
					}
				}
			}

			for (File file : archivos2) {
				if (file.isFile()) {
					String fileName = file.getName();

					if (!fileName.equals("intro.jpg")) {
						file.delete();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click() {
		try {
			// Crea una nueva instancia de la clase Robot
			Robot robot = new Robot();

			// Obtén la posición actual del cursor
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();

			// Mueve el cursor a la posición deseada
			int x = 1760;
			int y = 910;
			robot.mouseMove(x, y);

			// Haz clic en la posición deseada
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			// Mueve el cursor de regreso a su posición original
			robot.mouseMove((int) mousePoint.getX(), (int) mousePoint.getY());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		if (comando.equals("INICIAR")) {

			new Thread(new Runnable() {
				public void run() {

					ciclo1 = true;
					ciclo2 = true;
					vp.getV_principal().getListo().setVisible(true);
					vp.getV_principal().getColor().setVisible(true);
					vp.getV_principal().getMinutos().setVisible(false);
					vp.getV_principal().getB_iniciar().setVisible(false);
					vp.getV_principal().repaint();

					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					double width1 = screenSize.getWidth();
					double height1 = screenSize.getHeight();

					width = (int) width1;
					height = (int) height1;
					vp.getJp().mostrarInformacion("Su pantalla es " + height);

					if (width == 1920 && height == 1080) {

						while (ciclo1 == true) {
							borrar();
							capturar();
							try {
								Thread.sleep(7 * 1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

					} else if (width == 1280 && height == 720) {
						vp.getJp().mostrarInformacion("No hay soporte para este tipo de pantallas");

					} else {
						vp.getJp().mostrarInformacion("No hay soporte para este tipo de pantallas");
					}
				}
			}).start();

		}
		if (comando.equals("PARAR")) {

			vp.getV_principal().getMinutos().setText("0");
			vp.getV_principal().getListo().setVisible(false);
			vp.getV_principal().getColor().setVisible(false);
			vp.getV_principal().getMinutos().setVisible(false);
			vp.getV_principal().getB_iniciar().setVisible(true);
			ciclo2 = false;
			ciclo1 = false;
			borrar();

		}
		if (comando.equals("SALIR")) {
			int op = vp.getJp().mostrarYoN("Desea salir?");
			if (op == 0) {
				File file21 = new File("V2.txt");
				file21.delete();
				System.exit(0);

			} else if (op == 1) {

			}
		}
	}
}
