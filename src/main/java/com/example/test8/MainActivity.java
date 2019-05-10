package com.example.test8;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText genreView, titleView, contentView;
Button btn1,saveBtn;
int id;
int index;
AlertDialog Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genreView = findViewById(R.id.janre);
        titleView = findViewById(R.id.title);
        contentView = findViewById(R.id.content);
        btn1 = findViewById(R.id.btn1);
        saveBtn = findViewById(R.id.save_btn);

        TabHost host = findViewById(R.id.host);
        host.setup();
        TabHost.TabSpec spec;
        spec = host.newTabSpec("Tab1");
        spec.setIndicator("입력");
        spec.setContent(R.id.tab1);
        host.addTab(spec);

        spec = host.newTabSpec("Tab2");
        spec.setIndicator("조회");
        spec.setContent(R.id.tab2);
        host.addTab(spec);

    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            //알림대화상자 장르 선택 8,5
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("장르 선택");
            builder.setSingleChoiceItems(R.array.janre, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    index = which;
                }
            });
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String[] datas = getResources().getStringArray(R.array.janre);
                    String s = datas[index];

                    genreView.setText(s);
                }
            });
            builder.setNegativeButton("취소",null);


        }else if(v==saveBtn){
            //db생성 또는 열거 레코드 삽입
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();


            String genre = genreView.getText().toString();
            String title = titleView.getText().toString();
            String content = contentView.getText().toString();

            String insertSQL  = "insert into student values("+genre+"','"+title+"','"+content+")";

            db.execSQL(insertSQL);

            try{
                db.execSQL(insertSQL);
                Toast.makeText(this,"레코트 1개 추가 성공",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this,"record failed check recordlist",Toast.LENGTH_SHORT).show();

                Log.e("insert ===============", insertSQL);
            }


            db.close();
        }
    }
}
