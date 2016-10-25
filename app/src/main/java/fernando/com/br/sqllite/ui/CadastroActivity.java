package fernando.com.br.sqllite.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fernando.com.br.sqllite.R;
import fernando.com.br.sqllite.datacontroller.DbController;

public class CadastroActivity extends AppCompatActivity {

    private String email_usuario,senha_usuario,senha2;
    private DbController db;
    int erroEmail=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        db = new DbController((getBaseContext()));

        final EditText edtEmailCadastro = (EditText)findViewById(R.id.edtEmailCadastro);
        final EditText edtSenhaCadastro = (EditText)findViewById(R.id.edtSenhaCadastro);
        final EditText edtSenhaCadastro2 = (EditText)findViewById(R.id.edtSenhaCadastro2);
        Button bntCadastrar = (Button) findViewById(R.id.bntCadastrar);

        bntCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int error = 0;
                email_usuario = edtEmailCadastro.getText().toString().trim(); //trim -> tira todos os espaços do campo
                senha_usuario = edtSenhaCadastro.getText().toString().trim();
                senha2 = edtSenhaCadastro2.getText().toString().trim();

                if(email_usuario.equals("")){
                    edtEmailCadastro.setError("Este campo não pode estar vazio!");
                    edtEmailCadastro.requestFocus();
                    error = 1;
                }else if (senha_usuario.equals("")){
                    edtSenhaCadastro.setError("Este campo não pode esta vazio");
                    edtSenhaCadastro.requestFocus();
                    error = 1;
                }else if (senha2.equals("")) {
                    edtSenhaCadastro2.setError("Este campo não pode esta vazio");
                    edtSenhaCadastro2.requestFocus();
                    error = 1;
                }else if (!senha2.equals(senha_usuario)) {
                    edtSenhaCadastro2.setError("As senhas devem ser iguais cara!");
                    edtSenhaCadastro2.requestFocus();
                    edtSenhaCadastro2.setText("");
                    error = 1;
                    return;
                }

             //   error = verificaEmail(email_usuario); //essa porra ta retornando sempre 1
                if(erroEmail==1){
                    edtEmailCadastro.setError("Esse e-mail já esta sendo utilizado!");
                    edtEmailCadastro.requestFocus();
                }

                if(error == 0){
                    db.insertResgistro(email_usuario,senha_usuario);
                    Toast toast = Toast.makeText(CadastroActivity.this, "Usuarios adicionado com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }

            }
        });


    }

    public int verificaEmail(String email){
        boolean resultado = db.checaEmail(email);
        if(resultado == true){
            erroEmail = 1;
            return 1;
        }else {
            return 0;
        }
    }


}
