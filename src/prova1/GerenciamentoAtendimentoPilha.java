package prova1;
import java.util.Scanner;
/**
 *
 * @author: RAFMo
 */

class Elemento {
    String id;
    String dataHora;
    String descricao;
    
    
    public Elemento(String id, String descricao, String dataHora) {
        this.id = id;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Descricao: " + descricao + ", Data e Hora: " + dataHora;
    }
}

class Pilha {
    private No topo;

    public Pilha() {
        this.topo = null;
    }

    public void adicionar(Elemento elemento) {
        try {
            No novoNode = new No(elemento);
            novoNode.proximo = topo; 
            topo = novoNode; 
            System.out.println("Solicitacao adicionada: " + elemento);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar solicitacao: " + e.getMessage());
        }
    }

    public Elemento remove() throws Exception {
        if (this.pilhaVazia()) {
            throw new Exception("A pilha esta vazia!!");
        }
        Elemento elementoRemovido = topo.elemento;
        topo = topo.proximo;
        System.out.println("Solicitacao removida: " + elementoRemovido);
        return elementoRemovido;
    }

    public void mostrarTodos() {
        try {
            if (topo == null) {
                System.out.println("A pilha esta vazia.");
                return;
            }
            System.out.println("Solicitacoes no historico:");
            No atual = topo;
            while (atual != null) {
                System.out.println(atual.elemento);
                atual = atual.proximo;
            }
        } catch (Exception e) {
            System.out.println("Erro ao mostrar solicitacoes: " + e.getMessage());
        }
    }

    public boolean pilhaVazia() {
        return topo == null;
    }
}

class Historico {
    Elemento[] historico = new Elemento[] {
        new Elemento("CLI001", "Duvida sobre produto", "2024-09-01 10:30"),
        new Elemento("CLI002", "Reclamacao de servico", "2024-09-01 11:00"),
        new Elemento("CLI003", "Solicitacao de reembolso", "2024-09-01 11:30"),
        new Elemento("CLI004", "Informacoes de entrega", "2024-09-01 12:00"),
        new Elemento("CLI005", "Agendamento de visita", "2024-09-01 12:30"),
        new Elemento("CLI006", "Alteracao de pedido", "2024-09-01 13:00"),
        new Elemento("CLI007", "Cancelamento de contrato", "2024-09-01 13:30"),
        new Elemento("CLI008", "Renovacao de assinatura", "2024-09-01 14:00"),
        new Elemento("CLI009", "Suporte para instalacao", "2024-09-01 14:30"),
        new Elemento("CLI010", "Pedido de orcamento", "2024-09-01 15:00")
    };
}

public class GerenciamentoAtendimentoPilha {
    public static void main(String[] args) {
        Historico historico = new Historico();
        Pilha pilhaHistorico = new Pilha();

        for (Elemento e : historico.historico) {
            pilhaHistorico.adicionar(e);
        }

        pilhaHistorico.mostrarTodos();

        Scanner sc = new Scanner(System.in);
        String opcao = "";

        do {
            System.out.println("\nDigite a opcao. . . ");
            System.out.println("1 Adicionar uma nova solicitacao a pilha");
            System.out.println("2 Remover a solicitacao do topo da pilha");
            System.out.println("3 Exibir todas as solicitacoes na pilha");
            System.out.println("4 Sair");
            System.out.print("Digite a opcao. . . ");
            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o ID da solicitacao. . . ");
                    String id = sc.nextLine();
                    System.out.print("Digite a descrição da solicitação. . . ");
                    String descricao = sc.nextLine();
                    System.out.print("Digite a data e hora da solicitação. . . ");
                    String dataHora = sc.nextLine();

                    Elemento novoElemento = new Elemento(id, descricao, dataHora);
                    pilhaHistorico.adicionar(novoElemento);
                    break;

                case "2":
                    try {
                        pilhaHistorico.remove();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    pilhaHistorico.mostrarTodos();
                    break;

                case "4":
                    System.out.println("Encerrado o expediente");
                    break;

                default:
                    System.out.println("Opcao invalda");
            }

        } while (!opcao.equals("4"));

        sc.close();
    }
}