import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JScrollPane;

public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;
    public  Frame()
    { init(); }
    /*--- Test ---*/
    private boolean a=false;
    private int b=0;
    private Panel Page1 = new Panel();
    private Panel Page2 = new Panel();
    private Panel Page3 = new Panel();
    /*--- Other ---*/
    private Timer time;                             //計數器
    private int ChackUserInLengh=0;                 //Chack長度變數
    private int ChackUserInText=0;                  //Chack是否為整數變數
    private boolean Input=false;                    //判斷user boolean
    private boolean AB=false;                       //判斷猜中與猜錯 boolean
    private Random random = new Random();           //亂數
    private int random1[]=new int[4];               //儲存亂數
    private int AnswerA=0;                          //A變數
    private int AnswerB=0;                          //B變數
    //    private JLabel baclgrund=new JLabel(new ImageIcon("backgrund.jpg"));
    private int ArrayLable=0;
    private int test = 0;
    private JPanel lablePanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(lablePanel);
    /*--- AddContainer ---*/
    private Container cp;
    private String ButtonText[]={"Start","RANDING","EXIT"};
    private Panel CButtons=new Panel(new GridLayout(3,1,0,0)); //首頁清單
    private Panel N=new Panel(new GridLayout(1,5,3,3));  //輸入、CHACK
    /*--- Page1 Object ---*/
    private JLabel TitlePage1 = new JLabel("猜數字");              //主題 標籤 (猜數字)
    private JButton jbnList[] = new JButton[3];                         //清單 按鈕[3]
    /*--- Page2 Object ---*/
    private JLabel TitlePage2 = new JLabel("輸入名字");           //主題 標籤 (猜數字)
    private  JTextField NameIn = new JTextField();                      //輸入名字框
    private JButton Ok = new JButton("Yes"),No = new JButton("Cancel");     //確認與返回按鈕
    /*--- Page3 Object ---*/
    private JTextField UserInput[] = new JTextField[4];             //使用者輸入[4]
    private JButton jbnCHECK = new JButton("CHECK");        //檢查 按鈕
    private JLabel jlbTimeLable = new JLabel("T: 0");          //時間  標籤
    private int Time = 1;

    private void init() {
//      This and cp
        this.setBounds(50, 50, 600, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        cp = this.getContentPane();
        cp.setLayout(null);
//        cp.add(baclgrund);
//        baclgrund.setSize(600,300);
        cp.add(Page1);
        cp.add(Page2);
        cp.add(Page3);
//      Page1
        Page1.setVisible(true);
        Page1.setSize(600,300);
        Page1.setLayout(null);
        /*---set Title Type---*/
        Page1.add(TitlePage1);
        TitlePage1.setBounds(Math.round(this.getWidth()/1.6f),20,150,70); //邊界
        TitlePage1.setFont(new Font(null,Font.BOLD,30));  //字體
        TitlePage1.setForeground(Color.BLACK);     //顏色
        TitlePage1.setOpaque(false);
        /*---set Button Type---*/
        for(int i=0;i<3;i++) {
            jbnList[i] = new JButton();
            CButtons.add(jbnList[i]);
            jbnList[i].setText(ButtonText[i]);
        }
        CButtons.setBounds(Math.round(this.getWidth()/1.7f),(Math.round(this.getHeight()/3.3f)),150,150);
        Page1.add(CButtons);
        /*---set Start Type---*/
        jbnList[0].addActionListener(new ActionListener() {  //開始ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {
                Page1.setVisible(false);
                Page2.setVisible(true); } });
        /*---set Randing Type---*/
        jbnList[1].addActionListener(new ActionListener() {   //排行設定ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {

            } });
        /*---set Exit Type---*/
        jbnList[2].addActionListener(new ActionListener() {  // 離開ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(3); } });

//      Page2
        Page2.setVisible(false);
        Page2.setSize(600,300);
        Page2.setLayout(null);
        /*---set Title Type---*/
        Page2.add(TitlePage2);
        TitlePage2.setBounds(Math.round(this.getWidth()/1.6f),20,150,70); //邊界
        TitlePage2.setFont(new Font(null,Font.BOLD,30));  //字體
        TitlePage2.setForeground(Color.BLACK);     //顏色
        TitlePage2.setOpaque(false);
        /*---set NameIn Type---*/
        Page2.add(NameIn);
        NameIn.setBounds(Math.round(this.getWidth()/1.8f),(this.getHeight()/2)-50,200,50);
        /*---set OK or No Type---*/
        Page2.add(Ok);
        Ok.setBounds((Math.round(this.getWidth()/1.8f)),this.getHeight()/2,100,50);
        Ok.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Page2.setVisible(false);
                    Page3.setVisible(true);
                }
            }
        });
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(NameIn.getText().length() != 0) {
                    Page2.setVisible(false);
                    Page3.setVisible(true);
                    while (a==false) {
                        /*---亂數產生---*/
                        for (int RunRandom = 0; RunRandom < 4; RunRandom++) {
                            random1[RunRandom] = random.nextInt(9);  //產生亂數
                            /*---檢查亂數有無重複---*/
                            if (RunRandom == 3) {
                                for (int run1 = 0; run1 < 4; run1++) {
                                    for (int run2 = 0; run2 < 4; run2++) {
                                        if (random1[run1] == random1[run2]) {
                                            b++; } } }
                                if(b>4){
                                    a=false;
                                    b=0; }
                                else {
                                    a=true; } } } }
                    a=false;
                    for(int i=0;i<4;i++){
                        System.out.print(random1[i]+"\t"); } } } });
        Page2.add(No);
        No.setBounds((Math.round(this.getWidth()/1.8f)+100),this.getHeight()/2,100,50);
        No.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Page2.setVisible(false);
                Page1.setVisible(true); }});

