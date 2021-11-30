import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {

	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(9999);
		Socket s = ss.accept();
		
		//Pega as informações do input/output do Socket
		DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        while(true) {
        	
        	String result = "";
        	String calc = dis.readUTF();
        	StringTokenizer st = new StringTokenizer(calc);

        	//Finaliza o servidor atraves da informação que trazida do client
        	if(calc.equals("F") || calc.equals("f")) {
        		System.out.println("Servidor fora de operação (Parado pelo Client)");
				break;
			}
        	        	
        	//Monta a espressão e o calculo feito
        	double num1 = Double.parseDouble(st.nextToken());
        	String operator = st.nextToken();
        	double num2 = Double.parseDouble(st.nextToken());
        	
        	switch(operator) {
        	case "+":
        		result = String.valueOf(num1 + num2);
        		break;
        	case "/":
        		result = String.valueOf(num1 / num2);
        		break;
        	case "-":
        		result = String.valueOf(num1 - num2);
        		break;
        	case "*":
        		result = String.valueOf(num1 * num2);
        		break;
        	default:
        		result = "Valores incorretos";
        		break;
        	}
        	
        	//Escreve o resultado da operação na informação do socket
        	dos.writeUTF(result);
        	dos.flush();

        }
	}

}
