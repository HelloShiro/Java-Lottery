package game;

public class Money {
	public static int intMyMoney = 5000000,intSavedCount;
	public static void lossM() {
		intMyMoney -= 100;
		Screen.lblMyMoney.setText(String.format("%d", intMyMoney));
		
	}
	public static void rtnM() {
		intMyMoney += Screen.intSavedList.size() * 100;
		Screen.lblMyMoney.setText(String.format("%d", intMyMoney));
	}
}
