//
//	Project space-jazz
//	Licenced under the Genereal Public Licence v2
//
//	SpaceJazz.java
//	jazz? yes.
//

import javax.sound.sampled.AudioPermission;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceJazz {

	static SoundPlayer sound;
	
	public static void main(String[] args) {
		sound = new SoundPlayer("D:\\Audio\\Random\\Near Light Short WAV.wav");
		
		JFrame f = new JFrame("DEBUG TEST");
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final InputManager input = new InputManager();
		JPanel p = new JPanel();
		p.addKeyListener(input);
		f.add(p);
		p.requestFocus();

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true)

				{
					String debug = "";

					if (input.IsKeyPressed(InputManager.Key.ArrowRight))
					{
						sound.Stop();
						System.out.println("right");
					}
					
					if (input.IsKeyDown(InputManager.Key.ArrowDown)) {
						debug += " ArrowDown";
					}

					if (input.IsKeyDown(InputManager.Key.ArrowUp)) {
						debug += " ArrowUp";
					}

					if (input.IsKeyDown(InputManager.Key.ArrowRight)) {
						debug += " ArrowRight";
					}

					if (input.IsKeyDown(InputManager.Key.ArrowLeft)) {
						debug += " ArrowLeft";
					}

					if (input.IsKeyDown(InputManager.Key.SpaceBar)) {
						debug += " SpaceBar";
					}

					if (debug != "") {
						//System.out.println(debug);
					}
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		f.setVisible(true);
		f.requestFocus();
		p.requestFocus();
		t.start();
		
		
		sound.Play();
	}

}
