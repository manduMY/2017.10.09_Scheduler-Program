import javax.swing.JFrame;

public class BtnMain {

	public static void main(String[] args) {

		JFrame fr = new JFrame("ดÞทย");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BtnCalendar calen = new BtnCalendar();
		fr.getContentPane().add(calen);

		fr.pack();
		fr.setVisible(true);
	}
}
