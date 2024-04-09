//CRENÇAS ---------------------------------------------------------------------


//irDireita.

//!starting. // EXecução de plano

/* Plans  */

+!starting: true &
        posicao(I, J) &
        name(NAME) <- .print("Estou iniciando na posicao: ", I,";", J);
        seInitialPosition(I, J, NAME).    
    //!procurarLixo.


+!procurarLixo<-.drop_all_intentions;
                !mover;
                .wait(2000);
                !procurarLixo.



+!mover : direita &
        nsalas(N) &
        name(NOME)
        <- .println("tentnado mover para: ", direita);
                canIgoToRigth(NOME);
                .wait(1000);
                if (direita){
                        +direita;
                }
                else{
                        .println("bati na borda direita ou ja visitei");
                        -direita;
                        +baixo;
                }.
        
+!mover: baixo &
         nsalas(N) &
        name(NOME) 
        <- .println("tentnado mover para: ", baixo);
        canIgoToDown(NOME);

         if (baixo){
                        +baixo;

                }
                else{
                        .println("bati na borda Baixo ou ja visitei");
                        -baixo;
                        +esquerda;
                }.

+!mover: esquerda &
          nsalas(N) &
        name(NOME) 
         <- .println("tentnado mover para: ", esquerda);
        canIgoToLeft(NOME);
         if (esquerda){
                        
                        +esquerda;
                }
                else{
                        .println("bati na borda Esquerda ou ja visitei");
                      
                        -esquerda;
                        +cima;
                }.

-!mover: nsalas(N) &
         name(NOME) 
         <- .println("tentnado mover para: ", cima);
         canIgoToUp(NOME);
         if (cima){
                        +cima;
                } else{
                        .println("bati na borda CIMA oiu ja visitei");
                        -cima;
                        +direita;
                }.



+!enviarSinalParaAgente(AgenteDestino, Mensagem)
  <- .send(AgenteDestino,tell, lixoem(Mensagem)).
    




                

/*-!mover: at(X) <- .println("DEU TUDO ERRADO: ", X);
                                .println("ird: ", X);
                                goLeft.
*/



// ------------- SINAIS (Luan Santana.) --------------------------------------------

+sujo: sujoI(I) &
        sujoJ(J) &
        agenteSubordinado(Agente)<- .println("[RECEBI O SINAL SUJO] ENVIADO SINAL PARA: ", Agente);
                                //!enviarSinalParaAgente(Agente,"Tem sujeira Pra limpar").
                                .send(aspiradorB, tell, lixoem([I,J])).



+direita<- +direita.
+ndireita<- -direita.
+esquerda<- +esquerda.
+nesquerda<- -esquerda.
+baixo<- +baixo.
+nbaixo<- -baixo.
+cima<- +cima.
+ncima<- - cima.






// some usual includes for JaCaMo projects:
{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }
{ include("$moise/asl/org-obedient.asl") }