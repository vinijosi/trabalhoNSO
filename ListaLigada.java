package trabalhoNSO; 
  
  
  
public class ListaLigada { 
      
    public No primeiro, ultimo; 
      
    ListaLigada(){ 
        primeiro = null; 
        ultimo = null; 
    } 
      
    public boolean isVazia(){ 
        return(primeiro == null && ultimo == null); 
    } 
      
    public No pegaPrimeiro(){ 
        return primeiro;         
    } 
      
    public void addInicio(Processo o){ 
        No novoNo = new No(o); 
        this.primeiro = novoNo; 
        if (isVazia()){ 
            ultimo = novoNo; 
        } 
    } 
      
     public void addFinal(Processo o)  { 
         No novoNo = new No(o); 
         if (isVazia()) 
                 primeiro = novoNo; 
         else
                 ultimo.proximo = novoNo; 
         ultimo = novoNo; 
     } 
       
     public void addProximo (Processo o, No p, boolean teste){ 
         No novoNo = new No(o); 
           
         if(teste){ 
            novoNo.proximo = p.proximo; 
            p.proximo = novoNo;          
         } 
         else{                
                 addProximo(o,p.proximo,teste); 
             } 
         }    
           
//   public void addOrdenado(Processo o){ 
//        
//       No novoNo = new No (o); 
//       if(isVazia()){ 
//           primeiro = novoNo; 
//           ultimo = novoNo; 
//       }            
//       else{ 
//           if (novoNo.processo.tempoInicializacao < primeiro.processo.tempoInicializacao){ 
//               addInicio(o); 
//           } 
//           else{ 
//               if(novoNo.processo.tempoInicializacao > ultimo.processo.tempoInicializacao){ 
//                   addFinal(o); 
//               }                
//               else{ 
//                    
//                   addProximo(o,primeiro,(o.tempoInicializacao >= primeiro.processo.tempoInicializacao)); 
//                    
//               }                
//           } 
//        }               
//   } 
  
  
      
    public void imprimeLista(ListaLigada lista, No elemento){        
        if(lista.isVazia() ){            
            System.out.println("Lista estï¿½ vazia!");               
        } 
        else{            
            if(elemento==ultimo){ 
                System.out.printf("\n %s \n", elemento.processo.pid); 
            } 
            else{            
                System.out.printf("\n %s \n", elemento.processo.pid); 
                lista.imprimeLista(lista, elemento.proximo); 
            } 
        } 
    } 
      
      
  
} 
