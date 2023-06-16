import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itemCarrinhoList;

    public Carrinho(){
        this.limparCarrinho();
    }

    public void adicionar(final ItemCarrinho item){
        this.itemCarrinhoList.add(item);
    }

    public void remover(final ItemCarrinho item){
        this.itemCarrinhoList.remove(item);
    }

    public void limparCarrinho(){
        this.itemCarrinhoList = new ArrayList<>();
    }


    public List<ItemCarrinho> getItemCarrinhoList() {
        return itemCarrinhoList;
    }

    public BigDecimal getValorTotalCompra(){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ItemCarrinho item: this.itemCarrinhoList){
            valorTotal = valorTotal.add(item.getValorTotal());
        }
        return valorTotal;
    }

}
