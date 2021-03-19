package view;
import java.util.concurrent.Semaphore;

import controller.Bilheteria;

public class Show {

	public static void main(String[] args) {
		int perm = 1;
		
		Semaphore semaforo = new Semaphore(perm);
		for(int Pessoa = 1; Pessoa <= 300; Pessoa++) {
			Thread comprar = new Bilheteria(Pessoa, semaforo);
			comprar.start();
		}
	}
}
