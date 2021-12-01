import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		boolean stop = false;
		
		while(!stop) {

			Socket s = new Socket("localhost", 9999);
			DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			
			//Busca as informações da equação através do Scanner
			System.out.println("Calculadora Cliente/Servidor:");
			System.out.println("Digite a letra F para sair da calculadora.");
			System.out.println("Digite a conta a ser feita: (Ex: primeiro_numero operador segundo_numero)");
			
			String calc = br.readLine();
			
			//Finaliza as operações da calculadora ou Imprime o resultado da equação
			if(!(calc.equals("F") || calc.equals("f"))) {
				dos.writeUTF(calc);
				dos.flush();
				String result = dis.readUTF();
				System.out.println("Resultado = " + result);
			}else {
				stop = true;
				System.out.println("Calculadora fora de operação");
			}
		}
	}
}
