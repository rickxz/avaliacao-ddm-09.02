package ads.pdm.avaliacaoddm0912;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProdutos extends ArrayAdapter<Produto> {
    private Context context;
    private ArrayList<Produto> produtos;

    public AdapterProdutos(Context context, ArrayList<Produto> produtos) {
        super(context, R.layout.item_lista, produtos);
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_lista, parent, false);

        TextView lblNome = itemView.findViewById(R.id.lblNome);
        TextView lblMarca = itemView.findViewById(R.id.lblMarca);
        TextView lblQuantidade = itemView.findViewById(R.id.lblQuantidade);
        TextView lblComprado = itemView.findViewById(R.id.lblComprado);

        lblNome.setText(produtos.get(position).getNome());
        lblMarca.setText(produtos.get(position).getMarca());
        lblQuantidade.setText(produtos.get(position).getQuantidade());
        lblComprado.setText(produtos.get(position).getComprado());
        return itemView;

    }
}
