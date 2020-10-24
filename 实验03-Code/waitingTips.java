import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


import AES.FileAPI;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;


public class waitingTips{
    JFrame jf=new JFrame("请稍后");
    JLabel tip=new JLabel(" ",JLabel.CENTER);
    JProgressBar progress=new JProgressBar();
    int fileLength=0;
    boolean canShow=false;
    public  void InitEncryptShow(){
        jf.setSize(300,200);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        tip.setText("文件加密中....");
        tip.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jf.add(tip);
        tip.setBounds(80, 20, 140, 30);
        
        progress.setMinimum(0);
        Timer t=new Timer();
        t.schedule(new TimerTask(){
            public void run(){
                if(FileAPI.alreadyread){
                    fileLength=FileAPI.GetfileLength();
                    System.out.println("length="+fileLength);
                    progress.setMaximum(fileLength);
                    canShow=true;
                    t.cancel();
                }
            }
        },0,100);

        progress.setValue(0);
        progress.setStringPainted(true);
       
        jf.add(progress);
        progress.setBounds(50, 80, 200, 20);

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void show(){
        jf.setVisible(true);
        Timer t1=new Timer();
        t1.schedule(new TimerTask(){
            public void run(){
                if(canShow){
                    Timer t2=new Timer();
                    t2.schedule(new TimerTask(){
                        int current;
                        public void run(){
                            current=FileAPI.GetprogressNow();
                            System.out.println("cur="+current+" total="+fileLength);
                            if(fileLength-current<=10){
                                t2.cancel();
                            }else{
                                progress.setValue(current);
                            }
                        }
                    },0,50);
                    t1.cancel();
                }
            }
        }, 0,50);
    }

    public  void InitDecryptShow(){
        jf.setSize(300,200);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        tip.setText("文件解密中....");
        tip.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jf.add(tip);
        tip.setBounds(80, 20, 140, 30);
        
        progress.setMinimum(0);
        Timer t=new Timer();
        t.schedule(new TimerTask(){
            public void run(){
                if(FileAPI.alreadyread){
                    fileLength=FileAPI.GetfileLength();
                    System.out.println("length="+fileLength);
                    progress.setMaximum(fileLength);
                    canShow=true;
                    t.cancel();
                }
            }
        },0,100);

        progress.setValue(0);
        progress.setStringPainted(true);
       
        jf.add(progress);
        progress.setBounds(50, 80, 200, 20);

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public  void turnOff(){
        jf.dispose();
    }
}