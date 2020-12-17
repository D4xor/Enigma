

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
	 	for(char letra: textChar) {
	 		clavs = rotacionChar(claves,rotorClaves);
	 		/*
	 		System.out.println("----------------"+textSize(text));
	 		System.out.println("Camino "+ cnt + " : ");
	 		System.out.println("----------------"++textSize(text));
	 		System.out.println("Claves : "+ clavs[0]+" | "+clavs[1]+" | "+clavs[2]);
	 		System.out.println("Letra inicial : "+ letra);
	 		*/
	 		valu=newLetras[buscarLetraPos(letras,letra)];
	 		valu=offSetBefore(claves[2],valu);
	 		valu=rotorEtapa(letras,rotors[2],valu);
	 		valu=offSetAfter(claves[2],valu);
	 		valu=offSetBefore(claves[1],valu);
	 		valu=rotorEtapa(letras,rotors[1],valu);
	 		valu=offSetAfter(claves[1],valu);
	 		valu=offSetBefore(claves[0],valu);
	 		valu=rotorEtapa(letras,rotors[0],valu);
	 		valu=offSetAfter(claves[0],valu);
	 		valu=rotorEtapa(letras,reflector,valu);
	 		valu=offSetBefore(claves[0],valu);
	 		valu=rotorEtapa(rotors[0],letras,valu);
	 		valu=offSetAfter(claves[0],valu);
	 		valu=offSetBefore(claves[1],valu);
	 		valu=rotorEtapa(rotors[1],letras,valu);
	 		valu=offSetAfter(claves[1],valu);
	 		valu=offSetBefore(claves[2],valu);
	 		valu=rotorEtapa(rotors[2],letras,valu);
	 		valu=offSetAfter(claves[2],valu);
	 		valu=letras[buscarLetraPos(newLetras,valu)];
	 		//System.out.println("Letra final : " + valu);
	 		result+=valu;
	 		cnt++;
	 	}	
		return result;
	}
	
	public static char[] changeStringToArray(String texto) {
		return texto.toCharArray(); 
	}
	
	public static char etapaLetras(char[] etapa1,int letraPos) {	
		return etapa1[letraPos];
	}
	
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
	
	public static char[] obtenerClave(String palabra) {
		char[] res = new char[3];
		char[] letraText = changeStringToArray(palabra);
		for(int i=0;i<3;i++) {
			res[i]=letraText[i];
		}
		return res;
	}
	
	public static char[][] obtenerClavijas(String palabra){
		char[] letraText = changeStringToArray(palabra);
		int value = letraText.length - 3;
		int size = ((int)((value)/2));
		char[][] res = new char[size][2];
		for(int i = 0; i<((int)((value)/2));i++) {
			res[i][0] = letraText[(i*2)+3];
			res[i][1] = letraText[(i*2)+4];
		}
		return res;
	}
	
	public static double indice(String texto) {
		double value=0.0000;
		double textLen = texto.length();
		double countLetra;
		for(char letra:letras) {
			countLetra = nbLetras(letra, texto);
			value= value + (countLetra*(countLetra-1))/(textLen*(textLen-1));
		}
		return value;
	}
	
	public static double nbLetras(char letra, String texto) {
		char[] tex = changeStringToArray(texto);
		double number = 0;
		for(char let: tex) {
			if(letra == let) {
				number+=1;
			}
		}
		return number;
	}
	
	public static void displayTime(long t1, long t2) {
		long res = (int) ((t2-t1)/1000);
		int hours = (int)(res / 3600);
		int minutes = (int)((res - 3600*hours)/60);
		int seconds = (int) (res - hours * 3600 - minutes*60);
		System.out.println("Time of execution : " + hours + "h" + minutes + "min" + seconds + "sec" );
	}
	
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		String text = "KHIVQBTCYRFAFWPLVSCAMMRFVDMSIIRRTRZTLAOMWHFQDTOFARWZYVPWPZBNKWAV";  // Texto a decifrar
		char [][] rotors = {rotorI,rotorII,rotorIII};  //Selectionar los rotors
		String [] dictionario = {"ambiguo","obvio","trivial","estupendo","esther","bugzilla","lugar","pacifico","diarrea","hola","mundo","garabata","calabaza","herpes","celula","porro","suaves","albacete","fiesta","patata"};
		String res;
		String code;
		char[] cla = new char[3];
		char[][] clavi = new char[1][2];
		double indiceEsp = 0.077;
		
		for(int i = 0; i< letras.length;i++) { //Rotor I
			for(int j = 0; j< letras.length;j++) { // Rotor II
				for(int k = 0; k< letras.length;k++) { // Rotor III
					for(int m = 0; m< letras.length;m++) { // Primera letra a cambiar
						for(int n = m; n< letras.length;n++) { // Segunda letra a cambiar
							code="";
							cla[0]=letras[i];
							cla[1]=letras[j];
							cla[2]=letras[k];
							clavi[0][0]=letras[m];
							clavi[0][1]=letras[n];
							code+=letras[i];
							code+=letras[j];
							code+=letras[k];
							code+=letras[m];
							code+=letras[n];
							res = enigma(cla,clavi,text,rotors);
							for(String word: dictionario) { // Probar si tenemos un palabra en el texto que descifran
								if(res.contains(word.toUpperCase()) && indice(res)>(indiceEsp-0.010)) { // Probar si tenemos un indice cerca del indice español
									System.out.println("----------------"+textSize(res));
									System.out.println("Indice        : "+indice(res));
									System.out.println("Codigo        : "+code);
									System.out.println("Texto final   : "+res);
									System.out.println("----------------"+textSize(res));
								}
							}
						}
					}
				}
			}
		}
		long time2 = System.currentTimeMillis();
		displayTime(time1,time2); //Calcular tiempo de ejecucion 
	}

}
