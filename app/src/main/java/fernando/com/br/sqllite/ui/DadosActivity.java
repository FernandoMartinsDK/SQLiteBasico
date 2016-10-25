package fernando.com.br.sqllite.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fernando.com.br.sqllite.R;
import fernando.com.br.sqllite.datacontroller.DbController;

public class DadosActivity extends AppCompatActivity {

    private EditText campoEmail,campoSenha1,campoSenha2;
    private Button bntCancelar,bntSalvar;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        campoEmail = (EditText)findViewById(R.id.campoEmail);
        campoSenha1= (EditText)findViewById(R.id.campoSenha1);
        campoSenha2= (EditText)findViewById(R.id.campoSenha2);
        bntCancelar= (Button)findViewById(R.id.bntCancelar);
        bntSalvar = (Button)findViewById(R.id.bntSalvar);

        final PrincipalActivity pa = new PrincipalActivity();
        name = pa.op;
        campoEmail.setText(name);

        bntSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        bntCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DadosActivity.this,PrincipalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void salvar(){
        String novoNome = campoEmail.getText().toString().trim();
        String novaSenha1 = campoSenha1.getText().toString().trim();
        String novaSenha2 = campoSenha2.getText().toString().trim();

        if(novaSenha1.equals(novaSenha2)){
            DbController db = new DbController(getBaseContext());
            db.editarRegistro(name,novoNome,novaSenha1);
            Toast.makeText(DadosActivity.this, "Dados modificados com sucesso!", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(DadosActivity.this,PrincipalActivity.class);
            startActivity(it);
            finish();
        }else{
            Toast.makeText(DadosActivity.this, "Senhas não conferem!", Toast.LENGTH_SHORT).show();
        }
    }



}
