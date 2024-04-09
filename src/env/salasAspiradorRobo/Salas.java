// CArtAgO artifact code for project my1st_app

package salasAspiradorRobo;

import cartago.*;
import java.util.Random;
import java.util.*;
import salasAspiradorRobo.Memoria;





public class Salas extends Artifact {
    private boolean rooms [][] = null;
    private int numeroSalas = 0;
    private int inicialI = 0;
    private int inicialJ = 0;
    private int sujoI = 0;
    private int sujoJ = 0;
    private Random rnd = new Random (System.currentTimeMillis());
    HashMap<String, Memoria> memorias = new HashMap<>();
    //private List<int []> memorias = new ArrayList<>();

    void init(int numeroSalas){
        System.out.println("Inicio do construtor"+ numeroSalas);
        this.numeroSalas = numeroSalas; 
        rooms = new boolean[numeroSalas][numeroSalas];
        //memoria = new int[numeroSalas][numeroSalas];
        for(int i = 0 ; i < rooms.length; i ++){
            for (int j = 0; j < rooms[i].length; j++){
            rooms[i][j] = true;
                System.out.println("SALA: "+ i+","+ j+ "Ta sujo? "+ rooms[i][j]);
            }
        }
        
        //defineObsProperty("getI", this.getI());
        defineObsProperty("sujoI", sujoI);
        defineObsProperty("sujoJ", sujoJ);
        defineObsProperty("nsalas", this.numeroSalas );
        //System.out.println("Definiu todas a propriedades: "+ this.numeroSalas);
      
        //execInternalOp("gerarSujeira");
    }

    @OPERATION
    void seInitialPosition(int i, int j, String nome_robo)
    {   
        
       

        Memoria m = new Memoria(nome_robo);
        m.appendPositionInMemory(i, j);
      
        this.memorias.put(nome_robo, m);
         System.out.println("INSIDE SETINITIAL POSITION: ,"+ this.memorias.get(nome_robo)); 
        if(this.rooms[i][j]){
             signal("sujo");
        }
    }
 

    boolean canIgoTo(String nome_robo,int i, int j){
       
       

       Memoria m = this.memorias.get(nome_robo);
        boolean isIn = m.isInMemory(i, j);
        if(isIn){
            return false;
           
        }
        if(this.verificarBordas(i, j)){
            return false;
        }

       return true;
    }

    boolean verificarBordas(int i, int j){
        if(i < 0 || j < 0 || i > this.numeroSalas -1 || j > this.numeroSalas -1){
           //SE ENCONTROU BORDA RETORNA TRUE;
           return true;
        }
        return false;


    }


    @OPERATION
    void canIgoToLeft(String nome_robo){
         Memoria m = this.memorias.get(nome_robo);
         int I = m.getI();
         int J = m.getJ();
        boolean canI = this.canIgoTo(nome_robo, I, (J -1));
        if(canI){
            appendMemory(nome_robo, I, J -1);
            System.out.println("AGENTE: ,"+ nome_robo+ " VISITOU: ["+I+","+(J -1)+"]");
            signal("esquerda");
            if(findTrash(I, J- 1)){
                getObsProperty("sujoI").updateValue(I);
                getObsProperty("sujoJ").updateValue(J-1);
                signal("sujo");
            }
        }else{
            signal("nesquerda");
        }
    }
    @OPERATION
    void canIgoToRigth(String nome_robo){
        Memoria m = this.memorias.get(nome_robo);
         int I = m.getI();
         int J = m.getJ();
        boolean canI = this.canIgoTo(nome_robo, I, J +1);
        if(canI){
            appendMemory(nome_robo, I, J +1);
            System.out.println("AGENTE: ,"+ nome_robo+ " VISITOU: ["+I+","+(J+1)+"]");
            signal("direita");
            if(findTrash(I, J+1)){
                getObsProperty("sujoI").updateValue(I);
                getObsProperty("sujoJ").updateValue(J+1);
                signal("sujo");
            }
        }else{
            signal("ndireita");
        }
    }
    @OPERATION
    void canIgoToUp(String nome_robo){
        Memoria m = this.memorias.get(nome_robo);
         int I = m.getI();
         int J = m.getJ();
        boolean canI = this.canIgoTo(nome_robo, I-1, J);
        if(canI){
            appendMemory(nome_robo, I-1, J);
            System.out.println("AGENTE: ,"+ nome_robo+ " VISITOU: ["+(I-1)+","+J+"]");
            signal("cima");
            if(findTrash(I-1, J)){
                getObsProperty("sujoI").updateValue(I-1);
                getObsProperty("sujoJ").updateValue(J);
                signal("sujo");
            }
        }else{
            signal("ncima");
        }
    }

    @OPERATION
    void canIgoToDown(String nome_robo){

        Memoria m = this.memorias.get(nome_robo);
         int I = m.getI();
         int J = m.getJ();
        boolean canI = this.canIgoTo(nome_robo, I+1, J);
        if(canI){
            appendMemory(nome_robo, I+1, J);
            System.out.println("AGENTE: ,"+ nome_robo+ " VISITOU: ["+(I+1)+","+J+"]");
            signal("baixo");
            if(findTrash(I+1, J)){
                getObsProperty("sujoI").updateValue(I+1);
                getObsProperty("sujoJ").updateValue(J);
                signal("sujo");
            }
        }else{
            signal("nbaixo");
        }
    }


    
    void appendMemory(String nome_robo,int i, int j){
        
        Memoria m = this.memorias.get(nome_robo);
        m.appendPositionInMemory(i, j);
        this.memorias.put(nome_robo, m);

        
        

    }

  

    @OPERATION
    void clean(Object vet, String nome_robo){
        
    if (vet instanceof int[]) {
        int[] arrayInteiros = (int[]) vet;
    
        System.out.println("InsideCleN");
        int i = arrayInteiros[0];
        int j = arrayInteiros[1];
        if(rooms[i][j]){
            System.out.println("InsideClean opperation, !["+nome_robo+"]!  limpou sujeira sala: ["+ i+ ","+ j+ "]");
            rooms[i][j] = false;
        }
        else{
          System.out.println("O chao estava limpo");
        }
    }else{
         System.out.println("O objeto não é um array de inteiros.");
    }

    }

    boolean findTrash(int i, int j){
        return rooms[i][j];
        

    }
    @OPERATION
    int getI(String nome_robo){
        Memoria m =this.memorias.get(nome_robo);
        int I  =m.getI();
         System.out.println("INSIDE JAVA GET I"+I);
        return I;
    }

    @OPERATION
      int getJ(String nome_robo){
        Memoria m =this.memorias.get(nome_robo);
        return m.getJ();
    }
    /*void gerarSujeira(){
        while(true){
            if(rnd.nextDouble() <= 0.7){
                int aux = rnd.nextInt(rooms.length);
                rooms[aux] = true;
                System.out.println("Gerou Sujeiro na sala: "+aux);
                await_time(5000);

            }

        }


    }*/
	
}

