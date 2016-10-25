package fernando.com.br.sqllite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fernando.com.br.sqllite.R;
import fernando.com.br.sqllite.datacontroller.DbController;

public class LoginActivity extends AppCompatActivity {

    private DbController db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        db = new DbController((getBaseContext()));

        final EditText edtEmailLogin = (EditText)findViewById(R.id.edtEmailLogin);
        final EditText edtSenhaLogin = (EditText)findViewById(R.id.edtSenhaLogin);
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);



        btnEntrar.setOnClickListener(new View.OnClickListener() {
            int erroLogin=0;
            @Override
            public void onClick(View view) {
                boolean loginCorreto = false;
                String email,senha;

                //verifica se os campos estão nulos
                if(edtEmailLogin.equals("")){
                    edtEmailLogin.setError("Este campo não pode estar vazio!");
                    edtEmailLogin.requestFocus();
                    erroLogin = 1;
                }else if (edtSenhaLogin.equals("")){
                    edtSenhaLogin.setError("Este campo não pode esta vazio");
                    edtSenhaLogin.requestFocus();
                    erroLogin=1;
                }

                email = edtEmailLogin.getText().toString().trim();
                senha = edtSenhaLogin.getText().toString().trim();

                if(erroLogin == 0){
                    loginCorreto = login(email,senha);
                }

                if(loginCorreto == false && erroLogin == 0){
                    Toast.makeText(getApplicationContext(), "E-mail ou senha errado !!", Toast.LENGTH_SHORT).show();
                }else if(loginCorreto == true && erroLogin == 0){
                    Intent it = new Intent(LoginActivity.this,PrincipalActivity.class);
                    startActivity(it);
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(it);
            }
        });
    }

    public boolean login(String email, String senha){

        boolean loginCorreto = db.validacaoLogin(email,senha);
        return loginCorreto;
    }



}
