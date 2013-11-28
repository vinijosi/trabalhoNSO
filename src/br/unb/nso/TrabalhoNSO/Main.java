package br.unb.nso.TrabalhoNSO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
//import java.util.LinkedList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws InterruptedException {



		System.out.print("Pseudo SO - Vinicius & Vinicius");

		List<Processo> global = leArquivo();
		
		/*
		 * 2.1.1 - Despachante: Processo principal.
		 * Será responsável pela criação dos Processos.
		 * */ 

		Despachante despachante = new Despachante();
	
		while (despachante.temProcessos()){
			
			despachante.despachaProximo();
		
		}
		
		
	}

	
	/*
	 * Lendo do Arquivo e salvando em uma fila geral
	 * 
	 * */ 
	private static List<Processo> leArquivo() {

		//Dinamico implementar funcao para ler o arquivo 
		String endereco = "processos.txt";
		//Sera que enxerga?
		//sera que ele ta enxergando?
		List<Processo> lista = new ArrayList<Processo>() ;

		/*
		 * Le cada linha do aquivo e cria uma instancia de processo com o conteudo.
		 * 
		 * */ 

		try {
			int pid = 1;
			FileReader arq = new FileReader(endereco);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine(); 

			while (linha != null) {

				//Remover as linhas de impreessao deste metodo !!!!!

				System.out.printf("linha %s: %s\n",pid,  linha);

				String campos[] = linha.split(",");

				int tempoInicializacao = Integer.parseInt(campos[0]); // campo1
				int prioridade = Integer.parseInt(campos[1]); // campo2
				int tempoExecucao = Integer.parseInt(campos[2]); //
				int blocosMemoria = Integer.parseInt(campos[3]); // campo3
				int impressora = Integer.parseInt(campos[4]); // campo3
				int scanner = Integer.parseInt(campos[5]); // campo3
				int modem = Integer.parseInt(campos[6]); // campo3
				int disco = Integer.parseInt(campos[7]); // campo3

				System.out.printf(
						"Pid: %s\n" +
								"Tempo de Inicializacao: %s\n" +
								"Prioridade do Processo: %s\n" +
								"Tempo de Execucao: %s\n" +
								"Blocos de Memoria: %s\n" +
								"Impressora: %s\n" +
								"Scanner: %s\n" +
								"Modem: %s\n" +
								"Disco: %s\n"
								, pid, tempoInicializacao, prioridade, tempoExecucao, blocosMemoria, impressora, scanner, modem, disco);

				//TimeUnit.MILLISECONDS.sleep(1000);

				//Depois de ler o arquivo e imprimi-lo na tela inserimos em uma fila
				//A funcao insere na fila deve inserir o campo na fila / arvore adequada
				//
				//Inserimos o processo na fila Geral para posterior uso

				Processo lido = new Processo();
				lido.insereDados(lido, pid, tempoInicializacao, prioridade, tempoExecucao, blocosMemoria, impressora, scanner, modem, disco);
				lista.add(lido);
				pid = pid + 1;
				linha = lerArq.readLine(); // le as proximas linhas do arquivo ate a ultima				
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}

		/*
		 * Ordenando a lista global por tempo e em seguida por prioridade
		 * */

		Collections.sort(lista);		
		return lista;
	}

}

