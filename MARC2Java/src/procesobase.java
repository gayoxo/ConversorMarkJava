import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class procesobase {

	private static String SSB;
	private static int i;
	private static ArrayList<String> listaLineas;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileReader fr = null;
		try {
			File A=new File("marco.txt");
			fr = new FileReader (A);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			listaLineas=new ArrayList<String>();;
			while  ((linea = br.readLine())!=null)
			{
				listaLineas.add(linea);
			}
			
			procesaLineas();

			
			br.close();
			System.out.println("FIN");
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
		}

	}

	private static void procesaLineas() {
		i=0;
		SSB="";
		while (i<listaLineas.size()) {
			SSB=SSB+"{\n";
			
			generaCabecera();
			
			seteaLineB();
			seteaIndice1();
			seteaIndice2();
			i++;
			seteaValoresletras();
			SSB=SSB+"}\n";
		}
		
		System.out.println(SSB.toString());
		
	}

	private static void generaCabecera() {
		String Posible=listaLineas.get(i);
		if (Posible.startsWith("I"))
			{
			i++;
			String PosibleV=listaLineas.get(i).trim();
			i++;
			SSB=SSB+"CompleteIterator I1=new CompleteIterator(Datos);\n";
			SSB=SSB+"String Iden=\""+PosibleV+"\";\n";
			SSB=SSB+"Datos.getSons().add(I1);\n";
			}
	}

	private static void seteaValoresletras() {
		
		SSB=SSB+"HashMap<String, CompleteTextElementType> full = new HashMap<String, CompleteTextElementType>();\n";
		
		while (listaLineas.size()>i&&!listaLineas.get(i).equals("I"))
		{
		String valorS=listaLineas.get(i).trim();
		i++;
		char valor = valorS.charAt(1);
		String resultdo=listaLineas.get(i).trim();
		i++;
		String valorlimpio=lipiaValor(valor);
		
		SSB=SSB+"CompleteTextElementType "+valorlimpio+" = new CompleteTextElementType(\""+resultdo+"\", CTET1);\n";
		SSB=SSB+"CTET1.getSons().add("+valorlimpio+");\n";
		SSB=SSB+"full.put(\""+valor+"\", "+valorlimpio+");\n";
		}

		SSB=SSB+"TablaD.put(Iden, full);\n";
	}

	private static String lipiaValor(char valor) {
		switch (valor) {
		case '0':
			return "n0";
		case '1':
			return "n1";
		case '2':
			return "n2";
		case '3':
			return "n3";
		case '4':
			return "n4";
		case '5':
			return "n5";
		case '6':
			return "n6";
		case '7':
			return "n7";
		case '8':
			return "n8";
		case '9':
			return "n9";
		default:
			return Character.toString(valor);
		}

	}

	private static void seteaLineB() {
		String Posible=listaLineas.get(i);
		if (Posible.startsWith("A"))
			{
			i++;
			seteaLine();
			}
	}

	private static void seteaIndice2() {
		String Posible=listaLineas.get(i);
		if (Posible.startsWith("ID2"))
			{
			i++;
			generaID2();
			generaCampos2();
			}
	}
	
	private static void seteaIndice1() {
		String Posible=listaLineas.get(i);
		if (Posible.startsWith("ID1"))
			{
			i++;
			generaID1();
			generaCampos1();
			}
	}

	private static void generaCampos2() {
		
		SSB=SSB+"HashMap<String, String> full2I = new HashMap<String, String>();\n";		
		while (!listaLineas.get(i).equals("B"))
			{
			String valor=listaLineas.get(i).trim();
			i++;
			String resultdo=listaLineas.get(i).trim();
			i++;
			SSB=SSB+"full2I.put(\""+valor+"\", \""+resultdo+"\");\n";
			}

		SSB=SSB+"TablaID2.put(Iden, full2I);\n";
	}

	private static void generaID2() {
		String Nombre=listaLineas.get(i).trim();
		SSB=SSB+"CompleteTextElementType ID2 = new CompleteTextElementType(\""+Nombre+"\", CTET1);\n";
		SSB=SSB+"CTET1.getSons().add(ID2);\n";
		SSB=SSB+"TablaD2.put(Iden, ID2);\n";
		i++;
	}
	
	
	private static void generaCampos1() {
		
		SSB=SSB+"HashMap<String, String> full1I = new HashMap<String, String>();\n";		
		while (!listaLineas.get(i).equals("ID2")&&!listaLineas.get(i).equals("B"))
			{
			String valor=listaLineas.get(i).trim();
			i++;
			String resultdo=listaLineas.get(i).trim();
			i++;
			SSB=SSB+"full1I.put(\""+valor+"\", \""+resultdo+"\");\n";
			}

		SSB=SSB+"TablaID1.put(Iden, full1I);\n";
	}

	private static void generaID1() {
		String Nombre=listaLineas.get(i).trim();
		SSB=SSB+"CompleteTextElementType ID1 = new CompleteTextElementType(\""+Nombre+"\", CTET1);\n";
		SSB=SSB+"CTET1.getSons().add(ID1);\n";
		SSB=SSB+"TablaD1.put(Iden, ID1);\n";
		i++;
	}

	private static void seteaLine() {
		String Nombre=listaLineas.get(i).trim();
		i++;
		SSB=SSB+"CompleteElementType CTET1=new CompleteElementType(\""+Nombre+"\", I1);\n";
		SSB=SSB+"I1.getSons().add(CTET1);\n";
	}


}
