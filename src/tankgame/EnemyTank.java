package tankgame;

import java.util.Random;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots=new Vector<>();
    Vector<EnemyTank> enemyTanks=new Vector<>();
    boolean isLive=true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            case 0:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this){
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+40
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            if(this.getX()+40>= enemyTank.getX()
                                    &&this.getX()+40<= enemyTank.getX()+40
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+60
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            if(this.getX()+40>= enemyTank.getX()
                                    &&this.getX()+40<= enemyTank.getX()+60
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this){
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //...
                            if(this.getX()+60>= enemyTank.getX()
                                    &&this.getX()+60<= enemyTank.getX()+40
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            if(this.getX()+60>= enemyTank.getX()
                                    &&this.getX()+60<= enemyTank.getX()+40
                                    &&this.getY()+40>= enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //...
                            if(this.getX()+60>= enemyTank.getX()
                                    &&this.getX()+60<= enemyTank.getX()+60
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            if(this.getX()+60>= enemyTank.getX()
                                    &&this.getX()+60<= enemyTank.getX()+60
                                    &&this.getY()+40>= enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this){
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+40
                                    &&this.getY()+60>= enemyTank.getY()
                                    &&this.getY()+60<= enemyTank.getY()+60){
                                return true;
                            }
                            if(this.getX()+40>= enemyTank.getX()
                                    &&this.getX()+40<= enemyTank.getX()+40
                                    &&this.getY()+60>= enemyTank.getY()
                                    &&this.getY()+60<= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+60
                                    &&this.getY()+60>= enemyTank.getY()
                                    &&this.getY()+60<= enemyTank.getY()+40){
                                return true;
                            }
                            if(this.getX()+40>= enemyTank.getX()
                                    &&this.getX()+40<= enemyTank.getX()+60
                                    &&this.getY()+40>= enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this){
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+40
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+40
                                    &&this.getY()+40>= enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //...
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+60
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            if(this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+60
                                    &&this.getY()+40>= enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;

        }
        return  false;
    }

    @Override
    public void run() {
        setSpeed(10);
        while(true){
            if(isLive&&shots.size()==0){
                Shot s=null;
                switch (getDirect()){
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

//            System.out.println(getDirect());
            switch (getDirect()){
                case 0:
                    for(int i=0;i<3;i++){
                        if(!isTouchEnemyTank())
                        moveUp();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:
                    for(int i=0;i<3;i++){
                        if(!isTouchEnemyTank())
                        moveRight();

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:
                    for(int i=0;i<3;i++){
                        if(!isTouchEnemyTank())
                        moveDown();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case  3:
                    for(int i=0;i<3;i++){
                        if(!isTouchEnemyTank())
                        moveLeft();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }
            Random random = new Random();
            setDirect(random.nextInt(4));
//            System.out.println((int) Math.random()*4);
            if(!isLive){
                break;
            }
        }
    }
}
