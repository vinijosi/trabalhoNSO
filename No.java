package trabalhoNSO; 
  
  
  
public class No { 
      
    Processo processo; 
    No proximo; 
    No (Processo processo){      
        this.processo = processo; 
        proximo = null; 
    }    
      
    public Processo pegaProcesso() {   
        return processo;   
    }   
    public void setProcesso(Processo processo) {   
        this.processo = processo;   
    }   
    public No pegaProximo() {   
        return proximo;   
    }   
    public void setaProximo(No proximo) {   
        this.proximo = proximo;   
    }   
  
} 
