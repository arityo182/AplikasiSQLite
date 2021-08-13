package com.arbud.aplikasisqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arbud.aplikasisqlite.helper.DBHelper;

public class AddEditActivity extends AppCompatActivity {
    EditText text_id, text_name, text_address;
    Button btn_submit, btn_cancel;
    DBHelper SQLite = new DBHelper(this);
    String id, name, address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        text_id = findViewById(R.id.text_id);
        text_name =findViewById(R.id.text_name);
        text_address = findViewById(R.id.txt_address);
        btn_cancel = findViewById(R.id.btn_cencel);
        btn_submit = findViewById(R.id.btn_submit);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            text_id.setText(id);
            text_name.setText(name);
            text_address.setText(address);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (text_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit",e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank() {
        text_name.requestFocus();
        text_id.setText(null);
        text_name.setText(null);
        text_address.setText(null);
    }

    // Menyimpan Data ke Database SQlite

    private void save() {
        if (String.valueOf(text_name.getText()).equals(null) || String.valueOf(text_name.getText()).equals(null) ||
                String.valueOf(text_address.getText()).equals(null) || String.valueOf(text_address.getText()).equals(null) ) {
            Toast.makeText(getApplicationContext(),"Please Input Name or Address",Toast.LENGTH_SHORT).show();
        } else {
            SQLite.inssert(text_name.getText().toString().trim(), text_address.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(text_name.getText()).equals(null) || String.valueOf(text_name.getText()).equals(null) ||
                String.valueOf(text_address.getText()).equals(null) || String.valueOf(text_address.getText()).equals(null)) {
            Toast.makeText(getApplicationContext(),"Please Input Name or Address",Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(text_id.getText().toString().trim()), text_name.getText().toString()
            ,text_address.getText().toString().trim());
            blank();
            finish();
        }
    }

}
