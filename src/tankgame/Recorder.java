package tankgame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int allEnemyTankNum;
    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    private static BufferedReader br=null;
    private static String recordFile="myRecord.txt";
    public static Vector<EnemyTank> enemyTanks=null;
    private static Vector<Node> nodes=new Vector<>();
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks){
        Recorder.enemyTanks=enemyTanks;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static Vector<Node> getNodesAndEnemyTankNumRec(){
        try {
            br=new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum=Integer.parseInt(br.readLine());
            String line=null;
            while((line= br.readLine())!=null){
                String[] xyd=line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank=enemyTanks.get(i);
                if(enemyTank.isLive){
                    String record=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
                    bw.write(record+"\r\n");

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
    public  static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
