package ads.pdm.avaliacaoddm0912;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    AdapterProdutos adapter;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listaProdutos = findViewById(R.id.listaProdutos);
        btnAdicionar.setOnClickListener(new btnListener());
        listaListener listaListener = new listaListener();
        listaProdutos.setOnItemClickListener(listaListener);
        listaProdutos.setOnItemLongClickListener(listaListener);
        bd = openOrCreateDatabase("produtos", MODE_PRIVATE, null);
        String query = "create table if not exists produtos";
        query += "(nome varchar, marca varchar, quantidade varchar, comprado varchar)";
        bd.execSQL(query);

        Cursor cursor = bd.rawQuery("SELECT _rowid_ _id, nome, marca, quantidade, comprado from produtos", null);
        adapter = new AdapterProdutos(this, cursor);
        listaProdutos.setAdapter(adapter);
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
                String query = "insert into produtos(nome, marca, quantidade, comprado) values('";
                query += nome;
                query += "', '";
                query += marca;
                query += "', '";
                query += quantidade;
                query += "', '";
                query += "')";
                bd.execSQL(query);

                Cursor cursor = bd.rawQuery("select _rowid_ _id, nome, marca, quantidade, comprado from produtos", null);
                adapter.changeCursor(cursor);
            }
        }
    }

    private class listaListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapter.getItem(i);
            String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String comprado = cursor.getString(cursor.getColumnIndexOrThrow("comprado"));
            String query;
            if (comprado.equals("")) {
                query = "update produtos set comprado = " + "'*COMPRADO*'" + " where _rowid_ = " + id;
            } else {
                query = "update produtos set comprado = " + "''" + " where _rowid_ = " + id;
            }

            bd.execSQL(query);
            cursor = bd.rawQuery("select _rowid_ _id, nome, marca, quantidade, comprado from produtos", null);
            adapter.changeCursor(cursor);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapter.getItem(i);
            String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String query = "delete from produtos where _rowid_ = " + id;
            bd.execSQL(query);
            cursor = bd.rawQuery("select _rowid_ _id, nome, marca, quantidade, comprado from produtos", null);
            adapter.changeCursor(cursor);
            Toast.makeText(MainActivity.this, "Produto apagado com sucesso!", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}