package com.bluezhang.sqliteopenhelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.bluezhang.sqliteopenhelper.db.DatabaseHelper;
import com.bluezhang.sqliteopenhelper.entity.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.insertIntoDB(new Contact(1,"blueZhang","18330236280"));
        db.insertIntoDB(new Contact(2, "blueZhang", "18330236280"));
        List<Contact> all = db.getAllContacts();
        Toast.makeText(getApplicationContext(),all.toString(),Toast.LENGTH_LONG).show();

    }


}
