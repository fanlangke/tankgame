package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//坦克大战绘图区域
public class MyPanel extends JPanel implements KeyListener,Runnable{
    Hero hero =null;
    Vector<EnemyTank> enemyTanks=new Vector<>();
    Vector<Node> nodes =new Vector<>();
    Vector<Bomb> bombs=new Vector<>();

    int enemyTankSize=4;
    Image image1=null;
    public MyPanel(String key){
        File file=new File(Recorder.getRecordFile());
        if(file.exists()){
            nodes=Recorder.getNodesAndEnemyTankNumRec();
        }
        else{
            key="1";
        }

        Recorder.setEnemyTanks(enemyTanks);
        //初始化我
        hero=new Hero(500,500);
        hero.setSpeed(5);
        switch (key){
            case "1":
                //初始化多个敌人 用vector存
                for(int i=0;i<enemyTankSize;i++){
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    new Thread(enemyTank).start();
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank );
                }
                break;
            case "2":
                for(int i=0;i<nodes.size();i++){
                    Node node=nodes.get(i);

                    EnemyTank enemyTank = new EnemyTank(node.getX() , node.getY());
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());
                    new Thread(enemyTank).start();
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank );
                }
                break;
        }

            image1=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb.png"));
            new AePlayWave("src/BGM.wav").start();


    }

    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font=new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("你累计击毁坦克",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }
    @Override
    //画板
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        showInfo(g);
        //画出我方坦克
        if(hero!=null&&hero.isLive==true)
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        //画出我方坦克子弹
        if(hero.shot!=null&&hero.shot.isLive==true){

            g.fill3DRect(hero.shot.x-3,hero.shot.y-3,5,5,false);
        }
        g.drawImage(image1,0,0,1,1,this);
        //画出我方子弹
        for(int i=0;i<hero.shots.size();i++){
            Shot shot=hero.shots.get(i);
            if(shot!=null&&shot.isLive==true){
                g.draw3DRect(shot.x-3,shot.y-3,5,5,false);
            }
            else{
                hero.shots.remove(shot);
            }
        }
        //画爆炸
        for(int i=0;i<bombs.size();i++){
            Bomb bomb=bombs.get(i);
//            System.out.println("画炸弹");
            g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            bomb.lifeDown();;
            if(bomb.life==0)
                bombs.remove(bomb);
        }
        //画出敌人坦克和子弹
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank=enemyTanks.get(i);
            if(enemyTank.isLive){//敌人坦克存活才画
//                System.out.println(enemyTank.isLive);
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),0);
                for(int j=0;j< enemyTank.shots.size();j++){
                    Shot shot=enemyTank.shots.get(j);
                    if(shot.isLive==true){
//                        System.out.println(1);
                        g.draw3DRect(shot.x-3,shot.y-3,5,5,false);
                    }
                    else{
                        enemyTank.shots.remove(shot);

                    }
                }
            }
        }
    }
    //画坦克

    /**
     *
     * @param x
     * @param y
     * @param g 画笔
     * @param direct 方向
     * @param type  坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //给不同坦克上色
        switch(type){
            case 0://敌人
                g.setColor(Color.cyan);
                break;
            case 1://我
                g.setColor(Color.yellow);
                break;
        }
        switch (direct){
            case 0://上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("未处理");
                break;
        }
    }
    public void hitEnemyTank(){
        for(int j=0;j<hero.shots.size();j++){
            Shot shot=hero.shots.get(j);

            if(shot!=null&&shot.isLive){
//                System.out.println("在看了！！！"+j);
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    hitTank(shot,enemyTank);
                }
            }
        }

    }
    //判断我方子弹是否击中敌人坦克
    public  void hitTank(Shot s,Tank enemyTank){

        switch (enemyTank.getDirect()){
            case 0:
            case 2:
//                System.out.println(1);
                if(s.x>enemyTank.getX()&&s.x<enemyTank.getX()+40
                &&s.y>enemyTank.getY()&&s.y<enemyTank.getY()+60){

                    s.isLive=false;
                    enemyTank.isLive=false;
                    enemyTanks.remove(enemyTank);
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if(s.x>enemyTank.getX()&&s.x<enemyTank.getX()+60
                        &&s.y>enemyTank.getY()&&s.y<enemyTank.getY()+40){
                    s.isLive=false;
                    enemyTank.isLive=false;
                    enemyTanks.remove(enemyTank);
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirect(0);
            hero.moveUp();
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            hero.moveRight();
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            hero.moveDown();
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            hero.moveLeft();
        }
       else if(e.getKeyCode()==KeyEvent.VK_J){

            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中我的坦克
                if (hero.isLive & shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//        if(hero.shot!=null&&hero.shot.isLive){
//
//            for(int i=0;i<enemyTanks.size();i++){
//                EnemyTank enemyTank=enemyTanks.get(i);
////                System.out.println(1);
//                hitTank(hero.shot,enemyTank);
//            }
//        }
                hitEnemyTank();
                hitHero();
                this.repaint();
            }

        }
    }
