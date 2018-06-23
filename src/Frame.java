import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.IllegalComponentStateException;
public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;

    public Frame() {
        init();
    }

    /*--- Other ---*/
    private Timer P2Time;                             //計數器
    private int ChackUserInLengh = 0;                 //Chack長度變數
    private boolean Input;                    //判斷user boolean
    private Random random = new Random();           //亂數
    private int random1[] = new int[4];               //儲存亂數
    private int AnswerA = 0;                          //A變數
    private int AnswerB = 0;                          //B變數
    private int ArrayLable = 0;
    private boolean a = false;
    private int b = 0;
    private Object[] options = {"確定", "取消"};
    private JLayeredPane layeredPane = new JLayeredPane();
    /*--- Page1 Object ---*/
    private JPanel Page1 = new JPanel();
    private JLabel TitlePage1 = new JLabel();              //主題 標籤 (猜數字)
    private JButton jbnList[] = new JButton[3];                         //清單 按鈕[3]
    /*--- Page2 Object ---*/
    private JPanel Page2 = new JPanel();
    private JPanel RandingPanel = new JPanel(new GridLayout(6, 3, 3, 3));
    private String Name;  //暫定儲存Name
    private JLabel RandingLable[][] = new JLabel[6][3];
    //    private JLabel TitlePage2 = new JLabel("輸入名字");           //主題 標籤 (猜數字)
    private JTextField NameIn = new JTextField();                      //輸入名字框
    private JButton RandingBack = new JButton();
    private JButton Ok = new JButton(), No = new JButton();              //確認與返回按鈕
    /*--- Page3 Object ---*/
    private JPanel Page3 = new JPanel();
    private JPanel PromptPanel = new JPanel();
    private JLabel TimeLable = new JLabel("T: 0");          //時間 標籤
    private JLabel BGL_P3 = new JLabel();
    private int Time = 1;
    /*--- Test ---*/
    private JLabel BGLable1 = new JLabel(new ImageIcon("BackgrundImage/backgrund2.jpg"));
    private JLabel BGLName = new JLabel(new ImageIcon("name.png"));
    private JLabel DBLable = new JLabel(new ImageIcon("DB_YourName.png"));
    private JLabel BGLBook = new JLabel(new ImageIcon("book.png"));
    private JPanel TESTPanel = new JPanel();
    private int i1 = 1;  //若紀錄已滿則從1開始覆蓋
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel UseInputPanel = new JPanel(new GridLayout(2, 4));  //輸入及箭頭版面
    private JLabel UserLb[] = new JLabel[8];  //輸入及箭頭 ( 標籤 [] )
    private int testkey = 4;   //判斷UserLb[] 陣列裡位置  ( 整數 )
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private String own, two, three, four, ALL;  //抓取輸入值 ( 字串 )
    private int GetKey;  //取得Key輸入後判斷值 ( 整數 )
    private int GetKey_P1 =0;

    //    private boolean ChackRandingOpen=false;
    private void init() {
//      This and cp
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayeredPane(layeredPane);
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(0, 0, 600, 300);
        layeredPane.add(Page1, JLayeredPane.MODAL_LAYER);
        layeredPane.add(Page2, JLayeredPane.MODAL_LAYER);
        layeredPane.add(Page3, JLayeredPane.MODAL_LAYER);
        layeredPane.add(RandingPanel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(RandingBack, JLayeredPane.MODAL_LAYER);
        Page1.setBounds(0, 0, 600, 300);
        Page2.setBounds(0, 0, 600, 300);
        Page3.setBounds(0, 0, 600, 300);
        RandingPanel.setBounds(150, 25, 300, 200);
        RandingBack.setBounds(0, 80, 90, 90);
        BGLable1.setSize(this.getWidth(), this.getHeight());
        BGLName.setBounds(150, 0, 300, 270);
        DBLable.setSize(160, 100);
        Page1.requestFocus(true);
        Page1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()){
                    case 10:
                        switch (GetKey_P1){
                            case 0:
                                Page1.setVisible(false);
                                Page2.setVisible(true);
                                NameIn.requestFocus(true);
                                break;
                            case 1:
                                break;
                            case 2:
                                System.exit(3);
                                break;
                        }
                        break;
                    case 38:
                        if (GetKey_P1 > 0) {
                            GetKey_P1--;
                            P1Button_Icon(); }
                        break;
                    case 40:
                        if (GetKey_P1 < 2) {
                            GetKey_P1++;
                            P1Button_Icon(); }
                        break;
                }
            }
        });

