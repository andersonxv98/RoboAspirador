package salasAspiradorRobo;

import java.util.*;


public class Memoria {
    private int id =0;
    private ArrayList<int[]> memoria = new ArrayList<>();
    private int size = 0;
    private String name = "";
    private int  posiI = 0;
    private int  posiJ = 0;
    
    public Memoria(String name){
            this.name  = name; 
           System.out.println("ISIDE CONTRUTO`R MEMORIA");
        }

    public void appendPositionInMemory(int i, int j){
        this.posiI = i;
        this.posiJ = j;
        this.memoria.add(new int[]{i, j});
    }

    public boolean isInMemory(int i, int j){
        boolean result = false;
        for(int[] position : this.memoria){
            if(Arrays.equals(position,new int[]{i, j})){
                result = true;
                break;
            }
        } 
        return result;
    }

    int getI(){
        return this.posiI;
    }

    int getJ(){
        return this.posiJ;

    }

    public void clearMemory(){
        this.memoria.clear();
        System.out.println("Memoria do agente: "+this.id+" APAGADA");
    }

    public int getID(){

        return this.id;
    }
}