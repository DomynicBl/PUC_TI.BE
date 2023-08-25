import java.util.*;

class SomarInteiros {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
	int num1, num2, soma;
	
	System.out.print("Digite o primeiro numero:");
	num1 = sc.nextInt();
	System.out.print("Digite o segundo numero:");
	num2 = sc.nextInt();
	
	soma = num1 + num2;
	
	System.out.println("A soma dos dois numeros e " + soma + ".");
	
	}
}
