package Calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.*;

class CalculatorPanel extends JPanel {
	
	public CalculatorPanel() {
		setLayout(new BorderLayout());
		
		result = 0;
		lastCommand = "=";
		start = true;
		
		// Display erstellen
		display = new JButton("0");
		display.setPreferredSize(new Dimension(500, 40));
		display.setFont(new Font("Arial", Font.PLAIN, 18));
		display.setEnabled(false);
		UIManager.put("Button.disabledText", Color.black);
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction(this);
		ActionListener command = new CommandAction(this);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5));
		
		// JSON auslesen
		JSONObject buttons = new JSONObject(JSON.getJSON());
		JSONArray arrButtons = buttons.getJSONArray("buttons");
		
		// Buttons erstellen
		for (int i = 0; i < arrButtons.length(); i++) {
			String text = arrButtons.getJSONObject(i).getString("text");
			String action = arrButtons.getJSONObject(i).getString("action");
			String color = arrButtons.getJSONObject(i).getString("color");
			
			if (action.contentEquals("insert")) {
				addButton(text, insert, color);
			} else if (action.contentEquals("command")) {
				addButton(text, command, color);
			} else {
				addButton(text, null, null);
			}
		}
		
		add(panel, BorderLayout.CENTER);
	}
	
	// erstellt Buttons
	private void addButton(String label, ActionListener listener, String color) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		
		// Hintergrundfarbe definieren
		if (color != null) {
			if (color.contentEquals("Y")) {
				button.setBackground(Color.yellow);
			} else if (color.contentEquals("G")) {
				button.setBackground(Color.green);
			} else if (color.contentEquals("O")) {
				button.setBackground(Color.orange);
			} else if (color.contentEquals("P")) {
				button.setBackground(Color.pink);
				button.setFont(new Font("SegoeUISymbol", Font.BOLD, 16));
			}
		}
		panel.add(button);
	}
	
	private JPanel panel;
	protected JButton display;	
	protected double result;
	protected String lastCommand;
	protected boolean start;
}
