import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Loja {

    private final static String PRODUTO_FISICO = "F";
    private final static String PRODUTO_DIGITAL = "D";

    private List<Produto> produtosDisponiveis;
    private Carrinho carrinho;

    public Loja(){
        this.produtosDisponiveis = new ArrayList<>();
        this.carrinho = new Carrinho();
    }

    public void cadastrarProduto(){
        Produto produto = null;
        String tipo = JOptionPane.showInputDialog(null,
               "Informe o tipo do produto: Poduto físico (F) ou digital (D) ?",
                "Cadastro de Produtos",
                JOptionPane.QUESTION_MESSAGE);

        if(PRODUTO_FISICO.equalsIgnoreCase(tipo)){
            produto = this.cadastrarProdutoFisico();
        } else
            if(PRODUTO_DIGITAL.equalsIgnoreCase(tipo)){
                produto = this.cadastrarProdutoDigital();
            } else {
                JOptionPane.showMessageDialog( null,
                        "Tipo de produto inexistente",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

            if(Objects.nonNull(produto)){
                this.produtosDisponiveis.add(produto);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
    }

    public void exibirProduto() {
        Long codigo = Long.parseLong(JOptionPane.showInputDialog("Digite o código do produto: "));
        Produto produto;
        try {
            produto = this.buscarProduto(codigo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Produto não encontrado!",
                    "Erro.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, produto.exibirDetalhesProtuto(),
                "Produto: " + produto.getCodigo(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarProdutos(){
        String mensagem = "PRODUTOS CADASTRADOS:";

        if(this.produtosDisponiveis.isEmpty()){
            mensagem = mensagem.concat("\n\nAinda não existem produtos cadastrados.");
        } else{
            for(Produto produto: this.produtosDisponiveis){
                mensagem = mensagem.concat("\n\n" + produto.exibirResumoProduto()
                        + "\nQuantidade em Estoque: " + produto.getEstoque());
            }
        }

        JOptionPane.showMessageDialog(null, mensagem,
                "Produtos Cadastrados",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarCarrinho(){
        String mensagem = "CARRINHO:";

        if(this.carrinho.getItemCarrinhoList().isEmpty()){
            mensagem = mensagem.concat("\n\nCarrinho vazio.");
        } else{
            for(int i=0; i<this.carrinho.getItemCarrinhoList().size(); i++){
                ItemCarrinho item = carrinho.getItemCarrinhoList().get(i);
                mensagem = mensagem.concat("\n\nItem: " + (i + 1) + " - "
                        + item.getProduto().exibirResumoProduto()
                        + "\nQuantidade: " + item.getQuantidade()
                + " Preço: " + item.getProduto().getPreco()
                + " Total: " + item.getValorTotal());
            }
            mensagem = mensagem.concat("\n\nTotal dos Produtos: " + carrinho.getValorTotalCompra());
        }

        JOptionPane.showMessageDialog(null, mensagem,
                "Carrinho",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void adicionarProduto() {
        Long codigo = Long.parseLong(JOptionPane.showInputDialog(null,
                "Digite o código do Produto: ",
                "Adicionar Produto",
                JOptionPane.INFORMATION_MESSAGE));

        Produto produto;
        try {
            produto = this.buscarProduto(codigo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Produto não encontrado!",
                    "Erro.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(produto.getEstoque().equals(BigInteger.ZERO)){
            JOptionPane.showMessageDialog(null,
                    "Produto indisponível no estoque.",
                    "Estoque Zerado.",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            BigInteger quantidade = new BigInteger(JOptionPane.showInputDialog(null,
                    "Digite a quantidade desejada: ",
                    "Adicionar produto.",
            JOptionPane.INFORMATION_MESSAGE));
            if(quantidade.compareTo(produto.getEstoque()) > 0){
                JOptionPane.showMessageDialog(null,
                        "Estoque Insuficiente.",
                        "Estoque Insuficiente.",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                produto.setEstoque(produto.getEstoque().subtract(quantidade));
                carrinho.adicionar(new ItemCarrinho(produto, quantidade));
                JOptionPane.showMessageDialog(null,
                        "Produto adicionado com sucesso!",
                        "Adicionar Produto",
                         JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    public void removerProduto() {
        int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Digite o número do item a ser removido: ",
                "Remover Item do Carrinho",
                JOptionPane.INFORMATION_MESSAGE));

        ItemCarrinho itemCarrinho = this.carrinho.getItemCarrinhoList().get(item - 1);
        Long codigoProduto = itemCarrinho.getProduto().getCodigo();
        BigInteger quantidade = itemCarrinho.getQuantidade();

        Produto produto;
        try {
            produto = this.buscarProduto(codigoProduto);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Produto não encontrado!",
                    "Erro.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        produto.setEstoque(produto.getEstoque().add(quantidade));
        carrinho.remover(itemCarrinho);

        JOptionPane.showMessageDialog(null,
                "Item removido com sucesso!",
                "Remover Item do Carrinho",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void processarVenda(){
        JOptionPane.showMessageDialog(null,
                "Venda processada com sucesso!" +
                "\n Total R$ " + this.carrinho.getValorTotalCompra(),
                "Processar Venda",
                JOptionPane.INFORMATION_MESSAGE);
        this.carrinho.limparCarrinho();
    }

    public void about(){
        JOptionPane.showMessageDialog(null,
                "Sistema de Vendas"
        +"\n\nVersão 1.0.0"
        +"\n\nDesenvolvido por:"
        +"\n- Radmila Aparecida Lopes"
        +"\n- Tifani Mendonça",
                "SISTEMA DE VENDAS",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private Produto buscarProduto(final Long codigo) throws ProdutoNaoEncontradoException {
        List<Produto> collect = this.produtosDisponiveis.stream().filter(produto -> codigo.equals(produto.getCodigo())).collect(Collectors.toList());
        if(collect.size() == 0){
            throw new ProdutoNaoEncontradoException();
        }
        return collect.get(0);
    }

    private ProdutoFisico cadastrarProdutoFisico(){
        final ProdutoFisico produtoFisico = new ProdutoFisico();

       produtoFisico.setCodigo(Long.parseLong(JOptionPane.showInputDialog("Informe o código: ")));
       produtoFisico.setNome(JOptionPane.showInputDialog("Informe o nome: "));
       produtoFisico.setDescricao(JOptionPane.showInputDialog("Informe a descrição: "));
       produtoFisico.setPreco(new BigDecimal(JOptionPane.showInputDialog("Informe o preço: ")));
       produtoFisico.setEstoque(new BigInteger(JOptionPane.showInputDialog("Informe a quantidade em estoque: ")));

       produtoFisico.setDimensao(JOptionPane.showInputDialog("Informe as dimensões do produto (LxAxP): "));
       produtoFisico.setPeso(new BigDecimal(JOptionPane.showInputDialog("Informe o peso do produto: ")));

       return produtoFisico;
    }

    private ProdutoDigital cadastrarProdutoDigital(){
        final ProdutoDigital produtoDigital = new ProdutoDigital();

        produtoDigital.setCodigo(Long.parseLong(JOptionPane.showInputDialog("Informe o código: ")));
        produtoDigital.setNome(JOptionPane.showInputDialog("Informe o nome: "));
        produtoDigital.setDescricao(JOptionPane.showInputDialog("Informe a descrição: "));
        produtoDigital.setPreco(new BigDecimal(JOptionPane.showInputDialog("Informe o preço: ")));
        produtoDigital.setEstoque(new BigInteger(JOptionPane.showInputDialog("Informe a quantidade em estoque: ")));

        produtoDigital.setFormato(JOptionPane.showInputDialog("Informe o formato do arquivo: "));
        produtoDigital.setTamanho(new BigInteger(JOptionPane.showInputDialog("Informe o tamanho do arquivo (MB): ")));

        return produtoDigital;
    }

}
