package ads.pdm.avaliacaoddm0912;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Integrante: Herick Victor Rodrigues | SC301018X

    private Button btnAdicionar;
    private ListView listaProdutos;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private AdapterProdutos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listaProdutos = findViewById(R.id.listaProdutos);
        btnAdicionar.setOnClickListener(new btnListener());
        adapter = new AdapterProdutos(this, produtos);
        listaProdutos.setAdapter(adapter);
        listaListener listaListener = new listaListener();
        listaProdutos.setOnItemClickListener(listaListener);
        listaProdutos.setOnItemLongClickListener(listaListener);
    }

    private class btnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ProdutoActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String nome = intent.getStringExtra("nome");
                String marca = intent.getStringExtra("marca");
                String quantidade = intent.getStringExtra("quantidade");
                Produto produto = new Produto(nome, marca, quantidade);
                produtos.add(produto);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class listaListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Produto produto = produtos.get(i);
            if (produto.getComprado().equals("")) {
                produto.setComprado("* COMPRADO *");
            } else {
                produto.setComprado("");
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            produtos.remove(i);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Produto apagado com sucesso!", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}