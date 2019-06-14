package Model;

/**
 *	The backend of the Calculator that does basic calculator operations.
 * 
 */

/**
 * @author Patrick
 *
 */
public final class Calculator {
	/**
	 * Contains the value being displayed on the calculator in
	 * Stringbuilder form to be passed to the displayDouble
	 */
	protected StringBuilder build;
	/**
	 * Contains the value being displayed on the calculator
	 * screen.
	 */
	private String displayDouble = "0.0";
	/**
	 * Holds the current value being adjusted for calucation,
	 * i.e., the value being modified. 
	 */
	private Double currentValue;
	/**
	 * Holds the last input from the user.
	 */
	private Double lastValue;
	/**
	 * Holds the total value set aside after an arithmetic
	 * operation is called.
	 */

	private Double currentTotal;
	/**
	 * Checks if the last button pressed was an arithmetic
	 * operation.
	 */
	private boolean newValue = false;

	/**
	 * Saves the last arithmetic operation button pressed
	 * so the equal operation can operate correctly.
	 */
	private String lastOp = "+";

	/**
	 * Checks what was the last button pressed was an arithmetic
	 * operation to keep track of button order press (in case
	 * two arithmetic operations are pressed in a row.)
	 */
	private boolean lastPressIsOp = false;
	
	private boolean equalsFlag = false;

	
	/**
	 * Starts the calculator with no value. A constructor may
	 * not be necessary, but made one just in case. 
	 */
	public Calculator()	{
		currentValue = 0.0;
		currentTotal = 0.0;
		lastValue = 0.0;
		build = new StringBuilder();
	}

	/**
	 * Response to user actions and performs the backend operations.
	 * TODO: ABE - the buttonPressed button is basically the button
	 * that was pressed on the calculator. 
	 * 
	 * @param buttonPressed - the button pressed on the virtual
	 * calculator or through the keyboard.
	 * 
	 */
	public final void userButtons(final String buttonPressed){
		System.out.println("ButtonPressed: " + buttonPressed);	//	TODO: Feel free to delete this.
		
		//	this check is to see if an arithmetic button was pressed, thus meaning
		//	a new integer needs to be built as the secondary value.
		if (newValue == true)	{
			lastValue = currentValue;
			currentValue = 0.0;
			build.setLength(0);
			newValue = false;
		}
		switch(buttonPressed)	{
		
		
		//	clears the calculator's memory.
		case "AC":
			currentValue = 0.0;
			currentTotal = 0.0;
			lastValue = 0.0;
			build = new StringBuilder();
			lastOp = "+";
			displayDouble = currentValue.toString();
			lastPressIsOp = false;
			break;
			
		//	converts positive to negative and vice
		//	versa
		case "+/-":
			if (build.toString().contains("-"))	{
				build.deleteCharAt(0);
			}	else	{
				build.insert(0, "-");
			}
			break;
			
		//	The next values are the number buttons.
		case "0":
			build.append("0");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "1":
			build.append("1");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "2":
			build.append("2");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "3":
			build.append("3");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "4":
			build.append("4");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "5":
			build.append("5");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "6":
			build.append("6");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "7":
			build.append("7");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "8":
			build.append("8");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case "9":
			build.append("9");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
		case ".":
			if(build.length() == 0)	{
				build.append("0");
				lastPressIsOp = false;
			}
			build.append(".");
			displayDouble = build.toString();
			lastPressIsOp = false;
			break;
			
		//	If arithmetic buttons are pressed, they
		//	require the same behavior up until they
		//	are passed to the calculate method.
		case "+":
		case "-":
		case "*":
		case "/":
		case "=":
			if (!lastPressIsOp)	{
				if(build.length() > 0 && !(build.toString().equals("-")))
				{
					currentValue = Double.parseDouble(build.toString());
				}
				else
				{
					currentValue = lastValue;
				}
				calculate(buttonPressed);
			}	else	{
				if (buttonPressed != "=" && buttonPressed != lastOp){
					//	undo();
					lastOp = buttonPressed;
					currentValue = lastValue;
				}
				break;
			}
		}
		if (buttonPressed == "="){
			equalsFlag = true;
		} else	{
			equalsFlag = false;
		}
	}

	
	/**
	 * The portion of the calculator that does the calculations. Note that
	 * the calculations performed are not based on the button just pressed,
	 * but the last arithmetic value pressed before. 
	 * 
	 * @param buttonPressed - the button that was pressed.
	 */
	public final void calculate(final String buttonPressed)	{
		if (equalsFlag){
			switch(buttonPressed){
			case "+":
			case "-":
			case "*":
			case "/":
				currentValue = 0.0;
				lastOp = "+";
			}
		}
		switch(lastOp)	{
		case "+":
			currentTotal = currentTotal + currentValue;
			break;
		case "-":
			currentTotal = currentTotal - currentValue;
			break;
		case "*":
			currentTotal = currentTotal * currentValue;
			break;
		case "/":
			currentTotal = currentTotal / currentValue;
			break;
		}
		if(buttonPressed != "=")	{
			lastPressIsOp = true;
			lastOp = buttonPressed;
		}
		displayDouble = currentTotal.toString();
		newValue = true;
	}

	/**
	 * Method is for copying any integer in the component fields.
	 * 
	 * @param fieldCopy - the integer being copied to calculator.
	 */
	public void copiedValue(final String fieldCopy)	{
		lastPressIsOp = false;
		System.out.println("Copied from Field: " + fieldCopy);
		build.setLength(0);
		build.append(fieldCopy);
		displayDouble = build.toString();
		currentValue = Double.parseDouble(build.toString());
	}
	
	public String getTotal() {
		return currentTotal.toString();
	}

	
	/**
	 * 
	 * 
	 * 
	 * 
	 * ALL TEST FUNCTIONALITY IS BELOW. FEEL FREE TO DELETE IT WITH THE EXCEPTION OF
	 * THE LAST BRACKET.
	 * 
	 * 
	 * 
	 * 
	 */
	public void display()	{
		System.out.println("(backend)Build: " + build);
		System.out.println("Button Pressed was Arithmetic Op: " + lastPressIsOp);
		System.out.println("Last value is: " + lastValue);
		System.out.println("(backend)CurrentValue: " + currentValue);
		System.out.println("(backend)CurrentTotal: " + currentTotal);
		System.out.println("Displayed on Screen: " + displayDouble + "\n");
	}
}


