package ads.pdm.avaliacaoddm0912;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import java.util.ArrayList;

public class AdapterProdutos extends CursorAdapter {
    private Context context;

    public AdapterProdutos(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(context);
        View item_lista = li.inflate(R.layout.item_lista, parent, false);
        return item_lista;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblNome = view.findViewById(R.id.lblNome);
        TextView lblMarca = view.findViewById(R.id.lblMarca);
        TextView lblQuantidade = view.findViewById(R.id.lblQuantidade);
        TextView lblComprado = view.findViewById(R.id.lblComprado);

        String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        String marca = cursor.getString(cursor.getColumnIndexOrThrow("marca"));
        String quantidade = cursor.getString(cursor.getColumnIndexOrThrow("quantidade"));
        String comprado = cursor.getString(cursor.getColumnIndexOrThrow("comprado"));

        lblNome.setText(nome);
        lblMarca.setText(marca);
        lblQuantidade.setText(quantidade);
        lblComprado.setText(comprado);
    }
}
