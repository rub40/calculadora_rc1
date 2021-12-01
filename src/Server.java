import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {

	public static void main(String[] args) throws IOException {
		
		//Em C# o ServerSocket seria um TCPListener;
		ServerSocket ss = null;
		Socket s = null;
		
		try {
			ss = new ServerSocket(9999);
		}catch(Exception ex) {
			System.err.println(ex);
			return;
		}
		     
        while(true) {
   	
        	try {
        	
    			//Para iniciar um socket em C# � necess�rio iniciar uma instancia de um TCPListener que no caso faz a funcao do ServerSocket, O metodo em C# dentro do objeto TCPListener Seria .AcceptSocket();
        		s = ss.accept();
        		
        		//Pega as informa��es do input/output do Socket
        		//Em C# se utiliza o objeto BinaryWriter e BinaryReader();
        		DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                
            	String result = "";
            	String calc = dis.readUTF();
            	
            	if(calc != null) {
            		
            		//StringTokenizer separa os values de acordo com os espa�os, em C# seria utilizado o Split('') para separar uma string;
                	StringTokenizer st = new StringTokenizer(calc);
                	
                	//Monta a espress�o e o calculo feito
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
            	}
            
				//Escreve o resultado da opera��o na informa��o do socket
				dos.writeUTF(result);
				dos.flush();
				
				//Tenta fechar o socket
				try {
                    s.close();
                }
                catch (Exception ex) {
                	
                }
            	                
        	}catch(Exception ex) {
        		if(s != null) {
                    try {
                        s.close();
                    }
                    catch (Exception ex2) {
                    	
                    }
                }
        	}
        }
	}

}
