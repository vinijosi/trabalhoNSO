package trabalhoNSO; 
  
import java.util.Collections; 
import java.util.Iterator; 
import java.util.LinkedList; 
import java.util.List; 
import java.util.concurrent.TimeUnit; 
  
  
import trabalhoNSO.CPU; 
  
  
public class Main { 
  
    public static void main(String[] args) throws InterruptedException { 
          
        //-----------------Lendo do Arquivo e salvando em uma fila geral------------------------- 
        System.out.print("Pseudo SO - Vinicius & Vinicius"); 
        String caminho = "processos.txt"; 
        //Criada uma fila para receber todos os processos lidos 
        List<Processo> geral = new LinkedList<Processo>(); 
        LeArquivo.learquivo(caminho, geral);         
  
        //-----------------Percorrendo a lista geral para inserir nas filas auxiliares----------- 
        //Le o arquivo e salva na lista geral 
          
        System.out.println("Imprimindo a lista criada"); 
        Processo no = geral.get(0); 
        Iterator<Processo> casa = geral.iterator(); 
          
          
        while(casa.hasNext()){ 
            no = casa.next(); 
            System.out.println(no.tempoInicializacao); 
        } 
  
  
        Collections.sort(geral); 
          
        System.out.println("Imprimindo a lista ordenada"); 
        no = geral.get(0); 
        casa = geral.iterator(); 
          
          
        while(casa.hasNext()){ 
            no = casa.next(); 
            System.out.printf("Tempo: %s     ",no.tempoInicializacao); 
            System.out.printf("Prioridade: %s",no.prioridade); 
            System.out.printf("PID: %s", no.pid); 
            System.out.println(); 
            TimeUnit.MILLISECONDS.sleep(100); 
        } 
          
        System.out.println(); 
  
          
        CPU cpu = new CPU(); 
        int cont = 0; 
        no = geral.get(0); 
        casa = geral.iterator(); 
          
          
        while(casa.hasNext()){ 
        no = casa.next(); 
        cpu.processar(no,cont); 
        } 
          
    } 
        // TODO Auto-generated method stub 
  
} 
