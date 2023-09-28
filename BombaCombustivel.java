
package BombaCombustivel.java ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombaCombustivel {
    private int id;
    private String tipoCombustivel;
    private double valorLitro, quantidade;
    
    public BombaCombustivel(int id, String tipoCombustivel, double valorLitro, double quantidade) {
        this.id = id;
        this.tipoCombustivel = tipoCombustivel;
        this.valorLitro = valorLitro;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getValorLitro() {
        return valorLitro;
    }

    public void setValorLitro(double valorLitro) {
        this.valorLitro = valorLitro;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    public double abastecerPorValor(double valor) {
        double litros = valor / valorLitro;
        
        if (litros <= quantidade) {
            quantidade -= litros;
            return litros;
        }
        return -1;
    }
    
    public double abastecerPorLitro(double litros) {
        if (litros <= quantidade) {
            quantidade -= litros;
            return litros * valorLitro;
        }
        return -1;
    }
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        List<BombaCombustivel> posto = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        
        int id = 0;
        OUTER:
        while (true) {
            System.out.println("""
                               1- Registrar um nova bomba
                               2- Editar bomba existente""");
            int escolha = s.nextInt();
            switch (escolha) {
                case 1 -> {
                    System.out.println("Informe o tipo do combutível: ");
                    String tipo = s.next();
                    System.out.println("Informe o valor do combutível: ");
                    double valor = sc.nextDouble();
                    System.out.println("Informe a quantidade do combutível: ");
                    double qtd = sc.nextDouble();
                    BombaCombustivel b = new BombaCombustivel(++id, tipo, valor, qtd);
                    posto.add(b);
                }
                case 2 -> {
                    int sair = 1;
                    while(sair != 0) {
                        System.out.println("Informe o id da bomba: ");
                        int idEditar = sc.nextInt();
                        
                        for (BombaCombustivel b : posto) {
                            if (b.getId() == idEditar) {
                                System.out.println("""
                                                                                   1- Editar Tipo
                                                                                   2- Editar valor do litro
                                                                                   3- Editar quantidade
                                                                                   4- Abastecer por valor
                                                                                   5- Abastecer por litro""");
                                escolha = s.nextInt();
                                
                                switch(escolha) {
                                    case 1 -> {
                                        s.nextLine();  // Adicionado para consumir a nova linha pendente
                                        System.out.println("Informe o novo tipo: ");
                                        String novoTipo = s.nextLine();
                                        b.setTipoCombustivel(novoTipo);
                                    }
                                    case 2 -> {
                                        System.out.println("Informe o novo valor: ");
                                        double novoValor = sc.nextDouble();
                                        b.setValorLitro(novoValor);
                                    }
                                    case 3 -> {
                                        System.out.println("Informe a nova quantidade: ");
                                        double qtd = sc.nextDouble();
                                        b.setQuantidade(qtd);
                                    }
                                    case 4 -> {
                                        System.out.println("Informe o valor que deseja abastecer: ");
                                        double v = sc.nextDouble();
                                        
                                        System.out.println("Qtd. litros: " + b.abastecerPorValor(v));;
                                    }
                                    case 5 -> {
                                        System.out.println("Informe a quantidade de litros que deseja abastecer: ");
                                        double l = sc.nextDouble();
                                        
                                        System.out.println("Valor a pagar: " + b.abastecerPorLitro(l));;
                                    }
                                    default -> {
                                        System.out.println("Opção inválida");
                                        sair = 1;
                                    }
                                }
                            } else {
                                System.out.println("Opção inválida");
                                break;
                            }
                        }
                    }
                }
                default -> {
                    System.out.println("Opção inválida");
                    break OUTER;
                }
            }
        } 
    }
}
