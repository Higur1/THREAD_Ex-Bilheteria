package controller;

import java.util.concurrent.Semaphore;

public class Bilheteria extends Thread{
	private int Pessoa;
	private static int totalIngressos = 100;
	private Semaphore semaforo;
	
	public Bilheteria(int Pessoa, Semaphore semaforo) {
		this.Pessoa = Pessoa;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		SistemLoguin();
	}
	
	private void SistemLoguin() {
		int tempo = (int)(Math.random()*1501)+500;
		int Venda;
		try {
			sleep(tempo);
			if (tempo < 1000) {
				tempo = (int)(Math.random()*3001)+1000;
				try {
					sleep(tempo);
					if (tempo <= 2500) {
					//Seção crítica
						try {
							semaforo.acquire();
							Venda = (int)(Math.random()*3)+1;
							if(totalIngressos < Venda) {
								System.out.println("Pessoa #"+Pessoa+" Indisponibilidade de ingressos..... TimeOut");
							}
							else {
								totalIngressos -= Venda;
								System.out.println("Pessoa #"+Pessoa+" comprou "+ Venda+" ingressos");
							}
						System.out.println("    " +totalIngressos + " 	Ingressos disponiveis");
						}catch(InterruptedException i){
							i.printStackTrace();
						}finally {
							semaforo.release();
						}
					} 
					else {
						System.out.println("Pessoa #" +Pessoa+" Tempo de 2,5 excedido (TimeOut).....");
					}
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Pessoa #" +Pessoa+ " Tempo de 1s excedido (TimeOut).....");
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}