//      Page3
        Page3.setVisible(false);
        Page3.setLayout(null);
        Page3.setSize(600,300);
        Page3.add(N);
        N.setBounds(0,0,420,50);
        /*---set UserInput Type---*/
        for(int i=0;i<4;i++) {
            UserInput[i] = new JTextField(1);
            UserInput[i].setSize(30, 30);
            UserInput[i].setFont(new Font(null,Font.BOLD,20));
            UserInput[i].setHorizontalAlignment(JTextField.CENTER);
            N.add(UserInput[i]); }

        /*---set Time Type---*/
        Page3.add(jlbTimeLable);
        jlbTimeLable.setBounds(430,0,150,50);
        jlbTimeLable.setFont(new Font(null,Font.BOLD,36));
        time=new Timer(1000,new ActionListener() {       //計時
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbTimeLable.setText("T: "+Integer.toString(Time++)); } });



        /*---set scrollPane Type---*/
        Page3.add(scrollPane);
        scrollPane.setBounds(50, 100, 180, 165);
        scrollPane.setEnabled(false);
        lablePanel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()*2));
        lablePanel.revalidate(); //告?其他部件,我的?高?了



        /*----set CHECK and ActionListener---*/
        N.add(jbnCHECK);
        jbnCHECK.setSize(100,50);
        jbnCHECK.addActionListener(new ActionListener() {  // CHECK
            @Override
            public void actionPerformed(ActionEvent e) {
                /*----判斷Error變數---*/
                if (Input == false) {
                    for (int i = 0; i < 4; i++) {
                        if (UserInput[i].getText().length() == 0) {          //判斷是否有空格
                            ChackUserInLengh++;                           //有空格 則ChackUserInLengh++
                        } else if (UserInput[i].getText().length() != 0) {
                            try {
                                Integer.parseInt(UserInput[i].getText());     //判斷是否數字
                            } catch (NumberFormatException a) {          //例外則:
                                ChackUserInText++; } } }
                    /*----判斷Error變數成立---*/
                    if (ChackUserInLengh == 0 && ChackUserInText == 0) {
                        Input=true;
                        time.start();
                    } else if (ChackUserInLengh > 0 && ChackUserInText > 0) {
                        JOptionPane.showMessageDialog(null, "請在空格處輸入 "+"\n"+"並輸入數字(Ex: 1,2,3 - - -)");  //呼叫對話框
                    } else if (ChackUserInLengh > 0 && ChackUserInText == 0) {
                        JOptionPane.showMessageDialog(null, "請在空格處輸入");  //呼叫對話框
                    } else if (ChackUserInLengh == 0 && ChackUserInText > 0) {
                        JOptionPane.showMessageDialog(null, "請輸入整數　Ex: 1,2,3 - - -");  //呼叫對話框
                    }
                    ChackUserInLengh = 0;
                    ChackUserInText = 0;
                }
                    /*----判斷boolean變數---*/
                if(Input==true) {
                    for (int i = 0; i < 4; i++) {
                        if (random1[i] == Integer.parseInt(UserInput[i].getText())) {
                            AnswerA++;
                            AB = true;
                        } else if (AB != true) {
                            for (int a = 0; a < 4; a++) {
                                if (Integer.parseInt(UserInput[i].getText()) == random1[a]) {
                                    AnswerB++;
                                }
                            }
                        }
                        AB = false;
                    }
                    if (test >= 0) {
                        /*----新增標籤---*/
                        JLabel jlbPrompt[]=new JLabel[ArrayLable+1];
                        jlbPrompt[ArrayLable] = new JLabel();
                        /*----標籤抓取資訊---*/
                        for(int i=0 ;i<4;i++) {
                            if (i == 3) {
                                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText() + " " + UserInput[i].getText() + " ： ");
                            } else {
                                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText() + " " + UserInput[i].getText() + " "); } }
                        jlbPrompt[ArrayLable].setFont(new Font(null,Font.BOLD,18));

                        lablePanel.add(jlbPrompt[ArrayLable]);
                        /*----判斷A和B並輸出文字---*/
                        if (AnswerA == 4) {
                            time.stop();
                            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+"Win");
                            JOptionPane.showMessageDialog(null, "猜中了!!  答案為:"+
                                    jlbPrompt[ArrayLable].getText()+"\n"+"你用了 "+jlbTimeLable.getText()+" 秒完成");
                        } else if (AnswerA != 0 && AnswerB == 0) {
                            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerA + "A");
                        } else if (AnswerA == 0 && AnswerB != 0) {
                            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerB + "B");
                        } else if (AnswerA != 0 && AnswerB != 0) {
                            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerA + "A" + AnswerB + "B");
                        }else if (AnswerA == 0 && AnswerB == 0) {
                            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+"X"); }
                        /*----迴圈最後變更參數---*/
                        ArrayLable++;
                        AnswerA = 0;
                        AnswerB = 0;
                        Input = false; } } } });
//        jbnCHECK.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_ENTER){
//
//                }
//            }
//        });
    }
    public static void main(String[] args) {
        new Frame();
    }
}