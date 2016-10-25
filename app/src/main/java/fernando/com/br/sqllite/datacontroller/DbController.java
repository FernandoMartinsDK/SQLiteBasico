package fernando.com.br.sqllite.datacontroller;

//Faz os insert e select ou seja os comandos

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import fernando.com.br.sqllite.DataBase.DbHelper;
import fernando.com.br.sqllite.dataModel.ItemLogin;
import fernando.com.br.sqllite.ui.PrincipalActivity;

public class DbController {


    private static DbHelper dbHelper;
    private  static SQLiteDatabase db;
    private static final String leitura = "leitura";
    private static String escrita = "esctrita";

    public DbController(Context context){
        dbHelper = new DbHelper(context);
    }

    //abre a conexão
    private static SQLiteDatabase openConn(String operacao){
        if(db == null || !db.isOpen())
            if(operacao.equals(escrita))
                db = dbHelper.getWritableDatabase();
            else
                db = dbHelper.getReadableDatabase();
            return  db;
        }

    //fechar a conexão
    private static void  closeConn(){
        if(dbHelper != null){
            dbHelper.close();
        }
        if(db !=null){
            db.close();
        }
    }


    //Verifica se o email existe
    public boolean checaEmail(String email){
        boolean emailExiste=false;
        String query = "select email_usuario from usuarios where email_usuario = '" +email +"'";

        Cursor cursor = openConn(leitura).rawQuery(query,null);

        if(cursor != null){
            while(cursor.moveToNext()){ //percorre toda coluna q foi definida na query
                emailExiste = true;
            }
            cursor.close();
        }
        closeConn();
        return emailExiste;
    }


    //Insert
    public String insertResgistro(String email,String senha){

        long resultado;

        openConn(escrita);
        ContentValues valores = new ContentValues();
        valores.put("email_usuario",email); //nome da coluna , conteudo a ser inserido
        valores.put("senha_usuario",senha);
        resultado = db.insertWithOnConflict("usuarios",null,valores,SQLiteDatabase.CONFLICT_REPLACE);
        closeConn();

        if(resultado == -1){
            return "Erro ao inserir o registro";
        }else{
            return "Registro inserido com sucesso";
        }
    }


    //validação do Login
    public boolean validacaoLogin(String email, String senha){
        boolean resultado = false;

        openConn(leitura);
        String query = "select email_usuario,senha_usuario from usuarios where email_usuario = '" +email +"'"
                + " and " + " senha_usuario = '"+senha+ "'" ;

        Cursor cursor = openConn(leitura).rawQuery(query,null);
        PrincipalActivity pa = new PrincipalActivity();
        ItemLogin  itenLogin = new ItemLogin();
        if(cursor != null){
            while(cursor.moveToNext()){ //percorre toda coluna q foi definida na query
                pa.nome = cursor.getString(cursor.getColumnIndex("email_usuario"));
                resultado = true;
            }
            cursor.close();
        }
        closeConn();
        return resultado;
    }


    //Lista de cadastros
    public void emailCadastrados(){
        String mail;
        PrincipalActivity pa = new PrincipalActivity();

        openConn(leitura);
        String query = "select email_usuario from usuarios" ;
        Cursor cursor2 = openConn(leitura).rawQuery(query,null);

        if(cursor2!=null){
            while ( cursor2.moveToNext()){
                mail = cursor2.getString((cursor2.getColumnIndex("email_usuario")));
                pa.listaEmails.add(mail);
            }
            cursor2.close();
        }
        closeConn();
    }

    //Deleta Registro do Banco de Dados
    public void apagaRegistro (String registro){
        String where = "email_usuario = '"+registro+"'";
        openConn(escrita);
        db.delete("usuarios",where,null);
        closeConn();
    }

    //Editar Registro
    public void editarRegistro(String nomeAtual, String novoNome, String novaSenha){
        String where = "email_usuario = '" + nomeAtual+"'";
        openConn(escrita);
        ContentValues valores = new ContentValues();
        valores.put("email_usuario", novoNome);
        valores.put("senha_usuario", novaSenha);
        db.update("usuarios",valores,where,null);
        closeConn();
    }

}
