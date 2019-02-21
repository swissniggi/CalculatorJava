package Calculator;
import java.awt.event.*;

class InsertAction implements ActionListener {
	
	InsertAction(CalculatorPanel calculatorPanel) {
		panel = calculatorPanel;
	}
	
	public void actionPerformed(ActionEvent event) {
		String input = event.getActionCommand();
		
		if (panel.start) {
			panel.display.setText("");
			panel.start = false;
		}
		
		if (input == "Pi" && panel.display.getText() == "") {
			panel.display.setText("" + Math.PI);
		} else {
			panel.display.setText(panel.display.getText() + input);
		}
	}
	
	private CalculatorPanel panel;
}