//http://ankurm.com/implementing-diffie-hellman-key-exchange-algorithm-java/
package Diffie_Hellman;

import java.io.*;
import java.math.BigInteger;
class Diffie
{
    public static void main(String[]args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese un numero primo:");
        BigInteger p=new BigInteger(br.readLine());
        System.out.print("Introduzca la base de "+p+":");
        BigInteger g=new BigInteger(br.readLine());
        System.out.println("Introduzca el valor para A menor que "+p+":");
        BigInteger x=new BigInteger(br.readLine());
        BigInteger R1=g.modPow(x,p);
        System.out.println("R1="+R1);
        System.out.print("Introduzca el valor para B menor que "+p+":");
        BigInteger y=new BigInteger(br.readLine());
        BigInteger R2=g.modPow(y,p);
        System.out.println("R2="+R2);
        BigInteger k1=R2.modPow(x,p);
        System.out.println("Clave calculada al lado de Alice:"+k1);
        BigInteger k2=R1.modPow(y,p);
        System.out.println("Clave calculada en el lado de Bob:"+k2);
        //System.out.println("deffie hellman Clave secreta El cifrado ha tomado");
    }
}
 

