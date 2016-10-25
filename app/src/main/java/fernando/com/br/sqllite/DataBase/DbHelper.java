package fernando.com.br.sqllite.DataBase;

//Responsavel por criar o banco e a tabela SÓ
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    //nome do banco de dados
    private static final String dataBase_nome = "teste.db";
    //versão do banco de dados
    private static final int dataBase_versao = 1;
    //Criando Tabelas
    private static final String usuarios = "CREATE TABLE IF NOT EXISTS  usuarios ("
            + "id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + "email_usuario varchar(2) NOT NULL, "
            + "senha_usuario varchar(2) NOT NULL);";


    public DbHelper(Context context) {
        super(context, dataBase_nome,null,dataBase_versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usuarios);
        onUpgrade(db,1,dataBase_versao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion >= newVersion)return;
        switch (oldVersion){
            case 0:

            case 1:
                //upgrade
                break;

            default:
                throw new IllegalStateException("No upgrade specific for");//arrumar essa linha

        }
    }



}

