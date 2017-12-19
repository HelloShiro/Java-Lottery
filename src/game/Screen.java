package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Screen extends JFrame{
	
	private JLabel lblCrtNum ,lblMoney, lblBingoNum, lblBfrSort,lblAfrSort,lblSpecNum;
	private JTextArea taSeTsuMe;
	public static JLabel lblBingoMsg,lblMyMoney;
	private JButton btnSave,btnAuto,btnAuto10,btnClr,btnBingo,btnClrList;
	private JTextField[] myTextArray,bingoTextArray,bingoTextArraySort;
	private JButton btnArray[][];	
	private JScrollPane scroll,scroll2; private JList listBox,listBox2;
	private DefaultListModel<String> model;
	public static DefaultListModel<String> model2;
	private ArrayList<Integer> intBingoList,intTextList;
	public static ArrayList<int[]> intSavedList;
	private int[] intSavedArray;

	public static int intSpec; 
	
	public static void main(String[] args) {

		Screen s = new Screen();
		s.setLayout(null);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setSize(1050, 800);
		s.setVisible(true);
		
	}

	public Screen() {
			setMyTextArray();
			setBingoTextArray();
			setBtn();
			setLbl();
			setBtnArray();
			setListBox();
			setListResult();
		}
	public void setLbl() {
		lblCrtNum = new JLabel("口袋號碼");
		lblCrtNum.setBounds(600, 20, 70, 28);
		lblCrtNum.setFont(new Font("微軟正黑體",Font.BOLD,17));
		lblBingoNum = new JLabel("獎號");
		lblBingoNum.setBounds(100, 340, 70, 28);
		lblBingoNum.setFont(new Font("微軟正黑體",Font.BOLD,17));
		lblMoney = new JLabel("錢包: $");
		lblMoney.setBounds(50, 550, 180, 60);
		lblMoney.setFont(new Font("微軟正黑體",Font.BOLD,45));
		lblBingoMsg = new JLabel("中獎訊息");
		lblBingoMsg.setBounds(600, 270, 100, 60);
		lblBingoMsg.setFont(new Font("微軟正黑體",Font.BOLD,20));
		lblBfrSort = new JLabel("開出順序");
		lblBfrSort.setBounds(30, 380, 70, 28);
		lblBfrSort.setFont(new Font("微軟正黑體",Font.BOLD,17));
		lblAfrSort = new JLabel("大小順序");
		lblAfrSort.setBounds(30, 440, 70, 28);
		lblAfrSort.setFont(new Font("微軟正黑體",Font.BOLD,17));
		lblSpecNum = new JLabel("特別號");
		lblSpecNum.setBounds(463, 340, 70, 28);
		lblSpecNum.setFont(new Font("微軟正黑體",Font.BOLD,17));
		taSeTsuMe = new JTextArea();
		taSeTsuMe.setEditable(false);
		taSeTsuMe.setBounds(760, 330, 270, 380);
		taSeTsuMe.setFont(new Font("微軟正黑體",Font.BOLD,17));
		taSeTsuMe.setText("六碼相同: 頭獎($1,000,000,000,000)\n\n任五碼+特別號: 二獎($500,000,000)\n\n"
			+ "任五碼: 三獎($100,000,000)\n\n任四碼+特別號: 四獎($10,000,000)\n\n"
				+ "任四碼: 五獎($2,000,000)\n\n任三碼+特別號: 六獎($500,000)\n\n"
				+ "任兩碼+特別號: 七獎($1000)\n\n任三碼: 普獎($500)");
		lblMyMoney = new JLabel();
		lblMyMoney.setBounds(200, 550, 400, 100);
		lblMyMoney.setFont(new Font("微軟正黑體",Font.BOLD,90));	
		lblMyMoney.setText(String.format("%d", Money.intMyMoney));
		
		this.add(lblCrtNum);
		this.add(lblBingoNum);
		this.add(lblMoney);
		this.add(lblBingoMsg);
		this.add(lblAfrSort);
		this.add(lblBfrSort);
		this.add(lblSpecNum);
		this.add(taSeTsuMe);
		this.add(lblMyMoney);
	}
	
	public void setBtn() {
		btnClr = new JButton("清除");
		btnClr.setBounds(480, 120, 110, 40);
		btnClr.setFont(new Font("微軟正黑體",Font.BOLD,25));
		btnAuto = new JButton("快選");
		btnAuto.setBounds(480, 40, 110, 40);
		btnAuto.setFont(new Font("微軟正黑體",Font.BOLD,25));
		btnAuto10 = new JButton("快選x999");
		btnAuto10.setBounds(700, 260, 150, 40);
		btnAuto10.setFont(new Font("微軟正黑體",Font.BOLD,20));
		btnSave = new JButton("下注");
		btnSave.setBounds(480, 80, 110, 40);
		btnSave.setFont(new Font("微軟正黑體",Font.BOLD,25));
		btnBingo = new JButton("開獎");
		btnBingo.setFont(new Font("微軟正黑體",Font.BOLD,33));
		btnBingo.setBounds(480, 200, 110, 102);
		btnClrList = new JButton("捨棄選號");
		btnClrList.setBounds(850, 260, 150, 40);
		btnClrList.setFont(new Font("微軟正黑體",Font.BOLD,22));
		
		intTextList = new ArrayList<>();
		intBingoList = new ArrayList<>();
		intSavedList = new ArrayList<>();
	
		//按鈕的行為監聽器
		btnClr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intTextList.clear();
				for(JTextField text: myTextArray) {
					text.setText("");
				}
				setBtnEnable();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intSavedArray = new int[6];
				if(!myTextArray[5].getText().isEmpty()) {
					String text = ""; 
					for(int i=0;i < intTextList.size(); i++) {
						intSavedArray[i] = intTextList.get(i);
						text += String.format(" %d ", intSavedArray[i]);
					}
					intSavedList.add(intSavedArray);
					model.addElement(String.format("第%d組號碼: %s\n", intSavedList.size(), text));
					intTextList.clear();
					for(JTextField t: myTextArray) {
						t.setText("");
					}
					setBtnEnable();
					Money.lossM();
				}
			}
		});
			
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intTextList.clear();
				Logic.setRdNum(intTextList,6);
				Collections.sort(intTextList);
				setBtnEnable();
				for(int k=0;k<intTextList.size();k++) {		
						myTextArray[k].setText(String.format("%d", intTextList.get(k)));	
				}
			}
		});
		
		btnAuto10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intTextList.clear();
				for(int i=0;i<999;i++) {
					Logic.setRdNum(intTextList,6);
					Collections.sort(intTextList);
					setBtnEnable();
					for(int k=0;k<intTextList.size();k++) {		
							myTextArray[k].setText(String.format("%d", intTextList.get(k)));	
					}
					intSavedArray = new int[6];
					if(!myTextArray[5].getText().isEmpty()) {
						String text = ""; 
						for(int j=0;j < intTextList.size(); j++) {
							intSavedArray[j] = intTextList.get(j);
							text += String.format(" %d ", intSavedArray[j]);
						}
						intSavedList.add(intSavedArray);
						model.addElement(String.format("第%d組號碼: %s\n", intSavedList.size(), text));
						intTextList.clear();
						for(JTextField t: myTextArray) {
							t.setText("");
						}
						setBtnEnable();
						Money.lossM();
					}
				}
			}
		});
		
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(intSavedList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "未下注\n請儲存號碼");
				}else {	
					intBingoList.clear();
					Logic.setRdNum(intBingoList,7);
					intSpec = intBingoList.get(6);
					intBingoList.remove(6);
					bingoTextArray[6].setText(String.format("%d",intSpec)); 
					bingoTextArraySort[6].setText(String.format("%d",intSpec)); 
					for(int k=0;k<intBingoList.size();k++) {		
					bingoTextArray[k].setText(String.format("%d", intBingoList.get(k)));	
					}
					Collections.sort(intBingoList);
					for(int i=0;i<intBingoList.size();i++) {		
						bingoTextArraySort[i].setText(String.format("%d", intBingoList.get(i)));	
					}
					Logic.checkBingo(intSavedList,intBingoList);
					intSavedList.clear();
					model.clear();
					if(Integer.parseInt(lblMyMoney.getText()) <= 0) {
						int reply = JOptionPane.showConfirmDialog(null, "少玩大樂透\n事業更順遂\n是否繼續負載人生?", "您已破產", 0, 2);
						if(reply == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null, "脫離貧窮\n請加入我們的行列\n\n\n線上搜尋:iii-098");
							System.exit(0);
							}
						};
					}
				}
			});
		
			btnClrList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Money.rtnM();
					model.clear();
					intSavedList.clear();
				}
			});
			this.add(btnClr);
			this.add(btnAuto);
			this.add(btnAuto10);
			this.add(btnSave);
			this.add(btnBingo);
			this.add(btnClrList);
		}
	
	public void setMyTextArray() {
		myTextArray = new JTextField[6];
		int x = 560;
		for(int i=0; i< 6; i++) {
			x = x+60;
			myTextArray[i] = new JTextField();
			myTextArray[i].setBounds(x,50,60,40);
			myTextArray[i].setEditable(false);
			myTextArray[i].setFont(new Font("微軟正黑體",Font.BOLD,22));
			this.add(myTextArray[i]);
		}
	}
	
	public void setBingoTextArray() {
		bingoTextArray = new JTextField[7];
		bingoTextArraySort = new JTextField[7];
		int x = 40;
		for(int i=0; i< 7; i++) {
			x = x+60;
			bingoTextArray[i] = new JTextField();
			bingoTextArray[i].setBounds(x,375,60,40);
			bingoTextArray[i].setEditable(false);
			bingoTextArray[i].setFont(new Font("微軟正黑體",Font.BOLD,20));
			this.add(bingoTextArray[i]);
			bingoTextArraySort[i] = new JTextField();
			bingoTextArraySort[i].setBounds(x,435,60,40);
			bingoTextArraySort[i].setEditable(false);
			bingoTextArraySort[i].setFont(new Font("微軟正黑體",Font.BOLD,20));
			this.add(bingoTextArraySort[i]);
		}
	}
	public void setBtnArray() {
		btnArray= new JButton[7][7];
		int x=-10,y=20,num=0;
		for(int i=0; i<7; i++) {
			x=-50;
			y+=35;
			for(int j=0; j<7; j++) {
				x+=65;
				num+=1;	
				String strNum = String.format("%d",num);
				btnArray[i][j] = new JButton(strNum);
				btnArray[i][j].setBounds(x, y, 65, 35);
				btnArray[i][j].setOpaque(true);
				btnArray[i][j].setBackground(Color.lightGray);
				btnArray[i][j].setForeground(Color.darkGray);
				btnArray[i][j].setFont(new Font("微軟正黑體",Font.BOLD,18));
				this.add(btnArray[i][j]);

					btnArray[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
								for(int i=0; i<7; i++) {
									for(int j=0; j<7; j++) {
										if(e.getSource() == btnArray[i][j]) {
											if(btnArray[i][j].getForeground() == Color.darkGray) {
												if(myTextArray[5].getText().isEmpty()) {
												btnArray[i][j].setBackground(Color.PINK);	
												btnArray[i][j].setForeground(Color.RED);												
												intTextList.add(Integer.parseInt(btnArray[i][j].getText()));
												Collections.sort(intTextList);
													for(int k=0;k<intTextList.size();k++) {						
														myTextArray[k].setText(String.format("%d", intTextList.get(k)));
													}	
												}
												
											}else if (btnArray[i][j].getForeground() == Color.RED) {

													btnArray[i][j].setBackground(Color.lightGray);	
													btnArray[i][j].setForeground(Color.darkGray);
													intTextList.remove(Integer.valueOf(btnArray[i][j].getText()));
													Collections.sort(intTextList);
													for(JTextField text: myTextArray) {
														text.setText("");
													}
													for(int k=0;k<intTextList.size();k++) {						
														myTextArray[k].setText(String.format("%d", intTextList.get(k)));
												}	
											}
										}
									}
									
								}
							}
						});
					}	
				}
			}	
		
	public void setListBox() {
		model = new DefaultListModel<>();
		listBox = new JList<>(model);
		listBox.setFont(new Font("微軟正黑體",Font.BOLD,20));
		scroll = new JScrollPane(listBox);
		scroll.setBounds(600, 110, 420, 150);
		this.add(scroll);
	}
	public void setListResult() {
		model2 = new DefaultListModel<>();
		listBox2 = new JList<>(model2);
		listBox2.setFont(new Font("微軟正黑體",Font.BOLD,20));
		scroll2 = new JScrollPane(listBox2);
		scroll2.setBounds(600, 320, 150, 350);
		this.add(scroll2);
	}
	public void setBtnEnable() {
		for(JButton[] btn : btnArray) {
			for(JButton b : btn) {
				b.setBackground(Color.lightGray);	
				b.setForeground(Color.darkGray);
			}
		}
	}	
}
