import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		@SuppressWarnings("resource")
		Socket s = new Socket("localhost", 9999);
		boolean stop = false;
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		while(!stop) {

			//Busca as informações da equação através do Scanner
			System.out.println("Calculadora Cliente/Servidor:");
			System.out.println("Digite a letra F para sair da calculadora.");
			System.out.println("Digite a conta a ser feita: (Ex: primeiro_numero operador segundo_numero)");
			
			String calc = sc.nextLine();
			
			//Finaliza as operações da calculadora ou Imprime o resultado da equação
			if(!(calc.equals("F") || calc.equals("f"))) {
				dos.writeUTF(calc);
				String result = dis.readUTF();
				System.out.println("Resultado = " + result);
			}else {
				stop = true;
				System.out.println("Calculadora fora de operação");
			}
		}
	}
}
