  +!starting: true &
        posicao(I, J) &
        name(NAME) <- .print("Estou iniciando na posicao: ", I,";", J);
        seInitialPosition(I, J, NAME).  
  
  +lixoem(M): name(NOME) 
            <-.println("Recebi a mensagem");
                .println("EStou indo limpar...", M);
                clean(M,NOME).
            
{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }
{ include("$moise/asl/org-obedient.asl") }