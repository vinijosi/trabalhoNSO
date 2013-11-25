package trabalhoNSO; 

public class CPU { 
      
    int cont; 
    int semaforo; 
    int aux; 
      
    public void processar(Processo o, int cont){ 
        aux = 1; 
        System.out.printf("PID: %s \n",o.pid); 
//      System.out.println("offset: 0 "); 
        System.out.printf("blocks: %s \n", o.blocosMemoria); 
        System.out.printf("priority: %s \n",o.prioridade); 
        System.out.printf("time: %s \n",o.tempoExecucao); 
        System.out.printf("printers: %s  \n",o.impressora); 
        System.out.printf("scanners: %s \n",o.scanner); 
        System.out.printf("modems: %s \n",o.modem); 
        System.out.printf("drives: %s \n",o.disco); 
        System.out.println(); 
        System.out.println(); 
        System.out.println(); 
           
           
        System.out.printf("Process %s =>\n",o.pid); 
        System.out.printf("P%s STARTED\n",o.pid); 
          
        while (aux<=o.tempoExecucao){             
            System.out.printf("P%s instruction %s \n",o.pid,aux); 
            aux++; 
        } 
          
        System.out.printf("P%s return SIGINT\n\n",o.pid);        
          
    } 
      
} 