//      Page1--------------------------------------------------------------------
        Page1.setVisible(true);
        Page1.setLayout(null);
        /*---set Title Type---*/
        Page1.add(TitlePage1);
        TitlePage1.setBounds(340, 0, 250, 70); //邊界
        TitlePage1.setIcon(new ImageIcon("Title.png"));
        /*---set ListButton Type---*/
        for (int i = 0; i < 3; i++) {
            jbnList[i] = new JButton();
        }
        /*---set RandingPanel Type---*/
        RandingBack.setIcon(new ImageIcon("Back.png"));
        RandingBack.setBorderPainted(false);
        RandingBack.setContentAreaFilled(false);
        RandingBack.setOpaque(false);
        RandingPanel.setVisible(false);
        RandingBack.setVisible(false);

        /*---set Start Type---*/
        P1Button_Icon();
        Page1.add(jbnList[0], JLayeredPane.MODAL_LAYER);
        jbnList[0].setBounds(380, 80, 190, 50);
        jbnList[1].setIcon(new ImageIcon("RECORDY.png"));
        jbnList[0].setOpaque(false);
        jbnList[0].setBorderPainted(false);
        jbnList[0].setContentAreaFilled(false);
        /*---set Randing Type---*/
        Page1.add(jbnList[1], JLayeredPane.MODAL_LAYER);
        jbnList[1].setBounds(350, 140, 240, 50);
        jbnList[1].setIcon(new ImageIcon("RECORDW.png"));
        jbnList[1].setOpaque(false);
        jbnList[1].setBorderPainted(false);
        jbnList[1].setContentAreaFilled(false);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                RandingLable[i][j] = new JLabel();
                RandingLable[i][j].setFont(new Font(null, Font.BOLD, 20));
                if (i >= 1 && j == 0) {
                    RandingLable[i][0].setText(Integer.toString(i));
                }
                RandingPanel.add(RandingLable[i][j]);
            }
        }
        RandingLable[0][1].setText("Name");
        RandingLable[0][2].setText("Time");
        jbnList[1].addActionListener(new ActionListener() {   //排行設定ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {
                Page1.setVisible(false);
                RandingPanel.setVisible(true);
                RandingBack.setVisible(true);
                RandingBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Page1.setVisible(true);
                        RandingPanel.setVisible(false);
                        RandingBack.setVisible(false);
                    }
                });
            }
        });
        /*---set Exit Type---*/
        Page1.add(jbnList[2], JLayeredPane.MODAL_LAYER);
        jbnList[2].setBounds(405, 200, 135, 50);
        jbnList[2].setIcon(new ImageIcon("EXITW.png"));
        jbnList[2].setOpaque(false);
        jbnList[2].setBorderPainted(false);
        jbnList[2].setContentAreaFilled(false);
        Page1.add(BGLable1, JLayeredPane.DEFAULT_LAYER);

//      Page2--------------------------------------------------------------------
        Page2.setVisible(false);
        Page2.setLayout(null);
        /*---set NameIn Type---*/
        Page2.add(NameIn);
        NameIn.setBounds(235, 149, 120, 35);
        NameIn.setFont(new Font(null, Font.BOLD, 20));
        Page2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });
        NameIn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()){
                    case 10:
                        if (NameIn.getText().length() != 0) {
                            Name = NameIn.getText();  //抓取Name
                            Page2.setVisible(false);
                            Page3.setVisible(true);
                            DBLable.setVisible(false);
                            Page3.requestFocus(true);   //Page3 取得Focus權限
                            while (a == false) {
                                /*---亂數產生---*/
                                for (int RunRandom = 0; RunRandom < 4; RunRandom++) {
                                    random1[RunRandom] = random.nextInt(9);  //產生亂數
                                    /*---檢查亂數有無重複---*/
                                    if (RunRandom == 3) {
                                        for (int run1 = 0; run1 < 4; run1++) {
                                            for (int run2 = 0; run2 < 4; run2++) {
                                                if (random1[run1] == random1[run2]) {
                                                    b++; } } }
                                        if (b > 4) {
                                            a = false;
                                            b = 0;
                                        } else {
                                            a = true; } } } }
                            a = false;
                            for (int i = 0; i < 4; i++) {
                                System.out.print(random1[i] + "\t");
                            }
                            System.out.println("\n");
                        } else {
                            DBLable.setVisible(true); }
                        break;
                    case 27:
                        Page2.setVisible(false);
                        Page1.setVisible(true);
                        DBLable.setVisible(false);
                        Page1.requestFocus(true);
                        break;
                }
            }
        });
        /*---set DB Type---*/
        Page2.add(DBLable);
        DBLable.setVisible(false);
        DBLable.setLocation(350, 2);
        /*---set OK or No Type---*/
        Page2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });
        Page2.add(Ok);
        Ok.setBounds(503, 80, 90, 90);
        Ok.setIcon(new ImageIcon("Into.png"));
        Ok.setBorderPainted(false);
        Ok.setContentAreaFilled(false);
        /*---- No -----*/
        Page2.add(No);
        No.setBounds(0, 80, 90, 90);
        No.setIcon(new ImageIcon("Back.png"));
        No.setBorderPainted(false);
        No.setContentAreaFilled(false);
        Page2.add(BGLName, JLayeredPane.MODAL_LAYER);
