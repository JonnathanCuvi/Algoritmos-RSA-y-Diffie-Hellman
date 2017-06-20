/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

/**
 *
 * @author Estudiante
 */
/*
* RSA.java
*
* Creado 24 de octubre de 2007, 12:02 PM
*
*/
import java.math.BigInteger;
import java.util.*;
import java.io.*;
/**
*
* @author Casidiablo
*/
public class RSA {
    int tamPrimo;
    BigInteger n, q, p;
    BigInteger totient;
    BigInteger e, d;
    /** Constructor de la clase RSA */
    public RSA(int tamPrimo) {
        this.tamPrimo = tamPrimo;
        generaPrimos(); //Genera p y q
        generaClaves(); //Genera e y d
    }

    public void generaPrimos(){
        p = new BigInteger(tamPrimo, 10, new Random());
        do q = new BigInteger(tamPrimo, 10, new Random());
        while(q.compareTo(p)==0);
    }

    public void generaClaves(){
        // n = p * q
        n = p.multiply(q);
        // toltient = (p-1)*(q-1)
        totient = p.subtract(BigInteger.valueOf(1));
        totient = totient.multiply(q.subtract(BigInteger.valueOf(1)));
        // Elegimos un e coprimo de y menor que n
        do e = new BigInteger(2 * tamPrimo, new Random());
        while((e.compareTo(totient) != -1) ||
        (e.gcd(totient).compareTo(BigInteger.valueOf(1)) != 0));
        // d = e^1 mod totient
        d = e.modInverse(totient);
    }

    // Encripta el texto usando la clave pública
    
    public BigInteger[] encripta(String mensaje){
        int i;
        byte[] temp = new byte[1];//Este método devuelve la matriz de bytes resultante
        byte[] digitos = mensaje.getBytes();//getBytes Este método codifica esta cadena en una secuencia de bytes
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        for(i=0; i<bigdigitos.length;i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        BigInteger[] encriptado = new BigInteger[bigdigitos.length];

        for(i=0; i<bigdigitos.length; i++)
            encriptado[i] = bigdigitos[i].modPow(e,n);

        return(encriptado);
    }

    /// Desencripta el texto cifrado usando la clave privada
    public String desencripta(BigInteger[] encriptado) {
        BigInteger[] desencriptado = new BigInteger[encriptado.length];

        for(int i=0; i<desencriptado.length; i++)
            desencriptado[i] = encriptado[i].modPow(d,n);

        char[] charArray = new char[desencriptado.length];

        for(int i=0; i<charArray.length; i++)
            charArray[i] = (char) (desencriptado[i].intValue());

        return(new String(charArray));
    }

    public BigInteger damep() {return(p);}
    public BigInteger dameq() {return(q);}
    public BigInteger dametotient() {return(totient);}
    public BigInteger damen() {return(n);}
    public BigInteger damee() {return(e);}
    public BigInteger damed() {return(d);
   }
}
