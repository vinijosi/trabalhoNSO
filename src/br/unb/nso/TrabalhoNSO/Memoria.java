package br.unb.nso.TrabalhoNSO;


public class Memoria {


	private static int MEMCPU = 64;
	private static int MEMUSER	 = 1024 - MEMCPU;	

	private int[] memoriaCPU = new int[MEMCPU];
	private int[] memoriaUsuario = new int[MEMUSER];

	public Memoria(){
		for (int i = 0; i < MEMCPU; i++) {
			memoriaCPU[i] = 0;
		}
		for (int i = 0; i < MEMUSER; i++) {
			memoriaUsuario[i] = 0;
		}

	}

	public int memoriaLivre(Processo o) {
		if (o.prioridade == 0){
			//return MEMCPU;
			return blocosContiguosCpu(memoriaCPU, o);
		} else {
			return MEMUSER;
			//blocosContiguosUsuario(memoriaUsuario, blocosMemoria);
		}

	}


	/*
	 * Verifica se ha memoria livre na quantidade desejada 
	 * 
	 * ********************Implementar para blocos continuos
	 * */
	private int blocosContiguosCpu(int[] memoriaTipo,Processo o) {
		int blocosLivres = 0;
		for(int i = 0; i < memoriaTipo.length; i++){
			if (memoriaTipo[i] == 0){
				blocosLivres++; 
				if (blocosLivres == o.blocosMemoria){
					int j = (i - o.blocosMemoria);
					for ( int h = j; h < i ; h++){
						memoriaTipo[h] = o.pid;
					}
					return i - o.blocosMemoria;
					
				}
			} else {
				blocosLivres = 0;
			}

		}
		return blocosLivres;
	}

	public void alocaMemoria(Processo processo) {
		int aux = processo.blocosMemoria;
		if (processo.pid == 0) {
			System.out.printf("PROCESSO DE CPU PID %S",processo.pid);
			
			for (int i = 0; i<MEMCPU; i++){
				if ((aux > 0) && (memoriaCPU[i]==0)){

					memoriaCPU[i] = processo.pid;
					aux--;
					System.out.printf("Posicao %s da Memoria de CPU Alocada para processo (%s)\n",i, memoriaCPU[i]);
				}			

			}
		} else {
			for (int i = 0; i<MEMUSER; i++){
				if ((aux > 0) && (memoriaUsuario[i]==0)){

					memoriaUsuario[i] = processo.pid;
					aux--;
					System.out.printf("Posicao %s da Memoria de Usuario Alocada para processo (%s)\n",i, memoriaUsuario[i]);
				}			

			}
		}

	}

	public static void liberaMemoria(Processo o) {
		// TODO Auto-generated method stub
		
	}

}



