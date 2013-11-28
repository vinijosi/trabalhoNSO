	package br.unb.nso.TrabalhoNSO;
	
	@SuppressWarnings("serial")
	public class Processo extends Exception implements Comparable<Processo> {
	
		public int pid;
		public int tempoInicializacao;
		public int prioridade;
		public int tempoExecucao;
		public int blocosMemoria;
		public int impressora;
		public int scanner;
		public int modem;
		public int disco;
		public int idade;
		public int tempoNaCpu;
		
		public void insereDados(
				Processo o,
				int pid,
				int tempoInicializacao, 
				int prioridade, 
				int tempoExecucao,
				int blocosMemoria, 
				int impressora, 
				int scanner, 
				int modem, 
				int disco) {
			o.pid = pid;
			o.tempoInicializacao = tempoInicializacao;
			o.prioridade = prioridade;
			o.tempoExecucao = tempoExecucao;
			o.blocosMemoria = blocosMemoria;
			o.impressora = impressora;
			o.scanner = scanner;
			o.modem = modem;
			o.disco = disco;
			o.idade = 0;
			o.tempoNaCpu = 0;
			
		}
		
		/*
		 * O metodo compareTo garante que os processos estarao ordenados por tempo de execucao 
		 * e por prioridade 
		 * */
	
		@Override
		public int compareTo(Processo o) {
			if((this.tempoInicializacao - o.tempoInicializacao) != 0){
				return this.tempoInicializacao - o.tempoInicializacao;
			}
			else {
			return this.prioridade - o.prioridade;
			}
		}
	
		public boolean recursosLivres() {	
			try{
				return true;
			} catch (Exception e){
				System.out.println("Não existem recursos Disponiveis");			
			}
			// TODO Auto-generated method stub
			return true;
		}
	
		public void envelhece() {
			this.idade++;
			
		}
	
		
	}
