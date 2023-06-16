import javax.swing.*;

public class Menu {

    private Loja loja = new Loja();

    public void exibirMenu() {
    int opcao = 0;

        do{
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "SISTEMA DE VENDAS"
                    + "\n\nDigite a opção desejada:"
                    + "\n1 - Cadastrar Produto"
                    + "\n2 - Listar Produtos Disponíveis"
                    + "\n3 - Consultar Produto por Código"
                    + "\n4 - Visualizar Carrinho"
                    + "\n5 - Adicionar Itens no Carrinho"
                    + "\n6 - Remover Itens do Carrinho"
                    + "\n7 - Processar Venda"
                    + "\n8 - Sobre"
                    + "\n9 - Sair",
                    "Sistema de Vendas",
                    JOptionPane.INFORMATION_MESSAGE));
        switch (opcao) {
            case 1:
                loja.cadastrarProduto();
                break;
            case 2:
                loja.listarProdutos();
                break;
            case 3:
                loja.exibirProduto();
                break;
            case 4:
                loja.listarCarrinho();
                break;
            case 5:
                loja.adicionarProduto();
                break;
            case 6:
                loja.removerProduto();
                break;
            case 7:
                loja.processarVenda();
                break;
            case 8:
                loja.about();
                break;
            case 9:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção Inválida!");
                break;
        }
        } while (opcao != 9);

    }
}
