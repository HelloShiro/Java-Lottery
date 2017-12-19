package game;

import java.util.*;

import javax.swing.JTextField;

public class Logic {
	
	static public void setRdNum(ArrayList<Integer> intTmpList,int intSize) {
		Random random = new Random();
		while(intTmpList.size() < intSize) {
			int num = random.nextInt(49)+1;
			if(!intTmpList.contains(num)) {
				intTmpList.add(num);
			}else if(intTmpList.contains(num)){
				continue;
			}
		}
	}
	
	static void cleanTextArray(ArrayList<Integer> list, JTextField[] array) {
		list.clear();
		for(JTextField text: array) {
			text.setText("");
		}
	}

	
	static void checkBingo(ArrayList<int[]> myListArray, ArrayList<Integer> bingoList ) {
		Screen.model2.clear();
		int j;
		for(j=0; j<myListArray.size();j++) {
			int bingoCount = 0;
			boolean sameSpec = false;
			for(int i=0; i< myListArray.get(j).length; i++ ) {
				if(myListArray.get(j)[i] == bingoList.get(i)) {
					bingoCount++;
				}
				if(myListArray.get(j)[i] == Screen.intSpec) {
					sameSpec = true;
				}
			}
			if(bingoCount == 6) {
				Screen.model2.addElement(String.format("第%d組: 頭獎\n", j+1));
				Money.intMyMoney += 1000000000;
			}else if(bingoCount == 5 && sameSpec) {
				Screen.model2.addElement(String.format("第%d組: 二獎\n", j+1));
				
				Money.intMyMoney += 500000000;
			}else if(bingoCount == 5) {
				Screen.model2.addElement(String.format("第%d組: 三獎\n", j+1));
				Money.intMyMoney += 10000000;
			}else if(bingoCount == 4 && sameSpec) {
				Screen.model2.addElement(String.format("第%d組: 四獎\n", j+1));
				Money.intMyMoney += 5000000;
			}else if(bingoCount == 4) {
				Screen.model2.addElement(String.format("第%d組: 伍獎\n", j+1));
				Money.intMyMoney += 1000000;
			}else if(bingoCount == 3 && sameSpec) {
				Screen.model2.addElement(String.format("第%d組: 六獎\n", j+1));
				Money.intMyMoney += 50000;
			}else if(bingoCount == 2 && sameSpec) {
				Screen.model2.addElement(String.format("第%d組: 七獎\n", j+1));
				Money.intMyMoney += 1000;
				Screen.lblMyMoney.setText(String.format("%d", Money.intMyMoney));
			}else if(bingoCount == 3) {
				Screen.model2.addElement(String.format("第%d組: 普獎\n", j+1));
				Money.intMyMoney += 500;
				Screen.lblMyMoney.setText(String.format("%d", Money.intMyMoney));
			}else{
				Screen.model2.addElement(String.format("第%d組: 沒中", j+1));
			}
		}
	}
}
