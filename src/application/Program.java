package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produto> produtos = new ArrayList<>();
	
		System.out.print("Entre com o caminho do arquivo: ");
		String strCaminho = sc.nextLine();
		
		File caminho = new File(strCaminho);
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			
			String linha = br.readLine();
			
			while (linha != null) {
				
				String[] vect = linha.split(",");
				String nome = vect[0];
				double preco = Double.valueOf(vect[1]);
				int quantidade = Integer.valueOf(vect[2]);
				produtos.add(new Produto(nome, preco, quantidade));
				linha = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		new File(caminho + "\\out").mkdir();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho.getParent() + "\\out" + "summary.csv"))) {
			
			for (Produto produto : produtos) {
				bw.write(produto.getNome() + "," + String.format("%.2f", produto.precoTotal()));
				bw.newLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}
}