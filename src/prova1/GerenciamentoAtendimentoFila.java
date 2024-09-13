package prova1;
import java.util.Scanner;
/**
 *
 * @author: RAFMo
 */

class Elemento {
    String id;
    String nome;
    String descricao;

    public Elemento(String id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ",Descricao: " + descricao;
    }
}

class Fila {
    private No prox;
    private No ante;

    public Fila() {
        this.prox = null;
        this.ante = null;
    }

    public void adicionar(Elemento elemento) {
        try {
            No novoNode = new No(elemento);
            if (ante != null) {
                ante.proximo = novoNode; 
            }
            ante = novoNode; 
            if (prox == null) { 
                prox = ante;
            }
            System.out.println("Cliente adicionado a fila: " + elemento);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente a fila: " + e.getMessage());
        }
    }

    public Elemento remove() throws Exception {
        if (filaVazia()) {
            throw new Exception("A fila esta vazia!!");
        }
        Elemento clienteAtendido = prox.elemento;
        prox = prox.proximo; 
        if (prox == null) { 
            ante = null;
        }
        System.out.println("Cliente atendido: " + clienteAtendido);
        return clienteAtendido;
    }

    public void mostrarTodos() {
        try {
            if (prox == null) {
                System.out.println("A fila esta vazia.");
                return;
            }
            System.out.println("Clientes na fila:");
            No atual = prox;
            while (atual != null) {
                System.out.println(atual.elemento);
                atual = atual.proximo; 
            }
        } catch (Exception e) {
            System.out.println("Erro ao mostrar a fila: " + e.getMessage());
        }
    }

    public boolean filaVazia() {
        return prox == null;
    }
}

class FilaAtendimento {
    Elemento[] filaDeAtendimento = new Elemento[] {
        new Elemento("CLI001", "Maria Silva", "Duvida sobre produto"),
        new Elemento("CLI002", "Joao Souza", "Reclamacao de servico"),
        new Elemento("CLI003", "Ana Costa", "Solicitacao de reembolso"),
        new Elemento("CLI004", "Pedro Alves", "Informacoes de entrega"),
        new Elemento("CLI005", "Carla Dias", "Agendamento de visita"),
        new Elemento("CLI006", "Lucas Martins", "Alteracao de pedido"),
        new Elemento("CLI007", "Patricia Rocha", "Cancelamento de contrato"),
        new Elemento("CLI008", "Rafael Lima", "Renovacao de assinatura"),
        new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalacao"),
        new Elemento("CLI010", "Carlos Eduardo", "Pedido de orcamento")
    };
}

public class GerenciamentoAtendimentoFila {
    public static void main(String[] args) {
        FilaAtendimento filaAtendimento = new FilaAtendimento();
        Fila fila = new Fila();

        for (Elemento elemento : filaAtendimento.filaDeAtendimento) {
            fila.adicionar(elemento);
        }

        fila.mostrarTodos();

        Scanner sc = new Scanner(System.in);
        String opcao = "";

        do {
            System.out.println("\nDigite a opcao. . . ");
            System.out.println("1 Adicionar um novo cliente a fila");
            System.out.println("2 remover o proximo cliente da fila");
            System.out.println("3 Exibir todos os clientes na fila");
            System.out.println("4 Sair");
            System.out.print("Digite a opcao. . . ");
            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o ID do cliente. . .");
                    String id = sc.nextLine();
                    System.out.print("Digite o nome do cliente. . .");
                    String nome = sc.nextLine();
                    System.out.print("Digite a descrição. . .");
                    String descricao = sc.nextLine();

                    Elemento novoElemento = new Elemento(id, nome, descricao);
                    fila.adicionar(novoElemento);
                    break;

                case "2":
                    try {
                        fila.remove();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    fila.mostrarTodos();
                    break;

                case "4":
                    System.out.println("Encerrando o expediente");
                    break;

                default:
                    System.out.println("Opcao invalida");
            }

        } while (!opcao.equals("4"));

        sc.close();
    }
}