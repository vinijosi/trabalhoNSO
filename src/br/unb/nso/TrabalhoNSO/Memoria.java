package br.unb.nso.TrabalhoNSO;


public class Memoria {

	private static int MEMCPU = 64; //TAMANHO MAXIMO DA MEMORIA DE CPU
	private static int MEMUSER	 = 1024 - MEMCPU; //TAMANHO MAXIMO DA MEMORIA DE USUARIO

	private static  int[] memoriaCPU = new int[MEMCPU];
	private static  int[] memoriaUsuario = new int[MEMUSER];

	public Memoria(){
		for (int i = 0; i < MEMCPU; i++) {
			memoriaCPU[i] = 0;
		}
		for (int i = 0; i < MEMUSER; i++) {
			memoriaUsuario[i] = 0;
		}

	}

	public boolean memoriaLivre(Processo o) {
		if (o.prioridade == 0){
			return existemBlocosContiguosLivres(memoriaCPU, o);
		} else {
			return existemBlocosContiguosLivres(memoriaUsuario, o);
			//blocosContiguosUsuario(memoriaUsuario, blocosMemoria);
		}

	}
	/*
	 * Verifica se ha memoria livre na quantidade desejada 
	 * */
	private boolean existemBlocosContiguosLivres(int[] memoriaTipo, Processo o) {
		int blocosLivres = 0;
		for (int i = 0; i < memoriaTipo.length; i++){
			if (memoriaTipo[i] == 0 && blocosLivres < o.blocosMemoria){
				blocosLivres++;
			}
			if (blocosLivres == o.blocosMemoria){
				return true;
			}
		}
		return false;
	}


	//	private int blocosContiguosLivres(int[] memoriaTipo,Processo o) {
	//		int blocosLivres = 0;
	//					
	//		for(int i = 0; i < memoriaTipo.length; i++){
	//			if ((memoriaTipo[i] == 0)&&(blocosLivres == o.blocosMemoria)){
	//				blocosLivres++; 
	//					//					int j = (i - o.blocosMemoria);
	//					//					for ( int h = j; h < i ; h++){
	//					//						memoriaTipo[h] = o.pid;
	//					//					}
	//				System.out.printf("Primeiro Bloco Continuo Livre: %s\n", (i - o.blocosMemoria));
	//			} else {
	//				blocosLivres = 0;
	//			}
	//			if (blocosLivres == o.blocosMemoria){
	//				return blocosLivres;
	//				//break;
	//			}
	//		}
	//		return 0;	
	//	}

	//	public void alocaMemoria(Processo processo) {
	//		int aux = processo.blocosMemoria;
	//		if (processo.pid == 0) {
	//			System.out.printf("PROCESSO DE CPU PID %S",processo.pid);
	//
	//			for (int i = 0; i<MEMCPU; i++){
	//				if ((aux > 0) && (memoriaCPU[i]==0)){
	//
	//					memoriaCPU[i] = processo.pid;
	//					aux--;
	//					System.out.printf("Posicao %s da Memoria de CPU Alocada para processo (%s)\n",i, memoriaCPU[i]);
	//				}			
	//
	//			}
	//		} else {
	//			for (int i = 0; i<MEMUSER; i++){
	//				if ((aux > 0) && (memoriaUsuario[i]==0)){
	//
	//					memoriaUsuario[i] = processo.pid;
	//					aux--;
	//					System.out.printf("Posicao %s da Memoria de Usuario Alocada para processo (%s)\n",i, memoriaUsuario[i]);
	//				}			
	//
	//			}
	//		}
	//
	//	}

	public void alocaMemoria(Processo processo) {
		int blocosParaAlocar = processo.blocosMemoria;
		if (processo.pid == 0) {
			//int primeiroContinuioLivre = blocosContiguosLivres(memoriaCPU, processo);
			for (int i = 0/*primeiroContinuioLivre*/; i < MEMCPU; i++){
				if ((blocosParaAlocar > 0) && (memoriaCPU[i]==0)){

					memoriaCPU[i] = processo.pid;
					blocosParaAlocar--;
					processo.offsetMemoria = (i - processo.blocosMemoria);
					System.out.printf("Posicao %s da Memoria de %s Alocada para processo (%s)\n",i, memoriaCPU.getClass(), memoriaCPU[i]);
				}
			}

		} else {
			//int primeiroContinuioLivre = blocosContiguosLivres(memoriaUsuario, processo);
			for (int i = 0 /*primeiroContinuioLivre*/; i < MEMUSER; i++){
				if ((blocosParaAlocar > 0) && (memoriaUsuario[i]==0)){

					memoriaUsuario[i] = processo.pid;
					blocosParaAlocar--;
					processo.offsetMemoria = (i - processo.blocosMemoria);
					System.out.printf("Posicao %s da Memoria de %s Alocada para processo (%s)\n",i, memoriaUsuario.getClass(), memoriaUsuario[i]);
				}
			}

		}
	}

	public static  void liberaMemoria(Processo processo) {
		System.out.printf("Tentando liberar memoria\n ");
		if (processo.prioridade == 0) {
			System.out.printf("MEMORIA CPU\n ");
			for (int i = 0; i < MEMCPU; i++){
				System.out.printf("\nValor na Memoria: %s\n",memoriaCPU[i]);
				if (memoriaCPU[i]==processo.pid){
					System.out.printf("\nPosicao %s da Memoria Liberada\n",i);
					memoriaCPU[i] = 0;
				}

			}
		} else {
			for (int i = 0; i < MEMUSER; i++){
				//System.out.printf("MEMORIA USUARIO %s \n ", memoriaUsuario[i]);
				if (memoriaUsuario[i]==processo.pid){
					memoriaUsuario[i] = 0;
					System.out.printf("\nPosicao %s da Memoria Liberada\n",i);					
				}
			}

		}
	}

}