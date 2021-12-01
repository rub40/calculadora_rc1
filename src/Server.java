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
        	
        		s = ss.accept();
        		
        		//Pega as informações do input/output do Socket
        		DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                
            	String result = "";
            	String calc = dis.readUTF();
            	
            	if(calc != null) {
            		
                	StringTokenizer st = new StringTokenizer(calc);
                	
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
            	}
            
				//Escreve o resultado da operação na informação do socket
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
