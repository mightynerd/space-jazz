//spaceswag
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceJazz {

	public static void main(String[] args) {
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

					System.out.println(debug);
				}

			}
		});

		f.setVisible(true);
		f.requestFocus();
		p.requestFocus();
		t.start();
	}

}
