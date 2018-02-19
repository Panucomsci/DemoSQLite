package com.demosql.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demosql.demosqlite.MyApplications;
import com.demosql.demosqlite.R;

public class MainActivity extends AppCompatActivity {

    private EditText edtFName, edtLName, edtOld;
    private Button btnSubmit;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        edtFName = findViewById(R.id.main_edt_fname);
        edtLName = findViewById(R.id.main_edt_lname);
        edtOld = findViewById(R.id.main_edt_old);
        btnSubmit = findViewById(R.id.main_btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = edtFName.getText().toString();
                String lname = edtLName.getText().toString();
                String old = edtOld.getText().toString();
                if(fName != null && lname != null){
                    long result = MyApplications.getWritableDatabase().InsertNewMember(fName,lname,old);
                    Toast.makeText(mContext,fName+"Success", Toast.LENGTH_LONG).show();
                    edtFName.setText("");
                    edtLName.setText("");
                    edtOld.setText("");
                }
            }
        });
    }
}