//      Page3--------------------------------------------------------------------
        Page3.setVisible(false);
        Page3.setLayout(null);
        Page3.setSize(600, 300);
        Page3.add(UseInputPanel,JLayeredPane.MODAL_LAYER);
        UseInputPanel.setBounds(230, 2, 300, 130);
        UseInputPanel.setOpaque(false);
        /*---set UserLb Type---*/
        for (int i = 0; i < 8; i++) {
            UserLb[i] = new JLabel();
            UserLb[i].setSize(60, 70);
            UseInputPanel.add(UserLb[i]);
            UserLb[i].setOpaque(false);
            if (i >= 4) {
                UserLb[i].setIcon(new ImageIcon("Arrow.png"));
                UserLb[i].setVisible(false);
                if (i == 4) {
                    UserLb[i].setVisible(true);
                }
            }
        }
        /*---set Time Type---*/
        Page3.add(TimeLable,JLayeredPane.MODAL_LAYER);
        TimeLable.setBounds(270, 60, 250, 140);
        TimeLable.setFont(new Font(null, Font.BOLD, 90));
        TimeLable.setForeground(new Color(107, 107, 107));
//        jlbTimeLable.setBackground(new Color(0,0,0));
        /*---set TESTPanel Type---*/
        Page3.add(TESTPanel,JLayeredPane.MODAL_LAYER);
        TESTPanel.setBounds(5, 2, 220, 265);
        TESTPanel.setLayout(null);
        BGLBook.setSize(220, 280);
        TESTPanel.add(PromptPanel, JLayeredPane.MODAL_LAYER);
        TESTPanel.add(BGLBook, JLayeredPane.DEFAULT_LAYER);
        /*---set BGL_P3 Type---*/
        Page3.add(BGL_P3,JLayeredPane.DEFAULT_LAYER);
        BGL_P3.setIcon(new ImageIcon("BackgrundImage/Mood.jpg"));
        BGL_P3.setSize(600,300);
        /*---set PromptPanel Type---*/
        PromptPanel.setBounds(33, 20, 150, 300);
        PromptPanel.setOpaque(false);
        /*---set Time Type---*/
        P2Time = new Timer(1000, new ActionListener() {       //計時
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeLable.setText("T: " + Integer.toString(Time++));
            }
        });
        /*----set CHECK and ActionListener---*/
        Page3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GetKey = e.getKeyCode() - 48;
                switch (e.getKeyCode()) {
                    case 10:  //ENTER
                        Check();
                        break;
                    case 39:  //RIGHT
                        if (testkey < 7) {
                            testkey++;
                            ArrowSet();
                        }
                        break;
                    case 37:  //LEFT
                        if (testkey > 4) {
                            testkey--;
                            ArrowSet();
                        }
                        break;
                    case 48:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/0.png"));
                        NumberLocation();
                        break;
                    case 49:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/1.png"));
                        NumberLocation();
                        break;
                    case 50:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/2.png"));
                        NumberLocation();
                        break;
                    case 51:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/3.png"));
                        NumberLocation();
                        break;
                    case 52:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/4.png"));
                        NumberLocation();
                        break;
                    case 53:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/5.png"));
                        NumberLocation();
                        break;
                    case 54:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/6.png"));
                        NumberLocation();
                        break;
                    case 55:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/7.png"));
                        NumberLocation();
                        break;
                    case 56:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/8.png"));
                        NumberLocation();
                        break;
                    case 57:
                        UserLb[testkey - 4].setIcon(new ImageIcon("NumberImage/9.png"));
                        NumberLocation();
                        break;
                }
            }
        });
    }

    public void Check() {
        /*----判斷Error變數---*/
        ALL = own + two + three + four;
        if (ALL.getBytes().length != 4) {
            ChackUserInLengh++;//有空格 則ChackUserInLengh++
        }
        /*----判斷Error變數成立---*/
        if (ChackUserInLengh > 0) {
            JOptionPane.showMessageDialog(Page3, "請在空格處輸入");  //呼叫對話框
            Input = false;
        }else if (ChackUserInLengh == 0) {
            Input = true;
            P2Time.start();
        }
        ChackUserInLengh = 0;
        /*----判斷boolean變數---*/
        if (Input == true) {
            if (Integer.parseInt(own) == random1[0]) {
                AnswerA++;
            } else {
                TestB(Integer.parseInt(own));
            }
            if (Integer.parseInt(two) == random1[1]) {
                AnswerA++;
            } else {
                TestB(Integer.parseInt(two));
            }
            if (Integer.parseInt(three) == random1[2]) {
                AnswerA++;
            } else {
                TestB(Integer.parseInt(three));
            }
            if (Integer.parseInt(four) == random1[3]) {
                AnswerA++;
            } else {
                TestB(Integer.parseInt(four));
            }
            /*----新增標籤---*/
            JLabel jlbPrompt[] = new JLabel[ArrayLable + 1];
            jlbPrompt[ArrayLable] = new JLabel();
            /*----標籤抓取資訊---*/
            jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText() + " " + ALL + " ： ");
            jlbPrompt[ArrayLable].setFont(new Font(null, Font.BOLD, 18));
            PromptPanel.add(jlbPrompt[ArrayLable]);
            /*----判斷A和B並輸出文字---*/
            if (AnswerA == 4) {     // 全對時
                P2Time.stop();
                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText() + "Win" + "\t");
                /*----全對時對話框設定---*/
                int Response = JOptionPane.showOptionDialog(Page3, "猜中了!!  答案為:" +
                                jlbPrompt[ArrayLable].getText() + "\n" + "你用了 " + TimeLable.getText() + " 秒完成",
                        "結束", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null,
                        options, options[0]);
                if (i1 == 6) {
                    i1 = 1;
                }  //記錄滿時 i1=1 為覆蓋紀錄
                if (RandingLable[i1][2].getText().length() == 0) {
                    RandingLable[i1][1].setText(Name);
                    RandingLable[i1][2].setText(TimeLable.getText());
                    i1++;
                } else if (RandingLable[i1][2].getText().length() != 0) {
                    RandingLable[i1][1].setText(Name);
                    RandingLable[i1][2].setText(TimeLable.getText());
                    i1++;
                }
                if (Response == 0) {   //回應確認
                    Page3.setVisible(false);
                    Page1.setVisible(true);
                    PromptPanel.removeAll();
                    TimeLable.setText("T: 0");
                    NameIn.setText("");
                    Time = 1;
                    Page1.requestFocus(true);
                } else if (Response == 1) {  //回應取消
                    System.out.println("1");
                }
                /*-----------------------*/
            } else if (AnswerA != 0 && AnswerB == 0) {
                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerA+"A");
            } else if (AnswerA == 0 && AnswerB != 0) {
                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerB+"B");
            } else if (AnswerA != 0 && AnswerB != 0) {
                jlbPrompt[ArrayLable].setText(jlbPrompt[ArrayLable].getText()+AnswerA+"A"+AnswerB+"B");
            } else{
                jlbPrompt[ArrayLable].setText("X" + "\t");
            }
            ArrayLable++;
            AnswerA = 0;
            AnswerB = 0;
            Input = false;
        }
    }
    /*----箭頭顯示設定----*/
    public void ArrowSet() {
        for (int i = 4; i < 8; i++) {
            UserLb[i].setVisible(false);
        }
        UserLb[testkey].setVisible(true); }
    /*----首頁清單顯示設定----*/
    public void P1Button_Icon() {
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    jbnList[i].setIcon(new ImageIcon("STARTW.png"));
                    break;
                case 1:
                    jbnList[i].setIcon(new ImageIcon("RECORDW.png"));
                    break;
                case 2:
                    jbnList[i].setIcon(new ImageIcon("EXITW.png"));
                    break;
            }
        }
        switch (GetKey_P1){
            case 0:
                jbnList[0].setIcon(new ImageIcon("STARTY.png"));
                break;
            case 1:
                jbnList[1].setIcon(new ImageIcon("RECORDY.png"));
                break;
            case 2:
                jbnList[2].setIcon(new ImageIcon("EXITY.png"));
                break;
        }
    }
    /*----判斷位置存取資料(String)----*/
    public void NumberLocation() {
        int Test = testkey - 4;
        if (Test == 0) { own = Integer.toString(GetKey);   }
        if (Test == 1) { two = Integer.toString(GetKey);   }
        if (Test == 2) { three = Integer.toString(GetKey); }
        if (Test == 3) { four = Integer.toString(GetKey);  }
    }
    /*----判斷B----*/
    public int TestB(int SaveLocation) {
        for (int i = 0; i < 4; i++) {
            if (SaveLocation == random1[i]) {
                AnswerB++; }
        }
        return AnswerB; }
    public static void main(String[] args) {
        new Frame(); }
}