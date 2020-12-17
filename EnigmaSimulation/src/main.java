import java.util.Random;
import java.util.Scanner;

public class main {
	
	static char[] letras={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	static char[] rotorI= {'E','K','M','F','L','G','D','Q','V','Z','N','T','O','W','Y','H','X','U','S','P','A','I','B','R','C','J'};
	static char[] rotorII= {'A','J','D','K','S','I','R','U','X','B','L','H','W','T','M','C','Q','G','Z','N','P','Y','F','V','O','E'};
	static char[] rotorIII= {'B','D','F','H','J','L','C','P','R','T','X','V','Z','N','Y','E','I','W','G','A','K','M','U','S','Q','O'};
	static char[] rotorIV= {'E','S','O','V','P','Z','J','A','Y','Q','U','I','R','H','X','L','N','F','T','G','K','D','C','M','W','B'};
	static char[] rotorV= {'V','Z','B','R','G','I','T','Y','U','P','S','D','N','H','L','X','A','W','M','J','Q','O','F','E','C','K'};
	static char[] reflector= {'Y','R','U','H','Q','S','L','D','P','X','N','G','O','K','M','I','E','B','F','Z','C','W','V','J','A','T'};
	
	static char rotorIChange='R';
	static char rotorIIChange='F';
	static char rotorIIIChange='W';
	static char rotorIVChange='K';
	static char rotorVChange='A';
	
	static char[] rotorChanges= {rotorIChange,rotorIIChange,rotorIIIChange};
	
	public static String enigma(char[] claves,char[][] clavijas, String text, char [][] rotors) {
		String result="";
		int cnt=1;
		char valu;
		char[] rotorClaves = clavesRotor(rotors);
		char[] newLetras={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0;i<clavijas.length;i++) {
			newLetras=cambiarLetras(clavijas[i][0],clavijas[i][1],newLetras);
		}
		char[] clavs;
	 	char[] textChar=changeStringToArray(text.toUpperCase());
		System.out.println("----------------"+textSize(text));
		System.out.println("Texto inicial : "+text);
		System.out.println("----------------"+textSize(text));
	 	for(char letra: textChar) {
	 		clavs = rotacionChar(claves,rotorClaves); 
	 		
	 		//Tambor de entrada (clavijas)
	 		valu=newLetras[buscarLetraPos(letras,letra)];
	 		
	 		//Primer Rotor
	 		valu=offSetBefore(claves[2],valu);
	 		valu=rotorEtapa(letras,rotors[2],valu);
	 		valu=offSetAfter(claves[2],valu);
	 		
	 		//Segundo Rotor
	 		valu=offSetBefore(claves[1],valu);
	 		valu=rotorEtapa(letras,rotors[1],valu);
	 		valu=offSetAfter(claves[1],valu);
	 		
	 		//Tercero Rotor
	 		valu=offSetBefore(claves[0],valu);
	 		valu=rotorEtapa(letras,rotors[0],valu);
	 		valu=offSetAfter(claves[0],valu);
	 		
	 		//Reflector
	 		valu=rotorEtapa(letras,reflector,valu);
	 		
	 		//Tercero Rotor
	 		valu=offSetBefore(claves[0],valu);
	 		valu=rotorEtapa(rotors[0],letras,valu);
	 		valu=offSetAfter(claves[0],valu);
	 		
	 		//Segundo Rotor
	 		valu=offSetBefore(claves[1],valu);
	 		valu=rotorEtapa(rotors[1],letras,valu);
	 		valu=offSetAfter(claves[1],valu);
	 		
	 		//Primer Rotor
	 		valu=offSetBefore(claves[2],valu);
	 		valu=rotorEtapa(rotors[2],letras,valu);
	 		valu=offSetAfter(claves[2],valu);
	 		
	 		//Tambor de entrada (clavijas)
	 		valu=letras[buscarLetraPos(newLetras,valu)];

	 		result+=valu;
	 		cnt++;
	 	}	
		return result;
	}
	
	//Cambiar Texto en Char Array
	public static char[] changeStringToArray(String texto) {
		return texto.toCharArray(); 
	}
	
	public static char etapaLetras(char[] etapa1,int letraPos) {	
		return etapa1[letraPos];
	}
	
	//Buscar la posicion de una letra en un array
	public static int buscarLetraPos(char[] array, char letra) {
		int result=0;
		for(int i = 0;i<array.length;i++) {
			if(array[i]==letra) {
				result=i;
			}
		}
		return result;
	}
	
	public static char rotorEtapa(char[] array1,char[] array2, char letra) {		
		return array2[buscarLetraPos(array1,letra)];
	}
	
	public static char offSetBefore(char clave, char letra) {
		return letras[buscarLetraPos(letras,letras[((buscarLetraPos(letras, letra)+buscarLetraPos(letras,clave))+26)%26])] ;
	}
	public static char offSetAfter(char clave, char letra) {
		return letras[buscarLetraPos(letras,letras[((buscarLetraPos(letras, letra)-buscarLetraPos(letras,clave))+26)%26])] ;
	}
	
	//Clavijas
	public static char[] cambiarLetras(char letra1,char letra2,char[] newLetra) {
		int let1 = buscarLetraPos(newLetra,letra1);
		int let2 = buscarLetraPos(newLetra,letra2);
		newLetra[let1]= letra2;
		newLetra[let2]= letra1;
		return newLetra;
	}
	
	
	public static char[] rotacionChar(char[] claves,char[] rotorChange) {
		char[] clavs = claves;
		if(letras[(buscarLetraPos(letras,rotorChange[1])+25)%26]==clavs[1]) {
			clavs[1]=letras[(buscarLetraPos(letras,claves[1])+1)%26];
			if(rotorChange[1]==clavs[1]) {
				clavs[0]=letras[(buscarLetraPos(letras,claves[0])+1)%26];
			}
		}
		clavs[2]=letras[(buscarLetraPos(letras,claves[2])+1)%26];
		if(rotorChange[2]==clavs[2]) {
			clavs[1]=letras[(buscarLetraPos(letras,claves[1])+1)%26];
		}
		return clavs;
	}
	

	public static char[] clavesRotor(char[][] rotors) {
		char[] res = new char[3];
		for(int i = 0; i < rotors.length;i++) {
			if(rotors[i] == rotorI) {
				res[i] = rotorIChange;
			}
			if(rotors[i] == rotorII) {
				res[i] = rotorIIChange;
			}
			if(rotors[i] == rotorIII) {
				res[i] = rotorIIIChange;
			}
			if(rotors[i] == rotorIV) {
				res[i] = rotorIVChange;
			}
			if(rotors[i] == rotorV) {
				res[i] = rotorVChange;
			}
		}
		return res;
	}
	
	public static String textSize(String text) {
		String res = "";
		for(int i = 0;i<text.length();i++) {
			res+="-";
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		String text = "AAAA"; 					// Escribir el texto a cifrar
		char [][] rotors = {rotorI,rotorIV,rotorV};  //Selectionar los rotors
		char[][] clavijas= {{'A','C'},{'X','Z'}}; // Escribir si necesite de cambiar letras 
        char[] claves= {'A','D','U'}; 				// Escribir los claves
        
        //Exec el enigma codigo
		String res = enigma(claves,clavijas,text,rotors);
		
		//Mostrar el resultado
		System.out.println("----------------"+textSize(res));
		System.out.println("----------------"+textSize(res));
		System.out.println("Texto final   : "+res);
		System.out.println("----------------"+textSize(res));
	}

}
