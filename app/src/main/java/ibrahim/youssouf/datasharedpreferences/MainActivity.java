package ibrahim.youssouf.datasharedpreferences;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random alea;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText textesaisi;
    TextView textelu;
    Button savebut, readbut;
    int nbGenere = 0;
    String texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alea = new Random(SystemClock.currentThreadTimeMillis());
        textesaisi = findViewById(R.id.saisie); textelu = findViewById(R.id.textelu);
        savebut = findViewById(R.id.savebut); readbut = findViewById(R.id.readbut);
        sp = getPreferences(MODE_PRIVATE); editor = sp.edit();
        savebut.setEnabled(false); readbut.setEnabled(false);
    }

    public void save(View v) {
        texte = textesaisi.getText().toString();
        editor.putInt(texte,nbGenere);
        editor.commit();
        readbut.setEnabled(true);
    }

    public void read(View v) {
        String data = new String();
        for(String key: sp.getAll().keySet()) {
            data = data+key+":"+sp.getInt(key,0)+"\n";
        }
        textelu.setText(data);
    }

    public void generation(View v) {
        if(!textesaisi.getText().toString().equals("")) {
            nbGenere = alea.nextInt(1000);
            savebut.setEnabled(true);
        } else {
            textelu.setText("entrez votre texte au préalable");
        }
    }
}
