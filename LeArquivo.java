package br.unb.nso.TrabalhoNSO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
//import java.util.concurrent.TimeUnit;



public class LeArquivo {

	public static void learquivo(String arg, List<Processo> lista) throws InterruptedException {
		String endereco = arg;
		System.out.printf("\nContudo do arquivo texto:\n");
		
		try {
			int pid = 1;
			FileReader arq = new FileReader(endereco);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine(); // le a primeira linha
			// a variavel "linha" recebe o valor "null" quando o processo
			// de repeticao atingir o final do arquivo texto
						
			while (linha != null) {
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
				
				
				//Depois de ler o arquivo e imprim�-lo na tela inserimos em uma fila
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

		
		System.out.println();
		
		
	}
	
	
}
