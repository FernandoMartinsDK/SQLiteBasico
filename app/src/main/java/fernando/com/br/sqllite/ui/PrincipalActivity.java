package fernando.com.br.sqllite.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fernando.com.br.sqllite.R;
import fernando.com.br.sqllite.dataModel.ItemLogin;
import fernando.com.br.sqllite.datacontroller.DbController;

public class PrincipalActivity extends AppCompatActivity {

    public static int idNumber;
    public static String nome,numberId;
    public static ArrayList<String> listaEmails = new ArrayList<String>();
    private AlertDialog alerta;
    public static String op;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        DbController dbController = new DbController(getBaseContext());
        ListView lstEmail = (ListView)findViewById(R.id.lstEmail);
        TextView txtNome = (TextView)findViewById(R.id.txtNome);

        numberId = String.valueOf(idNumber);
        txtNome.setText("Bem vindo: "+nome);

        carregaLista(); //carrega e exibe a listView
        dbController.emailCadastrados(); //carrega e atualiza a lista

        lstEmail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                op = listaEmails.get(i).toString(); //passa o nome do item para a variavel "op"
                opcaoLista(view);//chama o alertDialog
            }
        });

    }


    public void carregaLista(){
        ListView lstEmail = (ListView)findViewById(R.id.lstEmail);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaEmails);
        lstEmail.setAdapter(adapter);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaEmails);
        lstEmail.setAdapter(itemsAdapter);
        listaEmails.clear(); //se não limpar toda vez q chama a tela a lista vai dubplicar o seu conteudo atual! e não queremos isso!!
    }


    public void opcaoLista(final View v){
        final DbController db = new DbController(getBaseContext());
        //Cria o AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("O que deseja fazer?");
        //define a mensagem
        builder.setMessage("Alterações aqui realizadas não poderão ser desfeitas!!");
        //Opção Apagar
        builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                db.apagaRegistro(op);
                carregaLista();
                db.emailCadastrados();
                Toast.makeText(PrincipalActivity.this, "Registro apagado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        //opção Editar
        builder.setNegativeButton("Editar ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(PrincipalActivity.this,DadosActivity.class);
                startActivity(it);
                finish();
                //Toast.makeText(MenuDadosBasicos.this, "Você clicou no botão Cancelar", Toast.LENGTH_SHORT).show();
            }
        });
        //Opção Cancelar
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MenuDadosBasicos.this, "Você clicou no botão Cancelar", Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();//cria o AlertDialog
        alerta.show();//Exibe o AlertDialog
    }


}
