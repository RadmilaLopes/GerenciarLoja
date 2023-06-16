import java.math.BigDecimal;
import java.math.BigInteger;

public class ItemCarrinho {

    private Produto produto;
    private BigInteger quantidade;

    public ItemCarrinho(Produto produto, BigInteger quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal(){
        return this.getProduto().getPreco().multiply(new BigDecimal(getQuantidade().toString()));
    }
}
